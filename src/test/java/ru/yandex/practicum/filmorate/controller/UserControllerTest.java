package ru.yandex.practicum.filmorate.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.objectsForTests.ObjectsUserControllerTest;
import ru.yandex.practicum.filmorate.service.UserService;
import ru.yandex.practicum.filmorate.storage.friends.FriendsDbSrorage;
import ru.yandex.practicum.filmorate.storage.user.UserDbStorage;


public class UserControllerTest {

    UserController userController;

    @BeforeEach
    public void beforeEach() {
        userController = new UserController(new UserService(new UserDbStorage(new JdbcTemplate()),
                new FriendsDbSrorage(new JdbcTemplate())));
    }

    @Test
    public void createTest() {
        User uncorrectMail = ObjectsUserControllerTest.uncorrectMail();
        try {
            userController.create(uncorrectMail);
        } catch (ValidationException e) {
            Assertions.assertEquals(e.getMessage(), "Введите корректный адрес электронной почты");
        }

        User uncorrectMail2 = ObjectsUserControllerTest.uncorrectMail2();
        try {
            userController.create(uncorrectMail2);
        } catch (ValidationException e) {
            Assertions.assertEquals(e.getMessage(), "Введите корректный адрес электронной почты");
        }

        User uncorrectLogin = ObjectsUserControllerTest.uncorrectLogin();
        try {
            userController.create(uncorrectLogin);
        } catch (ValidationException e) {
            Assertions.assertEquals(e.getMessage(), "Введите корректный логин (логин не может содержать пробелы)");
        }

        User uncorrectLogin2 = ObjectsUserControllerTest.uncorrectLogin2();
        try {
            userController.create(uncorrectLogin2);
        } catch (ValidationException e) {
            Assertions.assertEquals(e.getMessage(), "Введите корректный логин (логин не может содержать пробелы)");
        }

        User uncorrectBirthdayDate = ObjectsUserControllerTest.uncorrectBirthdayDate();
        try {
            userController.create(uncorrectBirthdayDate);
        } catch (ValidationException e) {
            Assertions.assertEquals(e.getMessage(), "Дата рождения не может быть позже текущего времени");
        }
    }

    @Test
    public void updateTest() {
        User uncorrectMail = ObjectsUserControllerTest.uncorrectMail();
        uncorrectMail.setId(1);
        try {
            userController.update(uncorrectMail);
        } catch (ValidationException e) {
            Assertions.assertEquals(e.getMessage(), "Введите корректный адрес электронной почты");
        }

        User uncorrectMail2 = ObjectsUserControllerTest.uncorrectMail2();
        uncorrectMail2.setId(1);
        try {
            userController.update(uncorrectMail2);
        } catch (ValidationException e) {
            Assertions.assertEquals(e.getMessage(), "Введите корректный адрес электронной почты");
        }

        User uncorrectLogin = ObjectsUserControllerTest.uncorrectLogin();
        uncorrectLogin.setId(1);
        try {
            userController.update(uncorrectLogin);
        } catch (ValidationException e) {
            Assertions.assertEquals(e.getMessage(), "Введите корректный логин (логин не может содержать пробелы)");
        }

        User uncorrectLogin2 = ObjectsUserControllerTest.uncorrectLogin2();
        uncorrectLogin2.setId(1);
        try {
            userController.update(uncorrectLogin2);
        } catch (ValidationException e) {
            Assertions.assertEquals(e.getMessage(), "Введите корректный логин (логин не может содержать пробелы)");
        }

        User uncorrectBirthdayDate = ObjectsUserControllerTest.uncorrectBirthdayDate();
        uncorrectBirthdayDate.setId(1);
        try {
            userController.update(uncorrectBirthdayDate);
        } catch (ValidationException e) {
            Assertions.assertEquals(e.getMessage(), "Дата рождения не может быть позже текущего времени");
        }
    }
}
