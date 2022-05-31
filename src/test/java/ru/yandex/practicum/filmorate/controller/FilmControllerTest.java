package ru.yandex.practicum.filmorate.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.objectsForTests.ObjectsFilmControllerTest;

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
        Film film = ObjectsFilmControllerTest.addCorrectFilm();
        try {
            filmController.add(film);
        } catch (ValidationException e) {
            System.out.println(e.getMessage());
        }
        Assertions.assertEquals(film, filmController.getFilms().get(1));

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
        Film film = ObjectsFilmControllerTest.addCorrectFilm();
        try {
            filmController.add(film);
        } catch (ValidationException e) {
            System.out.println(e.getMessage());
        }

        Film uncorrectIdByUpdate = ObjectsFilmControllerTest.uncorrectIdByUpdate();
        uncorrectIdByUpdate.setId(1);
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

    @Test
    public void getFilmsTest() {
        List<Film> films = new ArrayList<>();
        Assertions.assertEquals(films, filmController.getAllFilms());
        Film film1 = ObjectsFilmControllerTest.addCorrectFilm();
        film1.setId(1);
        films.add(film1);
        Film film2 = ObjectsFilmControllerTest.addCorrectFilm2();
        film2.setId(2);
        films.add(film2);
        try {
            filmController.add(ObjectsFilmControllerTest.addCorrectFilm());
            filmController.add(ObjectsFilmControllerTest.addCorrectFilm2());
        } catch (ValidationException e) {
            System.out.println(e.getMessage());
        }
        Assertions.assertEquals(films, filmController.getAllFilms());
    }
}
