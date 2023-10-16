package ru.anekdots.databasecontroller;

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

    /**
     *  Создание дополнительных правил для значений полей таблицы
     * @throws SQLException
     */
    void createExtraConstraints()throws  SQLException;

}
