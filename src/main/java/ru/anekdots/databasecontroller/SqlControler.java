package ru.anekdots.databasecontroller;


import ru.anekdots.databasecontroller.DB_Tables.JokesTable;
import ru.anekdots.databasecontroller.models.JokesModel;

import java.sql.*;
import java.util.List;

/**
 * Класс общения базы данных с основной логикой
 *
 */
public class SqlControler {
    static String ROOT = System.getProperty("user.dir");
    public static final String DB_URL = "jdbc:h2:" + ROOT + "\\src\\main\\java\\database\\test.h2";
    public static final String DB_Driver = "org.h2.Driver";
    static JokesTable jokesTable;

    java.sql.Connection connection;
    public SqlControler() throws SQLException, ClassNotFoundException{
        System.out.println(ROOT);
        Class.forName(DB_Driver);
        jokesTable = new JokesTable();
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
    }
    public void close(){
        jokesTable.close();

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
    public boolean addJoke(String JokeText) throws SQLException{
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
}
