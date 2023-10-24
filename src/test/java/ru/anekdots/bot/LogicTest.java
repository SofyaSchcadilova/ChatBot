package ru.anekdots.bot;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.anekdots.databasecontroller.SqlControler;

import java.sql.SQLException;

/**
 * Тест класса логики
 */

@ExtendWith(MockitoExtension.class)
public class LogicTest {
    @Mock
    SqlControler sqlControler = new SqlControler();

    public LogicTest() throws SQLException, ClassNotFoundException {
    }


    @Before
    public void beforeClass() {
        System.out.println("Starting testing");
    }

    @After
    public void afterClass() {
        System.out.println("Tests finished");
    }
    @Test
    public void thinkTest() throws SQLException, ClassNotFoundException {
        Logic logic = new Logic();
        String joke1 = "смешной анек";
        String joke2 = "не смешной анек";

        logic.think("Предложить анекдот", 5L);
        Assert.assertEquals("Анекдот добавлен!", logic.think(joke1, 5L));
        logic.think("Предложить анекдот", 3L);
        Assert.assertEquals("Такой анекдот уже есть!", logic.think(joke1, 3L));

        logic.think("Предложить анекдот", 5L);
        Assert.assertEquals("Анекдот добавлен!", logic.think(joke2, 5L));
        Assert.assertEquals( joke1 + "\n" + joke2 +"\n", logic.think("/getall", 5L));
    }
}