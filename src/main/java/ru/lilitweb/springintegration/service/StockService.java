package ru.lilitweb.springintegration.service;

import ru.lilitweb.springintegration.dto.StockQuoteDto;

public interface StockService {
    StockQuoteDto getStockQuoteInfo(String stockCode);
}
