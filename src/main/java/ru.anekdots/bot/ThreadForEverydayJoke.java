package ru.anekdots.bot;

import ru.anekdots.databasecontroller.SqlController;
import ru.anekdots.databasecontroller.models.UserModel;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.List;

/**
 * Открывается поток для проверки времени
 */
public class ThreadForEverydayJoke implements Runnable{

    public Thread thread;
    public TelegramBot bot;
    public ThreadForEverydayJoke(TelegramBot bot){
        this.bot = bot;
        thread = new Thread();
    }
    public void start(){
        thread.start();
    }

    public void run(){
        try {
            List<UserModel> users;
            while (true){
                users = bot.logic.DB.getAllUsers();
                for (UserModel user : users){
                    LocalTime currentTime = LocalTime.now();
                    int timeNow = currentTime.toSecondOfDay();
                    if (user.Time == -1){
                        continue;
                    }
                    if (timeNow/60 == user.Time/60) {
                        bot.sendMessage(bot.logic.think("нужен анекдот", user.Telegram_id), user.Telegram_id);
                    }
                }

                Thread.sleep(60000);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}