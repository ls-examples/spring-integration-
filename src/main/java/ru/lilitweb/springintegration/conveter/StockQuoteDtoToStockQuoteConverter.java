package ru.lilitweb.springintegration.conveter;

import org.springframework.core.convert.converter.Converter;
import ru.lilitweb.springintegration.domain.StockQuote;
import ru.lilitweb.springintegration.dto.StockQuoteDto;

import java.util.Date;

public class StockQuoteDtoToStockQuoteConverter implements Converter<StockQuoteDto, StockQuote> {
    @Override
    public StockQuote convert(StockQuoteDto source) {
        StockQuote sQuote = new StockQuote(
                source.getSymbol().toLowerCase(),
                source.getLatestPrice());
        sQuote.setClosePrice(source.getClose());
        sQuote.setOpenPrice(source.getOpen());
        sQuote.setLatestUpdate(formatDate(source.getLatestUpdate()));
        sQuote.setCloseTime(formatDate(source.getCloseTime()));
        sQuote.setOpenTime(formatDate(source.getOpenTime()));
        return sQuote;
    }

    private Date formatDate(Long timestamp) {
        if (timestamp == null) {
            return null;
        }
        return new Date(timestamp * 1000);
    }
}
