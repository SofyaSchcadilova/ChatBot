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

    public int User_rating;

    /**
     * 0 - свободное состояние
     * 1 - ожидание отправки сообщения
     * 2 - ожидание оценки(необязательно состояние)
     * 3 - ожидание количества шуток n
     * 4 - ожидание темы анекдота
     */
    public int State;

    public int count_of_jokes;


    /**
     * айди прошлой шутки
     */
    public int PrevJoke;
    /**
     * Время в сек от начала дня, когда отправлять шутку пользователю
     */
    public int Time;
    public UserModel(int id){
        this(id,-1, -1, null,-1,-1,-1,0);
    }

    public UserModel(int id, long Telegram_id){
        this(id,Telegram_id, -1, null,-1,-1,-1,0);
    }

    public UserModel(int id, long Telegram_id,int state, ArrayList<Boolean> seen){
        this(id,Telegram_id,state,seen,-1, -1, -1,0);
    }

    public UserModel(int id, long Telegram_id,int state, ArrayList<Boolean> seen, int PrevJoke, int Time){
        this(id,Telegram_id,state,seen,PrevJoke, Time, -1,0);
    }

    public UserModel(int id, long Telegram_id,int state, ArrayList<Boolean> seen, int PrevJoke, int Time, int User_rating, int count_of_jokes){
        super(id);
        this.Telegram_id = Telegram_id;
        this.SeenJokes = seen;
        this.State = state;
        this.PrevJoke = PrevJoke;
        this.Time = Time;
        this.User_rating = User_rating;
        this.count_of_jokes = count_of_jokes;
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
