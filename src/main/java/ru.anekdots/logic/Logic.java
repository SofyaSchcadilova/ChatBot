package ru.anekdots.logic;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import com.vdurmont.emoji.EmojiParser;
import ru.anekdots.bot.Bot;
import ru.anekdots.databasecontroller.SqlController;

import ru.anekdots.databasecontroller.models.JokesModel;
import ru.anekdots.databasecontroller.models.UserModel;
import ru.anekdots.logic.HtmlGetter;
import ru.anekdots.logic.LogicAnswer;
import ru.anekdots.logic.WebSearch;
import ru.anekdots.resourses.answers;

import java.io.Closeable;
import java.io.IOException;
import java.sql.SQLException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.time.LocalTime;
import java.time.Duration;
import java.time.format.DateTimeFormatter;

/**
 * Основной класс логики
  */
public class Logic implements Closeable {


    private Bot bot;
    /**
     * Управление базой данных
     */
    private SqlController DB;

    private WebSearch webSearch;

    private HtmlGetter htmlGetter;

    private HtmlParser htmlParser;


    public Logic(SqlController sql){
        this(null, sql, new WebSearch(),new HtmlGetter(),new HtmlParser());
    }


    public Logic() throws SQLException, ClassNotFoundException {
        this(null, new SqlController(), new WebSearch(),new HtmlGetter(),new HtmlParser());
    }

    public Logic(Bot bot) throws SQLException, ClassNotFoundException {
        this(bot, new SqlController(), new WebSearch(),new HtmlGetter(),new HtmlParser());
    }

    /**
     * Только для тестов
     * @param bot
     */
    public Logic(Bot bot, SqlController sql, WebSearch webSearch, HtmlGetter htmlGetter, HtmlParser htmlParser){
        this.bot = bot;
        this.DB = sql;
        this.webSearch = webSearch;
        this.htmlGetter = htmlGetter;
        this.htmlParser = htmlParser;
    }

    /**
     * Обработка запроса пользователя
     * @param rawText "сырой" текст
      */

