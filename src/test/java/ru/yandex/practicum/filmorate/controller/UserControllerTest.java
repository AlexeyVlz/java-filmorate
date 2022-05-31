package ru.yandex.practicum.filmorate.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.objectsForTests.ObjectsUserControllerTest;

import java.time.LocalDate;

public class UserControllerTest {

    UserController userController;

    @BeforeEach
    public void beforeEach() {
        userController = new UserController();
    }

    @Test
    public void createTest() {
        User user = ObjectsUserControllerTest.correctUser();
        try {
            userController.create(user);
        } catch (ValidationException e) {
            System.out.println(e.getMessage());
        }
        Assertions.assertEquals(user, userController.getUsers().get(1));

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

        User emptyName = ObjectsUserControllerTest.emptyName();
        User userToCheck = User.builder()
                .id(2)
                .email("777@yandex.ru")
                .login("Alexey777")
                .name("Alexey777")
                .birthday(LocalDate.of(1988, 9, 2))
                .build();
        try {
            userController.create(emptyName);
        } catch (ValidationException e) {
            System.out.println(e.getMessage());
        }
        Assertions.assertEquals(userToCheck, userController.getUsers().get(2));

    }
}
