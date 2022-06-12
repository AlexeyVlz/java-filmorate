package ru.yandex.practicum.filmorate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.user.InMemoryUserStorage;
import ru.yandex.practicum.filmorate.storage.user.UserStorage;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class UserService {

    UserStorage userStorage;

    @Autowired
    public UserService (UserStorage inMemoryUserStorage) {
        this.userStorage = inMemoryUserStorage;
    }

    public UserStorage getUserStorage() {
        return userStorage;
    }

    public void addNewFriend(Integer myId, Integer newFriendId) throws NullPointerException{
        User myAccount = ((InMemoryUserStorage) userStorage).getUsers().get(myId);
        myAccount.getFriendsList().add(newFriendId);
        userStorage.update(myAccount);
        User newFriendAccount = ((InMemoryUserStorage) userStorage).getUsers().get(newFriendId);
        newFriendAccount.getFriendsList().add(myId);
        userStorage.update(newFriendAccount);
    }

    public void deletingFriend(Integer myId, Integer newFriendId) throws NullPointerException {
        User myAccount = ((InMemoryUserStorage) userStorage).getUsers().get(myId);
        myAccount.getFriendsList().remove(newFriendId);
        userStorage.update(myAccount);
        User newFriendAccount = ((InMemoryUserStorage) userStorage).getUsers().get(newFriendId);
        newFriendAccount.getFriendsList().remove(myId);
        userStorage.update(newFriendAccount);
    }

    public List<Integer> mutualFriendsList(Integer myId, Integer newFriendId) throws NullPointerException {
        User user = ((InMemoryUserStorage) userStorage).getUsers().get(myId);
        User userFriend = ((InMemoryUserStorage) userStorage).getUsers().get(newFriendId);
        List<Integer> firstList;
        Set<Integer> secondList;
        if(userFriend.getFriendsList().size() <= user.getFriendsList().size()){
            firstList = new ArrayList<>(userFriend.getFriendsList());
            secondList = user.getFriendsList();
        } else {
            firstList = new ArrayList<>(user.getFriendsList());
            secondList = userFriend.getFriendsList();
        }
        for (int i = firstList.size() - 1; i >= 0; i--) {
            int size = secondList.size();
            secondList.add(firstList.get(i));
            if(secondList.size() > size){
                firstList.remove(firstList.get(i));
            }
        }
        return firstList;
    }

    public User create(User user){
        return userStorage.create(user);
    }

    public User update(User user) {
        return userStorage.update(user);
    }

    public List<User> getAllUsers() {
        return userStorage.getAllUsers();
    }
}
