package ru.anekdots.databasecontroller.models;

import ru.anekdots.databasecontroller.BaseModel;
import ru.anekdots.databasecontroller.TableOperations;

import java.sql.SQLException;

/**
 *  Модель шуток
 */
public class JokesModel extends BaseModel implements TableOperations {
    @Override
    public void createTable() throws SQLException {

    }

    @Override
    public void createForeignKey() throws SQLException {

    }

    @Override
    public void createExtraConstraints() throws SQLException {

    }
}
