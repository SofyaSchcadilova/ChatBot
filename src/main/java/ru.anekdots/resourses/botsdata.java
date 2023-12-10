package ru.anekdots.resourses;

import io.github.cdimascio.dotenv.Dotenv;

/**
 * Данные для авторизации бота
 */
public class botsdata {
    final Dotenv dotenv = Dotenv.load();
    /**
     * Токен бота
     */
    final public String TOKEN = dotenv.get("TOKEN");

    /**
     * Имя бота
     */
    final public String NAME = dotenv.get("NAME");
}
