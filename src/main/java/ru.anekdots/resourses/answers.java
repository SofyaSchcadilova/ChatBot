package ru.anekdots.resourses;

import ru.anekdots.databasecontroller.SqlControler;
import ru.anekdots.databasecontroller.models.JokesModel;

import java.sql.SQLException;
import java.util.List;

/**
 *  Ответы бота /
 *  Строки, которыми отвечает бот
  */

public class answers {
    /**
     * Стартовое сообщение
     */
    final public static String _START = """
                        Привет! Я бот с анекдотами, для справки нажмите /help

                        У меня есть несколько кнопок для удобства:

                        "Время анекдотов" - ты вводишь время в формате hh:mm, и каждый день в это время я буду отправлять самый смешной анекдот

                        "Новый анекдот" - я присылаю самый новый, самый свежий

                        "Предложить" - присылай свой анекдот, который в дальнейшем может быть добавлен в наш бот

                        После отправки анекдота появляется кнопка "Оценить" - оцени анекдот!!!""";
    /**
     * Сообщение - помощь
     */
    final static  public String _HELP = """
                        Я бот с анекдотами. У меня есть несколько кнопок для удобства:

                        "Время анекдотов" - ты вводишь время в формате hh:mm, и каждый день в это время я буду отправлять самый смешной анекдот

                        "Новый анекдот" - я присылаю самый новый, самый свежий

                        "Предложить" - ты присылаешь свой анекдот, который в дальнейшем может быть добавлен в наш бот

                        После отправки анекдота появляется кнопка "Оценить" - оцени анекдот!!!""";

    /**
     * сообщение - вывод всех шуток
     */
    public static String getAll() throws SQLException, ClassNotFoundException {
        SqlControler sqlControler = new SqlControler();
        List<JokesModel> jokesList = sqlControler.getAllJokes();
        if (jokesList.isEmpty()){
            return "Пока наша база шуток пуста! Но вы можете добавить свой анекдот:)";
        }
        String ans = "";
        for (int i = 0; i < jokesList.size(); i++){
            ans = ans  + jokesList.get(i).JokeText + "\n";
        }
        return ans;
    }
}
