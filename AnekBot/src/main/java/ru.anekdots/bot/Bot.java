package ru.anekdots.bot;

import lombok.Data;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


interface Bot {


    String getBotUsername();

    String getBotToken();


    void sendMessage(String text, long chatId);


}
