package ru.yandex.practicum.filmorate.storage.likes;

public interface LikesStorage {

    void addLike(Integer filmId, Integer userId);

    boolean removeLike(Integer filmId, Integer userId);

    int getCountLikes(Integer filmId);
}
