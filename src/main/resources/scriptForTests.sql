DELETE FROM USERS;
ALTER TABLE USERS ALTER COLUMN USER_ID RESTART WITH 1;

DELETE FROM FILMS;
ALTER TABLE FILMS ALTER COLUMN FILM_ID RESTART WITH 1;

DELETE FROM MPA;
ALTER TABLE MPA ALTER COLUMN MPA_ID RESTART WITH 1;

DELETE FROM USER_FRIENDS;
DELETE FROM GENRES;
DELETE FROM FILM_LIKES;
DELETE FROM FILM_GENRES;


CREATE TABLE IF NOT EXISTS USERS (
                                     USER_ID int PRIMARY KEY AUTO_INCREMENT,
                                     EMAIL VARCHAR(255) UNIQUE NOT NULL,
                                     USER_LOGIN VARCHAR(255) UNIQUE NOT NULL,
                                     USER_NAME VARCHAR(255) NOT NULL,
                                     BIRTHDAY DATE
);

CREATE TABLE IF NOT EXISTS USER_FRIENDS(
                                           USER_ID INT REFERENCES USERS(user_id) ON DELETE CASCADE,
                                           FRIEND_ID INT REFERENCES USERS(user_id) ON DELETE CASCADE,
                                           FRIENDSHIP_STATUS BOOLEAN,
                                           PRIMARY KEY (user_id, friend_id)
);

CREATE TABLE IF NOT EXISTS MPA (
                                   MPA_ID int PRIMARY KEY AUTO_INCREMENT,
                                   TITLE varchar(10)
);

CREATE TABLE IF NOT EXISTS FILMS (
                                     FILM_ID int PRIMARY KEY AUTO_INCREMENT,
                                     FILM_NAME varchar(255),
                                     DESCRIPTION varchar(200),
                                     RELEASEdATE date,
                                     DURATION int,
                                     MPA_ID int REFERENCES MPA(mpa_id)
);

CREATE TABLE IF NOT EXISTS GENRES (
                                      GENRE_ID int PRIMARY KEY,
                                      TITLE varchar(20)
);

CREATE TABLE IF NOT EXISTS FILM_LIKES (
                                          FILM_ID int REFERENCES FILMS(film_id) ON DELETE CASCADE,
                                          USER_LIKE int REFERENCES USERS(user_id) ON DELETE CASCADE,
                                          PRIMARY KEY (film_id, user_like)
);

CREATE TABLE IF NOT EXISTS FILM_GENRES (
                                           FILM_ID int REFERENCES FILMS(film_id) ON DELETE CASCADE,
                                           GENRE_ID int REFERENCES GENRES(genre_id ) ON DELETE CASCADE,
                                           UNIQUE (FILM_ID, GENRE_ID)
);


merge into MPA (MPA_ID, TITLE)
    values (1, 'G');

merge into MPA (MPA_ID, TITLE)
    values (2, 'PG');

merge into MPA (MPA_ID, TITLE)
    values (3, 'PG-13');

merge into MPA (MPA_ID, TITLE)
    values (4, 'R');

merge into MPA (MPA_ID, TITLE)
    values (5, 'NC-17');

merge into GENRES (GENRE_ID, TITLE)
    values (1, 'Комедия');

merge into GENRES (GENRE_ID, TITLE)
    values (2, 'Драма');

merge into GENRES (GENRE_ID, TITLE)
    values (3, 'Мультфильм');

merge into GENRES (GENRE_ID, TITLE)
    values (4, 'Триллер');

merge into GENRES (GENRE_ID, TITLE)
    values (5, 'Документальный');

merge into GENRES (GENRE_ID, TITLE)
    values (6, 'Боевик');