package ru.lilitweb.springintegration.flow.stocksflow;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.integration.dsl.IntegrationFlow;
import ru.lilitweb.springintegration.conveter.StockQuoteDtoToStockQuoteConverter;
import ru.lilitweb.springintegration.domain.StockQuote;
import ru.lilitweb.springintegration.dto.StockQuoteDto;
import ru.lilitweb.springintegration.service.notification.Message;
import ru.lilitweb.springintegration.service.stockanalyzer.StockAnalyzeResult;

@Configuration
public class StockIntegrationFlow {
    @Bean
    public IntegrationFlow stocksInfoUpdateFlow() {
        Converter<StockQuoteDto, StockQuote> converter = new StockQuoteDtoToStockQuoteConverter();
        return flow -> flow.split()
                //берем информацию об акции из апи
                .handle("stockService", "getStockQuoteInfo")
                //конвернтируем в domain
                .handle(converter, "convert")
                //сохраняем
                .handle("stockQuoteRepository", "save")
                //анализируем насколько сильно ли изменилась стоимость
                .handle("stockAnalyzerService", "analyze")
                //фильтруем и берем только те где сильно изменилась стоимость
                .filter(StockAnalyzeResult::getIsImportantDelta)
                //формируем сообщение для нотификации
                .transform(StockAnalyzeResult.class,
                        res -> {
                            StockQuote stockQuote = res.getCurrent();
                            return new Message("Изменилась стоимость акции " + stockQuote.getStockCode(),
                                    String.format("Было: %f\nСтало: %f\nДелта: %f",
                                            res.getPrevious().getLatestPrice(),
                                            stockQuote.getLatestPrice(),
                                            res.getDeltaPrice()
                                    )
                            );
                        }
                )
                //отправляем нотификацию
                .publishSubscribeChannel(subscription ->
                        subscription
                                .subscribe(subflow -> subflow
                                        .handle("logNotificationService", "notify"))
                                .subscribe(subflow -> subflow
                                        .handle("emailNotificationService", "notify"))
                                .subscribe(subflow -> subflow
                                        .handle("telegramNotificationService", "notify"))
                );
    }
}
