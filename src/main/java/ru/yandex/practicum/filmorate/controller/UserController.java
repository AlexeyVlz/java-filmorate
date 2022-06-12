package ru.yandex.practicum.filmorate.controller;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.UserService;
import ru.yandex.practicum.filmorate.storage.user.InMemoryUserStorage;
import ru.yandex.practicum.filmorate.storage.user.UserStorage;

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

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    public UserService getUserService() {
        return userService;
    }

    @PostMapping
    public User create(@RequestBody @Valid User user) {
        log.info("Получен запрос к эндпоинту: POST: /users");
        try{
            Check.checkUser(user);
            if(user.getName().isEmpty()) {
                user.setName(user.getLogin());
            }
            user = userService.create(user);
            log.info("Новый пользователь успешно добавлен");
            return user;
        } catch (ValidationException exception) {
            log.info("Возникла ошибка: " + exception.getMessage());
            throw new ValidationException(exception.getMessage());
        }
    }

    @PutMapping
    public User update(@RequestBody @Valid User user) {
        log.info("Получен запрос к эндпоинту: PUT: /users");
        try{
            Check.checkUser(user);
            if(user.getName().isEmpty()) {
                user.setName(user.getLogin());
            }
            user = userService.update(user);
            log.info("Пользователь успешно обновлён");
            return user;
        } catch (ValidationException | NullPointerException exception) {
            log.info("Возникла ошибка: " + exception.getMessage());
            throw new ValidationException(exception.getMessage());
        }
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

}
