package ru.anekdots;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import ru.anekdots.bot.TelegramBot;
import ru.anekdots.bot.ThreadForEverydayJoke;
import ru.anekdots.databasecontroller.SqlController;
import ru.anekdots.databasecontroller.models.JokesModel;

import java.io.IOException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws TelegramApiException, SQLException, ClassNotFoundException, IOException, InterruptedException {



        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        TelegramBot tgbot = new TelegramBot();
        telegramBotsApi.registerBot(tgbot);
        Thread thread = new Thread(new ThreadForEverydayJoke(tgbot));
        thread.start();

    }
}