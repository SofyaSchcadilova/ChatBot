package ru.anekdots.databasecontroller.models;

/**
 *  Модель шуток
 */
public class JokesModel extends BaseModel{

    public String JokeText;

    /**
     * Сумма очков
     */
    public int rate;

    public JokesModel(int id){
        super(id);
        rate = 0;
    }

    public JokesModel(String text, int rate){
        super(-1);
        this.rate = rate;
        JokeText = text;
    }
    public JokesModel(int id, String text, int rate){
        super(id);
        this.rate = rate;
        JokeText = text;
    }

}
