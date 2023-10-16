package ru.anekdots;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import ru.anekdots.bot.TelegramBot;
import ru.anekdots.databasecontroller.SqlControler;

public class Main {
    public static void main(String[] args) throws TelegramApiException {
        SqlControler test = new SqlControler();
        test.close();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(new TelegramBot());
    }
}