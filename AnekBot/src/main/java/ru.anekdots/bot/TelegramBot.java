package ru.anekdots.bot;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.anekdots.resourses.answers;
import ru.anekdots.resourses.botsdata;

import java.util.Objects;


public class TelegramBot extends TelegramLongPollingBot implements Bot{


    String Botname = botsdata.name;

    String Token = botsdata.token;
    @Override
    public String getBotUsername() {
        return Botname;
    }

    @Override
    public String getBotToken() {
        return Token;
    }

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

    @Override
    public void onUpdateReceived(Update update) {
        Long chatId = update.getMessage().getChatId();
        String text = update.getMessage().getText();

        switch (text) {
            case ("/start"):
                sendMessage(answers._start, chatId);
                break;
            case ("/help"):
                sendMessage(answers._help, chatId);
                break;
            default:
                sendMessage("Вы ввели: " + text, chatId);
                break;
        }
    }
}
