package ru.anekdots.bot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.anekdots.resourses.botsdata;

/***
 * Класс телеграм бота
 *
  */
public class TelegramBot extends TelegramLongPollingBot implements Bot{

    /**
     * Подключение основной логики
     */

    private Logic logic = new Logic(this);
    /**
     * Имя бота
     */
    private String Botname = botsdata.NAME;
    /**
     * Ключ/Токен бота
     */
    private String Token = botsdata.TOKEN;
    /**
     * Получить имя бота
     */
    @Override
    public String getBotUsername() {
        return Botname;
    }
    /**
     * Получить токен бота
     */
    @Override
    public String getBotToken() {
        return Token;
    }

    /**
     * Отправка сообщения
     * @param text текст сообщения
     * @param chatId айди чата
     */
    @Override
    public void sendMessage(String text, long chatId) {
        SendMessage message = new SendMessage();
        message.setText(text);
        message.setChatId(String.valueOf(chatId));
        try {
            execute(message);
        }
        catch (TelegramApiException e){
            throw new RuntimeException(e);
        }
    }
    /**
     * Действия бота при получении
     */
    @Override
    public void onUpdateReceived(Update update) {
        Long chatId = update.getMessage().getChatId();
        String text = update.getMessage().getText();

        sendMessage(logic.think(text), chatId);
    }
}
