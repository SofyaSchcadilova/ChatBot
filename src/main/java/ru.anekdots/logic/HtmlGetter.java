package ru.anekdots.logic;

import org.htmlunit.BrowserVersion;
import org.htmlunit.WebClient;
import org.htmlunit.html.DomElement;
import org.htmlunit.html.DomNodeList;
import org.htmlunit.html.HtmlPage;

import java.io.IOException;

public class HtmlGetter {

    public String getHtml(String url) throws IOException {

        WebClient webClient = new WebClient(BrowserVersion.EDGE);
        webClient.getOptions().setJavaScriptEnabled(false); // Выключить js
        webClient.waitForBackgroundJavaScript(1000); // important! wait until javascript finishes rendering
        HtmlPage page = webClient.getPage(url);

        String xPath = "/html/body/div[2]/div[4]/div[1]/div[1]/div[1]";

        DomElement element = page.getFirstByXPath(xPath);
        String text = element.getTextContent();

        return text;
    }

}
