package ru.yandex.practicum.filmorate.controller;

import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.User;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    Map<Integer, User> users = new HashMap<>();

    @PutMapping ("/create")
    public User create(@RequestBody User user) throws ValidationException {
        try{
            if(user.getEmail().length() < 1 || user.getEmail().equals("") || user.getEmail().contains("@")) {
                throw new ValidationException("Введите корректный адрес электронной почты");
            }
            if(user.getLogin().length() < 1 || user.getLogin().contains(" ")){
                throw new ValidationException("Введите корректный логин (логин не может содержать пробелы)");
            }
            if(user.getBirthday().isAfter(LocalDateTime.now())) {
                throw new ValidationException("Дата рождения не может быть позже текущего времени");
            }
            if(user.getName().isEmpty()) {
                user.setName(user.getLogin());
            }
            users.put(user.getId(), user);
            return user;
        } catch (ValidationException exception) {
            throw new ValidationException(exception.getMessage());
        }
    }

    @PutMapping ("/update")
    public User update(@RequestBody User user) throws ValidationException {
        if (!users.containsKey(user.getId())){
            throw new NullPointerException("Пользователь не обнаружен");
        }
        try{
            if(user.getEmail().length() < 1 || user.getEmail().equals("") || user.getEmail().contains("@")) {
                throw new ValidationException("Введите корректный адрес электронной почты");
            }
            if(user.getLogin().length() < 1 || user.getLogin().contains(" ")){
                throw new ValidationException("Введите корректный логин (логин не может содержать пробелы)");
            }
            if(user.getBirthday().isAfter(LocalDateTime.now())) {
                throw new ValidationException("Дата рождения не может быть позже текущего времени");
            }
            if(user.getName().isEmpty()) {
                user.setName(user.getLogin());
            }
            users.put(user.getId(), user);
            return user;
        } catch (ValidationException exception) {
            System.out.println(exception.getMessage());
            throw new ValidationException(exception.getMessage());
        }
    }

    @GetMapping("/users")
    public Map<Integer, User> getUsers() {
        return users;
    }

}
