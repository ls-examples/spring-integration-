package ru.lilitweb.springintegration.service.notification;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@DisplayName("Методы сервиса нотификаций в канал телеграма должны ")
class NotificationServiceToTelegramTest {

    @Mock
    TelegramBot bot;

    private String chatId = "2432512515";

    @InjectMocks
    NotificationServiceToTelegram notificationService =
            new NotificationServiceToTelegram(bot, chatId);

    @Test
    @DisplayName("вызывать метод send JavaMailSender с нужными параметрами. Текущий метод: notify")
    void shouldExecuteMethodSendFromJavaMailSender() throws Exception {
        Message message = new Message("subject", "text");
        notificationService.notify(message);
        SendMessage messageT = new SendMessage()
                .setChatId(chatId)
                .setText("subject\ntext");

        verify(bot, times(1)).execute(messageT);
    }

}
