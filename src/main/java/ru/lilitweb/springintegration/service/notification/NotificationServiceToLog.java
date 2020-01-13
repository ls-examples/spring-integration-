package ru.lilitweb.springintegration.service.notification;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NotificationServiceToLog implements NotificationService {
    private static final Logger log = LoggerFactory.getLogger(NotificationServiceToLog.class);
    private String type;

    public NotificationServiceToLog(String type) {
        this.type = type;
    }

    @Override
    public void notify(Message message) {
        log.info(type + ":\n" + message.getSubject() + "\n" + message.getText());
    }
}
