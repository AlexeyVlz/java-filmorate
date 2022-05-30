package ru.yandex.practicum.filmorate.controller;

import com.sun.jdi.connect.VMStartException;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/films")
public class FilmController {

    Map<Integer, Film> films = new HashMap<>();

    @PutMapping("/add")
    public Film add(@RequestBody Film film) throws ValidationException {
        try {
            if(film.getName().length() < 1 || film.getName().equals("")){
                throw new ValidationException("название не может быть пустым");
            }
            if(film.getDescription().length() > 200){
                throw new ValidationException("максимальная длина описания — 200 символов");
            }
            if(film.getReleaseDate().isBefore(LocalDateTime.of
                    (1895, 12, 28, 0, 0))){
                throw new ValidationException("дата релиза должна быть не раньше 28 декабря 1895 года");
            }
            if(film.getDuration().toSeconds() <= 0){
                throw new ValidationException("продолжительность фильма должна быть положительной");
            }
            films.put(film.getId(), film);
            return film;
        } catch (ValidationException exception) {
            System.out.println(exception.getMessage());
            throw new ValidationException(exception.getMessage());
        }
    }

    @PutMapping ("/update")
    public Film update(@RequestBody Film film) throws ValidationException {
        if(!films.containsKey(film.getId())){
            throw new NullPointerException("Такой фильм ранее не включался в рейтинг");
        }
        try {
            if(film.getName().length() < 1 || film.getName().equals("")){
                throw new ValidationException("название не может быть пустым");
            }
            if(film.getDescription().length() > 200){
                throw new ValidationException("максимальная длина описания — 200 символов");
            }
            if(film.getReleaseDate().isBefore(LocalDateTime.of
                    (1895, 12, 28, 0, 0))){
                throw new ValidationException("дата релиза должна быть не раньше 28 декабря 1895 года");
            }
            if(film.getDuration().toSeconds() <= 0){
                throw new ValidationException("продолжительность фильма должна быть положительной");
            }
            films.put(film.getId(), film);
            return film;
        } catch (ValidationException exception) {
            System.out.println(exception.getMessage());
            throw new ValidationException(exception.getMessage());
        }
    }

    @GetMapping ("/films")
    public Map<Integer, Film> getFilms() {
        return films;
    }
}
