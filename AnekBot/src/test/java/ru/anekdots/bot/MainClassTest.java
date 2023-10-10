package ru.anekdots.bot;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;
import ru.anekdots.Main;
import ru.anekdots.resourses.answers;

public class MainClassTest {
    @Before
    public void beforeClass() {
        System.out.println("Starting testing");
    }
    @After
    public void afterClass() {
        System.out.println("Tests finished");
    }
    @Test
    public void thinkTest() {
        Assert.assertEquals("Вы написали: Test1 default case", MainClass.think("Test1 default case"));
        Assert.assertEquals(answers._start,MainClass.think("/start"));
        Assert.assertEquals(answers._help,MainClass.think("/help"));
    }

}