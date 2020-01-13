package ru.lilitweb.springintegration.domain;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.lang.NonNull;

import java.util.Date;

@Data
@Document(collection = "stocks")
public class StockQuote {
    @Id
    private String Id;

    private float closePrice;
    private float openPrice;

    @NonNull
    private String stockCode;

    @NonNull
    private float latestPrice;
    private Date latestUpdate;
    private Date openTime;
    private Date closeTime;

    @CreatedDate
    private Date createdAt;
}
