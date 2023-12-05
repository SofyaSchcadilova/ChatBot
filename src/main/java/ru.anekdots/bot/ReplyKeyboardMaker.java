package ru.anekdots.bot;

import com.vdurmont.emoji.EmojiParser;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

/**
 * Методы класса реализуют наборы кнопок
 */
public class ReplyKeyboardMaker {
    /**
     * @return реплай клавиатура с кнопками основного меню
     */
    public ReplyKeyboardMarkup getMainMenuKeyboard(){
        KeyboardRow row1 = new KeyboardRow();
        row1.add("нужен анекдот");
        row1.add("анекдот по теме");
        row1.add("предложить анекдот");

        KeyboardRow row2 = new KeyboardRow();
        row2.add("время анекдота");
        row2.add("все анекдоты");
        row2.add("лучшие анекдоты");

        KeyboardRow row3 = new KeyboardRow();
        row3.add("статистика");
        row3.add("топ шутников");
        row3.add("нужна помощь");

        List<KeyboardRow> keyboard = new ArrayList<>();
        keyboard.add(row1);
        keyboard.add(row2);
        keyboard.add(row3);

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setKeyboard(keyboard);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);

        return replyKeyboardMarkup;
    }

    /**
     *
     * @return реплай клавиатура с кнопками оценки
     */
    public ReplyKeyboardMarkup getEvaluationKeyboard(){

        KeyboardRow row = new KeyboardRow();
        row.add(EmojiParser.parseToUnicode("\uD83D\uDC4D"));
        row.add(EmojiParser.parseToUnicode("\uD83D\uDC4E"));

        List<KeyboardRow> keyboard = new ArrayList<>();
        keyboard.add(row);

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setKeyboard(keyboard);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);

        return replyKeyboardMarkup;
    }
}
