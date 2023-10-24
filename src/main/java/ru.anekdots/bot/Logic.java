package ru.anekdots.bot;

import ru.anekdots.databasecontroller.SqlControler;
import ru.anekdots.databasecontroller.models.JokesModel;
import ru.anekdots.databasecontroller.models.UserModel;
import ru.anekdots.resourses.answers;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

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
     * Хранение коллекции id пользователей, чтобы принимать новые анекдоты
     */

    Set<Long> userWithJoke= new TreeSet<>();

    void addUserWithJoke(Long chatID){
        userWithJoke.add(chatID);
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
            case ("Предложить анекдот"):
                answer = "Введите анекдот";
                break;
            case ("successfullyAdded"):
                answer = "Анекдот добавлен!";
                break;
            case ("unSuccessfullyAdded"):
                answer = "Такой анекдот уже есть!";
                break;
            default:
                answer = "Я не знаю такую команду :(\nВведи /help для справки";
                break;
            }
        return answer;
    }

    public void close(){
        DB.close();
    }
}
