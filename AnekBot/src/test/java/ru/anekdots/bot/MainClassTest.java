package ru.anekdots.bot;

import org.junit.AfterClass;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.testng.annotations.BeforeClass;
import ru.anekdots.Main;
import ru.anekdots.resourses.answers;

import static org.junit.jupiter.api.Assertions.*;
class MainClassTest {
    @BeforeAll
    public static void beforeClass() {
        System.out.println("Starting testing");
    }
    @AfterAll
    public  static void afterClass() {
        System.out.println("Tests finished");
    }
    @org.junit.jupiter.api.Test
    void thinkTest() {
        assertEquals("Вы написали: Test1 default case", MainClass.think("Test1 default case"));
        assertEquals(answers._start,MainClass.think("/start"));
        assertEquals(answers._help,MainClass.think("/help"));
    }

}