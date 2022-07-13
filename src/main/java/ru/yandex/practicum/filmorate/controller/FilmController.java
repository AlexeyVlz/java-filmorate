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
        log.info("Получен запрос к эндпоинту: POST: /films");
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
            if(film.getId() < 0){
                throw new NullPointerException("Некорректно задан id фильма");
            }
            Check.checkFilm(film);
            Film film1 = filmService.update(film);
            log.info("Фильм успешно обновлен");
            return film1;
        } catch (ValidationException exception) {
            log.info("Возникла ошибка: " + exception.getMessage());
            throw new ValidationException(exception.getMessage());
        } catch (NullPointerException exception) {
            log.info("Возникла ошибка: " + exception.getMessage());
            throw new NullPointerException(exception.getMessage());
        }
    }

    @GetMapping
    public List<Film> getAllFilms() {
        return filmService.getAllFilms();
    }

    @GetMapping ("/{id}")
    public Film getFilmById (@PathVariable("id") Integer id) {
        log.info("Получен запрос к эндпоинту: GET: /films/{id}");
        try {
            return filmService.getFilmById(id);
        } catch (NullPointerException exception){
            log.info("Возникла ошибка: " + exception.getMessage());
            throw new NullPointerException(exception.getMessage());
        }

    }

    @PutMapping ("/{id}/like/{userId}")
    public void addLike (@PathVariable("id") Integer id, @PathVariable("userId") Integer userId) {
        log.info("Получен запрос к эндпоинту: PUT: /films/{id}/like/{userId}");
        try {
            filmService.addLike(id, userId);
        } catch (NullPointerException exception){
            log.info("Возникла ошибка: " + exception.getMessage());
            throw new NullPointerException(exception.getMessage());
        }
    }

    @DeleteMapping ("/{id}/like/{userId}")
    public boolean removeLike(@PathVariable Integer id, @PathVariable Integer userId) {
        log.info("Получен запрос к эндпоинту: DELETE: /films/{id}/like/{userId}");
        try {
            return filmService.removeLike(id, userId);
        } catch (NullPointerException exception){
            log.info("Возникла ошибка: " + exception.getMessage());
            throw new NullPointerException(exception.getMessage());
        }
    }

    @GetMapping ("/popular")
    public List<Film> mostPopularFilms (@RequestParam(required = false) Integer count) {
        if(count == null) {
            count = 10;
        }
        if(count <= 0){
            throw new ValidationException("Количество фильмов должно быть больше нуля");
        }
        return filmService.mostPopularFilms(count);
    }

    @DeleteMapping ("/{id}")
    public boolean removeFilmById(@PathVariable Integer id) {
        try{
            return filmService.removeFilmById(id);
        } catch (NullPointerException exception){
            log.info("Возникла ошибка: " + exception.getMessage());
            throw new NullPointerException(exception.getMessage());
        }
    }
}
