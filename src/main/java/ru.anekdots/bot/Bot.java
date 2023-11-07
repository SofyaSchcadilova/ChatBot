package ru.anekdots.bot;


/**
 * Интерфейс всех ботов
 */
interface Bot {


    String getBotUsername();

    String getBotToken();

    void sendMessage(LogicAnswer textAnswer, long chatId);


}
