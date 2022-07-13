package ru.yandex.practicum.filmorate.storage;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import ru.yandex.practicum.filmorate.objectsForTests.ObjectsFilmControllerTest;
import ru.yandex.practicum.filmorate.objectsForTests.ObjectsUserControllerTest;
import ru.yandex.practicum.filmorate.storage.film.FilmDBStorage;
import ru.yandex.practicum.filmorate.storage.likes.LikesDbStorage;
import ru.yandex.practicum.filmorate.storage.user.UserDbStorage;

@SpringBootTest
@AutoConfigureTestDatabase
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:scriptForTests.sql")
public class LikesStorageApplicationTests {

    private final LikesDbStorage likesDbStorage;
    private final UserDbStorage userDbStorage;
    private final FilmDBStorage filmDBStorage;

    @Test
    public void addAndRemoveLikeTest() {
        userDbStorage.save(ObjectsUserControllerTest.correctUser());
        userDbStorage.save(ObjectsUserControllerTest.correctUser2());
        filmDBStorage.add(ObjectsFilmControllerTest.addCorrectFilm());
        likesDbStorage.addLike(1, 1);
        likesDbStorage.addLike(1, 2);
        Assertions.assertEquals(likesDbStorage.getCountLikes(1), 2);
        likesDbStorage.removeLike(1, 1);
        Assertions.assertEquals(likesDbStorage.getCountLikes(1), 1);
    }





}
