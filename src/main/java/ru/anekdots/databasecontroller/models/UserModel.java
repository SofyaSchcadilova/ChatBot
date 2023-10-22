package ru.anekdots.databasecontroller.models;

/**
 * Модель пользоваетеля
 */
public class UserModel extends BaseModel{

    int Telegram_id;
    int Discord_id;
    String Name;

    String SomeInformation;

    public UserModel(int id){
        super(id);
    }


}
