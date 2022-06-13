package ru.yandex.practicum.filmorate.storage.user;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.User;

import java.util.*;

@Component
public class InMemoryUserStorage implements UserStorage{

    private final Map<Integer, User> users;
    private int id;

    public InMemoryUserStorage() {
        users = new HashMap<>();
        id = 0;
    }

    public Map<Integer, User> getUsers() {
        return users;
    }

    public int getId() {
        return id;
    }

    public User create(User user){
        user = user.toBuilder().friendsList(new TreeSet<>()).build();
        user.setId(++id);
        users.put(user.getId(), user);
        return user;
    }

    public User update(User user) {
        if (!users.containsKey(user.getId())){
            throw new NullPointerException("Пользователь не обнаружен");
        }
        users.put(user.getId(), user);
        return user;
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(users.values());
    }

}
