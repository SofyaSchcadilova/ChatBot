package ru.anekdots.bot;

import org.junit.*;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.rules.TestName;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.anekdots.databasecontroller.SqlController;
import ru.anekdots.databasecontroller.models.JokesModel;
import ru.anekdots.databasecontroller.models.UserModel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Random;

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

        Mockito.when(sqlController.getUserByTelegramId(3L)).thenReturn(user);
        Mockito.when(sqlController.addJoke(joke1)).thenReturn(true);
        Mockito.when(sqlController.addUser(3L)).thenReturn(true);

        Logic logic = new Logic(sqlController);

        logic.think("Предложить анекдот", 3L);
        Assert.assertEquals("Анекдот добавлен!", logic.think(joke1, 3L));
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
        Assert.assertEquals("Такой анекдот уже есть!", logic.think(joke1, 3L));
    }
    @Test
    public void thinkTestgetAll() throws SQLException, IOException {
        String joke1 = "смешной анек";
        String joke2 = "не смешной анек";
        SqlController sqlController = Mockito.mock(SqlController.class);
        Logic logic = new Logic(sqlController);

        UserModel user = new UserModel(1,3L, 0, null);

        Mockito.when(sqlController.getUserByTelegramId(3L)).thenReturn(user);
        Mockito.when(sqlController.addUser(3L)).thenReturn(true);
        Mockito.when(sqlController.getAllJokes()).thenReturn(joke1+"\n"+joke2 +"\n");

        Assert.assertEquals( joke1 + "\n" + joke2 +"\n", logic.think("/getall", 3L));
    }
    @Test
    public void thinkTest_getRandomJokeFromDataBase() throws SQLException, IOException {
        SqlController sqlController = Mockito.mock(SqlController.class);
        Logic logic = new Logic(sqlController);
        UserModel user = new UserModel(1,3L, 0, null);
        JokesModel joke1 = new JokesModel(1, "анек1", 0);

        Mockito.when(sqlController.getUserByTelegramId(3L)).thenReturn(user);
        Mockito.when(sqlController.addUser(3L)).thenReturn(true);

        Mockito.when(sqlController.getNumberOfJokes()).thenReturn(2);
        Mockito.when(sqlController.IsSeenJoke(3L, 1)).thenReturn(false);
        Mockito.when(sqlController.getRandomJoke()).thenReturn(joke1);

        Assert.assertEquals( "анек1", logic.think("анекдот", 3L));
    }
    @Test
    public void thinkTest_getRandomJokeFromDataBaseAllJokesHaveBeenSent() throws SQLException, IOException {
        SqlController sqlController = Mockito.mock(SqlController.class);
        Logic logic = new Logic(sqlController);
        UserModel user = new UserModel(1,3L, 0, null);
        JokesModel joke1 = new JokesModel(1, "анек1", 0);
        JokesModel joke2 = new JokesModel(2, "анек2", 0);

        Mockito.when(sqlController.getUserByTelegramId(3L)).thenReturn(user);
        Mockito.when(sqlController.addUser(3L)).thenReturn(true);

        Mockito.when(sqlController.getNumberOfJokes()).thenReturn(2);
        Mockito.when(sqlController.IsSeenJoke(3L, 1)).thenReturn(true);
        Mockito.when(sqlController.getRandomJoke()).thenReturn(joke1);
        Mockito.when(sqlController.IsSeenJoke(3L, 2)).thenReturn(true);
        Mockito.when(sqlController.getRandomJoke()).thenReturn(joke2);
        Assert.assertEquals( "Анекдоты закончились((\nПодожди пока появятся новые или предложи свой!", logic.think("анекдот", 3L));
    }
}