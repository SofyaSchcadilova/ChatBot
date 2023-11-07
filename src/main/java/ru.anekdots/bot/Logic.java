package ru.anekdots.bot;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import ru.anekdots.databasecontroller.SqlController;

import ru.anekdots.databasecontroller.models.JokesModel;
import ru.anekdots.resourses.answers;

import java.io.IOException;
import java.sql.SQLException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.time.LocalTime;
import java.time.Duration;
import java.time.format.DateTimeFormatter;

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
     * Обработка запроса пользователя
     * @param rawText "сырой" текст
      */

    String think(String rawText, Long userId) throws SQLException, IOException {
        String answer;

        if (!DB.IsUserExists(userId)){
            DB.addUser(userId);
            DB.setState(userId, 0);
        }


        if (DB.getUserByTelegramId(userId).State == 1){

            try {
                if (DB.addJoke(rawText)) {
                    DB.addJoke(rawText);
                    DB.setState(userId, 0);
                    return "Анекдот добавлен!";
                } else {
                    DB.setState(userId, 0);
                    return "Такой анекдот уже есть!";
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }



        if (((rawText.charAt(2) == ':') || (rawText.charAt(1) == ':')) && (rawText.length() <= 5)) {
            Pattern pattern = Pattern.compile("^([01]?[0-9]|2[0-3]):[0-5][0-9]$");
            Matcher matcher = pattern.matcher(rawText);
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            sdf.setLenient(false);
            if (!matcher.matches()) {
                return "Введи время в формате hh:mm";
            }
            try {
                Date parsedDate = sdf.parse(rawText);
                if (parsedDate != null) {
                    String timeString = rawText + ":00";
                    String time0000 = "00:00:00";

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                    // Парсинг строки в объект LocalTime
                    LocalTime timeForJoke = LocalTime.parse(timeString, formatter);
                    LocalTime midnight = LocalTime.parse(time0000, formatter);

                    Duration duration = Duration.between(midnight, timeForJoke);
                    int secondsSinceStartOfDay = (int) duration.getSeconds();
                    DB.setUserTime(userId, secondsSinceStartOfDay);
                    return "Хорошо! Жди анекдот в " + rawText;
                }
                return "Введи время в формате hh:mm";
            } catch (ParseException e) {
                return "Введи время в формате hh:mm";
            }
        }

        rawText = rawText.toLowerCase();
        switch (rawText) {
            case ("/start"):
                answer = answers._START;
                break;
            case ("/help"), ("нужна помощь"):
                answer = answers._HELP;
                break;

            case ("анекдот"):
                int numberOfJokes = DB.getNumberOfJokes(); //число всех шуток в базе
                int sentJokes = 0; //число отправленных шуток, если = numberOfJokes, то отправляется текст "шутки закончились"
                for (int i = 1; i <= numberOfJokes; i++){
                    if (DB.IsSeenJoke(userId, i)) { //анекдот уже отправлялся
                        sentJokes += 1;
                    }
                }
                if (sentJokes == numberOfJokes){
                    return "Анекдоты закончились((\nПодожди пока появятся новые или предложи свой!";
                } else {
                    while (true) {
                        JokesModel joke = DB.getRandomJoke();
                        if (!DB.IsSeenJoke(userId, joke.getId())) { //анекдот уже отправлялся
                            DB.setSeenJoke(userId, joke.getId());
                            DB.savePrevJoke(userId, joke.getId());
                            return joke.JokeText;
                        }
                    }
                }
            case ("предложить анекдот"):
                DB.setState(userId, 1);
                answer = "Введите анекдот";
                break;
            case ("время"):
                answer = "Введите время, в которое я буду отправлять тебе анекдот, в формате hh:mm";
                break;
            case ("/getall"):
                answer = DB.getAllJokes();
                break;
            //case ()

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
