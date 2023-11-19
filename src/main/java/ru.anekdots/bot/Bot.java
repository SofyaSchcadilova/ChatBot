package ru.anekdots.bot;


import ru.anekdots.logic.LogicAnswer;

/**
 * Интерфейс всех ботов
 */
public interface Bot {


    String getBotUsername();

    String getBotToken();

    void sendMessage(LogicAnswer textAnswer, long chatId);


}
