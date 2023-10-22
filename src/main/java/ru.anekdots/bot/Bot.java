package ru.anekdots.bot;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Интерфейс всех ботов
 */
interface Bot {


    String getBotUsername();

    String getBotToken();

    void sendMessage(String text, long chatId);


}
