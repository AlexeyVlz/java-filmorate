package ru.yandex.practicum.filmorate.storage;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.objectsForTests.ObjectsUserControllerTest;
import ru.yandex.practicum.filmorate.storage.friends.FriendsDbSrorage;
import ru.yandex.practicum.filmorate.storage.user.UserDbStorage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
@AutoConfigureTestDatabase
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:scriptForTests.sql")
public class FriendStorageApplicationTests {

    private final FriendsDbSrorage friendsDbSrorage;
    private final UserDbStorage userDbStorage;

    @Test
    public void deletingFriendTest() {
        User user1 = ObjectsUserControllerTest.correctUser();
        userDbStorage.save(user1);
        user1.setId(1);
        User user2 = ObjectsUserControllerTest.correctUser2();
        userDbStorage.save(user2);
        user2.setId(2);
        friendsDbSrorage.addNewFriend(1, 2);
        friendsDbSrorage.acceptFriendship(1, 2);
        friendsDbSrorage.deletingFriend(1, 2);
        User user = ObjectsUserControllerTest.correctUser3();
        userDbStorage.save(user);
        user.setId(3);
        friendsDbSrorage.addNewFriend(1, 3);
        friendsDbSrorage.acceptFriendship(1, 3);
        friendsDbSrorage.addNewFriend(2, 3);
        friendsDbSrorage.acceptFriendship(2, 3);
        Assertions.assertEquals(friendsDbSrorage.getUserFriends(1), new ArrayList<>(List.of(user)));
        Assertions.assertEquals(friendsDbSrorage.getUserFriends(2), new ArrayList<>(List.of(user)));
    }

    @Test @Transactional
    public void addNewFriendAndGetUserFriendsAndAcceptFriendshipTest () {
        User user1 = ObjectsUserControllerTest.correctUser();
        userDbStorage.save(user1);
        user1.setId(1);
        User user2 = ObjectsUserControllerTest.correctUser2();
        userDbStorage.save(user2);
        user2.setId(2);
        User user3 = ObjectsUserControllerTest.correctUser3();
        userDbStorage.save(user3);
        user3.setId(3);
        friendsDbSrorage.addNewFriend(1, 2);
        friendsDbSrorage.acceptFriendship(1, 2);
        Assertions.assertEquals(friendsDbSrorage.getUserFriends(1), new ArrayList<>(List.of(user2)));
        Assertions.assertEquals(friendsDbSrorage.getUserFriends(2), new ArrayList<>(List.of(user1)));
    }

    @Test
    public void mutualFriendsListTest () {
        User user1 = ObjectsUserControllerTest.correctUser();
        userDbStorage.save(user1);
        user1.setId(1);
        User user2 = ObjectsUserControllerTest.correctUser2();
        userDbStorage.save(user2);
        user2.setId(2);
        User user3 = ObjectsUserControllerTest.correctUser3();
        userDbStorage.save(user3);
        user3.setId(3);
        friendsDbSrorage.addNewFriend(1, 3);
        friendsDbSrorage.acceptFriendship(1, 3);
        friendsDbSrorage.addNewFriend(2, 3);
        friendsDbSrorage.acceptFriendship(2, 3);
        Assertions.assertEquals(friendsDbSrorage.mutualFriendsList(1,2), new ArrayList<>(List.of(user3)));
    }


}
