package ru.anekdots.databasecontroller.models;

/**
 *  Модель шуток
 */
public class JokesModel extends BaseModel{

    public String JokeText;
    /**
     * Количество голосов
     */
    public int rates;
    /**
     * Сумма очков
     */
    public int rate;

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
    public JokesModel(int id, String text, int rate, int rates){
        super(id);
        this.rates = rates;
        this.rate = rate;
        JokeText = text;
    }

}
