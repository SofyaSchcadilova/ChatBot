package ru.anekdots.logic;

import javassist.expr.NewArray;
import org.htmlunit.BrowserVersion;
import org.htmlunit.WebClient;
import org.htmlunit.html.DomElement;
import org.htmlunit.html.DomNodeList;
import org.htmlunit.html.HtmlPage;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
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

    private final String banned_chars = "[~#@*+%{}<>\\[\\]|\"\\_^]";

    public WebSearch( ){
        this.url = "https://www.google.com/search?q=anekdot.ru+";
    }



    private boolean isContainBannedChars(String request) {
        Pattern pattern = Pattern.compile(banned_chars);
        Matcher matcher = pattern.matcher(request);
        return matcher.find();
    }


    /**
     * Поиск шуток на сайте anekdot.ru
     * @param request какая шутка
     * @return Список ссылок на эти шутки <br><br> Если не нашлось, то возвращается пустой список <br> Если запрос содержит "плохие символы", то возвращается null
     *
     * @throws IOException
     */
    public List<String> find(String request) throws IOException {

        if (isContainBannedChars(request)){
            return null;
        }



        List<String> ans = new ArrayList<String>();

        WebClient webClient = new WebClient(BrowserVersion.EDGE);
        webClient.getOptions().setJavaScriptEnabled(true); // Включить js для корректного поиска
        webClient.getOptions().setThrowExceptionOnScriptError(false); // Проигнорировать проблемы с js
        webClient.waitForBackgroundJavaScript(3000); // подождать прогрузки
        HtmlPage page = webClient.getPage(url+URLEncoder.encode(request,"UTF-8"));



        DomNodeList<DomElement> elements = page.getElementsByTagName("a");



        for (DomElement el : elements) {
            Pattern pattern = Pattern.compile("https:\\/\\/www\\.anekdot\\.ru\\/id\\/-?\\d+");
            Matcher matcher = pattern.matcher(el.getAttribute("href"));
            if (matcher.find()){
                ans.add(el.getAttribute("href").substring(matcher.start(),matcher.end()));
            }
        }
        return ans;
    }
}
