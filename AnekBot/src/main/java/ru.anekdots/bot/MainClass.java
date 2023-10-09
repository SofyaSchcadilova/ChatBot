package ru.anekdots.bot;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import ru.anekdots.resourses.answers;

public class MainClass {

    TelegramBot bot = new TelegramBot();

    public void init() throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(bot);
    }

    static String think(String rawText) {
        String answer;
        switch (rawText) {
            case ("/start"):
                answer = answers._start;
                break;
            case ("/help"):
                answer = answers._help;
                break;
            default:
                answer = "Вы написали: " + rawText;
                break;
            }
        return answer;
    }
}