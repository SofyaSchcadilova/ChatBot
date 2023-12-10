package ru.anekdots.logic;


public class HtmlParser {
    /**
     * Парсинг html страницы
     */
    public String parseHtml(String page){

        StringBuilder text = new StringBuilder();
        boolean tagForImage = false;
        for (int i = 0; i < page.length() - 11; i++){
            if ((page.charAt(i) == 'c') && (page.charAt(i + 1) == 'l') && (page.charAt(i + 2) == 'a') && (page.charAt(i + 3) == 's') && (page.charAt(i + 4) == 's') && (page.charAt(i + 5) == '=') && (page.charAt(i + 6) == '"') && (page.charAt(i + 7) == 't') && (page.charAt(i + 8) == 'e') && (page.charAt(i + 9) == 'x') && (page.charAt(i + 10) == 't') && (page.charAt(i + 11) == '"')) {
                for (int j = i + 13; j < page.length(); j++) {
                    if (!tagForImage) {
                        if ((page.charAt(j) == '<') && (page.charAt(j + 1) == '/') && (page.charAt(j + 2) == 'd')) {
                            return text.toString();
                        } else if ((page.charAt(j) == '<') && (page.charAt(j + 1) == 'b') && (page.charAt(j + 2) == 'r')) {
                            j += 6;
                        } else if ((page.charAt(j) == '<') && (page.charAt(j + 1) == 'i') && (page.charAt(j + 2) == 'm')) {
                            tagForImage = true;
                        } else {
                            text.append(page.charAt(j));
                        }
                    } else {
                        if (page.charAt(j) == '>'){
                            tagForImage = false;
                        }
                    }
                }
            }
        }

        return text.toString();
    }
}
