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
    @Test
    public void thinkTest() throws SQLException, ClassNotFoundException {

        Assert.assertEquals("Вы написали: Test default case", Logic.think("Test default case"));

    }

}