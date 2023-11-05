package ru.anekdots.bot;

import ru.anekdots.databasecontroller.SqlController;

import ru.anekdots.resourses.answers;

import java.sql.SQLException;

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
    SqlController DB;


    Logic(SqlController sql){
        DB = sql;
    }


    Logic() throws SQLException, ClassNotFoundException {
        DB = new SqlController();
    }

    Logic(Bot bot) throws SQLException, ClassNotFoundException {
        DB = new SqlController();
        this.bot = bot;
    }

    /**
     * Хранение коллекции id пользователей, чтобы принимать новые анекдоты
     */

    static Set<Long> userWithJoke = new TreeSet<>();

    void addUserWithJoke(Long chatID){
        userWithJoke.add(chatID);
    }


    /**
     * Обработка запроса пользователя
     * @param rawText "сырой" текст
      */

    String think(String rawText, Long userId) throws SQLException {
        String answer;


        if (userWithJoke.contains(userId)){
            try {
                if (DB.addJoke(rawText)) {
                    DB.addJoke(rawText);
                    userWithJoke.remove(userId);
                    return "Анекдот добавлен!";
                } else {
                    userWithJoke.remove(userId);
                    return "Такой анекдот уже есть!";
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }


        rawText = rawText.toLowerCase();
        switch (rawText) {
            case ("/start"):
                answer = answers._START;
                break;
            case ("/help"):
                answer = answers._HELP;
                break;
            case ("предложить анекдот"):
                addUserWithJoke(userId);
                answer = "Введите анекдот";
                break;
            case ("/getall"):
                answer = DB.getAllJokes();
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
