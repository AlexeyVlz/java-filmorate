package ru.yandex.practicum.filmorate.storage.friends;

import ru.yandex.practicum.filmorate.model.User;

import java.util.List;

public interface FriendStorage {

    void addNewFriend(Integer userId, Integer newFriendId);

    boolean deletingFriend(Integer userID, Integer newFriendId);

    List<User> mutualFriendsList(Integer userId, Integer otherId);

    List<User> getUserFriends(Integer id);
}
