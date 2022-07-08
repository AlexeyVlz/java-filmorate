/*package ru.yandex.practicum.filmorate.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.objectsForTests.ObjectsUserControllerTest;
import ru.yandex.practicum.filmorate.service.UserService;
import ru.yandex.practicum.filmorate.storage.user.InMemoryUserStorage;
import ru.yandex.practicum.filmorate.storage.user.UserStorage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserControllerTest {

    UserController userController;

    @BeforeEach
    public void beforeEach() {
        userController = new UserController(new UserService(new InMemoryUserStorage()));
    }

    @Test
    public void createTest() {
        User user = ObjectsUserControllerTest.correctUser();
        try {
            userController.create(user);
        } catch (ValidationException e) {
            System.out.println(e.getMessage());
        }
        InMemoryUserStorage userStorage = (InMemoryUserStorage) userController.getUserService().getUserStorage();
        Assertions.assertEquals(user, userStorage.getUsers().get(1));

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
        Assertions.assertEquals(userToCheck, userStorage.getUsers().get(2));
    }

    @Test
    public void updateTest() {
        User user = ObjectsUserControllerTest.correctUser();
        User user2 = ObjectsUserControllerTest.correctUser2();
        try {
            userController.create(user);
            userController.create(user2);
        } catch (ValidationException e) {
            System.out.println(e.getMessage());
        }

        User uncorrectId = ObjectsUserControllerTest.correctUser();
        uncorrectId.setId(111);
        try{
            userController.update(uncorrectId);
        } catch (ValidationException e){
            Assertions.assertEquals(e.getMessage(), "Пользователь не обнаружен");
        }

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

    @Test
    public void getAllUsersRTest() {
        User user = ObjectsUserControllerTest.correctUser();
        User user2 = ObjectsUserControllerTest.correctUser2();
        try {
            userController.create(user);
            userController.create(user2);
        } catch (ValidationException e) {
            System.out.println(e.getMessage());
        }
        List<User> usersList = new ArrayList<>();
        user.setId(1);
        usersList.add(user);
        user2.setId(2);
        usersList.add(user2);
        Assertions.assertEquals(usersList, userController.getAllUsers());

    }

}*/
