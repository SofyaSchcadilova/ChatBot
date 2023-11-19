package ru.anekdots.logic;

import org.htmlunit.BrowserVersion;
import org.htmlunit.WebClient;
import org.htmlunit.html.DomElement;
import org.htmlunit.html.DomNodeList;
import org.htmlunit.html.HtmlPage;

import java.io.IOException;

public class HtmlGetter {



    public void test() throws IOException {
        String url = "https://www.google.com/search?q=anekdot.ru+%D0%B0%D0%BD%D0%B5%D0%BA%D0%B4%D0%BE%D1%82+%D0%BF%D1%80%D0%BE+%D0%B1%D0%B0%D0%B1%D0%BA%D1%83";

        WebClient webClient = new WebClient(BrowserVersion.CHROME);
        webClient.getOptions().setJavaScriptEnabled(true); // enable javascript
        webClient.getOptions().setThrowExceptionOnScriptError(false); //even if there is error in js continue
        webClient.waitForBackgroundJavaScript(2500); // important! wait until javascript finishes rendering
        HtmlPage page = webClient.getPage(url);

        DomNodeList<DomElement> ans = page.getElementsByTagName("a");

        for (DomElement el : ans) {
            System.out.println(el.getAttribute("href"));
        }
    }
}
