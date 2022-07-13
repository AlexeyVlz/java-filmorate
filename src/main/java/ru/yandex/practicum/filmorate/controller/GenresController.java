package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.filmorate.model.FilmGenres;
import ru.yandex.practicum.filmorate.service.FilmService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/genres")
public class GenresController {

    private final FilmService filmService;

    @Autowired
    public GenresController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping
    public List<FilmGenres> getAllGenres() {
        log.info("Получен запрос к эндпоинту: GET: /genres");
        return filmService.getAllGenres();
    }

    @GetMapping("/{id}")
    public FilmGenres getGenre(@PathVariable("id") Integer id) {
        log.info("Получен запрос к эндпоинту: GET: /genres/{id}");
        try{
            if(id <= 0) {
                throw new NullPointerException("неверно задан id объекта genre");
            }
            return filmService.getGenre(id);
        } catch (NullPointerException exception){
            log.info("Возникла ошибка: " + exception.getMessage());
            throw new NullPointerException(exception.getMessage());
        }
    }
}
