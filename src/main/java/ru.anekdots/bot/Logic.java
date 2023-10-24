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

    static Set<Long> userWithJoke= new TreeSet<>();

    static void addUserWithJoke(Long chatID){
        userWithJoke.add(chatID);
    }

    /**
     * Обработка запроса пользователя
     * @param rawText "сырой" текст, userId временно
      */

    static String think(String rawText, Long userId) {
        String answer;


        if (userWithJoke.contains(userId)){
            try {
                if (SqlControler.addJoke(rawText)) {
                    SqlControler.addJoke(rawText);
                    return "Анекдот добавлен!";
                } else {
                    return "Такой анекдот уже есть!";
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        userWithJoke.remove(userId);

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
