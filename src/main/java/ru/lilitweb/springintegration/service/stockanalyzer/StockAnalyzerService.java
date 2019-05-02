package ru.lilitweb.springintegration.service.stockanalyzer;

import ru.lilitweb.springintegration.domain.StockQuote;

public interface StockAnalyzerService {
    StockAnalyzeResult analyze(StockQuote quote);
}
