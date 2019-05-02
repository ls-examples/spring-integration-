package ru.lilitweb.springintegration.service.notification;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Message {
    private String subject;
    private String text;
}