    public LogicAnswer think(String rawText, Long userId) throws SQLException, IOException {
        String answer;
        LogicAnswer logicAnswer;
        String evaluationKeyboard = "evaluationKeyboard";
        String menuKeyboard = "menuKeyboard";
        rawText = rawText.toLowerCase();

        if (!DB.IsUserExists(userId)){
            DB.addUser(userId);
            DB.setState(userId, 0);
        }
        UserModel cur_user = DB.getUserByTelegramId(userId);

        if (cur_user.State == 1){
            try {
                if (DB.addJoke(rawText,DB.getUserByTelegramId(userId).getId())) {
                    DB.setState(userId, 0);
                    return new LogicAnswer("Анекдот добавлен!", menuKeyboard);
                } else {
                    DB.setState(userId, 0);
                    return new LogicAnswer("Такой анекдот уже есть!", menuKeyboard);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        if (cur_user.State == 2) {
            while (!rawText.equals(EmojiParser.parseToUnicode("\uD83D\uDC4D"))
                    && !rawText.equals(EmojiParser.parseToUnicode("\uD83D\uDC4E")))
                return new LogicAnswer(EmojiParser.parseToUnicode
                        ("Пожалуйста, оцени анекдот"), evaluationKeyboard);
            if (EmojiParser.parseToUnicode("\uD83D\uDC4D").equals(rawText))
                DB.changeRate(cur_user.PrevJoke, true);
            else if (EmojiParser.parseToUnicode("\uD83D\uDC4E").equals(rawText))
                DB.changeRate(cur_user.PrevJoke, false);

            DB.setState(cur_user.Telegram_id,0);
            return new LogicAnswer("Спасибо за оценку!", menuKeyboard);
        }

        if (cur_user.State == 3){
            try {
                DB.setState(userId, 0);
                int n = Integer.parseInt(rawText);
                return new LogicAnswer(DB.getBestJokes(n), menuKeyboard);
            }
            catch (NumberFormatException nfe)
            {
                return new LogicAnswer("Введи число!", menuKeyboard);
            }
        }

        if (cur_user.State == 4){
            if (rawText.length() > 12 && rawText.substring(0, 12).equals("анекдот про ")) {
                DB.setState(userId, 0);
                List<String> jokes = webSearch.find(rawText.substring(12));

                if (jokes == null)
                    return new LogicAnswer("Произошла какая-то ошибка:(\n Попробуй снова!",
                            menuKeyboard);
                else if (jokes.isEmpty())
                    return new LogicAnswer("Пупупу... Анекдотов про "
                            + rawText.substring(12) + " нет...", menuKeyboard);

                for (String joke : jokes) {
                    joke = htmlParser.parseHtml(htmlGetter.getHtml(joke));
                    if (DB.addJoke(joke)) {
                        DB.setSeenJoke(userId, DB.findJokeByText(joke));
                        DB.savePrevJoke(userId, DB.findJokeByText(joke));
                        DB.setState(userId, 2);
                        return new LogicAnswer(joke, evaluationKeyboard);
                    } else if (!DB.IsSeenJoke(userId, DB.findJokeByText(joke)) &&
                            DB.getJokeById(DB.findJokeByText(joke)).rate > -5) {
                        DB.setSeenJoke(userId, DB.findJokeByText(joke));
                        DB.savePrevJoke(userId, DB.findJokeByText(joke));
                        DB.setState(userId, 2);
                        return new LogicAnswer(joke, evaluationKeyboard);
                    }
                }
                return new LogicAnswer("Ты уже получил все анекдоты про " +
                        rawText.substring(12) + "...", menuKeyboard);
            }
            else
                return new LogicAnswer("Введи в формате \"анекдот про ...\"!", null);
        }



        if ((rawText.length() == 5) && ((rawText.charAt(2) == ':') || (rawText.charAt(1) == ':'))) {
            Pattern pattern = Pattern.compile("^([01]?[0-9]|2[0-3]):[0-5][0-9]$");
            Matcher matcher = pattern.matcher(rawText);
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            sdf.setLenient(false);
            if (!matcher.matches()) {
                return new LogicAnswer("Введи время в формате hh:mm", menuKeyboard);
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
                    return new LogicAnswer("Хорошо! Жди анекдот в " + rawText, menuKeyboard);
                }
                return new LogicAnswer("Введи время в формате hh:mm", menuKeyboard);
            } catch (ParseException e) {
                return new LogicAnswer("Введи время в формате hh:mm", menuKeyboard);
            }
        }


        switch (rawText) {
            case ("/start"):
                answer = new answers()._START;
                logicAnswer = new LogicAnswer(answer, menuKeyboard);
                break;
            case ("/help"), ("нужна помощь"):
                answer = new answers()._HELP;
                logicAnswer = new LogicAnswer(answer, menuKeyboard);
                break;
            case ("/needjoke"), ("нужен анекдот"):
                int numberOfJokes = DB.getNumberOfJokes(); //число всех шуток в базе
                int sentJokes = 0; //число отправленных шуток, если = numberOfJokes, то отправляется текст "шутки закончились"
                for (int i = 1; i <= numberOfJokes; i++){
                    if (DB.IsSeenJoke(userId, i) || DB.getJokeById(i).rate <=-5 ) { //анекдот уже отправлялся
                        sentJokes += 1;
                    }
                }
                if (sentJokes == numberOfJokes){
                    return new LogicAnswer(
                            "Шутки кончились...\nПодожди пока появятся новые или предложи свои!",
                            menuKeyboard);
                } else {
                    while (true) {
                        JokesModel joke = DB.getRandomJoke();
                        if (!DB.IsSeenJoke(userId, joke.getId()) && joke.rate>-5) { //анекдот уже отправлялся
                            DB.setSeenJoke(userId, joke.getId());
                            DB.savePrevJoke(userId, joke.getId());
                            DB.setState(userId, 2);
                            return new LogicAnswer(joke.JokeText, evaluationKeyboard);
                        }
                    }
                }
            case ("анекдот по теме"):
                answer = "Введи про кого или про что хочешь получить анекдот в формате \"анекдот про ...\"";
                logicAnswer = new LogicAnswer(answer, null);
                DB.setState(userId, 4);
                break;
            case ("/suggest"), ("предложить анекдот"):
                DB.setState(userId, 1);
                answer = "Введи анекдот";
                logicAnswer = new LogicAnswer(answer, null);
                break;
            case ("/joketime"), ("время анекдота"):
                answer = "Введи время, в которое я буду отправлять тебе анекдот, в формате hh:mm";
                logicAnswer = new LogicAnswer(answer, null);
                break;
            case ("/getall"), ("все анекдоты"):
                answer = DB.getAllJokes();
                logicAnswer = new LogicAnswer(answer, menuKeyboard);
                break;
            case ("/gettop"), ("лучшие анекдоты"):
                answer = "Введи количество шуток";
                logicAnswer = new LogicAnswer(answer, null);
                DB.setState(userId, 3);
                break;

            default:
                answer = "Я не знаю такую команду :(\nВведи /help для справки";
                logicAnswer = new LogicAnswer(answer, menuKeyboard);
                break;
            }
        return logicAnswer;
    }

    public List<UserModel> getAllUsers() throws SQLException {
        return DB.getAllUsers();
    }

    public void close(){
        DB.close();
    }
}
