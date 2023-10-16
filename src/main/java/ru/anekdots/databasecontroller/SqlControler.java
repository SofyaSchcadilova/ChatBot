package ru.anekdots.databasecontroller;


import java.sql.*;

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
            connection = DriverManager.getConnection(DB_URL);//соединениесБД
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

    public void createTable(){

    }

}
