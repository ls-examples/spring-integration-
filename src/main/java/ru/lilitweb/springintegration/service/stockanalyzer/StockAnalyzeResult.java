package ru.lilitweb.springintegration.service.stockanalyzer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import ru.lilitweb.springintegration.domain.StockQuote;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class StockAnalyzeResult {
    @NonNull
    private Float deltaPrice;

    @NonNull
    private StockQuote current;
    private StockQuote previous;
    private Boolean isImportantDelta = false;

}
