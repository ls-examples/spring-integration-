package ru.lilitweb.springintegration.flow.stocksflow;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

import java.util.Collection;

@MessagingGateway
public interface StocksInfoUpdateMessageGateway {
    @Gateway(requestChannel = "stocksInfoUpdateFlow.input")
    void updateStocksInfo(Collection<String> stocks);
}
