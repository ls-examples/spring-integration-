package ru.lilitweb.springintegration.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;
import ru.lilitweb.springintegration.dto.StockQuoteDto;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Методы сервиса акций должны ")
class StockServiceImplTest {
    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private StockService stockService = new StockServiceImpl(restTemplate);

    @Test
    @DisplayName("возвращать объект из запроса, вызывая getForObject с нужными параметрами. Текущий метод: getStockQuoteInfo")
    public void shouldReturnStockQuoteDto() {
        StockQuoteDto dtoFromResponce = new StockQuoteDto("test");
        Mockito
                .when(restTemplate.getForObject("https://api.iextrading.com/1.0/stock/test/quote", StockQuoteDto.class))
                .thenReturn(dtoFromResponce);
        StockQuoteDto dto = stockService.getStockQuoteInfo("test");

        assertEquals(dtoFromResponce.getSymbol(), dto.getSymbol());
    }
}
