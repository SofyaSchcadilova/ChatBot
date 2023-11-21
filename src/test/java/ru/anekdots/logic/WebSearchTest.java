package ru.anekdots.logic;

import org.htmlunit.html.DomElement;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.TestName;
import org.mockito.Mockito;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

class WebSearchTest {


    @Rule
    public TestName testName = new TestName();
    String inp;


    @Before
    public void beforeClass() {
        System.out.println("Starting test " + testName.getMethodName());
    }
    List<String> answer = List.of("https://www.anekdot.ru/id/-12412",
            "rt.ru/id/4213",
            "https://www.bruh.ru/id/-12412");

    String url = "https://www.google.com/search?q=anekdot.ru+";
    @After
    public void afterClass() { System.out.println("Test finished " + testName.getMethodName());
    }

    /**
     * Тест на наличие плохих символов
     * @throws IOException
     */
    @Test
    void TestFindBannedChars() throws IOException {

        inp = "анекдот про бабку!&*#@$@#$%";

        WebSearch webSearch = Mockito.mock(WebSearch.class);

        Mockito.when(webSearch.getRawLinks(inp)).thenReturn(answer);
        Mockito.when(webSearch.isContainBannedChars(Mockito.anyString())).thenCallRealMethod();
        Mockito.when(webSearch.find(Mockito.anyString())).thenCallRealMethod();

        Assert.assertEquals(null, webSearch.find(inp));
    }

    /**
     * Основной тест
     * @throws IOException
     */
    @Test
    void TestFindFound() throws IOException {

        inp = "анекдот про бабку";

        WebSearch webSearch = Mockito.mock(WebSearch.class);
        Mockito.when(webSearch.getRawLinks(inp)).thenReturn(answer);
        Mockito.when(webSearch.isContainBannedChars(Mockito.anyString())).thenCallRealMethod();
        Mockito.when(webSearch.find(Mockito.anyString())).thenCallRealMethod();

        ArrayList<String> out = new ArrayList<String>(webSearch.find(inp));
        Assert.assertEquals(1, out.size());


        Assert.assertEquals(answer.get(0),out.get(0));
    }
}