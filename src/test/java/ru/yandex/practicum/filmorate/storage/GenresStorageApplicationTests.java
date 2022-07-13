package ru.yandex.practicum.filmorate.storage;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.FilmGenres;
import ru.yandex.practicum.filmorate.objectsForTests.ObjectsFilmControllerTest;
import ru.yandex.practicum.filmorate.storage.film.FilmDBStorage;
import ru.yandex.practicum.filmorate.storage.genres.GenresDBStorage;

import java.util.ArrayList;
import java.util.Arrays;

@SpringBootTest
@AutoConfigureTestDatabase
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:scriptForTests.sql")
public class GenresStorageApplicationTests {

    private final GenresDBStorage genresDBStorage;
    private final FilmDBStorage filmDBStorage;

    @Test
    public void addAndGetTest() {
        Film film = ObjectsFilmControllerTest.CorrectFilmWithGenres();
        genresDBStorage.addGenres(filmDBStorage.add(film));
        Assertions.assertEquals(new ArrayList<>(Arrays.asList(
                            new FilmGenres(2, "Драма"),
                            new FilmGenres(5, "Боевик"))), genresDBStorage.getFilmGenres(1));
    }
}
