package ru.yandex.practicum.filmorate.controller;

import com.sun.jdi.connect.VMStartException;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.service.FilmService;
import ru.yandex.practicum.filmorate.storage.film.FilmStorage;
import ru.yandex.practicum.filmorate.storage.film.InMemoryFilmStorage;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
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

    private final FilmService filmService;

    @Autowired
    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    public FilmService getFilmService() {
        return filmService;
    }

    @PostMapping
    public Film add(@RequestBody @Valid Film film)  {
        log.info("Получен запрос к эндпоинту: PUT: /films");
        try {
            Check.checkFilm(film);
            film = filmService.add(film);
            log.info("Новый фильм успешно добавлен");
            return film;
        } catch (ValidationException exception) {
            log.info("Возникла ошибка: " + exception.getMessage());
            throw new ValidationException(exception.getMessage());
        }
    }

    @PutMapping
    public Film update(@RequestBody @Valid Film film)  {
        log.info("Получен запрос к эндпоинту: PUT: /films");
        try {
            Check.checkFilm(film);
            film = filmService.update(film);
            log.info("Фильм успешно обновлен");
            return film;
        } catch (ValidationException | NullPointerException exception) {
            log.info("Возникла ошибка: " + exception.getMessage());
            throw new ValidationException(exception.getMessage());
        }
    }

    @GetMapping
    public List<Film> getAllFilms() {
        return filmService.getAllFilms();
    }
}
