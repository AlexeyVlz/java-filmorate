package ru.yandex.practicum.filmorate.controller;

import com.sun.jdi.connect.VMStartException;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;

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

    private final Map<Integer, Film> films;
    private int id;

    public FilmController() {
        this.films = new HashMap<>();
        this.id = 0;
    }

    public Map<Integer, Film> getFilms() {
        return films;
    }

    @PostMapping
    public Film add(@RequestBody @Valid Film film)  {
        log.info("Получен запрос к эндпоинту: PUT: /films");
        try {
            Check.checkFilm(film);
            film.setId(++id);
            films.put(film.getId(), film);
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
            if(!films.containsKey(film.getId())){
                throw new NullPointerException("Такой фильм ранее не включался в рейтинг");
            }
            Check.checkFilm(film);
            films.put(film.getId(), film);
            log.info("Фильм успешно обновлен");
            return film;
        } catch (ValidationException | NullPointerException exception) {
            log.info("Возникла ошибка: " + exception.getMessage());
            throw new ValidationException(exception.getMessage());
        }
    }

    @GetMapping
    public List<Film> getAllFilms() {
        return new ArrayList<>(films.values());
    }
}
