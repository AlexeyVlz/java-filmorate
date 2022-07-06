CREATE TABLE IF NOT EXISTS USERS (
     user_id int PRIMARY KEY AUTO_INCREMENT,
     email VARCHAR(255) UNIQUE NOT NULL,
     login VARCHAR(255) UNIQUE NOT NULL,
     user_name VARCHAR(255) NOT NULL,
     birthday DATE
);

CREATE TABLE IF NOT EXISTS USER_FRIENDS(
     user_id INT REFERENCES USERS(user_id) ON DELETE CASCADE,
     friend_id INT REFERENCES USERS(user_id) ON DELETE CASCADE,
     friendship_status BOOLEAN,
     PRIMARY KEY (user_id, friend_id)
);

CREATE TABLE IF NOT EXISTS FILMS (
     film_id int PRIMARY KEY AUTO_INCREMENT,
     film_name varchar(255),
     description varchar(200),
     releaseDate date,
     duration int,
     mpa_id int REFERENCES MPA(mpa_id)
);

CREATE TABLE IF NOT EXISTS GENRES (
    genge_id int PRIMARY KEY AUTO_INCREMENT,
    title varchar(20)
);

CREATE TABLE IF NOT EXISTS FILM_LIKES (
    film_id int REFERENCES FILMS(film_id) ON DELETE CASCADE,
    user_like int REFERENCES USERS(user_id)
    PRIMARY KEY (film_id, user_like)
);

CREATE TABLE IF NOT EXISTS FILM_GENRES (
    film_id int REFERENCES FILMS(film_id) ON DELETE CASCADE,
    genre_id int REFERENCES GENRES(genge_id ) ON DELETE CASCADE,
);

CREATE TABLE IF NOT EXISTS MPA (
    mpa_id int PRIMARY KEY AUTO_INCREMENT,
    title varchar(10)
);



