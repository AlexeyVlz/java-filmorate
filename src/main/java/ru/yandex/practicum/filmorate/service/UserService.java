package ru.yandex.practicum.filmorate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.user.InMemoryUserStorage;
import ru.yandex.practicum.filmorate.storage.user.UserStorage;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

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

    public User addNewFriend(Integer myId, Integer newFriendId) {
        try {
            if(!((InMemoryUserStorage) userStorage).getUsers().containsKey(myId)
            || !((InMemoryUserStorage) userStorage).getUsers().containsKey(newFriendId)){
                throw new NullPointerException();
            }
            User myAccount = getUserById(myId);
            myAccount.getFriendsList().add(newFriendId);
            userStorage.update(myAccount);
            User newFriendAccount = getUserById(newFriendId);
            newFriendAccount.getFriendsList().add(myId);
            userStorage.update(newFriendAccount);
            return myAccount;
        } catch (NullPointerException exception){
            throw new NullPointerException("Пользователь не найден");
        }
    }

    public User deletingFriend(Integer myId, Integer newFriendId) {
        User myAccount = getUserById(myId);
        myAccount.getFriendsList().remove(newFriendId);
        userStorage.update(myAccount);
        User newFriendAccount = getUserById(newFriendId);
        newFriendAccount.getFriendsList().remove(myId);
        userStorage.update(newFriendAccount);
        return myAccount;
    }

    public List<User> mutualFriendsList(Integer myId, Integer otherId) {
        User user = getUserById(myId);
        User userFriend = getUserById(otherId);
        List<Integer> firstList;
        Set<Integer> secondList;
        if(userFriend.getFriendsList().size() <= user.getFriendsList().size()){
            firstList = new ArrayList<>(userFriend.getFriendsList());
            secondList = new TreeSet<>(user.getFriendsList());
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
        List<User> mutualFriends = new ArrayList<>();
        for(Integer friendId : firstList) {
            mutualFriends.add(((InMemoryUserStorage) userStorage).getUsers().get(friendId));
        }
        return mutualFriends;
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

    public List<User> getUserFriends(Integer id) {
        List<User> friends = new ArrayList<>();
        for(Integer friendId : getUserById(id).getFriendsList()) {
            friends.add(((InMemoryUserStorage) userStorage).getUsers().get(friendId));
        }
        return friends;
    }

    public User getUserById(Integer id) {
        if(!((InMemoryUserStorage) userStorage).getUsers().containsKey(id)){
            throw new NullPointerException("Пользователь не обнаружен");
        }
        return ((InMemoryUserStorage) userStorage).getUsers().get(id);
    }


    public void removeUserById(Integer id) throws NullPointerException {
        userStorage.removeUserById(id);
    }
}
