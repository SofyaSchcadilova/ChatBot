# ChatBot

Current Features: 

      -Бот, который присылает вам шутки
      
      -База, хранящая шутки

      -Присылаются уникальные, уже не отправленные, шутки

      -Возможность получения ежедневных шуток
     
Write Bot's token and name into AnekBot/src/main/java/ru.anekdots/resourses/botsdata.java


Вторая задача:

Пользователь может предложить анекдот, который в дальнейшем будет добавлен в нашу базу анекдотов.
Умеет выводить список анекдотов.
Есть проверка на существование при проверке анекдота.
- Добавить анекдот
- Введите анекдот
- В дагестанском цирке клоун во время выступления запоминает тех зрителей, которые не смеются
- Хахаха, ваш анекдот добавлен!

- Добавить анекдот
- Введите анекдот
- В дагестанском цирке клоун во время выступления запоминает тех зрителей, которые не смеются
- Такой анекдот уже есть!

Третья задача:
- 1) пользователь нажимает на кнопку «анекдот» и бот отправляет случайный анекдот из базы данных

- 2) после отправки в чат анекдота (случайный анекдот из базы данных) есть кнопка оценить анекдот, далее высвечивается две кнопки 👍🏽 и 👎🏽, первая добавляет один балл (+1), вторая убирает (-1); если у анекдота общая оценка <-5, он удаляется

(Случайный анекдот из базы данных: генерируется рандомное число (от 1 до N, где N - количество анекдотов в базе), если этот анекдот до этого не отправлялся пользователю (в базе хранятся данные об этом), то программа отправляет его, иначе ищется ближайший неотправленный ранее анекдот)

- 3) каждый день в определённое время (пользователь может это время выбрать сам) бот отправляет анекдот

- 4) есть функция получения лучших анекдотов (topN). N - число, обозначающее сколько анекдотов вывести, его вводит пользователь



Следующие задачи, которые мы планируем реализовать:

- хранятся данные какие анекдоты были отправлены, анекдот не отправляется повторно

- подключаем сайт анекдоты.ру, пользователь вводит фразу «про …» и бот отправляет ему анекдот про … с этого сайта (например, про обезьян)


Так же анекдот не присылается второй раз

База анекдотов хранится в sql таблице
