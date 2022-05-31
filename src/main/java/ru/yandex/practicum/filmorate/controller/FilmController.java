package ru.yandex.practicum.filmorate.controller;

import com.sun.jdi.connect.VMStartException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/films")
public class FilmController {

    private Map<Integer, Film> films = new HashMap<>();

    @PutMapping("/add")
    public Film add(@RequestBody Film film) throws ValidationException {
        log.info("Получен запрос к эндпоинту: GET: /films/add");
        try {
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
            if(film.getDuration().toSeconds() <= 0){
                throw new ValidationException("продолжительность фильма должна быть положительной");
            }
            films.put(film.getId(), film);
            log.info("Новый фильм успешно добавлен");
            return film;
        } catch (ValidationException exception) {
            log.info("Возникла ошибка: " + exception.getMessage());
            throw new ValidationException(exception.getMessage());
        }
    }

    @PutMapping ("/update")
    public Film update(@RequestBody Film film) throws ValidationException {
        log.info("Получен запрос к эндпоинту: PUT: /films/update");
        try {
            if(!films.containsKey(film.getId())){
                throw new NullPointerException("Такой фильм ранее не включался в рейтинг");
            }
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
            if(film.getDuration().toSeconds() <= 0){
                throw new ValidationException("продолжительность фильма должна быть положительной");
            }
            films.put(film.getId(), film);
            log.info("Фильм успешно обновлен");
            return film;
        } catch (ValidationException | NullPointerException exception) {
            log.info("Возникла ошибка: " + exception.getMessage());
            throw new ValidationException(exception.getMessage());
        }
    }

    @GetMapping ("/films")
    public List<Film> getFilms() {
        return new ArrayList<>(films.values());
    }
}
