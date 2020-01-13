package ru.lilitweb.springintegration.service.notification;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class NotificationServiceToTelegram implements NotificationService {
    private TelegramBot bot;
    private String chatId;

    public NotificationServiceToTelegram(TelegramBot bot, String chatId) {
        this.bot = bot;
        this.chatId = chatId;
    }

    @Override
    public void notify(Message message) throws Exception {
        String text = message.getSubject() + "\n" + message.getText();
        SendMessage messageT = new SendMessage()
                .setChatId(chatId)
                .setText(text);
        bot.execute(messageT);
    }
}
