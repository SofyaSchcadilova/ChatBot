package ru.anekdots.databasecontroller.DB_Tables;

import ru.anekdots.databasecontroller.SqlControler;

import java.io.Closeable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BaseTable implements Closeable {
    /**
     * JDBC-соединение для работы с таблицей
     */
    Connection connection;

    Statement statement;
    String tableName;

    BaseTable(String tableName) throws SQLException {
        this.tableName = tableName;
        this.connection = SqlControler.getConnection();
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
            connection = SqlControler.getConnection();
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
