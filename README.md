# java-filmorate
Template repository for Filmorate project.

## Схема БД:

![](Схема.png)

## Схема базы данных содержит семь таблиц:
1. USERS - данные о пользователи;
2. FILMS - данные о фильмах;
3. USER_FRIENDS - данные о друзьях пользователя;
4. GENRES - данные о всех возможных жанрах фильмов.
5. FILM_GENRES - жанры каждого фильма
6. FILM_LIKES - данные о лайках к фильму
7. MPA - все возможные возрастные ограничения

## Примеры запроса к базе данных:
- запрос списка юзров:
```
SELECT *
FROM USERS;
```
- запрос списка фильмов соответствующих ограничению "лицам до 18 лет просмотр запрещён":
```
SELECT *
FROM FILMS
WHEN MPA_ID LIKE 5;
```