package ru.anekdots.logic;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import ru.anekdots.bot.ReplyKeyboardMaker;

/**
 * Класс ответов бота, содержащих определенные наборы кнопок
 */

public class LogicAnswer {

    private String answer_;
    private Object keyboardType_;
    public LogicAnswer(String answer, Object keyboardType){
        answer_ = answer;

        if (keyboardType == "evaluationKeyboard"){
            ReplyKeyboardMaker replyKeyboardMaker = new ReplyKeyboardMaker();
            keyboardType_ = replyKeyboardMaker.getEvaluationKeyboard();
        }
        else if (keyboardType == "menuKeyboard"){
            ReplyKeyboardMaker replyKeyboardMaker = new ReplyKeyboardMaker();
            keyboardType_ = replyKeyboardMaker.getMainMenuKeyboard();
        }
        else
            keyboardType_ = null;
    }
    public String getAnswer(){
        return answer_;
    }
    public Object getKeyboardType(){
        return keyboardType_;
    }
    public void setAnswer(String answer) {
        answer_ = answer;
    }
    public void setKeyboardType(Object keyboardType){
        keyboardType_ = keyboardType;
    }

}


