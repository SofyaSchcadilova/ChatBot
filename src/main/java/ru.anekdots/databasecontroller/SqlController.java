package ru.anekdots.databasecontroller;


import ru.anekdots.databasecontroller.DB_Tables.JokesTable;
import ru.anekdots.databasecontroller.DB_Tables.UserTable;
import ru.anekdots.databasecontroller.models.JokesModel;
import ru.anekdots.databasecontroller.models.UserModel;

import java.io.IOException;
import java.sql.*;
import java.util.List;

/**
 * Класс общения базы данных с основной логикой
 *
 */
public class SqlController {
    static String ROOT = System.getProperty("user.dir");
    public static final String DB_URL = "jdbc:h2:" + ROOT + "\\src\\main\\database\\curDB.h2";
    public static final String DB_Driver = "org.h2.Driver";
    static JokesTable jokesTable;
    static UserTable userTable;
    java.sql.Connection connection;
    public SqlController() throws SQLException, ClassNotFoundException{
        System.out.println(DB_URL);
        Class.forName(DB_Driver);
        jokesTable = new JokesTable();
        userTable = new UserTable();
        createTables();
        /*
        try {
            Class.forName(DB_Driver); //Проверяем наличие JDBC драйвера для работы с БД
            connection = DriverManager.getConnection(DB_URL);//соединение с БД
            System.out.println("Соединение с СУБД выполнено.");


        } catch (ClassNotFoundException e) {
            e.printStackTrace(); // обработка ошибки  Class.forName
            System.out.println("JDBC драйвер для СУБД не найден!");
        } catch (SQLException e) {
            e.printStackTrace(); // обработка ошибок  DriverManager.getConnection
            System.out.println("Ошибка SQL !");
        }
        */
    }

