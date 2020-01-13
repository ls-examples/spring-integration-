package ru.lilitweb.springintegration.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StockQuoteDto {
    private float close;
    private float open;
    private float latestPrice;

    public StockQuoteDto(String symbol) {
        this.symbol = symbol;
    }

    public StockQuoteDto(String symbol, Float latestPrice) {
        this.symbol = symbol;
        this.latestPrice = latestPrice;
    }

    public StockQuoteDto() {}

    private String symbol;
    private Long latestUpdate;

    private Long openTime;
    private Long closeTime;
}
