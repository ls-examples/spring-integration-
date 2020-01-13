package ru.lilitweb.springintegration.jobs;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.lilitweb.springintegration.flow.stocksflow.StocksInfoUpdateMessageGateway;

import java.util.Collections;

@ExtendWith(MockitoExtension.class)
class StocksInfoUpdaterTest {
    @Mock
    StocksInfoUpdateMessageGateway stockUpdateGateway;

    @InjectMocks
    private StocksInfoUpdater stocksInfoUpdater = new StocksInfoUpdater(stockUpdateGateway);

    @Test
    void updateStocksInfo() {
        stocksInfoUpdater.updateStocksInfo();
        Mockito.verify(stockUpdateGateway, Mockito.times(1)).updateStocksInfo(Collections.singleton("amd"));
    }
}
