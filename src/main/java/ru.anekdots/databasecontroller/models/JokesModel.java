package ru.anekdots.databasecontroller.models;

import java.util.Objects;

/**
 *  Модель шуток
 */
public class JokesModel extends BaseModel{

    public String JokeText;

    /**
     * Сумма очков
     */
    public int rate;


    /**
     * Айди пользователя в базе данных, который добавил эту шутку
     */
    public int user_id;

    public JokesModel(int id){
        this(id,"",0,-1);
    }

    public JokesModel(String text, int rate){
        this(-1,text,rate,-1);
    }
    public JokesModel(int id, String text, int rate){
        this(id,text,rate,-1);
    }
    public JokesModel(int id, String text, int rate, int user_id){
        super(id);
        this.rate = rate;
        JokeText = text;
        this.user_id = user_id;
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj) return true;
        if (obj == null || obj.getClass() != getClass()) return false;
        JokesModel othJoke = (JokesModel) obj;
        return id == othJoke.id;
    }


    @Override
    public int hashCode(){
        return rate;
    }


}
