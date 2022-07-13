package ru.yandex.practicum.filmorate.storage.user;

import ru.yandex.practicum.filmorate.model.User;

import java.util.List;

public interface UserStorage {

    User save(User user);

    User getUserById(Integer id);

    List<User> getAllUsers();

    User update(User user);

    boolean removeUserById(Integer id);


}
