package ru.lilitweb.springintegration.service;

import org.springframework.web.client.RestOperations;
import ru.lilitweb.springintegration.dto.StockQuoteDto;


public class StockServiceImpl implements StockService {

    private RestOperations rest;

    private final String apiUrl = "https://api.iextrading.com/1.0";


    public StockServiceImpl(RestOperations rest) { ;
        this.rest = rest;
    }

    @Override
    public StockQuoteDto getStockQuoteInfo(String stockCode) {
        return rest.getForObject(String.format("%s/stock/%s/quote", apiUrl, stockCode), StockQuoteDto.class);
    }
}
