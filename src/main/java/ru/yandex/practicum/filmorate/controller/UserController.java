package ru.yandex.practicum.filmorate.controller;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.OutputStream;
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

    private final Map<Integer, User> users;
    private int id;

    public UserController() {
        this.users = new HashMap<>();
        this.id = 0;
    }

    public Map<Integer, User> getUsers() {
        return users;
    }

    @PostMapping
    public User create(@RequestBody @Valid User user) throws ValidationException {
        log.info("Получен запрос к эндпоинту: POST: /users");
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
            user.setId(++id);
            users.put(user.getId(), user);
            log.info("Новый пользователь успешно добавлен");
            return user;
        } catch (ValidationException exception) {
            log.info("Возникла ошибка: " + exception.getMessage());
            throw new ValidationException(exception.getMessage());
        }
    }

    @PutMapping
    public User update(@RequestBody @Valid User user) throws ValidationException {
        log.info("Получен запрос к эндпоинту: PUT: /users");
        try{
            if (!users.containsKey(user.getId())){
                throw new NullPointerException("Пользователь не обнаружен");
            }
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

            log.info("Пользователь успешно обновлён");
            return user;
        } catch (ValidationException | NullPointerException exception) {
            log.info("Возникла ошибка: " + exception.getMessage());
            throw new ValidationException(exception.getMessage());
        }
    }

    @GetMapping
    public List<User> getAllUsers() {
        log.info("Получен запрос к эндпоинту: GET: /users");
        log.info("Список пользователей отправлен");
        return new ArrayList<>(users.values());
    }

}
