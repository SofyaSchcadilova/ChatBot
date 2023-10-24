package ru.anekdots.bot;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.anekdots.databasecontroller.SqlControler;

import java.sql.SQLException;

/**
 * Тест класса логики
 */

@ExtendWith(MockitoExtension.class)
public class LogicTest {


    public LogicTest() throws SQLException, ClassNotFoundException {
    }

    @Before
    public void beforeClass() {
        System.out.println("Starting testing");
    }

    @After
    public void afterClass() {        System.out.println("Test finished");
    }
    @Test
    public void thinkTest_FirstAttemptAddJoke() throws SQLException {
        String joke1 = "Joke1";
        SqlControler sqlControler = Mockito.mock(SqlControler.class);

        Mockito.when(sqlControler.addJoke(joke1)).thenReturn(true);
        Logic logic = new Logic(sqlControler);

        logic.think("Предложить анекдот", 5L);
        Assert.assertEquals("Анекдот добавлен!", logic.think(joke1, 5L));
    }
    @Test
    public void thinkTest_SecondAttemptAddJoke() throws SQLException {
        String joke1 = "Joke1";
        SqlControler sqlControler = Mockito.mock(SqlControler.class);

        Mockito.when(sqlControler.addJoke(joke1)).thenReturn(false);
        Logic logic = new Logic(sqlControler);
        // Повторно отправили ту же самую шутку
        logic.think("Предложить анекдот", 3L);
        Assert.assertEquals("Такой анекдот уже есть!", logic.think(joke1, 3L));
    }
    @Test
    public void thinkTestgetAll() throws SQLException {
        String joke1 = "смешной анек";
        String joke2 = "не смешной анек";
        SqlControler sqlControler = Mockito.mock(SqlControler.class);
        Logic logic = new Logic(sqlControler);
        Mockito.when(sqlControler.getAllJokes()).thenReturn(joke1+"\n"+joke2 +"\n");

        Assert.assertEquals( joke1 + "\n" + joke2 +"\n", logic.think("/getall", 5L));
    }
}