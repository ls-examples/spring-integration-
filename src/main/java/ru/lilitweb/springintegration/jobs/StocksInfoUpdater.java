package ru.lilitweb.springintegration.jobs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.lilitweb.springintegration.flow.stocksflow.StocksInfoUpdateMessageGateway;

import java.util.Collections;

@Component
public class StocksInfoUpdater {
    private static final Logger log = LoggerFactory.getLogger(StocksInfoUpdater.class);
    private StocksInfoUpdateMessageGateway stockUpdater;

    @Autowired
    public StocksInfoUpdater(StocksInfoUpdateMessageGateway stockUpdateGateway) {
        this.stockUpdater = stockUpdateGateway;
    }

    @Scheduled(fixedDelay = 1800000) //раз в три минуты
    public void updateStocksInfo() {
        log.info("Start update stocks quote");
        stockUpdater.updateStocksInfo(Collections.singleton("amd"));
        log.info("Finish update stocks quote");
    }
}
