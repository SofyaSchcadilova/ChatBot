package ru.anekdots.databasecontroller.models;

/**
 *  Модель шуток
 */
public class JokesModel extends BaseModel{

    String JokeText;
    int rates;
    int rate;

    public JokesModel(int id){
        super(id);
        rate = 0;
        rates = 0;
    }

    public JokesModel(int id, String text){
        super(id);
        rate = 0;
        rates = 0;
        JokeText = text;
    }

}
