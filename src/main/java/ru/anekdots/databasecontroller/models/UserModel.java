package ru.anekdots.databasecontroller.models;

import java.util.Objects;

/**
 * Модель пользоваетеля
 */
public class UserModel extends BaseModel{

    int Telegram_id;
    int Discord_id;
    String Name;

    String SomeInformation;

    int State;

    public UserModel(int id){
        super(id);
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj) return true;
        if (obj == null || obj.getClass() != getClass()) return false;
        UserModel UserModel = (UserModel) obj;
        return Telegram_id == UserModel.Telegram_id;
    }

    @Override
    public int hashCode(){
        return Objects.hashCode(Telegram_id);
    }

}
