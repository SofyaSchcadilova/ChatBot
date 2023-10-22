package ru.anekdots.bot;

import ru.anekdots.databasecontroller.SqlControler;
import ru.anekdots.databasecontroller.models.JokesModel;
import ru.anekdots.resourses.answers;

import java.sql.SQLException;
import java.util.List;

/**
 * Основной класс логики
  */
public class Logic {


    Bot bot;
    /**
     * Управление базой данных
     */
    SqlControler DB;


    Logic(Bot bot) throws SQLException, ClassNotFoundException {
        DB = new SqlControler();
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

    public void close(){
        DB.close();
    }
}
