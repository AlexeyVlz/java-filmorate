package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.User;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

    private final Map<Integer, User> users = new HashMap<>();


    public Map<Integer, User> getUsers() {
        return users;
    }

    @PutMapping ("/create")
    public User create(@RequestBody User user) throws ValidationException {
        log.info("Получен запрос к эндпоинту: PUT: /users/create");
        try{
            if(user.getEmail().length() < 1 || user.getEmail().equals("") || !user.getEmail().contains("@")) {
                throw new ValidationException("Введите корректный адрес электронной почты");
            }
            if(user.getLogin().length() < 1 || user.getLogin().contains(" ")){
                throw new ValidationException("Введите корректный логин (логин не может содержать пробелы)");
            }
            if(user.getBirthday().isAfter(LocalDate.now())) {
                throw new ValidationException("Дата рождения не может быть позже текущего времени");
            }
            if(user.getName().isEmpty()) {
                user.setName(user.getLogin());
            }
            users.put(user.getId(), user);
            log.info("Новый пользователь успешно добавлен");
            return user;
        } catch (ValidationException exception) {
            log.info("Возникла ошибка: " + exception.getMessage());
            throw new ValidationException(exception.getMessage());
        }
    }

    @PutMapping ("/update")
    public User update(@RequestBody User user) throws ValidationException {
        log.info("Получен запрос к эндпоинту: PUT: /users/update");
        try{
            if (!users.containsKey(user.getId())){
                throw new NullPointerException("Пользователь не обнаружен");
            }
            if(user.getEmail().length() < 1 || user.getEmail().equals("") || user.getEmail().contains("@")) {
                throw new ValidationException("Введите корректный адрес электронной почты");
            }
            if(user.getLogin().length() < 1 || user.getLogin().contains(" ")){
                throw new ValidationException("Введите корректный логин (логин не может содержать пробелы)");
            }
            if(user.getBirthday().isAfter(LocalDate.now())) {
                throw new ValidationException("Дата рождения не может быть позже текущего времени");
            }
            if(user.getName().isEmpty()) {
                user.setName(user.getLogin());
            }
            users.put(user.getId(), user);
            log.info("Пользователь успешно обновлён");
            return user;
        } catch (ValidationException | NullPointerException exception) {
            log.info("Возникла ошибка: " + exception.getMessage());
            System.out.println(exception.getMessage());
            throw new ValidationException(exception.getMessage());
        }
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return new ArrayList<>(users.values());
    }

}
