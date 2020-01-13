package ru.lilitweb.springintegration.service.notification;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;


public class NotificationServiceToEmail implements NotificationService {
    private JavaMailSender emailSender;
    private String[] emailTo;


    public NotificationServiceToEmail(JavaMailSender emailSender, String[] emailTo) {
        this.emailSender = emailSender;
        this.emailTo = emailTo;
    }

    @Override
    public void notify(Message message) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(emailTo);
        mailMessage.setSubject(message.getSubject());
        mailMessage.setText(message.getText());
        emailSender.send(mailMessage);
    }
}
