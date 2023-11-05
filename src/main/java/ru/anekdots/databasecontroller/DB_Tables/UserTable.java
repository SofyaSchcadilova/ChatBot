package ru.anekdots.databasecontroller.DB_Tables;

import ru.anekdots.databasecontroller.models.UserModel;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;


/**
 * Класс управления с таблицей пользователей
 *
 * !!! НЕ ПОЛЬЗОВАТЬСЯ ЭТИМ КЛАССОМ НАПРЯМУЮ !!!
 */
public class UserTable extends  BaseTable implements TableOperations{

    public UserTable() throws SQLException {
        super("UserTable");
    }

    /**
     * Создать,если нет, таблицу
     * @throws SQLException
     */
    @Override
    public void createTable() throws SQLException {
        super.executeSqlStatement("CREATE TABLE IF NOT EXISTS Users(" +
                "id BIGINT AUTO_INCREMENT PRIMARY KEY," +
                "telegram_id BIGINT NOT NULL," +
                "state INTEGER NOT NULL," +
                "seenJokes BINARY(255))," +
                "prevJoke INTEGER NOT NULL," +
                "time INTEGER NOT NULL","Создана таблица " + tableName);

    }

    /**
     * Маски для байтов
     */
    private static final byte[] BIT_FLAGS = { (byte) 1, (byte) 2, (byte) 4, (byte) 8, (byte) 16, (byte) 32,
            (byte) 64, (byte) 128 };

