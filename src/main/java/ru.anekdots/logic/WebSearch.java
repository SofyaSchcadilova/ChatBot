package ru.anekdots.logic;

import org.htmlunit.BrowserVersion;
import org.htmlunit.WebClient;
import org.htmlunit.html.DomElement;
import org.htmlunit.html.HtmlPage;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Ищет шутки через google.com <br><br> на сайте anekdot.ru
 *
 * Поиск в гугле обусловлен тем, что реализация семантического поиска чересчур сложна.
 */
public class WebSearch {
    String url; // ?????
    WebClient webClient = null;

    public WebSearch(WebClient webClient){
        this.webClient = webClient;
    }
    private final String banned_chars = "[~#@*+%{}<>\\[\\]|\"\\_^]+";

    public WebSearch( ){
        this.url = "https://www.google.com/search?q=anekdot.ru+";
    }



    public boolean isContainBannedChars(String request) {
        Pattern pattern = Pattern.compile(banned_chars);
        Matcher matcher = pattern.matcher(request);

        return matcher.find();
    }


    /**
     * Получить список elements из html страницы
     */
    public List<String> getRawLinks(String request ) throws IOException {

        if (webClient == null) {
            webClient = new WebClient(BrowserVersion.EDGE);
        }
        webClient.getOptions().setJavaScriptEnabled(true); // Включить js для корректного поиска
        webClient.getOptions().setThrowExceptionOnScriptError(false); // Проигнорировать проблемы с js
        webClient.waitForBackgroundJavaScript(3000); // подождать прогрузки
        HtmlPage page = webClient.getPage(url + URLEncoder.encode(request, "UTF-8"));


        List<String> ans = new ArrayList<String>();
        Iterable<DomElement> dom = page.getElementsByTagName("a");
        for (DomElement el : dom) {
            ans.add(el.getAttribute("href"));
        }

        return ans;



    }


    /**
     * Поиск шуток на сайте anekdot.ru
     * @param request какая шутка
     * @return Список ссылок на эти шутки <br><br> Если не нашлось, то возвращается пустой список <br> Если запрос содержить "плохие символы", то возвращается null
     *
     * @throws IOException
     */
    public List<String> find(String request) throws IOException {

        if (isContainBannedChars(request)) {
            return null;
        }
        List<String> ans = new ArrayList<String>();


        List<String> list = getRawLinks(request);

        for (String el : list) {
            Pattern pattern = Pattern.compile("https:\\/\\/www\\.anekdot\\.ru\\/id\\/-?\\d+");
            Matcher matcher = pattern.matcher(el);
            if (matcher.find()){
                ans.add(el.substring(matcher.start(),matcher.end()));
            }
        }
        return ans;
    }

}
