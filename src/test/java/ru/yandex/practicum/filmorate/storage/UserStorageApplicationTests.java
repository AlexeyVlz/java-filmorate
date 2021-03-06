package ru.yandex.practicum.filmorate.storage;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.objectsForTests.ObjectsUserControllerTest;
import ru.yandex.practicum.filmorate.storage.film.FilmDBStorage;
import ru.yandex.practicum.filmorate.storage.user.UserDbStorage;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@AutoConfigureTestDatabase
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:scriptForTests.sql")
public class UserStorageApplicationTests {

    private final UserDbStorage userStorage;

    @Test
    public void testSaveAndGetUserById() {
        User user = ObjectsUserControllerTest.correctUser();
        userStorage.save(user);
        user.setId(1);
        Assertions.assertEquals(userStorage.getUserById(1), user);
    }

    @Test
    public void testGetAllUsers(){
        List<User> users = new ArrayList<>();
        User user1 = ObjectsUserControllerTest.correctUser();
        userStorage.save(user1); // new
        user1.setId(1);
        User user2 = ObjectsUserControllerTest.correctUser2();
        userStorage.save(user2);
        user2.setId(2);
        users.add(user1);
        users.add(user2);
        Assertions.assertEquals(users, userStorage.getAllUsers());
    }

    @Test
    public void testUpdateUser() {
        User user2 = ObjectsUserControllerTest.correctUser2(); //new
        userStorage.save(user2); //new
        User user = ObjectsUserControllerTest.correctUser2().toBuilder().email("333@yandex.ru").build();
        user.setId(1);
        Assertions.assertEquals(user, userStorage.getUserById(1));
    }

    @Test
    public void testRemoveUserById() {
        User user = ObjectsUserControllerTest.correctUser();
        userStorage.save(user); //new
        user.setId(1);
        List<User> users = new ArrayList<>(List.of(user));
        User user2 = ObjectsUserControllerTest.correctUser2(); //new
        userStorage.save(user2);
        userStorage.removeUserById(2);
        Assertions.assertEquals(users, userStorage.getAllUsers());
    }
}
