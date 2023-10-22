package ru.anekdots.databasecontroller.DB_Tables;

import java.sql.SQLException;

public class JokesTable extends BaseTable implements TableOperations{
    public JokesTable() throws SQLException{
        super("Jokes");
    }

    @Override
    public void createTable() throws SQLException {
        super.executeSqlStatement("CREATE TABLE IF NOT EXISTS Jokes(" +
                "id BIGINT AUTOINCREMENT PRIMARY KEY," +
                "jokesText VARCHAR(1023) NOT NULL," +
                "rate INTEGER NOT NULL," +
                "rates INTEGER NOT NULL","Создана таблица" + tableName);
    }

    @Override
    public void createForeignKey() throws SQLException{

    }
}
