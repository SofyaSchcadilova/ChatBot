package ru.anekdots.bot;

import io.github.cdimascio.dotenv.Dotenv;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.groupadministration.GetChat;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.anekdots.databasecontroller.models.UserModel;
import ru.anekdots.logic.Logic;
import ru.anekdots.logic.LogicAnswer;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.List;


/***
 * Класс телеграм бота
 *
  */
public class TelegramBot extends TelegramLongPollingBot implements Bot{

    /**
     * Подключение основной логики
     */

    public Logic logic;
    final Dotenv dotenv = Dotenv.load();
    /**
     * Имя бота
     */
    final private String Botname = dotenv.get("NAME");
    /**
     * Ключ/Токен бота
     */
    final private String Token = dotenv.get("TOKEN");


    public TelegramBot() throws SQLException, ClassNotFoundException, InterruptedException {
        logic = new Logic(this);
        Thread thread = new Thread(new ThreadForEverydayJoke());
        thread.start();

    }

    /**
     * Получить имя бота
     */
    @Override
    public String getBotUsername() {
        return Botname;
    }
    /**
     * Получить токен бота
     */
    @Override
    public String getBotToken() {
        return Token;
    }

    /**
     * Отправка сообщения
     * @param textAnswer текст сообщения
     * @param chatId айди чата
     */
    @Override
    public void sendMessage(LogicAnswer textAnswer, long chatId) {
        SendMessage message = new SendMessage();
        message.setText(textAnswer.getAnswer());
        message.setChatId(String.valueOf(chatId));

        if (textAnswer.getKeyboardType() != null)
            message.setReplyMarkup((ReplyKeyboard) textAnswer.getKeyboardType());

        try {
            execute(message);
        }
        catch (TelegramApiException e){
            throw new RuntimeException(e);
        }
    }
    /**
     * Действия бота при получении
     */
    @Override
    public void onUpdateReceived(Update update) {
        Long chatId = update.getMessage().getChatId();
        String text = update.getMessage().getText();
        try {
            sendMessage(logic.think(text, chatId), chatId);
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public class ThreadForEverydayJoke implements Runnable{


        ThreadForEverydayJoke(){

        };
        public void run(){
            try {
                List<UserModel> users;
                while (true){
                    users = logic.getAllUsers();
                    for (UserModel user : users){
                        LocalTime currentTime = LocalTime.now();
                        int timeNow = currentTime.toSecondOfDay();
                        if (user.Time == -1){
                            continue;
                        }
                        if (timeNow/60 == user.Time/60) {
                            sendMessage(logic.think("нужен анекдот", user.Telegram_id), user.Telegram_id);
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

}
