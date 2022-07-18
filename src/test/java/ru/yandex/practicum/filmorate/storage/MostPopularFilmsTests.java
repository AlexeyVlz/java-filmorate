package ru.yandex.practicum.filmorate.storage;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.objectsForTests.ObjectsFilmControllerTest;
import ru.yandex.practicum.filmorate.objectsForTests.ObjectsUserControllerTest;
import ru.yandex.practicum.filmorate.storage.film.FilmDBStorage;
import ru.yandex.practicum.filmorate.storage.likes.LikesDbStorage;
import ru.yandex.practicum.filmorate.storage.mostPopularFilms.MostPopularFilmsDbStorage;
import ru.yandex.practicum.filmorate.storage.user.UserDbStorage;

import java.util.ArrayList;
import java.util.Arrays;

@SpringBootTest
@AutoConfigureTestDatabase
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:scriptForTests.sql")
public class MostPopularFilmsTests {

    private final MostPopularFilmsDbStorage mostPopularFilmsDbStorage;
    private final UserDbStorage userDbStorage;
    private final FilmDBStorage filmDBStorage;
    private final LikesDbStorage likesDbStorage;

    @Test
    public void getMostPopularFilmsTest() {
        Film film1 = ObjectsFilmControllerTest.addCorrectFilm();
        Film film2 = ObjectsFilmControllerTest.addCorrectFilm2();
        Film film3 = ObjectsFilmControllerTest.addCorrectFilm3();
        filmDBStorage.add(film1);
        filmDBStorage.add(film2);
        filmDBStorage.add(film3);
        film1.setId(1);
        film2.setId(2);
        film3.setId(3);
        User user1 = ObjectsUserControllerTest.correctUser();
        User user2 = ObjectsUserControllerTest.correctUser2();
        User user3 = ObjectsUserControllerTest.correctUser3();
        userDbStorage.save(user1);
        userDbStorage.save(user2);
        userDbStorage.save(user3);
        likesDbStorage.addLike(1, 1);
        likesDbStorage.addLike(1, 2);
        likesDbStorage.addLike(1, 3);
        likesDbStorage.addLike(2,1);
        likesDbStorage.addLike(2,2);
        likesDbStorage.addLike(3,1);
        Assertions.assertEquals(new ArrayList<>(Arrays.asList(film1, film2, film3)),
                mostPopularFilmsDbStorage.getMostPopularFilms(3));
    }
}
