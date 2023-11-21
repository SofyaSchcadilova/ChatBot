package ru.anekdots.logic;

import org.htmlunit.WebClient;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.TestName;
import org.mockito.MockedConstruction;
import org.mockito.Mockito;
import ru.anekdots.databasecontroller.SqlController;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WebSearchTest {


    @Rule
    public TestName testName = new TestName();
    WebClient webClient;
    String inp;
    @Before
    public void beforeClass() {
        System.out.println("Starting test " + testName.getMethodName());
         webClient = Mockito.mock(WebClient.class);
        MockedConstruction<WebClient> mockedConstruction = Mockito.mockConstruction(WebClient.class);
        Mockito.doNothing().when(webClient).getOptions().setJavaScriptEnabled(true);
        Mockito.doNothing().when(webClient).getOptions().setThrowExceptionOnScriptError(false);
        Mockito.doNothing().when(webClient).waitForBackgroundJavaScript(3000);
    }
    String answer = "https://www.anekdot.ru/id/-12412\n" +
            "rt.ru/id/4213\n" +
            "https://www.bruh.ru/id/-12412";
    String url = "https://www.google.com/search?q=anekdot.ru+";
    @After
    public void afterClass() { System.out.println("Test finished " + testName.getMethodName());
    }


    /*
        WebClient webClient = new WebClient(BrowserVersion.EDGE);
        webClient.getOptions().setJavaScriptEnabled(true); // Включить js для корректного поиска
        webClient.getOptions().setThrowExceptionOnScriptError(false); // Проигнорировать проблемы с js
        webClient.waitForBackgroundJavaScript(3000); // подождать прогрузки
        HtmlPage page = webClient.getPage(url+URLEncoder.encode(request,"UTF-8"));

    */
    @Test
    void TestFindBannedChars() throws IOException {

        inp = "анекдот про бабку!@#$%";
        Mockito.when(webClient.getPage(url + URLEncoder.encode(inp,"UTF-8"))).thenReturn(answer);


        WebSearch webSearch = new WebSearch(webClient);

        Assert.assertEquals(null,webSearch.find(inp));
    }

    @Test
    void TestFindFound() throws IOException {

        inp = "анекдот про бабку!";
        Mockito.when(webClient.getPage(url + URLEncoder.encode(inp,"UTF-8"))).thenReturn(answer);


        WebSearch webSearch = new WebSearch(webClient);
        ArrayList<String> out = webSearch.find(inp);
        ArrayList<String> exp = List.of("https://www.anekdot.ru/id/-12412");
        Assert.assertEquals(1, out.size());
        String temp1, temp2;


        Assert.assertEquals(null,webSearch.find(inp));
    }
}