# ChatBot

Current Features: 

      -Бот, который присылает вам шутки
      
      -База, хранящая шутки

      -Присылаются уникальные, уже не отправленные, шутки

      -Возможность получения ежедневных шуток
      
      -Пользователь может добавить свой анекдот в базу данных

      -Каждый анекдот можно оценить => есть топ анекдотов и топ шутников

      -Статистика у каждого пользователя

      -Получение анекдота из интернета, нужно ввести "анекдот про..."
     
Токен бота и его имя прописываются в AnekBot/src/main/java/ru.anekdots/resourses/botsdata.java

Анекдот не присылается второй раз

База анекдотов хранится в sql таблице

# Деплой:

1. Клонировать репозиторий

2. Токен бота и его имя прописать в файл .env

Скачать java и javac, если они не установлены на сервере
Скачать maven, если он не установлен (sudo apt install maven)

3. cd путь/к/папке/проекта

4. mvn clean install

5. cd target (переходим в папку target)

6. screen

7. java -jar AnekBot-1.0-SNAPSHOT.jar
