package ru.lilitweb.springintegration.service.notification;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@DisplayName("Методы сервиса нотификаций на почту должны ")
class NotificationServiceToEmailTest {
    @Mock
    JavaMailSender emailSender;

    private String[] emails = new String[]{"examle@gmail.com", "example2@gmail.com"};

    @InjectMocks
    NotificationServiceToEmail notificationService =
            new NotificationServiceToEmail(emailSender, emails);


    @Test
    @DisplayName("вызывать метод send JavaMailSender с нужными параметрами. Текущий метод: notify")
    void shouldExecuteMethodSendFromJavaMailSender() {
        Message message = new Message("subject", "text");
        notificationService.notify(message);

        SimpleMailMessage emMess = new SimpleMailMessage();
        emMess.setTo(emails);
        emMess.setSubject(message.getSubject());
        emMess.setText(message.getText());

        verify(emailSender, times(1)).send(emMess);
    }
}
