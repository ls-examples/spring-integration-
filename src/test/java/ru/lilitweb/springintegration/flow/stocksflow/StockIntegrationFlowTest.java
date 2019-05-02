package ru.lilitweb.springintegration.flow.stocksflow;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.lilitweb.springintegration.domain.StockQuote;
import ru.lilitweb.springintegration.dto.StockQuoteDto;
import ru.lilitweb.springintegration.repostory.StockQuoteRepository;
import ru.lilitweb.springintegration.service.StockService;
import ru.lilitweb.springintegration.service.notification.Message;
import ru.lilitweb.springintegration.service.notification.NotificationService;
import ru.lilitweb.springintegration.service.stockanalyzer.StockAnalyzeResult;
import ru.lilitweb.springintegration.service.stockanalyzer.StockAnalyzerService;

import java.util.Arrays;


@ExtendWith(SpringExtension.class)
@EnableIntegration
@IntegrationComponentScan
@ComponentScan
public class StockIntegrationFlowTest {

    @MockBean(name="stockQuoteRepository")
    StockQuoteRepository repository;

    @MockBean(name="stockAnalyzerService")
    StockAnalyzerService stockAnalyzerService;

    @MockBean(name="stockService")
    StockService stockService;

    @MockBean(name="emailNotificationService")
    NotificationService emailNotificationService;

    @MockBean(name="telegramNotificationService")
    NotificationService telegramNotificationService;

    @Autowired
    StocksInfoUpdateMessageGateway stocksInfoUpdateMessageGateway;

    @Test
    void stocksInfoUpdateFlow() throws Exception {
        String stockCode = "amd";
        StockQuote quote = new StockQuote(stockCode, 5f);
        StockQuote previous = new StockQuote(stockCode, 3f);
        Mockito
                .when(stockService.getStockQuoteInfo(stockCode))
                .thenReturn(new StockQuoteDto(stockCode, 5f));
        Mockito
                .when(repository.save(Mockito.any(StockQuote.class)))
                .thenReturn(quote);

        Mockito
                .when(stockAnalyzerService.analyze(Mockito.any(StockQuote.class)))
                .thenReturn(
                        new StockAnalyzeResult(
                                2f,
                                quote,
                                previous,
                                true
                        )
                );

        stocksInfoUpdateMessageGateway.updateStocksInfo(Arrays.asList(stockCode));

        Message mess = new Message("Изменилась стоимость акции " + stockCode,
               "Было: 3,000000\nСтало: 5,000000\nДелта: 2,000000"
        );
        Mockito.verify(stockService, Mockito.times(1))
                .getStockQuoteInfo(stockCode);
        Mockito.verify(repository, Mockito.times(1))
                .save(quote);
        Mockito.verify(stockAnalyzerService, Mockito.times(1))
                .analyze(quote);
        Mockito.verify(emailNotificationService, Mockito.times(1))
                .notify(mess);
        Mockito.verify(telegramNotificationService, Mockito.times(1))
                .notify(mess);
    }
}
