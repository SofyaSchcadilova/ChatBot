package ru.anekdots.bot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Objects;

public class TelegramBot extends TelegramLongPollingBot {

    @Override
    public String getBotUsername() {
        return "anekbots_bot";
    }

    @Override
    public String getBotToken() {
        return "6608006411:AAHxxxH19u2kfWORCgO5u47vgGoO_Gk2ad0";
    }

    @Override
    public void onUpdateReceived(Update update) {
        String chatId = update.getMessage().getChatId().toString();
        String text = update.getMessage().getText();
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);

        switch (text) {
            case ("/start"):
                sendMessage.setText("""
                        Привет! Я бот с анекдотами, для справки нажмите /help

                        У меня есть несколько кнопок для удобства:

                        "Время анекдотов" - ты вводишь время в формате hh:mm, и каждый день в это время я буду отправлять самый смешной анекдот

                        "Новый анекдот" - я присылаю самый новый, самый свежий

                        "Предложить" - присылай свой анекдот, который в дальнейшем может быть добавлен в наш бот

                        После отправки анекдота появляется кнопка "Оценить" - оцени анекдот!!!""");
                break;
            case ("/help"):
                sendMessage.setText("""
                        Я бот с анекдотами. У меня есть несколько кнопок для удобства:

                        "Время анекдотов" - ты вводишь время в формате hh:mm, и каждый день в это время я буду отправлять самый смешной анекдот

                        "Новый анекдот" - я присылаю самый новый, самый свежий

                        "Предложить" - ты присылаешь свой анекдот, который в дальнейшем может быть добавлен в наш бот

                        После отправки анекдота появляется кнопка "Оценить" - оцени анекдот!!!""");
                break;
            default:
                sendMessage.setText("Вы ввели: " + text);
                break;
        }

        try {
            this.execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
