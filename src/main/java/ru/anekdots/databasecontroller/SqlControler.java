package ru.anekdots.databasecontroller;


import ru.anekdots.databasecontroller.models.JokesModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс общения базы данных с основной логикой
 *
 */
public class SqlControler {
    static String ROOT = System.getProperty("user.dir");

    public static final String DB_URL = "jdbc:h2:" + ROOT + "\\src\\main\\java\\database\\test.h2";
    public static final String DB_Driver = "org.h2.Driver";

    java.sql.Connection connection;
    public SqlControler(){
        System.out.println(ROOT);
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
    }

    public void close(){
        try {
            if (!connection.isClosed()) {
                connection.close();       // отключение от БД
                System.out.println("Отключение от СУБД выполнено.");
            }
        }
        catch (SQLException e) {
            e.printStackTrace(); // обработка ошибок  DriverManager.getConnection
            System.out.println("Ошибка SQL !");
        }
    }

    public void addJoke(String JokeText){

    }

    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(DB_URL);
    }



    public void createTables(){

    }

    public void addUser(String Name, int TelegramId, int DiscordId){

    }

    public JokesModel getJokeById(int id) {
        return new JokesModel(1,"Колобок повесился");
    }
    public int findJokeByText(String text) {
        return 0;
    }
    public List<JokesModel> getJokes(int count){
        List<JokesModel> ans = new ArrayList<JokesModel>();
        ans.add(new JokesModel(1,"Колобок повесился"));
        ans.add(new JokesModel(2,"Колобок повесился (тип дважды сказананя шутка в два раза смешнее"));
        return ans;
    }
}
