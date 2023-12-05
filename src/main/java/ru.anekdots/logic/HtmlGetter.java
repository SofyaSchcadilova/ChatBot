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
        webClient.waitForBackgroundJavaScript(1000);
        HtmlPage page = webClient.getPage(url);
        webClient.close();
        return page.asXml().replaceFirst("<\\?xml version=\"1.0\" encoding=\"(.+)\"\\?>", "<!DOCTYPE html>");
    }

}
