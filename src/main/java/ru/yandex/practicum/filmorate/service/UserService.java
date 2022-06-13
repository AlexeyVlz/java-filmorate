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

    public User addNewFriend(Integer myId, Integer newFriendId) throws NullPointerException{
        User myAccount = getUserById(myId);
        myAccount.getFriendsList().add(newFriendId);
        userStorage.update(myAccount);
        User newFriendAccount = getUserById(newFriendId);
        newFriendAccount.getFriendsList().add(myId);
        userStorage.update(newFriendAccount);
        return myAccount;
    }

    public User deletingFriend(Integer myId, Integer newFriendId) throws NullPointerException {
        User myAccount = getUserById(myId);
        myAccount.getFriendsList().remove(newFriendId);
        userStorage.update(myAccount);
        User newFriendAccount = getUserById(newFriendId);
        newFriendAccount.getFriendsList().remove(myId);
        userStorage.update(newFriendAccount);
        return myAccount;
    }

    public List<Integer> mutualFriendsList(Integer myId, Integer otherId) throws NullPointerException {
        User user = getUserById(myId);
        User userFriend = getUserById(otherId);
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

    public User getUserById(Integer id) {
        if(!((InMemoryUserStorage) userStorage).getUsers().containsKey(id)){
            throw new NullPointerException("Пользователь не обнаружен");
        }
        return ((InMemoryUserStorage) userStorage).getUsers().get(id);
    }
}
