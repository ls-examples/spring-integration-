package ru.lilitweb.springintegration.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import ru.lilitweb.springintegration.repostory.StockQuoteRepository;
import ru.lilitweb.springintegration.service.StockService;
import ru.lilitweb.springintegration.service.StockServiceImpl;
import ru.lilitweb.springintegration.service.notification.*;
import ru.lilitweb.springintegration.service.stockanalyzer.StockAnalyzerService;
import ru.lilitweb.springintegration.service.stockanalyzer.StockAnalyzerServiceImpl;

@Configuration
public class ServiceConfig {
    private static final Logger log = LoggerFactory.getLogger(ServiceConfig.class);

    @Bean
    public StockService stockService() {
        return new StockServiceImpl(new RestTemplate());
    }

    @Bean
    public StockAnalyzerService stockAnalyzerService(StockQuoteRepository repo) {
        return new StockAnalyzerServiceImpl(repo);
    }

    @Bean
    public TelegramBot telegramBot(TelegramProperties props) {
        return new TelegramBot(props.getBotName(), props.getToken());
    }

    @Bean
    public NotificationService telegramNotificationService(TelegramBot telegramBot,
                                                           TelegramProperties props) throws TelegramApiRequestException {
        TelegramBotsApi botsApi = new TelegramBotsApi();
        botsApi.registerBot(telegramBot);
        return new NotificationServiceToTelegram(telegramBot, props.getChatId());
    }

    @Bean
    public NotificationService emailNotificationService(
            EmailNotificationProperties props,
            JavaMailSender mailSender) {
        return new NotificationServiceToEmail(mailSender, props.emails.toArray(new String[0]));
    }

    @Bean
    public NotificationService logNotificationService() {
        return new NotificationServiceToLog("log notification:");
    }
}
