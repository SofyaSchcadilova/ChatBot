package ru.anekdots.bot;

import ru.anekdots.resourses.answers;
/**
 * Основной класс логики
  */
public class Logic {


    Bot bot;

    Logic(Bot bot){
        this.bot = bot;
    }

    /**
     * Обработка запроса пользователя
     * @param rawText "сырой" текст
      */

    static String think(String rawText) {
        String answer;
        switch (rawText) {
            case ("/start"):
                answer = answers._START;
                break;
            case ("/help"):
                answer = answers._HELP;
                break;
            default:
                answer = "Вы написали: " + rawText;
                break;
            }
        return answer;
    }
}
