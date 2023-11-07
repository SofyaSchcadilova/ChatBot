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
                "rate INTEGER NOT NULL)","Создана таблица " + tableName);
    }

    /**
     * Найти айди шутки по тексту
     * @param text
     * @return возвращает Id, -1 если такой нет
     * @throws SQLException
     */
    public int find(String text) throws SQLException{
        ResultSet rs = executeSqlStatement("SELECT id FROM Jokes WHERE (jokesText='" +text+ "')");
        if (!rs.next()){
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

        ResultSet rs = executeSqlStatement("SELECT * FROM Jokes WHERE (id="+(id)+")");
        if (!rs.next()){
            return new JokesModel(-1);
        }

        return new JokesModel(rs.getInt("id"),
                rs.getString("jokesText"),
                rs.getInt("rate"));
    }

    /**
     * Сколько шуток в базе данных
     * @return
     * @throws SQLException
     */
    public int getNumberOfJokes() throws SQLException {
       ResultSet rs = executeSqlStatement("SELECT COUNT(*) as count FROM Jokes");
       if (rs.next()){
           return rs.getInt("count");
       }
       return 0;
    }

    /**
     * Получить N шуток
     * @param N count
     * @return
     * @throws SQLException
     */
    public List<JokesModel> getJokes(int N) throws SQLException{
        ResultSet rs = executeSqlStatement("SELECT * " +
                "FROM Jokes " +
                "limit "+ N);
        List<JokesModel> ans = new ArrayList<JokesModel>();

        while (rs.next()){
            ans.add(new JokesModel(rs.getInt("id"),
                    rs.getString("jokesText"),
                    rs.getInt("rate")));
        }
        return ans;
    }
    /**
     * Получить все шутки из БД
     * @return возвращает список объектов класса JokesModel
     * @throws SQLException
     */

    public List<JokesModel> getAllJokes() throws SQLException{
        ResultSet rs = executeSqlStatement("SELECT *" + "FROM Jokes");
        List<JokesModel> ans = new ArrayList<JokesModel>();
        while (rs.next()){
            ans.add(new JokesModel(rs.getInt("id"),
                    rs.getString("jokesText"),
                    rs.getInt("rate")));
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
            executeSqlStatement("INSERT INTO Jokes(jokesText, rate) " +
                    "VALUES ('" + text + "', 0)");
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Изменить рейтинг
     * @param up true если поднять, false если опустить
     *
     * @throws SQLException
     */
    public void changeRate(int jokeId, boolean up) throws SQLException {
        ResultSet rs = executeSqlStatement("Select * FROM Jokes WHERE (id="+ jokeId +")");
        if (!rs.next()){
            return;
        }
        int curRate = rs.getInt("rate");
        if (up) curRate += 1;
        else curRate -= 1;

        executeSqlStatement("UPDATE Jokes SET rate = "+ curRate);
    }

    /**
     * Получить лучшие n шуток
     * @param n Количество шуток
     * @return
     * @throws SQLException
     */
    public ArrayList<JokesModel> getBestJokes(int n) throws SQLException {
        ResultSet rs = executeSqlStatement("SELECT TOP " + n + " *  FROM Jokes\n ORDER BY rate DESC");
        ArrayList<JokesModel> ans = new ArrayList<JokesModel>();
        while (rs.next()){
            ans.add(
                    new JokesModel( rs.getInt("id"),
                            rs.getString("jokesText"),
                            rs.getInt("rate")
                            )
                    );

        }
        return ans;
    }
    @Override
    public void createForeignKey() throws SQLException{

    }

    /**
     * Получить случайную шутку из Базы данных
     * @return
     * @throws SQLException
     */
    public JokesModel getRandomJoke() throws SQLException {
        ResultSet rs = executeSqlStatement("SELECT * FROM Jokes " +
                "ORDER BY RAND() " +
                "LIMIT 1");
        if (!rs.next()){
            return new JokesModel(-1);
        }
        return  new JokesModel( rs.getInt("id"),
                rs.getString("jokesText"),
                rs.getInt("rate"));
    }
}
