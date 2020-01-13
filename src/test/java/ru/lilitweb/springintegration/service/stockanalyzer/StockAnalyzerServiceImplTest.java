package ru.lilitweb.springintegration.service.stockanalyzer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import ru.lilitweb.springintegration.domain.StockQuote;
import ru.lilitweb.springintegration.repostory.StockQuoteRepository;

import java.util.Collections;

import static org.junit.Assert.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Методы сервиса анализа акций должны ")
public class StockAnalyzerServiceImplTest {
    @Mock
    private StockQuoteRepository repository;

    @InjectMocks
    private StockAnalyzerService stockAnalyzeService = new StockAnalyzerServiceImpl(repository);

    @Test
    @DisplayName("возвращать корректный результат, с пометкой важности - isImportantDelta = true, " +
            " если цена увеличилась больче чем на 5 процентов. Текущий метод analyze")
    public void shouldReturnValidResultIfPriceIncreaseMoreThenFivePercent() {
        String code = "stockCode";
        Float previousPrice = 3f;
        Float newPrice = previousPrice * 1.06f;
        StockQuote quote = new StockQuote(code, newPrice);
        quote.setId("2");

        StockQuote previous = new StockQuote(code, previousPrice);
        previous.setId("1");

        StockAnalyzeResult expectedRes = new StockAnalyzeResult(newPrice - previousPrice, quote);
        expectedRes.setIsImportantDelta(true);
        expectedRes.setPrevious(previous);
        Mockito
                .when(repository.findByStockCodeAndIdNot(code, "2",
                        PageRequest.of(0, 1, Sort.by(Sort.Direction.DESC, "createdAt"))))
                .thenReturn(new PageImpl<StockQuote>(Collections.singletonList(previous)));

        StockAnalyzeResult res = stockAnalyzeService.analyze(quote);
        assertEquals(expectedRes.getIsImportantDelta(), res.getIsImportantDelta());
        assertEquals(expectedRes, res);
    }

    @Test
    @DisplayName("возвращать корректный результат, с пометкой важности - isImportantDelta = true, " +
            " если цена увеличилась больче чем на 5 процентов. Текущий метод analyze")
    public void shouldReturnValidResultIfPriceMoreThenFivePercent() {
        String code = "stockCode";
        Float previousPrice = 3f;
        Float newPrice = previousPrice * 1.06f;
        StockQuote quote = new StockQuote(code, newPrice);
        quote.setId("2");

        StockQuote previous = new StockQuote(code, previousPrice);
        previous.setId("1");

        StockAnalyzeResult expectedRes = new StockAnalyzeResult(newPrice - previousPrice, quote);
        expectedRes.setIsImportantDelta(true);
        expectedRes.setPrevious(previous);
        Mockito
                .when(repository.findByStockCodeAndIdNot(code, "2",
                        PageRequest.of(0, 1, Sort.by(Sort.Direction.DESC, "createdAt"))))
                .thenReturn(new PageImpl<StockQuote>(Collections.singletonList(previous)));

        StockAnalyzeResult res = stockAnalyzeService.analyze(quote);
        assertEquals(expectedRes.getIsImportantDelta(), res.getIsImportantDelta());
        assertEquals(expectedRes, res);
    }

    @Test
    @DisplayName("возвращать корректный результат, с пометкой важности - isImportantDelta = false, " +
            " если цена уменьшилась меньше чем на 5 процентов. Текущий метод analyze")
    public void shouldReturnValidResultIfPriceDecreaseLessThenFivePercent() {
        String code = "stockCode";
        Float previousPrice = 3f;
        Float newPrice = previousPrice * 0.96f;
        StockQuote quote = new StockQuote(code, newPrice);
        quote.setId("2");

        StockQuote previous = new StockQuote(code, previousPrice);
        previous.setId("1");

        StockAnalyzeResult expectedRes = new StockAnalyzeResult(previousPrice - newPrice, quote);
        expectedRes.setIsImportantDelta(false);
        expectedRes.setPrevious(previous);
        Mockito
                .when(repository.findByStockCodeAndIdNot(code, "2",
                        PageRequest.of(0, 1, Sort.by(Sort.Direction.DESC, "createdAt"))))
                .thenReturn(new PageImpl<StockQuote>(Collections.singletonList(previous)));

        StockAnalyzeResult res = stockAnalyzeService.analyze(quote);
        assertEquals(expectedRes.getIsImportantDelta(), res.getIsImportantDelta());
        assertEquals(expectedRes, res);
    }

    @Test
    @DisplayName("возвращать корректный результат, с пометкой важности - isImportantDelta = true, " +
            " если цена уменьшилась больше чем на 5 процентов. Текущий метод analyze")
    public void shouldReturnValidResultIfPriceDecreaseMoreThenFivePercent() {
        String code = "stockCode";
        Float previousPrice = 3f;
        Float newPrice = previousPrice * 0.94f;
        StockQuote quote = new StockQuote(code, newPrice);
        quote.setId("2");

        StockQuote previous = new StockQuote(code, previousPrice);
        previous.setId("1");

        StockAnalyzeResult expectedRes = new StockAnalyzeResult(previousPrice - newPrice, quote);
        expectedRes.setIsImportantDelta(true);
        expectedRes.setPrevious(previous);
        Mockito
                .when(repository.findByStockCodeAndIdNot(code, "2",
                        PageRequest.of(0, 1, Sort.by(Sort.Direction.DESC, "createdAt"))))
                .thenReturn(new PageImpl<StockQuote>(Collections.singletonList(previous)));

        StockAnalyzeResult res = stockAnalyzeService.analyze(quote);
        assertEquals(expectedRes.getIsImportantDelta(), res.getIsImportantDelta());
        assertEquals(expectedRes, res);
    }
}
