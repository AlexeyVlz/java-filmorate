package ru.yandex.practicum.filmorate.storage;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.objectsForTests.ObjectsFilmControllerTest;
import ru.yandex.practicum.filmorate.storage.film.FilmDBStorage;
import ru.yandex.practicum.filmorate.storage.genres.GenresDBStorage;
import ru.yandex.practicum.filmorate.storage.genres.GenresStorage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
@AutoConfigureTestDatabase
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class FilmStorageApplicationTests {

    private final FilmDBStorage filmStorage;

    @Test
    public void testAdd() {
        Film film = ObjectsFilmControllerTest.addCorrectFilm();
        filmStorage.add(film);
        film.setId(1);
        Assertions.assertEquals(film, filmStorage.getFilmById(1));
    }

    @Test
    public void testGetFilmById() {
        Film film = ObjectsFilmControllerTest.addCorrectFilm();
        film.setId(1);
        Assertions.assertEquals(film, filmStorage.getFilmById(1));
    }

    @Test
    public void testGetAllFilms() {
        Film film1 = ObjectsFilmControllerTest.addCorrectFilm();
        film1.setId(1);
        Film film2 = ObjectsFilmControllerTest.addCorrectFilm2();
        filmStorage.add(film2);
        film2.setId(2);
        List<Film> films = new ArrayList<>(Arrays.asList(film1, film2));
        Assertions.assertEquals(films, filmStorage.getAllFilms());
    }

    @Test
    public void testUpdate() {
        Film film = ObjectsFilmControllerTest.addCorrectFilm2();
        film.setId(1);
        filmStorage.update(film);
        Assertions.assertEquals(film, filmStorage.getFilmById(1));
    }
}
