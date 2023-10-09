package ru.anekdots;

import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.anekdots.bot.MainClass;

public class Main {
    public static void main(String[] args) throws TelegramApiException {
        MainClass cursession = new MainClass();
        cursession.init();
    }
}