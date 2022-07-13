package ru.yandex.practicum.filmorate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.friends.FriendStorage;
import ru.yandex.practicum.filmorate.storage.user.UserStorage;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Service
public class UserService {

    private final UserStorage userStorage;
    private final FriendStorage friendStorage;

    @Autowired
    public UserService (UserStorage inMemoryUserStorage, FriendStorage friendStorage) {
        this.userStorage = inMemoryUserStorage;
        this.friendStorage = friendStorage;
    }

    public UserStorage getUserStorage() {
        return userStorage;
    }

    public void addNewFriend(Integer userId, Integer newFriendId) {
        friendStorage.addNewFriend(userId, newFriendId);
    }

    public boolean deletingFriend(Integer userID, Integer newFriendId) {
        return friendStorage.deletingFriend(userID, newFriendId);
    }

    public List<User> mutualFriendsList(Integer userId, Integer otherId) {
        return friendStorage.mutualFriendsList(userId, otherId);
    }

    public User create(User user){
        return userStorage.save(user);
    }

    public User update(User user) {
        return userStorage.update(user);
    }

    public List<User> getAllUsers() {
        return userStorage.getAllUsers();
    }

    public List<User> getUserFriends(Integer id) {
        return friendStorage.getUserFriends(id);
    }

    public User getUserById(Integer id) {
        return userStorage.getUserById(id);
    }

    public boolean removeUserById(Integer id) throws NullPointerException {
        return userStorage.removeUserById(id);
    }
}
