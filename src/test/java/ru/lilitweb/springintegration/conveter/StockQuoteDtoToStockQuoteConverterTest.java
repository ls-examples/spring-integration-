package ru.lilitweb.springintegration.conveter;

import org.junit.jupiter.api.Test;
import ru.lilitweb.springintegration.domain.StockQuote;
import ru.lilitweb.springintegration.dto.StockQuoteDto;

import static org.junit.jupiter.api.Assertions.*;


class StockQuoteDtoToStockQuoteConverterTest {

    @Test
    void convert() {
        StockQuoteDtoToStockQuoteConverter converter =  new StockQuoteDtoToStockQuoteConverter();
        StockQuote expectedQuote = new StockQuote("code", 1);

        StockQuoteDto source = new StockQuoteDto("code");
        source.setLatestPrice(1);

        StockQuote res = converter.convert(source);
        assertEquals(expectedQuote, res);
    }
}