    /**
     * ArrayList boolean из массива byte[]
     * @param input byte[]
     * @return
     */
    private static ArrayList<Boolean> bytesToBooleanArray(byte[] input) {
        ArrayList<Boolean> out = new ArrayList<Boolean>();


        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < 8; j++) {
                if ((input[i] & BIT_FLAGS[j]) == BIT_FLAGS[j])
                    out.add(true);
                else
                    out.add(false);
            } //for
        } //for

        return out;
    }
    /**
     * Массив byte[] из Boolean[]
     * @param bools Boolean[]
     * @return
     */
    private byte[] toByteArray(Boolean[] bools) {
        BitSet bits = new BitSet(bools.length);
        for (int i = 0; i < bools.length; i++) {
            if (bools[i]) {
                bits.set(i);
            }
        }

        byte[] bytes = bits.toByteArray();
        if (bytes.length * 8 >= bools.length) {
            return bytes;
        } else {
            return Arrays.copyOf(bytes, bools.length / 8 + (bools.length % 8 == 0 ? 0 : 1));
        }
    }


    /**
     * Получить пользователя по его айди телеграма
     * @param TelegramId
     * @return JokesModel хранящую копию информации из БД
     * @throws SQLException
     */
    public UserModel getUserByTelegramId(long TelegramId) throws SQLException, IOException {

        ResultSet rs = executeSqlStatement("SELECT * FROM Users WHERE (telegram_id="+ TelegramId +")");
        if (!rs.next()){
            return new UserModel(-1);
        }
        InputStream input = rs.getBinaryStream("seenJokes");
        ArrayList<Boolean> seen = new ArrayList<Boolean>(bytesToBooleanArray(input.readAllBytes()));

        UserModel ans = new UserModel(rs.getInt("id"),
                rs.getLong("telegram_id"),
                rs.getInt("state"),
                seen,
                rs.getInt("prevJoke"),
                rs.getInt("time"));
        return ans;
    }

    /**
     *  Получить пользователя по его айди в базе
     * @param BaseId
     * @return
     * @throws SQLException
     * @throws IOException
     */
    public UserModel getUserByBaseId(int BaseId) throws SQLException, IOException {

        ResultSet rs = executeSqlStatement("SELECT * FROM Users WHERE (id="+ BaseId +")");
        if (!rs.next()){
            return new UserModel(-1);
        }
        InputStream input = rs.getBinaryStream("seenJokes");
        ArrayList<Boolean> seen = new ArrayList<Boolean>(bytesToBooleanArray(input.readAllBytes()));

        UserModel ans = new UserModel(rs.getInt("id"),
                rs.getLong("telegram_id"),
                rs.getInt("state"),
                seen,
                rs.getInt("prevJoke"),
                rs.getInt("time"));
        return ans;
    }


    /**
     * Добавить пользователя (по телеграм айди)
     * @param Telegram_id
     * @return
     * @throws SQLException
     * @throws IOException
     */
    public boolean addUser(long Telegram_id) throws SQLException, IOException {
        if (!IsUserExists(Telegram_id)) {
            executeSqlStatement("INSERT INTO Users( telegram_id,state, prevJoke) " +
                    "VALUES (" + Telegram_id + ", 0, -1)");
            byte[] bytes = new byte[255];
            Arrays.fill(bytes,(byte) 0);

            String sql = "UPDATE Users SET seenJokes = ? WHERE telegram_id = " + Telegram_id;
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setBinaryStream(1,new ByteArrayInputStream(bytes));
            ps.executeUpdate();

            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Добавить пользователя (по UserModel) ( пока не отличается от телеграм айди)
     * @param user
     * @return
     * @throws SQLException
     * @throws IOException
     */
    public boolean addUser(UserModel user) throws SQLException, IOException {
        return addUser(user.Telegram_id);
    }

    /**
     * Существует ли такой пользователь в Базе данных ( по его телеграмм айди)
     * @return
     * @throws SQLException
     */
    public boolean IsUserExists(long TelegramId) throws SQLException {
        ResultSet rs = executeSqlStatement("SELECT * FROM Users WHERE (telegram_id ="+ TelegramId +")");
        return rs.next();
    }
    @Override
    public void createForeignKey() throws SQLException {

    }

    /**
     * Проверить, посмотрел ли пользователь эту шутку
     * @param Telegram_id
     * @param JokeId
     * @return
     * @throws SQLException
     * @throws IOException
     */
    public boolean IsSeenJoke(long Telegram_id,int JokeId) throws SQLException, IOException {
        ResultSet rs = executeSqlStatement("SELECT * FROM Users WHERE (telegram_id="+ Telegram_id +")");
        if (!rs.next()){
            return false;
        }
        UserModel user = getUserByTelegramId(Telegram_id);

        return user.isSeenJoke(JokeId);
    }

    /**
     * Пользователь посмотрел данную шутку
     * @param Telegram_id
     * @param JokeId
     * @throws SQLException
     * @throws IOException
     */
    public void setSeenJoke(long Telegram_id, int JokeId) throws SQLException, IOException {
        ResultSet rs = executeSqlStatement("SELECT * FROM Users WHERE (telegram_id="+ Telegram_id +")");
        if (!rs.next()){
            return;
        }
        InputStream input = rs.getBinaryStream("seenJokes");
        ArrayList<Boolean> seen = new ArrayList<Boolean>(bytesToBooleanArray(input.readAllBytes()));
        seen.set(JokeId,true);

        Boolean[] temp = seen.toArray(new Boolean[seen.size()]);
        byte[] t2 = toByteArray(temp);

        String sql = "UPDATE Users SET seenJokes = ? WHERE telegram_id = " + Telegram_id;
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setBinaryStream(1,new ByteArrayInputStream(t2));
        ps.executeUpdate();
    }

    /**
     *  Сохранить последнюю просмотренную шутку
     * @param telegram_id
     * @param JokeId
     * @throws SQLException
     */
    public void savePrevJoke(long telegram_id, int JokeId) throws SQLException {
        if (IsUserExists(telegram_id)){
            executeSqlStatement("UPDATE Users SET prevJoke =" + JokeId + " WHERE telegram_id ="+ telegram_id);
        }
    }

    /**
     * Получить время отправки анекдотов у пользователя
     * @param telegram_id
     * @return -1 - значит нет такого пользователя
     * @throws SQLException
     */
    public int getUserTime(long telegram_id) throws SQLException {
        ResultSet rs = executeSqlStatement("SELECT * FROM Users WHERE (telegram_id="+ telegram_id +")");
        if (!rs.next()){
            return -1;
        }
        return rs.getInt("time");
    }

    /**
     * Установить время отправки анекдотов у пользователя
     * @param telegram_id
     * @param time - время в секундах от начала дня
     * @return -1 - значит нет такого пользователя
     * @throws SQLException
     */
    public void setUserTime(long telegram_id, int time) throws SQLException {
        if (!IsUserExists(telegram_id)) {
            throw new SQLException("Нет такого пользователя");
        }
        executeSqlStatement("UPDATE Users SET time =" + String.valueOf(time) + " WHERE telegram_id =" + telegram_id);
    }

    public void setState(long telegram_id, int state) throws SQLException {
        if (!IsUserExists(telegram_id)) {
            throw new SQLException("Нет такого пользователя");
        }
        executeSqlStatement("UPDATE Users SET state =" + String.valueOf(state) + " WHERE telegram_id =" + telegram_id);
    }

}
