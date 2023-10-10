package ru.anekdots.bot;

import lombok.Data;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


interface Bot {

    String Token = null;

    String Botname = null;

    String getBotUsername();

    String getBotToken();

    void sendMessage(String text, long chatId);


}
