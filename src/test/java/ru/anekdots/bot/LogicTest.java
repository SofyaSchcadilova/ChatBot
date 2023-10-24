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
        logic.think("Предложить анекдот", 5L);
        Assert.assertEquals("Анекдот добавлен!", logic.think("новый анек", 5L));
        logic.think("Предложить анекдот", 3L);
        Assert.assertEquals("Такой анекдот уже есть!", logic.think("Пошёл как-то мужик в лес", 3L));
    }
}