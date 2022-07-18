package ru.yandex.practicum.filmorate.objectsForTests;

import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Date;

public class ObjectsUserControllerTest {

    public static User correctUser() {
        return User.builder()
                .email("777@yandex.ru")
                .login("Alexey777")
                .name("Alexey")
                .birthday(java.sql.Date.valueOf(LocalDate.of(1988, 9, 2)))
                .build();
    }

    public static User correctUser2() {
        return User.builder()
                .email("333@yandex.ru")
                .login("Roman333")
                .name("Roman")
                .birthday(java.sql.Date.valueOf(LocalDate.of(2000, 1, 1)))
                .build();
    }

    public static User correctUser3() {
        return User.builder()
                .email("888@yandex.ru")
                .login("Roman888")
                .name("Romario")
                .birthday(java.sql.Date.valueOf(LocalDate.of(2000, 1, 1)))
                .build();
    }

    public static User uncorrectMail() {
        return User.builder()
                .email("777yandex.ru")
                .login("Alexey777")
                .name("Alexey")
                .birthday(java.sql.Date.valueOf(LocalDate.of(1988, 9, 2)))
                .build();
    }

    public static User uncorrectMail2() {
        return User.builder()
                .email("")
                .login("Alexey777")
                .name("Alexey")
                .birthday(java.sql.Date.valueOf(LocalDate.of(1988, 9, 2)))
                .build();
    }

    public static User uncorrectLogin() {
        return User.builder()
                .email("777@yandex.ru")
                .login("Alexey 777")
                .name("Alexey")
                .birthday(java.sql.Date.valueOf(LocalDate.of(1988, 9, 2)))
                .build();
    }

    public static User uncorrectLogin2() {
        return User.builder()
                .email("777@yandex.ru")
                .login("")
                .name("Alexey")
                .birthday(java.sql.Date.valueOf(LocalDate.of(1988, 9, 2)))
                .build();
    }


    public static User uncorrectBirthdayDate() {
        return User.builder()
                .email("777@yandex.ru")
                .login("Alexey777")
                .name("Alexey")
                .birthday(java.sql.Date.valueOf(LocalDate.of(2030, 9, 2)))
                .build();
    }

    public static User emptyName() {
        return User.builder()
                .email("777@yandex.ru")
                .login("Alexey777")
                .name("")
                .birthday(java.sql.Date.valueOf(LocalDate.of(1988, 9, 2)))
                .build();
    }
}
