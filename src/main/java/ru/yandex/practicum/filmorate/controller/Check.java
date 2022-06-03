package ru.yandex.practicum.filmorate.controller;

import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;

import java.time.LocalDate;

public class Check {

    public static void checkUser(User user) throws ValidationException {
        if(user.getEmail().length() < 1 || user.getEmail().equals("") || !user.getEmail().contains("@")) {
            throw new ValidationException("Введите корректный адрес электронной почты");
        }
        if(user.getLogin().length() < 1 || user.getLogin().contains(" ")){
            throw new ValidationException("Введите корректный логин (логин не может содержать пробелы)");
        }
        if(user.getBirthday().isAfter(LocalDate.now())) {
            throw new ValidationException("Дата рождения не может быть позже текущего времени");
        }
    }

    public static void checkFilm(Film film) {
        if(film.getName().length() < 1 || film.getName().equals("")){
            throw new ValidationException("название не может быть пустым");
        }
        if(film.getDescription().length() > 200){
            throw new ValidationException("максимальная длина описания — 200 символов");
        }
        if(film.getReleaseDate().isBefore(LocalDate.of
                (1895, 12, 28))){
            throw new ValidationException("дата релиза должна быть не раньше 28 декабря 1895 года");
        }
        if(film.getDuration() <= 0){
            throw new ValidationException("продолжительность фильма должна быть положительной");
        }
    }

}
