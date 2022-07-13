package ru.yandex.practicum.filmorate.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.objectsForTests.ObjectsFilmControllerTest;
import ru.yandex.practicum.filmorate.service.FilmService;
import ru.yandex.practicum.filmorate.storage.film.FilmDBStorage;
import ru.yandex.practicum.filmorate.storage.film.FilmStorage;
import ru.yandex.practicum.filmorate.storage.genres.GenresDBStorage;
import ru.yandex.practicum.filmorate.storage.genres.GenresStorage;
import ru.yandex.practicum.filmorate.storage.likes.LikesDbStorage;
import ru.yandex.practicum.filmorate.storage.mostPopularFilms.MostPopularFilmsDbStorage;
import ru.yandex.practicum.filmorate.storage.mpa.MpaDbStorage;

import java.util.ArrayList;
import java.util.List;

public class FilmControllerTest {

    FilmController filmController;

    @BeforeEach
    public void beforeEach() {
        filmController = new FilmController(new FilmService(new FilmDBStorage(new JdbcTemplate(),
                new GenresDBStorage(new JdbcTemplate())),
                new LikesDbStorage(new JdbcTemplate()), new MostPopularFilmsDbStorage(new JdbcTemplate()),
                        new GenresDBStorage(new JdbcTemplate()), new MpaDbStorage(new JdbcTemplate())));
    }

    @Test
    public void addTest() {
        Film emptyName = ObjectsFilmControllerTest.addEmptyNameFilm();
        try {
            filmController.add(emptyName);
        } catch (ValidationException e) {
            Assertions.assertEquals(e.getMessage(), "название не может быть пустым");
        }

        Film tooLongDescription = ObjectsFilmControllerTest.addTooLongDescription();
        try {
            filmController.add(tooLongDescription);
        } catch (ValidationException e) {
            Assertions.assertEquals(e.getMessage(), "максимальная длина описания — 200 символов");
        }

        Film errorDateRelease = ObjectsFilmControllerTest.addErrorDateRelease();
        try {
            filmController.add(errorDateRelease);
        } catch (ValidationException e) {
            Assertions.assertEquals(e.getMessage(), "дата релиза должна быть не раньше 28 декабря 1895 года");
        }

        Film errorDuration = ObjectsFilmControllerTest.addErrorDuration();
        try {
            filmController.add(errorDuration);
        } catch (ValidationException e) {
            Assertions.assertEquals(e.getMessage(), "продолжительность фильма должна быть положительной");
        }
    }

    @Test
    public void updateTest() {
        Film emptyName = ObjectsFilmControllerTest.addEmptyNameFilm();
        emptyName.setId(1);
        try {
            filmController.update(emptyName);
        } catch (ValidationException e) {
            Assertions.assertEquals(e.getMessage(), "название не может быть пустым");
        }

        Film tooLongDescription = ObjectsFilmControllerTest.addTooLongDescription();
        tooLongDescription.setId(1);
        try {
            filmController.update(tooLongDescription);
        } catch (ValidationException e) {
            Assertions.assertEquals(e.getMessage(), "максимальная длина описания — 200 символов");
        }

        Film errorDateRelease = ObjectsFilmControllerTest.addErrorDateRelease();
        errorDateRelease.setId(1);
        try {
            filmController.update(errorDateRelease);
        } catch (ValidationException e) {
            Assertions.assertEquals(e.getMessage(), "дата релиза должна быть не раньше 28 декабря 1895 года");
        }

        Film errorDuration = ObjectsFilmControllerTest.addErrorDuration();
        errorDuration.setId(1);
        try {
            filmController.update(errorDuration);
        } catch (ValidationException e) {
            Assertions.assertEquals(e.getMessage(), "продолжительность фильма должна быть положительной");
        }
    }


}