    public void createTables() throws SQLException{
        jokesTable.createTable();
        userTable.createTable();
    }
    public void close(){
        jokesTable.close();
        userTable.close();
    }

    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(DB_URL);
    }
    
    /**
     * Добавить шутку в БД
     *
     * @param JokeText
     * @return true если добавилась, false если такая шутка уже есть
     *
     * @throws SQLException
     */
    public boolean addJoke(String JokeText) throws SQLException {
        return jokesTable.addJokes(JokeText);
    }


    /**
     * Получить шутку по её айди
     * @param id
     * @return JokesModel хранящую копию информации из БД
     * @throws SQLException
     */
    public JokesModel getJokeById(int id) throws SQLException {
        return jokesTable.getById(id);
    }

    /**
     * Найти айди шутки по тексту
     * @param text
     * @return возвращает Id, -1 если такой нет
     * @throws SQLException
     */
    public int findJokeByText(String text) throws SQLException {
        return jokesTable.find(text);
    }

    /**
     * Получить несколько шуток
     * @param count
     * @return
     * @throws SQLException
     */
    public List<JokesModel> getJokes(int count) throws SQLException{
        List<JokesModel> ans = jokesTable.getJokes(count);
        return ans;
    }

    /**
     * Получить все шутки из БД
     * @return возвращает список JokesModel
     * @throws SQLException
     */

    public String getAllJokes() throws SQLException {
        List<JokesModel> jokesList = jokesTable.getAllJokes();
        if (jokesList.isEmpty()){
            return "Пока наша база шуток пуста! Но вы можете добавить свой анекдот:)";
        }
        String ans = "";
        for (int i = 0; i < jokesList.size(); i++){
            ans = ans  + jokesList.get(i).JokeText + "\n";
        }
        return ans;
    }

    /**
     * Получить пользователя по его телеграм айди
     * @param TelegramId
     * @return
     * @throws SQLException
     * @throws IOException
     */
    public UserModel getUserByTelegramId(long TelegramId) throws SQLException, IOException {
        return userTable.getUserByTelegramId(TelegramId);
    }


    /**
     *  Получить пользователя по его айди в базе
     * @param BaseId
     * @return
     * @throws SQLException
     * @throws IOException
     */
    public UserModel getUserByBaseId(int BaseId) throws SQLException, IOException {
        return userTable.getUserByBaseId(BaseId);
    }




    /**
     * Существует ли такой пользователь в Базе данных ( по его телеграмм айди)
     * @return
     * @throws SQLException
     */
    public boolean IsUserExists(long TelegramId) throws SQLException {
        return userTable.IsUserExists(TelegramId);
    }
    /**
     * Добавить пользователя (по UserModel) (пока не отличается от телеграм айди)
     * @param user
     * @return
     * @throws SQLException
     * @throws IOException
     */
    public boolean addUser(UserModel user) throws SQLException, IOException {
        return userTable.addUser(user);
    }
    /**
     * Добавить пользователя (по телеграм айди)
     * @param Telegram_id
     * @return
     * @throws SQLException
     * @throws IOException
     */
    public boolean addUser(long Telegram_id) throws SQLException, IOException {
        return userTable.addUser(Telegram_id);
    }

    /**
     * Пользователь посмотрел данную шутку
     * @param Telegram_id
     * @param JokeId
     * @throws SQLException
     * @throws IOException
     */
    public void setSeenJoke(long Telegram_id, int JokeId) throws SQLException, IOException {
        userTable.setSeenJoke(Telegram_id, JokeId);
    }

    /**
     * Проверить, посмотрел ли пользователь эту шутку
     * @param Telegram_id
     * @param JokeId
     * @return
     * @throws SQLException
     * @throws IOException
     */
    public boolean IsSeenJoke(long Telegram_id,int JokeId) throws SQLException, IOException{
        return userTable.IsSeenJoke(Telegram_id, JokeId);
    }

    /**
     * Получить случайную шутку из Базы данных
     * @return
     * @throws SQLException
     */
    public JokesModel getRandomJoke() throws SQLException {
        return jokesTable.getRandomJoke();
    }

    /**
     * Сколько шуток в базе данных
     * @return
     * @throws SQLException
     */
    public int getNumberOfJokes() throws SQLException {
        return jokesTable.getNumberOfJokes();
    }

    /**
     * Получить лучшие n шуток
     * @param n Количество шуток
     * @return
     * @throws SQLException
     */
    public String getBestJokes(int n) throws SQLException {
        List<JokesModel> jokesList = jokesTable.getBestJokes(n);
        if (jokesList.isEmpty())
            return "Пока наша база шуток пуста! Но вы можете добавить свой анекдот:)";
        else if(n > jokesList.size())
            return "Пока у нас нет столько шуток o_O \n Но вы можете увеличить их количество, добавив свой анекдот.";
        else{
            String ans = "";
            for (int i = 0; i < n; i++)
                ans = ans + jokesList.get(i).JokeText + "\n";
            return ans;
        }
    }

    /**
     *  Сохранить последнюю просмотренную шутку
     * @param telegram_id
     * @param JokeId
     * @throws SQLException
     */
    public void savePrevJoke(long telegram_id, int JokeId) throws SQLException {
        userTable.savePrevJoke(telegram_id,JokeId);
    }



    /**
     * Получить время отправки анекдотов у пользователя
     * @param Telegram_id
     * @return -1 - значит нет такого пользователя
     * @throws SQLException
     */
    public int getUserTime(long Telegram_id) throws SQLException {
        return userTable.getUserTime(Telegram_id);
    }




    /**
     * Установить время отправки анекдотов у пользователя
     * @param telegram_id
     * @param time - время в секундах от начала дня
     * @return -1 - значит нет такого пользователя
     * @throws SQLException
     */
    public void setUserTime(long telegram_id, int time) throws SQLException {
        userTable.setUserTime(telegram_id, time);
    }

    /**
     *  Установка состояния пользователя
     * @param telegram_id
     * @param state
     * @throws SQLException
     */
    public void setState(long telegram_id, int state) throws SQLException {
        userTable.setState(telegram_id,state);
    }

    /**
     * {@link UserTable#getAllUsers()}
     */
    public List<UserModel> getAllUsers() throws SQLException {
        return  userTable.getAllUsers();
    }

    /**
     * Изменить рейтинг
     * @param up true если поднять, false если опустить
     *
     * @throws SQLException
     */
    public void changeRate(int jokeId, boolean up) throws SQLException {
        jokesTable.changeRate(jokeId,up);
    }
}
