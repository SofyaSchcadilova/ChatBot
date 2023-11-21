package ru.anekdots.databasecontroller.DB_Tables;

import ru.anekdots.databasecontroller.SqlController;

import java.io.Closeable;
import java.sql.*;

public class BaseTable implements Closeable {
    /**
     * JDBC-соединение для работы с таблицей
     */
    Connection connection;

    Statement statement;
    String tableName;

    BaseTable(String tableName) throws SQLException {
        this.tableName = tableName;
        getConnection();
    }

    void getConnection() throws SQLException {
        connection = DriverManager.getConnection(SqlController.DB_URL);
    }

    @Override
    public void close(){
        try {
            if (connection != null && !connection.isClosed())
                connection.close();
                System.out.println(tableName+" закрыта");
        } catch (SQLException e){
            System.out.println("Ошибка закрытия Sql соединения " + tableName);
        }
    }

    /**
     * Попытаться открыть соединения заново
     * @throws SQLException
     */
    void reopenConnection() throws SQLException{
        if (connection == null || connection.isClosed()) {
            getConnection();
        }
    }

    /**
     * Выполнить sql запрос
     * @param sql сам запрос
     * @param description описание запроса (для логов)
     * @throws SQLException
     */
    ResultSet executeSqlStatement(String sql, String description) throws SQLException {
        if (statement!=null && !statement.isClosed()){
            statement.close();
        }
        reopenConnection();
        statement = connection.createStatement();
        statement.execute(sql);
        ResultSet ans = statement.getResultSet();
        if (description != null){
            System.out.println(description);
        }
        return ans;
    }
    /**
     * Выполнить sql запрос
     * @param sql сам запрос
     * @throws SQLException
     */
    ResultSet executeSqlStatement(String sql) throws SQLException {
        return executeSqlStatement(sql, null);
    };


}
