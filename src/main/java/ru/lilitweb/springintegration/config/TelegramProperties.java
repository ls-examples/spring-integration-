package ru.lilitweb.springintegration.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "telegram")
@Data
public class TelegramProperties {
    private  String botName;
    private  String token;
    private  String chatId;
}
