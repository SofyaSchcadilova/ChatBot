package ru.anekdots.databasecontroller.DB_Tables;

import ru.anekdots.databasecontroller.models.JokesModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * Класс управления с таблицей шуток
 *
 * !!! НЕ ПОЛЬЗОВАТЬСЯ ЭТИМ КЛАССОМ НАПРЯМУЮ !!!
 */
public class JokesTable extends BaseTable implements TableOperations{
    public JokesTable() throws SQLException{
        super("Jokes");
    }

    @Override
    public void createTable() throws SQLException {
        super.executeSqlStatement("CREATE TABLE IF NOT EXISTS Jokes(" +
                "id BIGINT AUTO_INCREMENT PRIMARY KEY," +
                "jokesText VARCHAR(1023) NOT NULL," +
                "rate INTEGER NOT NULL," +
                "rates INTEGER NOT NULL)","Создана таблица " + tableName);
    }

    /**
     * Найти айди шутки по тексту
     * @param text
     * @return возвращает Id, -1 если такой нет
     * @throws SQLException
     */
    public int find(String text) throws SQLException{
        ResultSet rs = executeSqlStatement("SELECT id FROM Jokes WHERE (jokesText=\'"+text+"\')");
        if (rs.next()==false){
            return -1;
        }
        return rs.getInt("id");
    }

    /**
     * Получить шутку по её айди
     * @param id
     * @return JokesModel хранящую копию информации из БД
     * @throws SQLException
     */
    public JokesModel getById(int id) throws SQLException{

        ResultSet rs = executeSqlStatement("SELECT * FROM Jokes WHERE (id="+String.valueOf(id)+")");
        if (rs.next()==false){
            return new JokesModel(-1);
        }
        JokesModel ans = new JokesModel(rs.getInt("id"),
                rs.getString("jokesText"),
                rs.getInt("rate"),
                rs.getInt("rates"));
        return ans;
    }
    public List<JokesModel> getJokes(int count) throws SQLException{
        ResultSet rs = executeSqlStatement("SELECT * " +
                "FROM Jokes " +
                "limit "+String.valueOf(count));
        List<JokesModel> ans = new ArrayList<JokesModel>();

        while (rs.next()){
            ans.add(new JokesModel(rs.getInt("id"),
                    rs.getString("jokesText"),
                    rs.getInt("rate"),
                    rs.getInt("rates")));
        }
        return ans;
    }
    /**
     * Добавить шутку в БД
     *
     * @param text
     * @return true если добавилась, false если такая шутка уже есть
     *
     * @throws SQLException
     */
    public boolean addJokes(String text) throws  SQLException{
        if (find(text)==-1) {
            executeSqlStatement("INSERT INTO Jokes(jokesText, rate, rates) " +
                    "VALUES (\'" + text + "\', 0, 0)");
            return true;
        }
        else {
            return false;
        }
    }
    @Override
    public void createForeignKey() throws SQLException{

    }
}
