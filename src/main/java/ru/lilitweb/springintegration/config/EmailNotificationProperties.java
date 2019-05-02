package ru.lilitweb.springintegration.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@Data
@ConfigurationProperties("emailnotification")
public class EmailNotificationProperties {
    List<String> emails;
}
