package ru.anekdots.bot;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

import java.sql.SQLException;

/**
 * Тест класса логики
 */
public class LogicTest {
    @Before
    public void beforeClass() {
        System.out.println("Starting testing");
    }

    @After
    public void afterClass() {
        System.out.println("Tests finished");
    }

    /*@Test
    public void thinkTest() throws SQLException {
        Logic.think("Предложить анекдот", 5L);
        Assert.assertEquals("Анекдот добавлен!", Logic.think("смешной анек", 5L));
        Logic.think("Предложить анекдот", 3L);
        Assert.assertEquals("Такой анекдот уже есть!", Logic.think("смешной анек", 3L));
        Logic.think("Предложить анекдот", 5L);
        Logic.think("колобок повесился:(", 5L);
        Assert.assertEquals("смешной анек\nколобок повесился:(", Logic.think("/getAll", 3L));
    }*/

}