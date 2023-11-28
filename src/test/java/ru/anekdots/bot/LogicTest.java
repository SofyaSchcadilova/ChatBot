package ru.anekdots.bot;

import com.vdurmont.emoji.EmojiParser;
import org.htmlunit.WebClient;
import org.junit.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ru.anekdots.databasecontroller.SqlController;
import ru.anekdots.databasecontroller.models.JokesModel;
import ru.anekdots.databasecontroller.models.UserModel;
import ru.anekdots.logic.*;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import static org.powermock.api.mockito.PowerMockito.when;


/**
 * Тест класса логики
 */

@ExtendWith(MockitoExtension.class)
public class LogicTest {

    @Rule
    public TestName testName = new TestName();

    public LogicTest() throws SQLException, ClassNotFoundException {
    }

    @Before
    public void beforeClass() {
        System.out.println("Starting test " + testName.getMethodName());
    }

    @After
    public void afterClass() { System.out.println("Test finished " + testName.getMethodName());
    }


    @Test
    public void thinkTest_FirstAttemptAddJoke() throws SQLException, IOException {
        String joke1 = "Joke1";
        SqlController sqlController = Mockito.mock(SqlController.class);


        UserModel user = new UserModel(1,3L, 1, null);
        Mockito.when(sqlController.IsUserExists(3L)).thenReturn(true);
        Mockito.when(sqlController.getUserByTelegramId(3L)).thenReturn(user);
        Mockito.when(sqlController.addJoke(Mockito.anyString())).thenReturn(true);
        Mockito.when(sqlController.addUser(3L)).thenReturn(false);

        Logic logic = new Logic(sqlController);


        //logic.think("Предложить анекдот", 3L);
        Assert.assertEquals("Анекдот добавлен!", logic.think(joke1, 3L).getAnswer());
    }
    @Test
    public void thinkTest_SecondAttemptAddJoke() throws SQLException, IOException {
        String joke1 = "Joke1";
        SqlController sqlController = Mockito.mock(SqlController.class);

        UserModel user = new UserModel(1,3L, 1, null);

        Mockito.when(sqlController.addJoke(joke1)).thenReturn(false);
        Mockito.when(sqlController.addUser(3L)).thenReturn(true);
        Mockito.when(sqlController.getUserByTelegramId(3L)).thenReturn(user);

        Logic logic = new Logic(sqlController);
        // Повторно отправили ту же самую шутку
        logic.think("Предложить анекдот", 3L);
        Assert.assertEquals("Такой анекдот уже есть!", logic.think(joke1, 3L).getAnswer());
    }
    /**
     * Тест для проверки что если время сейчас совпадает со временем отправки анекдота у пользователя,
     * то программа отправляет анекдот
     * @throws SQLException
     * @throws IOException
     */
    @Test
    public void test_sendJokeEveryDay() throws SQLException, IOException {
        SqlController sqlController = Mockito.mock(SqlController.class);
        Logic logic = new Logic(sqlController);
        UserModel user = new UserModel(1,3L, 0, null, 0, 10);
        JokesModel joke1 = new JokesModel(1, "анек1", 0);
        JokesModel joke2 = new JokesModel(2, "анек2", 0);
        LocalTime localTime = LocalTime.of(0, 0, 0);

        Mockito.when(sqlController.getUserByTelegramId(3L)).thenReturn(user);
        Mockito.when(sqlController.addUser(3L)).thenReturn(true);

        Mockito.when(sqlController.getNumberOfJokes()).thenReturn(2);
        Mockito.when(sqlController.IsSeenJoke(3L, 1)).thenReturn(false);
        Mockito.when(sqlController.getRandomJoke()).thenReturn(joke1);

        Set<Integer> time = new TreeSet<>();
        for (int i = 0; i < 5; i++){
            time.add(localTime.toSecondOfDay() + 60);
        }

        for (int timeNow : time){
            if (timeNow == user.Time){
                Assert.assertEquals( "анек1", logic.think("анекдот", 3L));
            } else {
                continue;
            }
        }
    }
    @Test

