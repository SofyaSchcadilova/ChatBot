package ru.anekdots.bot;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import ru.anekdots.resourses.answers;
// Основной класс логики
public class MainClass {

    TelegramBot bot = new TelegramBot();
    // Запуск бота
    public void init() throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(bot);
    }

    // Обработка запроса пользователя
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
