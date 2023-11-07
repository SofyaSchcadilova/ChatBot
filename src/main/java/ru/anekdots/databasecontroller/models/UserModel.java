package ru.anekdots.databasecontroller.models;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Модель пользоваетеля
 */
public class UserModel extends BaseModel{

    public long Telegram_id;

    /**
     * Не использовать напрямую
     * Просмотренные шутки
     * Хранится как маска, где i-ая позиция бита это просмотренная шутка с айди i
     * Не использовать напрямую
     */
    public ArrayList<Boolean> SeenJokes;


    /**
     * 0 - свободное состояние
     * 1 - ожидание отправки сообщения
     * 2 - ожидание оценки(необязательно состояние)
     * 3 - ожидание количества шуток n
     */
    public int State;



    /**
     * айди прошлой шутки
     */
    public int PrevJoke;
    /**
     * Время в сек от начала дня, когда отправлять шутку пользователю
     */
    public int Time;
    public UserModel(int id){
        super(id);
    }

    public UserModel(int id, long Telegram_id){
        super(id);
        this.Telegram_id = Telegram_id;
    }

    public UserModel(int id, long Telegram_id,int state, ArrayList<Boolean> seen){
        super(id);
        this.Telegram_id = Telegram_id;
        this.SeenJokes = seen;
        this.State = state;
    }

    public UserModel(int id, long Telegram_id,int state, ArrayList<Boolean> seen, int PrevJoke, int Time){
        super(id);
        this.Telegram_id = Telegram_id;
        this.SeenJokes = seen;
        this.State = state;
        this.PrevJoke = PrevJoke;
        this.Time = Time;
    }
    @Override
    public boolean equals(Object obj){
        if (this == obj) return true;
        if (obj == null || obj.getClass() != getClass()) return false;
        UserModel UserModel = (UserModel) obj;
        return Telegram_id == UserModel.Telegram_id;
    }

    /**
     * Видел ли пользователь шутку с Id...
     * @param jokeId
     * @return
     */
    public boolean isSeenJoke(int jokeId){
        return SeenJokes.get(jokeId)==true;
    }

    @Override
    public int hashCode(){
        return Objects.hashCode(Telegram_id);
    }

}
