package ru.yandex.practicum.filmorate.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FilmControllerTest {

    FilmController filmController;

    @BeforeEach
    public void beforeEach() {
        filmController = new FilmController();
    }

    @Test
    public void addTest() {
        Film film = ObjectsFilmControllerTest.correctFilm();
        try {
            filmController.add(film);
        } catch (ValidationException e) {
            System.out.println(e.getMessage());
        }
        Assertions.assertEquals(film, filmController.getFilms().get(1));

        Film emptyName = ObjectsFilmControllerTest.emptyNameFilm();
        try {
            filmController.add(emptyName);
        } catch (ValidationException e) {
            Assertions.assertEquals(e.getMessage(), "название не может быть пустым");
        }

        Film tooLongDescription = ObjectsFilmControllerTest.tooLongDescription();
        try {
            filmController.add(tooLongDescription);
        } catch (ValidationException e) {
            Assertions.assertEquals(e.getMessage(), "максимальная длина описания — 200 символов");
        }

        Film errorDateRelease = ObjectsFilmControllerTest.errorDateRelease();
        try {
            filmController.add(errorDateRelease);
        } catch (ValidationException e) {
            Assertions.assertEquals(e.getMessage(), "дата релиза должна быть не раньше 28 декабря 1895 года");
        }

        Film errorDuration = ObjectsFilmControllerTest.errorDuration();
        try {
            filmController.add(errorDuration);
        } catch (ValidationException e) {
            Assertions.assertEquals(e.getMessage(), "продолжительность фильма должна быть положительной");
        }
    }

    @Test
    public void updateTest() {
        Film film = ObjectsFilmControllerTest.correctFilm();
        try {
            filmController.add(film);
        } catch (ValidationException e) {
            System.out.println(e.getMessage());
        }
        Film uncorrectIdByUpdate = ObjectsFilmControllerTest.uncorrectIdByUpdate();
        try {
            filmController.update(uncorrectIdByUpdate);
        } catch (ValidationException e) {
            Assertions.assertEquals(e.getMessage(), "Такой фильм ранее не включался в рейтинг");
        }

        Film correctIdByUpdate = ObjectsFilmControllerTest.correctIdByUpdate();
        try {
            filmController.update(correctIdByUpdate);
        } catch (ValidationException e) {
            System.out.println(e.getMessage());
        }
        Assertions.assertEquals(correctIdByUpdate, filmController.getFilms().get(1));

        Film emptyName = ObjectsFilmControllerTest.emptyNameFilm();
        try {
            filmController.update(emptyName);
        } catch (ValidationException e) {
            Assertions.assertEquals(e.getMessage(), "название не может быть пустым");
        }

        Film tooLongDescription = ObjectsFilmControllerTest.tooLongDescription();
        try {
            filmController.update(tooLongDescription);
        } catch (ValidationException e) {
            Assertions.assertEquals(e.getMessage(), "максимальная длина описания — 200 символов");
        }

        Film errorDateRelease = ObjectsFilmControllerTest.errorDateRelease();
        try {
            filmController.update(errorDateRelease);
        } catch (ValidationException e) {
            Assertions.assertEquals(e.getMessage(), "дата релиза должна быть не раньше 28 декабря 1895 года");
        }

        Film errorDuration = ObjectsFilmControllerTest.errorDuration();
        try {
            filmController.update(errorDuration);
        } catch (ValidationException e) {
            Assertions.assertEquals(e.getMessage(), "продолжительность фильма должна быть положительной");
        }
    }

    @Test
    public void getFilmsTest() {
        List<Film> films = new ArrayList<>();
        Assertions.assertEquals(films, filmController.getFilms());
        films.add(ObjectsFilmControllerTest.correctFilm());
        films.add(ObjectsFilmControllerTest.correctFilm2());
        try {
            filmController.add(ObjectsFilmControllerTest.correctFilm());
            filmController.add(ObjectsFilmControllerTest.correctFilm2());
        } catch (ValidationException e) {
            System.out.println(e.getMessage());
        }
        Assertions.assertEquals(films, filmController.getFilms());
    }
}
