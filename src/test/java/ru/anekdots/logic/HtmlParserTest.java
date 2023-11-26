package ru.anekdots.logic;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.rules.TestName;

import java.io.IOException;

public class HtmlParserTest {

    /**
     * Проверка парсинга
     * @throws IOException
     */
    @Test
    void TestHtmlParsing() throws IOException {

        String htmlCode = "<!DOCTYPE html>\n" +
                "<html lang=\"ru\">\n" +
                "<head>\n" +
                "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">\n" +
                "<meta name=\"viewport\" content=\"width=device-width\">\n" +
                "<link rel=\"icon\" type=\"image/png\" href=\"/favicon-144x144.png\">\n" +
                "<title>Анекдот №587827 Мужчина, на полчаса отключивший рабочий телефон, чтобы не…</title><meta name=\"keywords\" content=\"анекдот,мужчина,полчаса,отключивший,рабочий\"><meta name=\"description\" content=\"№587827 Мужчина, на полчаса отключивший рабочий телефон, чтобы не слушать нотации жены, три дня слушал начальника.\"><link rel=\"canonical\" href=\"https://www.anekdot.ru/id/587827/\"><link rel=\"amphtml\" href=\"https://www.anekdot.ru/id/587827/?amp\"><meta name=\"title\" content=\"Анекдот №587827\"><link href=\"/skin/s_skin_2.css?394\" rel=\"stylesheet\" type=\"text/css\">\n" +
                "<link rel=\"alternate\" type=\"application/rss+xml\" title=\"Лучшие за день: анекдот, история и фраза\" href=\"/rss/export20.xml\">\n" +
                "<link rel=\"search\" type=\"application/opensearchdescription+xml\" title=\"Поиск анекдотов\" href=\"/opensearch.xml\">\n" +
                "<link rel=\"image_src\" href=\"https://www.anekdot.ru/i/logo.png\">\n" +
                "<meta property=\"og:image\" content=\"https://www.anekdot.ru/i/logo.png\">\n" +
                "<meta property=\"og:title\" content=\"Анекдот №587827\"><meta property=\"og:description\" content=\"№587827 Мужчина, на полчаса отключивший рабочий телефон, чтобы не слушать нотации жены, три дня слушал начальника.\"><meta property=\"fb:app_id\" content=\"261539093888160\">\n" +
                "<meta property=\"fb:admins\" content=\"100002345594790\"/><script src=\"/js/s_main.js?394\" async></script>\n" +
                "<script src=\"https://yandex.ru/ads/system/header-bidding.js\" async></script>\n" +
                "<script>window.yaContextCb = window.yaContextCb || []</script>\n" +
                "<script src=\"https://yandex.ru/ads/system/context.js\" async></script>\n" +
                "<script src=\"https://content.adriver.ru/AdRiverFPS.js\" async></script>\n" +
                "<script async src=\"https://cdn.skcrtxr.com/roxot-wrapper/js/roxot-manager.js?pid=33925d96-3dd7-41ce-9740-152a7d952ceb\"></script><script>\n" +
                "const adfoxBiddersMap={myTarget:\"763128\",betweenDigital:\"793538\",adriver:\"776589\",otm:\"1460822\",getintent:\"958501\",buzzoola:\"769160\",relap:\"958503\",segmento:\"1458193\",sape:\"1697231\",mediasniper:\"2297743\",mediatoday:\"2365797\",roxot:\"2723510\"};adUnits=[{code:\"adfox_head\",sizes:[[970,90]],bids:[{bidder:\"sape\",params:{placementId:821028}},{bidder:\"myTarget\",params:{placementId:244029}},{bidder:\"betweenDigital\",params:{placementId:2507076}},{bidder:\"adriver\",params:{placementId:\"20:anekdot_970x90_head\",additional:{\"ext\":{\"query\":\"custom=10=20&cid=\"+localStorage.getItem('adrcid')}}}},{bidder:\"otm\",params:{placementId:\"884\"}},{bidder:\"getintent\",params:{placementId:\"134_anekdot.ru_desktop_adfox_head_970x90\"}},{bidder:\"buzzoola\",params:{placementId:1220268}},{bidder:\"relap\",params:{placementId:\"WWhoZEWEolii_v7-\"}},{bidder:\"segmento\",params:{placementId:183}},{bidder:\"mediasniper\",params:{placementId:3451}},{bidder:\"roxot\",params:{placementId:\"50c93ebd-e3af-4658-9d03-c95050e64df4\"},sendTargetRef: true}]},{code:\"adfox_top1\",codeType:\"combo\",sizes:[[970,250],[300,250],[300,300],[970,90],[728,90]],bids:[{bidder:\"sape\",params:{placementId:864348}},{bidder:\"myTarget\",params:{placementId:1428873}},{bidder:\"betweenDigital\",params:{placementId:4718687}},{bidder:\"adriver\",params:{placementId:\"20:anekdot_300x250_top1\",additional:{ext:{query:\"custom=10=20&cid=\"+localStorage.getItem('adrcid')}}}},{bidder:\"otm\",params:{placementId:\"50048\"}},{bidder:\"getintent\",params:{placementId:\"134_anekdot.ru_desktop_adfox_top1_300x250\"}},{bidder:\"buzzoola\",params:{placementId:1220269}},{bidder:\"relap\",params:{placementId:\"TbJN7y_viJJof01N\"}},{bidder:\"segmento\",params:{placementId:184}},{bidder:\"mediasniper\",params:{placementId:3452}},{bidder:\"roxot\",params:{placementId:\"5fc2c31c-c996-4c02-8739-cb44109ce773\"},sendTargetRef: true}]},{code:\"adfox_mid1\",codeType:\"combo\",sizes:[[970,250],[300,250],[300,300],[970,90],[728,90]],bids:[{bidder:\"sape\",params:{placementId:864349}},{bidder:\"adriver\",params:{placementId:\"20:anekdot_300x250_top2\",additional:{ext:{query:\"custom=10=20&cid=\"+localStorage.getItem('adrcid')}}}},{bidder:\"myTarget\",params:{placementId:1428880}},{bidder:\"betweenDigital\",params:{placementId:4718688}},{bidder:\"otm\",params:{placementId:\"50049\"}},{bidder:\"getintent\",params:{placementId:\"134_anekdot.ru_desktop_adfox_mid1_300x250\"}},{bidder:\"buzzoola\",params:{placementId:1220271}},{bidder:\"relap\",params:{placementId:\"oZKSbk-O7RftcnJy\"}},{bidder:\"segmento\",params:{placementId:186}},{bidder:\"mediasniper\",params:{placementId:3453}},{bidder:\"roxot\",params:{placementId:\"357d7c81-fc4f-4ff0-a2d6-7b030328d0dd\"},sendTargetRef: true}]},{code:\"adfox_side1_160\",sizes:[[160,600]],bids:[{bidder:\"sape\",params:{placementId:821033}},{bidder:\"betweenDigital\",params:{placementId:2507394}},{bidder:\"otm\",params:{placementId:\"889\"}},{bidder:\"getintent\",params:{placementId:\"134_anekdot.ru_desktop_adfox_side1_160x600\"}},{bidder:\"relap\",params:{placementId:\"HCUlSmuqUKpQkJCQ\"}},{bidder:\"segmento\",params:{placementId:188}},{bidder:\"mediasniper\",params:{placementId:3449}},{bidder:\"roxot\",params:{placementId:\"76d0aa67-b60a-4494-9ec9-c34709aa06f8\"},sendTargetRef: true}]},{code:\"adfox_side2_160\",sizes:[[160,600]],bids:[{bidder:\"sape\",params:{placementId:821034}},{bidder:\"betweenDigital\",params:{placementId:2507392}},{bidder:\"otm\",params:{placementId:\"890\"}},{bidder:\"getintent\",params:{placementId:\"134_anekdot.ru_desktop_adfox_side2_160x600\"}},{bidder:\"relap\",params:{placementId:\"KtUqrGysfWedgLa2\"}},{bidder:\"segmento\",params:{placementId:189}},{bidder:\"mediasniper\",params:{placementId:3450}},{bidder:\"roxot\",params:{placementId:\"07ae9594-ed1a-4357-a34f-d002bcfb1fb4\"},sendTargetRef: true}]},{code:\"adfox_side1\",sizes:[[300,600],[300,250],[300,300],[300,200],[300,500],[240,400],[240,600],[160,600]],bids:[{bidder:\"sape\",params:{placementId:864344}},{bidder:\"adriver\",params:{placementId:\"20:anekdot_300x600_1\",additional:{ext:{query:\"custom=10=20&cid=\"+localStorage.getItem('adrcid')}}}},{bidder:\"myTarget\",params:{placementId:244022}},{bidder:\"betweenDigital\",params:{placementId:2505377}},{bidder:\"otm\",params:{placementId:882}},{bidder:\"buzzoola\",params:{placementId:1220266}},{bidder:\"getintent\",params:{placementId:\"134_anekdot.ru_desktop_adfox_side1_300x600\"}},{bidder:\"relap\",params:{placementId:\"WHd3ORjZxjzGhoaG\"}},{bidder:\"segmento\",params:{placementId:181}},{bidder:\"mediasniper\",params:{placementId:3447}},{bidder:\"roxot\",params:{placementId:\"2ccfa6d9-153d-4306-9130-5c6b848ddcf1\"},sendTargetRef: true}]},{code:\"adfox_side2\",sizes:[[300,600],[300,250],[300,300],[300,200],[300,500],[240,400],[240,600],[160,600]],bids:[{bidder:\"sape\",params:{placementId:864347}},{bidder:\"adriver\",params:{placementId:\"20:anekdot_300x600_2\",additional:{\"ext\":{\"query\":\"custom=10=20&cid=\"+localStorage.getItem('adrcid')}}}},{bidder:\"myTarget\",params:{placementId:244027}},{bidder:\"betweenDigital\",params:{placementId:2505752}},{bidder:\"buzzoola\",params:{placementId:1220267}},{bidder:\"otm\",params:{placementId:883}},{bidder:\"getintent\",params:{placementId:\"134_anekdot.ru_desktop_adfox_side2_300x600\"}},{bidder:\"relap\",params:{placementId:\"Av0CX59f8-kTgLCw\"}},{bidder:\"segmento\",params:{placementId:182}},{bidder:\"mediasniper\",params:{placementId:3448}},{bidder:\"mediatoday\",params:{placementId:9887}},{bidder:\"roxot\",params:{placementId:\"38a08b4a-098d-4016-89d9-8541b41a01d3\"},sendTargetRef: true}]}];var userTimeout=1200;window.YaHeaderBiddingSettings={biddersMap:adfoxBiddersMap,adUnits:adUnits,timeout:userTimeout};\n" +
                "</script></head>\n" +
                "<body>\n" +
                "<div class=\"a_abs\">\n" +
                "<div id=\"Rambler-counter\"></div>\n" +
                "<script>\n" +
                "new Image().src=\"//counter.yadro.ru/hit?r\"+\n" +
                "escape(document.referrer)+((typeof(screen)==\"undefined\")?\"\":\n" +
                "\";s\"+screen.width+\"*\"+screen.height+\"*\"+(screen.colorDepth?\n" +
                "screen.colorDepth:screen.pixelDepth))+\";u\"+escape(document.URL)+\n" +
                "\";h\"+escape(document.title.substring(0,150))+\n" +
                "\";\"+Math.random();\n" +
                "var _top100q=_top100q || [];\n" +
                "_top100q.push([\"setAccount\",\"1376\"]);\n" +
                "_top100q.push([\"trackPageviewByLogo\",document.getElementById(\"Rambler-counter\")]);\n" +
                "(function(){\n" +
                "var pa=document.createElement(\"script\");\n" +
                "pa.type=\"text/javascript\";\n" +
                "pa.async=true;\n" +
                "pa.src=(\"https:\"==document.location.protocol ? \"https:\":\"http:\")+\"//st.top100.ru/top100/top100.js\";\n" +
                "var s=document.getElementsByTagName(\"script\")[0];\n" +
                "s.parentNode.insertBefore(pa,s);\n" +
                "})();\n" +
                "</script>\n" +
                "<img src=\"//top-fwz1.mail.ru/counter?id=7;js=na\" height=\"1\" width=\"1\" alt=\"\">\n" +
                "<script>\n" +
                "(function(i,s,o,g,r,a,m){i[\"GoogleAnalyticsObject\"]=r;i[r]=i[r]||function(){\n" +
                "(i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),\n" +
                "m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)\n" +
                "})(window,document,\"script\",\"https://www.google-analytics.com/analytics.js\",\"ga\");\n" +
                "ga(\"create\",\"UA-93084919-1\",\"auto\");\n" +
                "ga(\"send\",\"pageview\");\n" +
                "</script>\n" +
                "<script>\n" +
                "(function(d, w, c){ (w[c] = w[c] || []).push(function(){try{ w.yaCounter49813411=new Ya.Metrika2({id:49813411,clickmap:true,trackLinks:true,accurateTrackBounce:true});} catch(e){}}); var n=d.getElementsByTagName(\"script\")[0],s=d.createElement(\"script\"),f=function(){n.parentNode.insertBefore(s,n);}; s.type=\"text/javascript\";s.async=true;s.src=\"https://mc.yandex.ru/metrika/tag.js\"; if(w.opera==\"[object Opera]\"){d.addEventListener(\"DOMContentLoaded\",f,false);}else{f();}})(document, window, \"yandex_metrika_callbacks2\");\n" +
                "</script>\n" +
                "<noscript><div><img src=\"https://mc.yandex.ru/watch/49813411\" style=\"position:absolute;left:-9999px;\" alt=\"\"></div></noscript>\n" +
                "</div><div class=\"wrapper desktop\"><div class=\"author nav-line\">\n" +
                "<ul id=\"topmenu\">\n" +
                "<li><a href=\"/last/anekdot/\">Анекдоты</a>\n" +
                "<ul>\n" +
                "<li><a href=\"/last/anekdot/\">основные</a></li>\n" +
                "<li><a href=\"/best/anekdot/1126/\">лучшие прошлых лет</a></li>\n" +
                "<li><a href=\"/release/anekdot/day/\">все новые за день</a></li>\n" +
                "<li><a href=\"/last/burning/\">злободневные</a></li>\n" +
                "<li><a href=\"/last/non_burning/\">без политики</a></li>\n" +
                "<li><a href=\"/last/good/\">приличные</a></li>\n" +
                "<li><a href=\"/last/anekdot_original/\">авторские</a></li>\n" +
                "</ul>\n" +
                "<ul class=\"second\">\n" +
                "<li><a href=\"/release/anekdot/week/\">лучшие недели</a></li>\n" +
                "<li><a href=\"/release/anekdot/month/\" title=\"Самые смешные анекдоты за месяц\">лучшие месяца</a></li>\n" +
                "<li><a href=\"/release/anekdot/year/\">лучшие года</a></li>\n" +
                "<li><a href=\"/author-best/years/?years=anekdot\">лучшие 1995-2023</a></li>\n" +
                "<li><a href=\"/random/anekdot/\">случайные</a></li>\n" +
                "<li><a href=\"/tags/\">по темам</a></li>\n" +
                "<li><a href=\"/ymd/ymd.html?j23\">по датам</a></li>\n" +
                "</ul>\n" +
                "</li>\n" +
                "<li><a href=\"/last/story/\">Истории</a>\n" +
                "<ul>\n" +
                "<li><a href=\"/last/story/\">основные</a></li>\n" +
                "<li><a href=\"/best/story/1126/\">лучшие прошлых лет</a></li>\n" +
                "<li><a href=\"/release/story/day/\">все новые за день</a></li>\n" +
                "<li><a href=\"/last/funny/\">юмористические</a></li>\n" +
                "<li><a href=\"/last/non_funny/\">несмешные</a></li>\n" +
                "<li><a href=\"/last/story_original/\">авторские</a></li>\n" +
                "</ul>\n" +
                "<ul class=\"second\">\n" +
                "<li><a href=\"/release/story/week/\">лучшие недели</a></li>\n" +
                "<li><a href=\"/release/story/month/\" title=\"Самые смешные истории за месяц\">лучшие месяца</a></li>\n" +
                "<li><a href=\"/release/story/year/\">лучшие года</a></li>\n" +
                "<li><a href=\"/author-best/years/?years=story\">лучшие 1995-2023</a></li>\n" +
                "<li><a href=\"/random/story/\">случайные</a></li>\n" +
                "<li><a href=\"/ymd/ymd.html?o23\">по датам</a></li>\n" +
                "</ul>\n" +
                "</li>\n" +
                "<li><a href=\"/last/mem/\">Мемы</a>\n" +
                "<ul>\n" +
                "<li><a href=\"/last/mem/\">основные</a></li>\n" +
                "<li><a href=\"/best/mem/1126/\">лучшие прошлых лет</a></li>\n" +
                "<li><a href=\"/release/mem/day/\">все новые за день</a></li>\n" +
                "<li><a href=\"/last/mem_burning/\">злободневные</a></li>\n" +
                "<li><a href=\"/last/mem_non_burning/\">без политики</a></li>\n" +
                "<li><a href=\"/last/mem_non_erotica/\">без эротики</a></li>\n" +
                "<li><a href=\"/last/mem_video/\">видео</a></li>\n" +
                "<li><a href=\"/last/mem_non_video/\">без видео</a></li>\n" +
                "</ul>\n" +
                "<ul class=\"second\">\n" +
                "<li><a href=\"/release/mem/week/\">лучшие недели</a></li>\n" +
                "<li><a href=\"/release/mem/month/\">лучшие месяца</a></li>\n" +
                "<li><a href=\"/release/mem/year/\">лучшие года</a></li>\n" +
                "<li><a href=\"/author-best/years/?years=mem\">лучшие 2019-2023</a></li>\n" +
                "<li><a href=\"/random/mem/\">случайные</a></li>\n" +
                "<li><a href=\"/ymd/ymd.html?n23\">по датам</a></li>\n" +
                "<li><a href=\"/last/mem_original/\">авторские</a></li>\n" +
                "<li></li>\n" +
                "</ul>\n" +
                "</li>\n" +
                "<li><a href=\"/last/aphorism/\">Фразы</a>\n" +
                "<ul>\n" +
                "<li><a href=\"/last/aphorism/\">основные</a></li>\n" +
                "<li><a href=\"/best/aphorism/1126/\">лучшие прошлых лет</a></li>\n" +
                "<li><a href=\"/release/aphorism/day/\">все новые за день</a></li>\n" +
                "<li><a href=\"/random/aphorism/\">случайные</a></li>\n" +
                "<li></li>\n" +
                "</ul>\n" +
                "<ul class=\"second\">\n" +
                "<li><a href=\"/release/aphorism/week/\">лучшие недели</a></li>\n" +
                "<li><a href=\"/release/aphorism/month/\">лучшие месяца</a></li>\n" +
                "<li><a href=\"/release/aphorism/year/\">лучшие года</a></li>\n" +
                "<li><a href=\"/author-best/years/?years=aphorism\">лучшие 1995-2023</a></li>\n" +
                "<li><a href=\"/ymd/ymd.html?a23\">по датам</a></li>\n" +
                "</ul>\n" +
                "</li>\n" +
                "<li class=\"i5\"><a href=\"/last/poems/\">Стишки</a>\n" +
                "<ul>\n" +
                "<li><a href=\"/last/poems/\">основные</a></li>\n" +
                "<li><a href=\"/best/poems/1126/\">лучшие прошлых лет</a></li>\n" +
                "<li><a href=\"/release/poems/day/\">все новые за день</a></li>\n" +
                "<li><a href=\"/random/poems/\">случайные</a></li>\n" +
                "<li></li>\n" +
                "</ul>\n" +
                "<ul class=\"second\">\n" +
                "<li><a href=\"/release/poems/week/\">лучшие недели</a></li>\n" +
                "<li><a href=\"/release/poems/month/\">лучшие месяца</a></li>\n" +
                "<li><a href=\"/release/poems/year/\">лучшие года</a></li>\n" +
                "<li><a href=\"/author-best/years/?years=poems\">лучшие 1995-2023</a></li>\n" +
                "<li><a href=\"/ymd/ymd.html?c23\">по датам</a></li>\n" +
                "</ul>\n" +
                "</li>\n" +
                "<li><a href=\"/last/caricatures/\">Карикатуры</a>\n" +
                "<ul>\n" +
                "<li><a href=\"/random/caricatures/\">случайные</a></li>\n" +
                "<li><a href=\"/ymd/ymd.html?e22\">по датам</a></li>\n" +
                "<li><a href=\"/author-best/years/?years=pics\">лучшие 1995-2022</a></li>\n" +
                "</ul>\n" +
                "</li>\n" +
                "<li class=\"i6\"><a href=\"/author-best/top10/\">Авторы</a>\n" +
                "<ul>\n" +
                "<li><a href=\"/static/ratings.html\">рейтинги</a></li>\n" +
                "<li><a href=\"/author-best/rating/\">авторские рейтинги</a></li>\n" +
                "<li><a href=\"/author-best/top10/\">авторские десятки</a></li>\n" +
                "<li><a href=\"/author-best/top100/\">авторские сотни</a></li>\n" +
                "<li><a href=\"/author-best/list/\">наши авторы</a></li>\n" +
                "<li><a href=\"/author-best/\">лучшее от авторов</a></li>\n" +
                "<li><a href=\"/author-best/comments/\">комментаторы</a></li>\n" +
                "<li><a href=\"/konkurs/prizes.html\">премии сайта</a></li>\n" +
                "</ul>\n" +
                "<li class=\"i6\"><a href=\"/upload/anekdot/\">Прислать</a>\n" +
                "<ul>\n" +
                "<li><a href=\"/upload/anekdot/\">анекдот</a></li>\n" +
                "<li><a href=\"/upload/story/\">историю</a></li>\n" +
                "<li><a href=\"/upload/caricatures/\">карикатуру</a></li>\n" +
                "<li><a href=\"/upload/mem/\">мем</a></li>\n" +
                "<li><a href=\"/upload/aphorism/\">фразу</a></li>\n" +
                "<li><a href=\"/upload/poems/\">стишок</a></li>\n" +
                "</ul>\n" +
                "</li>\n" +
                "<li class=\"i8\"><a href=\"/search/\">Поиск</a></li>\n" +
                "</ul>\n" +
                "</div>    <div class=\"header desktop\"><div class=\"top-line\">\n" +
                "    <div class=\"fl bold\"><a href=\"/an/an1126/jxbtracoenN231126;50.html\">Лучшее дня 26 ноября 2023</a></div>\n" +
                "    <div class=\"fr\"><span class=\"settings link\"\">Настройки</span> | <a href=\"https://gb.anekdot.ru/login/\">Войти</a> | <a href=\"https://gb.anekdot.ru/register/\">Регистрация</a></div>\n" +
                "    <a href=\"//pda.anekdot.ru/\" title=\"Анекдоты для мобильных телефонов\">Для мобильного</a> | <a href=\"/subscribe/\" title=\"Ежедневные почтовые рассылки смешных анекдотов\">Рассылки</a> | <a href=\"/static/rss.html\" title=\"Трансляции анекдотов, историй и фраз в RSS, социальные сети\">Соцсети и RSS</a> |\n" +
                "    <a href=\"https://gb.anekdot.ru/\" title=\"Обсуждение работы сайта и анекдотов\">Гостевые книги</a>\n" +
                "    </div>\n" +
                "    <div class=\"promo\">\n" +
                "    <a class=\"a-sprite logo\" title=\"Анекдоты из России - самые смешные анекдоты, истории, фразы, стишки и карикатуры.\" href=\"/\"></a>\n" +
                "    <div class=\"head-right\">\n" +
                "        <div class=\"head-text\"><span>Предупреждение: у нас есть цензура и предварительный отбор публикуемых материалов. Анекдоты здесь бывают... какие угодно. Если вам это не нравится, пожалуйста, покиньте сайт.</span> <span class=\"bold\">18+</span></div>\n" +
                "        <!--noindex--><div class=\"b-soc\">\n" +
                "            <a class=\"a-sprite b-soc-icon tg\" target=\"_blank\" rel=\"nofollow\" href=\"tg://resolve?domain=anekdot_ru_anekdot\"></a>\n" +
                "            <a class=\"a-sprite b-soc-icon vk\" target=\"_blank\" rel=\"nofollow\" href=\"/away/?id=2\"></a>\n" +
                "            <a class=\"a-sprite b-soc-icon fb\" target=\"_blank\" rel=\"nofollow\" href=\"/away/?id=3\"></a>\n" +
                "            <a class=\"a-sprite b-soc-icon tw\" target=\"_blank\" rel=\"nofollow\" href=\"/away/?id=1\"></a>\n" +
                "            <a class=\"a-sprite b-soc-icon od\" target=\"_blank\" rel=\"nofollow\" href=\"/away/?id=4\"></a>\n" +
                "        </div>\n" +
                "        <div class=\"promo-top\"><div class=\"a_mh90 fs0\">\n" +
                "\n" +
                "<div id=\"adfox_head\"></div>\n" +
                "\n" +
                "<script>\n" +
                "\n" +
                "window.yaContextCb.push(()=>{\n" +
                "\n" +
                "Ya.adfoxCode.create({ownerId: 254948,containerId: 'adfox_head',params: {pp: 'g',ps: 'cxlp',p2: 'y',puid1: ''}});\n" +
                "\n" +
                "});\n" +
                "\n" +
                "</script>\n" +
                "\n" +
                "<div id='krt-1293303'></div>\n" +
                "\n" +
                "</div></div><!--/noindex-->\n" +
                "    </div>\n" +
                "    <div class=\"cb\"></div>\n" +
                "    </div></div><div class=\"block-row\" style=\"height:20px\"></div><div class=\"content content-min\">\n" +
                "\t<div class=\"col-left col-left-margin\">\n" +
                "<div class=\"a_id_item a_mt10\" data-t=\"x\"><h1>Анекдот №587827</h1><div class=\"text\">Мужчина, на полчаса  отключивший рабочий телефон, чтобы не слушать нотации жены, три дня слушал начальника.</div><div class=\"rates\" data-id=\"587827\"><span class=\"vote p\" title=\"за 9 голосов\">+</span><span class=\"value\" title=\"всего 24 голоса\">-3<span class=\"delta\"></span></span><span class=\"vote m\" title=\"против 12 голосов\">&ndash;</span></div><div class=\"info\"><a href=\"https://gb.anekdot.ru/profile/?id=20417\">locked</a> <a class=\"user-star\" href=\"/author-best/stars/#u20417\">★★</a></div><div class=\"release\">Выпуск: <a href=\"/an/an1207/x120703;100.html#11\">анекдоты, остальные новые 03 июля 2012</a></div><div class=\"release a_mb30\">Проголосовало за – 9, против – 12</div></div>\n" +
                "<!--noindex-->\n" +
                "<div class=\"a_id_share\">\n" +
                "\t<a class=\"btn a-sprite vk\" target=\"_blank\" rel=\"nofollow\" href=\"https://vk.com/share.php?url=https%3A%2F%2Fwww.anekdot.ru%2Fid%2F587827%2F\"></a>\n" +
                "\t<a class=\"btn a-sprite fb\" target=\"_blank\" rel=\"nofollow\" href=\"https://www.facebook.com/sharer/sharer.php?src=sp&amp;u=https%3A%2F%2Fwww.anekdot.ru%2Fid%2F587827%2F\"></a>\n" +
                "\t<a class=\"btn a-sprite tw\" target=\"_blank\" rel=\"nofollow\" href=\"https://twitter.com/intent/tweet?url=https%3A%2F%2Fwww.anekdot.ru%2Fid%2F587827%2F&amp;text=%E2%84%96587827%20%D0%9C%D1%83%D0%B6%D1%87%D0%B8%D0%BD%D0%B0%2C%20%D0%BD%D0%B0%20%D0%BF%D0%BE%D0%BB%D1%87%D0%B0%D1%81%D0%B0%20%D0%BE%D1%82%D0%BA%D0%BB%D1%8E%D1%87%D0%B8%D0%B2%D1%88%D0%B8%D0%B9%20%D1%80%D0%B0%D0%B1%D0%BE%D1%87%D0%B8%D0%B9%20%D1%82%D0%B5%D0%BB%D0%B5%D1%84%D0%BE%D0%BD%2C%20%D1%87%D1%82%D0%BE%D0%B1%D1%8B%20%D0%BD%D0%B5%20%D1%81%D0%BB%D1%83%D1%88%D0%B0%D1%82%D1%8C%20%D0%BD%D0%BE%D1%82%D0%B0%D1%86%D0%B8%D0%B8%20%D0%B6%D0%B5%D0%BD%D1%8B%2C%20%D1%82%D1%80%D0%B8%20%D0%B4%D0%BD%D1%8F%20%D1%81%D0%BB%D1%83%D1%88%D0%B0%D0%BB%20%D0%BD%D0%B0%D1%87%D0%B0%D0%BB%D1%8C%D0%BD%D0%B8%D0%BA%D0%B0.\"></a>\n" +
                "\t<a class=\"btn a-sprite ok\" target=\"_blank\" rel=\"nofollow\" href=\"https://connect.ok.ru/dk?st.cmd=WidgetSharePreview&amp;st.shareUrl=https%3A%2F%2Fwww.anekdot.ru%2Fid%2F587827%2F\"></a>\n" +
                "\t<a class=\"btn a-sprite lj\" target=\"_blank\" rel=\"nofollow\" href=\"https://www.livejournal.com/update.bml?event=https%3A%2F%2Fwww.anekdot.ru%2Fid%2F587827%2F%20%E2%84%96587827%20%D0%9C%D1%83%D0%B6%D1%87%D0%B8%D0%BD%D0%B0%2C%20%D0%BD%D0%B0%20%D0%BF%D0%BE%D0%BB%D1%87%D0%B0%D1%81%D0%B0%20%D0%BE%D1%82%D0%BA%D0%BB%D1%8E%D1%87%D0%B8%D0%B2%D1%88%D0%B8%D0%B9%20%D1%80%D0%B0%D0%B1%D0%BE%D1%87%D0%B8%D0%B9%20%D1%82%D0%B5%D0%BB%D0%B5%D1%84%D0%BE%D0%BD%2C%20%D1%87%D1%82%D0%BE%D0%B1%D1%8B%20%D0%BD%D0%B5%20%D1%81%D0%BB%D1%83%D1%88%D0%B0%D1%82%D1%8C%20%D0%BD%D0%BE%D1%82%D0%B0%D1%86%D0%B8%D0%B8%20%D0%B6%D0%B5%D0%BD%D1%8B%2C%20%D1%82%D1%80%D0%B8%20%D0%B4%D0%BD%D1%8F%20%D1%81%D0%BB%D1%83%D1%88%D0%B0%D0%BB%20%D0%BD%D0%B0%D1%87%D0%B0%D0%BB%D1%8C%D0%BD%D0%B8%D0%BA%D0%B0.&amp;subject=%D0%90%D0%BD%D0%B5%D0%BA%D0%B4%D0%BE%D1%82%20%E2%84%96587827%20%D0%9C%D1%83%D0%B6%D1%87%D0%B8%D0%BD%D0%B0%2C%20%D0%BD%D0%B0%20%D0%BF%D0%BE%D0%BB%D1%87%D0%B0%D1%81%D0%B0%20%D0%BE%D1%82%D0%BA%D0%BB%D1%8E%D1%87%D0%B8%D0%B2%D1%88%D0%B8%D0%B9%20%D1%80%D0%B0%D0%B1%D0%BE%D1%87%D0%B8%D0%B9%20%D1%82%D0%B5%D0%BB%D0%B5%D1%84%D0%BE%D0%BD%2C%20%D1%87%D1%82%D0%BE%D0%B1%D1%8B%20%D0%BD%D0%B5%E2%80%A6\"></a>\n" +
                "\t<a class=\"btn a-sprite viber\" target=\"_blank\" rel=\"nofollow\" href=\"viber://forward?text=%D0%90%D0%BD%D0%B5%D0%BA%D0%B4%D0%BE%D1%82%20%E2%84%96587827%20%D0%9C%D1%83%D0%B6%D1%87%D0%B8%D0%BD%D0%B0%2C%20%D0%BD%D0%B0%20%D0%BF%D0%BE%D0%BB%D1%87%D0%B0%D1%81%D0%B0%20%D0%BE%D1%82%D0%BA%D0%BB%D1%8E%D1%87%D0%B8%D0%B2%D1%88%D0%B8%D0%B9%20%D1%80%D0%B0%D0%B1%D0%BE%D1%87%D0%B8%D0%B9%20%D1%82%D0%B5%D0%BB%D0%B5%D1%84%D0%BE%D0%BD%2C%20%D1%87%D1%82%D0%BE%D0%B1%D1%8B%20%D0%BD%D0%B5%E2%80%A6%20https%3A%2F%2Fwww.anekdot.ru%2Fid%2F587827%2F\"></a>\n" +
                "\t<a class=\"btn a-sprite tg\" target=\"_blank\" rel=\"nofollow\" href=\"https://t.me/share/url?url=https%3A%2F%2Fwww.anekdot.ru%2Fid%2F587827%2F&amp;text=%D0%90%D0%BD%D0%B5%D0%BA%D0%B4%D0%BE%D1%82%20%E2%84%96587827%20%D0%9C%D1%83%D0%B6%D1%87%D0%B8%D0%BD%D0%B0%2C%20%D0%BD%D0%B0%20%D0%BF%D0%BE%D0%BB%D1%87%D0%B0%D1%81%D0%B0%20%D0%BE%D1%82%D0%BA%D0%BB%D1%8E%D1%87%D0%B8%D0%B2%D1%88%D0%B8%D0%B9%20%D1%80%D0%B0%D0%B1%D0%BE%D1%87%D0%B8%D0%B9%20%D1%82%D0%B5%D0%BB%D0%B5%D1%84%D0%BE%D0%BD%2C%20%D1%87%D1%82%D0%BE%D0%B1%D1%8B%20%D0%BD%D0%B5%E2%80%A6\"></a>\n" +
                "\t<a class=\"btn a-sprite whatsapp\" target=\"_blank\" rel=\"nofollow\" href=\"https://api.whatsapp.com/send?text=%D0%90%D0%BD%D0%B5%D0%BA%D0%B4%D0%BE%D1%82%20%E2%84%96587827%20%D0%9C%D1%83%D0%B6%D1%87%D0%B8%D0%BD%D0%B0%2C%20%D0%BD%D0%B0%20%D0%BF%D0%BE%D0%BB%D1%87%D0%B0%D1%81%D0%B0%20%D0%BE%D1%82%D0%BA%D0%BB%D1%8E%D1%87%D0%B8%D0%B2%D1%88%D0%B8%D0%B9%20%D1%80%D0%B0%D0%B1%D0%BE%D1%87%D0%B8%D0%B9%20%D1%82%D0%B5%D0%BB%D0%B5%D1%84%D0%BE%D0%BD%2C%20%D1%87%D1%82%D0%BE%D0%B1%D1%8B%20%D0%BD%D0%B5%E2%80%A6%20https%3A%2F%2Fwww.anekdot.ru%2Fid%2F587827%2F\"></a>\n" +
                "\t<span class=\"btn a-sprite copy\" onclick=\"copyToClipboard('https://www.anekdot.ru/id/587827/');\" title=\"Скопировать ссылку в буфер\"></span>\n" +
                "</div>\n" +
                "<!--/noindex-->\n" +
                "<div class=\"votes\"><span class=\"votes-link closed\" onclick=\"return showVotes(587827);\">Статистика голосований по странам</span></div><div id=\"votes-data\" class=\"a_mb20 a_hidden\"></div><div class=\"comments-form\"><div class=\"bold a_mb30 a_mt30\">Чтобы оставить комментарии, необходимо <a href=\"https://gb.anekdot.ru/login/?return=https%3A%2F%2Fwww.anekdot.ru%2Fid%2F587827%2F\">авторизоваться</a>. За оскорбления и спам - бан.</div></div>\n" +
                "<!--noindex-->\n" +
                "<p class=\"a_mt30\">\n" +
                "<a href=\"/author-best/comments/\" style=\"color:blue\">Общий рейтинг комментаторов</a><br>\n" +
                "<a href=\"/author-best/blocks/\" style=\"color:blue\">Рейтинг стоп-листов</a>\n" +
                "</p>\n" +
                "</div><!--noindex--><div class=\"col-right\"><div class=\"block\"><div class=\"a_mh400 fs0\">\n" +
                "\n" +
                "<div id=\"adfox_side1\"></div>\n" +
                "\n" +
                "<script>\n" +
                "\n" +
                "window.yaContextCb.push(()=>{\n" +
                "\n" +
                "Ya.adfoxCode.create({ownerId:254948,containerId:'adfox_side1',params:{pp:'noj',ps:'cxlp',p2:'fvyf',puid1:''}});\n" +
                "\n" +
                "});\n" +
                "\n" +
                "</script>\n" +
                "\n" +
                "<div id='krt-1293301'></div>\n" +
                "\n" +
                "</div></div></div><!--/noindex--></div>\n" +
                "<div class=\"cb\"></div>\t<div class=\"block-row bottommenu2-background\">\n" +
                "\t\t<div class=\"bottommenu2\">\n" +
                "\t\t\t<div class=\"leftlink\"><a href=\"/\">главная</a> &bull; <a href=\"/last/anekdot/\" title=\"Свежие анекдоты\">анекдоты</a> &bull; <a href=\"/last/story/\" title=\"Новые истории\">истории</a> &bull; <a href=\"/last/caricatures/\" title=\"Смешные карикатуры\">карикатуры</a> &bull;  <a href=\"/last/mem/\" title=\"Смешные мемы\">мемы</a> &bull; <a href=\"/last/aphorism/\" title=\"Новые афоризмы и фразы\">фразы</a> &bull; <a href=\"/last/poems/\" title=\"Прикольные стишки\">стишки</a> &bull;</div><div class=\"tell\">\n" +
                "\t\t\t<div class=\"tellbutton_\"><a title=\"Расскажи текст!\" href=\"/upload/\">пришли текст!</a></div>\n" +
                "\t\t\t</div></div>\n" +
                "\t</div>\n" +
                "<div class=\"block-row bottombanner\"><div id=\"adfox_166193192576493022\"></div>\n" +
                "\n" +
                "<script>\n" +
                "\n" +
                "window.yaContextCb.push(()=>{\n" +
                "\n" +
                "Ya.adfoxCode.create({ownerId:254948,containerId:'adfox_166193192576493022',params:{pp:'nol',ps:'cxlp',p2:'y',puid1:'',puid2:'',puid3:''}})\n" +
                "\n" +
                "})\n" +
                "\n" +
                "</script></div><div class=\"block-row search\">\n" +
                "\t<form action=\"/search/\" method=\"get\">\n" +
                "\t\t<select name=\"rubrika\" class=\"rubrika\">\n" +
                "\t\t\t<option value=\"all\">искать везде</option><option selected=\"selected\" value=\"j\">анекдоты</option><option value=\"o\">истории</option><option value=\"a\">фразы</option><option value=\"c\">стишки</option>\n" +
                "\t\t</select>\n" +
                "\t\t<input type=\"text\" name=\"query\" placeholder=\"Поиск анекдотов\" class=\"query\" maxlength=\"100\">&nbsp;<input type=\"submit\" class=\"kaktam\" value=\"искать\">\n" +
                "\t</form>\n" +
                "</div>\n" +
                "<div class=\"copyright\">\n" +
                "\t<div class=\"links\"><a href=\"/static/index_adv.html\">реклама на сайте</a> | <a href=\"/static/contacts.html\">контакты</a> | <a href=\"/static/index_about.html\">о проекте</a> | <a href=\"/static/webmaster.html\">вебмастеру</a> | <a href=\"/news/\">новости</a></div>\n" +
                "\t<div class=\"fr\">&copy; 1995—2023 Анекдоты из России. Составитель <span class=\"bold\">Дима Вернер</span></div>&nbsp;</div>\n" +
                "<!--noindex--><div class=\"counters\">\n" +
                "<a href=\"https://top100.rambler.ru/navi/1376/\" target=\"_blank\" rel=\"nofollow\" class=\"a-sprite btn_top100\" title=\"Rambler's Top100\"></a>\n" +
                "<a href=\"https://top.mail.ru/jump?from=7\" target=\"_blank\" rel=\"nofollow\"><img src=\"//top-fwz1.mail.ru/counter?id=7;t=55;l=1\" height=\"31\" width=\"88\" alt=\"Рейтинг@Mail.ru\"></a>\n" +
                "<a href=\"https://www.liveinternet.ru/click\" target=\"_blank\" rel=\"nofollow\"><img src=\"//counter.yadro.ru/logo?11.6\" title=\"LiveInternet: показано число просмотров за 24 часа, посетителей за 24 часа и за сегодня\" alt=\"\" width=\"88\" height=\"31\"></a>\n" +
                "</div><!--/noindex-->\n" +
                "</div><script>\n" +
                "if(['www.'+'anekdot.'+'ru', 'pda.'+'anekdot.'+'ru', 'v3.'+'anekdot.'+'ru', 'lime.'+'anekdot.'+'ru'].indexOf(location.hostname)<0) window.location='https://'+'www.'+'anekdot.'+'ru';\n" +
                "</script><span class=\"scrollup\"><span></span></span>\n" +
                "</body>\n" +
                "</html>";

        HtmlParser htmlParser = new HtmlParser();
        Assertions.assertEquals("Мужчина, на полчаса  отключивший рабочий телефон, " +
                "чтобы не слушать нотации жены, три дня слушал начальника.",
                htmlParser.parseHtml(htmlCode));
    }
}