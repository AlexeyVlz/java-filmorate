package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.filmorate.model.FilmMpa;
import ru.yandex.practicum.filmorate.service.FilmService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/mpa")
public class MpaController {

    private final FilmService filmService;

    @Autowired
    public MpaController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping
    public List<FilmMpa> getAllMpa() {
        log.info("Получен запрос к эндпоинту: GET: /mpa");
        return filmService.getAllMpa();
    }

    @GetMapping("/{id}")
    public FilmMpa getMpaById(@PathVariable("id") Integer id) {
        log.info("Получен запрос к эндпоинту: GET: /mpa/{id}");
        try{
            if(id <= 0) {
                throw new NullPointerException("неверно задан id объекта MPA");
            }
            return filmService.getMpaById(id);
        } catch (NullPointerException exception){
            log.info("Возникла ошибка: " + exception.getMessage());
            throw new NullPointerException(exception.getMessage());
        }
    }
}
