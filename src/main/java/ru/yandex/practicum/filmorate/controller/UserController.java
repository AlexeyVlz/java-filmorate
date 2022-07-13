package ru.yandex.practicum.filmorate.controller;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.UserService;
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
        } catch (NullPointerException exception){
            log.info("Возникла ошибка: " + exception.getMessage());
            throw new NullPointerException(exception.getMessage());
        }
    }

    @PutMapping
    public User update(@RequestBody @Valid User user) {
        log.info("Получен запрос к эндпоинту: PUT: /users");
        if(user.getId() <= 0){
            throw new NullPointerException("Некорректно задан id пользователя");
        }
        try{
            Check.checkUser(user);
            if(user.getName().isEmpty()) {
                user.setName(user.getLogin());
            }
            User user1 = userService.update(user);
            log.info("Пользователь успешно обновлён");
            return user1;
        } catch (ValidationException exception) {
            log.info("Возникла ошибка: " + exception.getMessage());
            throw new ValidationException(exception.getMessage());
        } catch (NullPointerException exception){
            log.info("Возникла ошибка: " + exception.getMessage());
            throw new NullPointerException(exception.getMessage());
        }
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping ("/{id}")
    public  User getUserById (@PathVariable Integer id){
        log.info("Получен запрос к эндпоинту: GET: /users/{id}");
        try {
            if(id <= 0){
                throw new NullPointerException("Некорректно задан id пользователя");
            }
            return userService.getUserById(id);
        } catch (NullPointerException exception){
            log.info("Возникла ошибка: " + exception.getMessage());
            throw new NullPointerException(exception.getMessage());
        }
    }

    @PutMapping ("/{id}/friends/{friendId}")
    public void addNewFriend (@PathVariable Integer id, @PathVariable Integer friendId) {
        log.info("Получен запрос к эндпоинту: PUT: /users/{id}/friends/{friendId}");
        if(id <= 0 || friendId <= 0){
            throw new NullPointerException("Некорректно задан id пользователя / id нового друга");
        }
        try{
            userService.addNewFriend(id, friendId);
        } catch (NullPointerException exception){
            log.info("Возникла ошибка: " + exception.getMessage());
            throw new NullPointerException(exception.getMessage());
        }
    }

    @DeleteMapping ("{id}/friends/{friendId}")
    public boolean deletingFriend (@PathVariable Integer id, @PathVariable Integer friendId){
        log.info("Получен запрос к эндпоинту: DELETE: /users/{id}/friends/{friendId}");
        try{
            return userService.deletingFriend(id, friendId);
        } catch (NullPointerException exception){
            log.info("Возникла ошибка: " + exception.getMessage());
            throw new NullPointerException(exception.getMessage());
        }
    }

    @GetMapping ("{id}/friends/common/{otherId}")
    public List<User> mutualFriendsList (@PathVariable Integer id, @PathVariable Integer otherId) {
        log.info("Получен запрос к эндпоинту: GET: /users/{id}/friends/common/{otherId}");
        try{
            return userService.mutualFriendsList(id, otherId);
        } catch (NullPointerException exception){
            log.info("Возникла ошибка: " + exception.getMessage());
            throw new NullPointerException(exception.getMessage());
        }
    }

    @GetMapping ("{id}/friends")
    public List<User> getUserFriends (@PathVariable Integer id) {
        log.info("Получен запрос к эндпоинту: GET: /users/{id}/friends");
        try{
            return userService.getUserFriends(id);
        } catch (NullPointerException exception){
            log.info("Возникла ошибка: " + exception.getMessage());
            throw new NullPointerException(exception.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public boolean removeUserById(@PathVariable Integer id) {
        try{
            return userService.removeUserById(id);
        } catch (NullPointerException exception){
            log.info("Возникла ошибка: " + exception.getMessage());
            throw new NullPointerException(exception.getMessage());
        }
    }

}
