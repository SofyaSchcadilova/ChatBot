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
                         
                        "Предложить анекдот" - ты присылаешь свой анекдот, который в дальнейшем может быть добавлен в наш бот""";
    /**
     * Сообщение - помощь
     */
    final static  public String _HELP = """
                        Я бот с анекдотами
                         
                        Команда "Предложить анекдот" - ты присылаешь свой анекдот, который в дальнейшем может быть добавлен в наш бот""";


}
