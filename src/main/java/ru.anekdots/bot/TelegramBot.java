package ru.anekdots.bot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.anekdots.logic.Logic;
import ru.anekdots.logic.LogicAnswer;
import ru.anekdots.resourses.botsdata;

import java.io.IOException;
import java.sql.SQLException;


/***
 * Класс телеграм бота
 *
  */
public class TelegramBot extends TelegramLongPollingBot implements Bot{

    /**
     * Подключение основной логики
     */

    public Logic logic;
    /**
     * Имя бота
     */
    private String Botname = botsdata.NAME;
    /**
     * Ключ/Токен бота
     */
    private String Token = botsdata.TOKEN;


    public TelegramBot() throws SQLException, ClassNotFoundException, InterruptedException {
        logic = new Logic(this);


    }

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
     * @param textAnswer текст сообщения
     * @param chatId айди чата
     */
    @Override
    public void sendMessage(LogicAnswer textAnswer, long chatId) {
        SendMessage message = new SendMessage();
        message.setText(textAnswer.getAnswer());
        message.setChatId(String.valueOf(chatId));

        if (textAnswer.getKeyboardType() != null)
            message.setReplyMarkup((ReplyKeyboard) textAnswer.getKeyboardType());

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
        try {
            sendMessage(logic.think(text, chatId), chatId);
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }



}