    public void thinkTest_getAll() throws SQLException, IOException {
        String joke1 = "смешной анек";
        String joke2 = "не смешной анек";
        SqlController sqlController = Mockito.mock(SqlController.class);
        Logic logic = new Logic(sqlController);

        UserModel user = new UserModel(1,3L, 0, null);

        Mockito.when(sqlController.getUserByTelegramId(3L)).thenReturn(user);
        Mockito.when(sqlController.addUser(3L)).thenReturn(true);
        Mockito.when(sqlController.getAllJokes()).thenReturn(joke1+"\n"+joke2 +"\n");

        Assert.assertEquals( joke1 + "\n" + joke2 +"\n", logic.think("/getall", 3L).getAnswer());
    }
    /**
     * Проверка что программа отпраляет случайный анекдот из базы
     * @throws SQLException
     * @throws IOException
     */
    @Test
    public void thinkTest_getRandomJokeFromDataBase() throws SQLException, IOException {
        SqlController sqlController = Mockito.mock(SqlController.class);
        Logic logic = new Logic(sqlController);
        UserModel user = new UserModel(1,3L, 0, null);
        JokesModel joke1 = new JokesModel(1, "анек1", 0);


        Mockito.when(sqlController.getJokeById(1)).thenReturn(joke1);

        Mockito.when(sqlController.getUserByTelegramId(3L)).thenReturn(user);
        Mockito.when(sqlController.addUser(3L)).thenReturn(true);

        Mockito.when(sqlController.getNumberOfJokes()).thenReturn(1);
        Mockito.when(sqlController.IsSeenJoke(3L, 1)).thenReturn(false);
        Mockito.when(sqlController.getRandomJoke()).thenReturn(joke1);

        Assert.assertEquals( "анек1", logic.think("нужен анекдот", 3L).getAnswer());
    }
    /**
     * Тест на то, что если анекдоты просмотрены, то программа отвечает, что анекдоты закончились
     * @throws SQLException
     * @throws IOException
     */
    @Test
    public void thinkTest_getRandomJokeFromDataBaseAllJokesHaveBeenSent() throws SQLException, IOException {
        SqlController sqlController = Mockito.mock(SqlController.class);
        Logic logic = new Logic(sqlController);

        UserModel user = new UserModel(1,3L, 0, null);
        JokesModel joke1 = new JokesModel(1, "анек1", 0);
        JokesModel joke2 = new JokesModel(2, "анек2", 0);

        Mockito.when(sqlController.getUserByTelegramId(3L)).thenReturn(user);
        Mockito.when(sqlController.addUser(3L)).thenReturn(true);
        Mockito.when(sqlController.getJokeById(1)).thenReturn(joke1);
        Mockito.when(sqlController.getJokeById(2)).thenReturn(joke2);

        Mockito.when(sqlController.getNumberOfJokes()).thenReturn(2);
        Mockito.when(sqlController.IsSeenJoke(3L, 1)).thenReturn(true);
        Mockito.when(sqlController.getRandomJoke()).thenReturn(joke1);
        Mockito.when(sqlController.IsSeenJoke(3L, 2)).thenReturn(true);
        Mockito.when(sqlController.getRandomJoke()).thenReturn(joke2);


        Assert.assertEquals( "Шутки кончились...\nПодожди пока появятся новые или предложи свои!", logic.think("нужен анекдот", 3L).getAnswer());
    }

    /**
     * Вывод списка лучших анекдотов (топN)
     * @throws SQLException
     * @throws IOException
     */
    @Test
    public void thinkTest_getBestJokeFromDataBase() throws SQLException, IOException{
        SqlController sqlController = Mockito.mock(SqlController.class);
        Logic logic = new Logic(sqlController);
        UserModel user = new UserModel(1,3L, 0, null);
        JokesModel joke1 = new JokesModel(1, "анек1", 0);
        JokesModel joke2 = new JokesModel(2, "анек2", 1);
        JokesModel joke3 = new JokesModel(3, "анек3", 2);
        JokesModel joke4 = new JokesModel(4, "анек4", 3);

        Mockito.when(sqlController.getUserByTelegramId(3L)).thenReturn(user);
        Mockito.when(sqlController.addUser(3L)).thenReturn(true);
        Assert.assertEquals("Введи количество шуток", logic.think("/gettop", 3L).getAnswer());
        user.State = 3;
        Mockito.when(sqlController.getBestJokes(2)).thenReturn(joke4 + "\n" + joke3 + "\n");
        Assert.assertEquals(joke4 + "\n" + joke3 + "\n", logic.think("2", 3L).getAnswer());
    }

    /**
     * Пользователь ставит оценку пользователю
     * @throws SQLException
     * @throws IOException
     */
    @Test
    public void thinkTest_changeRate() throws SQLException, IOException{
        SqlController sqlController = Mockito.mock(SqlController.class);
        Logic logic = new Logic(sqlController);
        UserModel user = new UserModel(1,3L, 2, null);
        JokesModel joke1 = new JokesModel(1, "анек1", 0);
        user.PrevJoke = 1;

        Mockito.when(sqlController.getUserByTelegramId(3L)).thenReturn(user);
        Mockito.when(sqlController.addUser(3L)).thenReturn(true);

        logic.think(EmojiParser.parseToUnicode("\uD83D\uDC4D"), 3L).getAnswer();
        Mockito.doNothing().when(sqlController).changeRate(1, true);
        Assert.assertEquals("Спасибо за оценку!", logic.think(EmojiParser.parseToUnicode("\uD83D\uDC4D"), 3L).getAnswer());
    }

    /**
     * Проверка анекдот по теме (анекдот про... и высвечивается анекдот про...)
     * @throws SQLException
     * @throws IOException
     */
    @Test
    public void thinkTest_jokeAbout() throws SQLException, IOException{
        SqlController sqlController = Mockito.mock(SqlController.class);
        WebSearch webSearch = Mockito.mock(WebSearch.class);
        HtmlGetter htmlGetter = Mockito.mock(HtmlGetter.class);
        HtmlParser htmlParser = Mockito.mock(HtmlParser.class);



        UserModel user = new UserModel(1,3L, 4, null);

        String mocked_str = "Mocked";

        List<String> mocked_list = List.of(mocked_str);

        Mockito.when(webSearch.find(Mockito.anyString())).thenReturn(mocked_list);
        Mockito.when(htmlGetter.getHtml(Mockito.anyString())).thenReturn(mocked_str);
        Mockito.when(htmlParser.parseHtml(Mockito.anyString())).thenReturn(mocked_str);



        Mockito.when(sqlController.getUserByTelegramId(3L)).thenReturn(user);
        Mockito.when(sqlController.addUser(3L)).thenReturn(true);
        Mockito.when(sqlController.addJoke(Mockito.anyString())).thenReturn(true);


        Logic logic = new Logic(null, sqlController, webSearch, htmlGetter, htmlParser);

        Assert.assertEquals(mocked_str,logic.think("анекдот про mocked",user.Telegram_id).getAnswer());
    }

}