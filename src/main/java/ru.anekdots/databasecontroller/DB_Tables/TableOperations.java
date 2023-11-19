package ru.anekdots.databasecontroller.DB_Tables;

import java.sql.SQLException;

/**
 * Интерфейс таблицы
 */
public interface TableOperations {
    /**
     * Создание таблицы
     * @throws SQLException
     */
    void createTable() throws SQLException;

    /**
     * Создание связий между таблицами
     * @throws SQLException
     */
    void createForeignKey() throws SQLException;


}
