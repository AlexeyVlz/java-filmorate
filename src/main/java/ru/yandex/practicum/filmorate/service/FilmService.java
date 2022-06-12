package ru.yandex.practicum.filmorate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.controller.FilmController;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.film.FilmStorage;
import ru.yandex.practicum.filmorate.storage.film.InMemoryFilmStorage;
import ru.yandex.practicum.filmorate.storage.user.InMemoryUserStorage;
import ru.yandex.practicum.filmorate.storage.user.UserStorage;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Service
public class FilmService {

    private final FilmStorage filmStorage;
    private final UserStorage userStorage;

    @Autowired
    public FilmService(FilmStorage filmStorage, UserStorage userStorage) {
        this.filmStorage = filmStorage;
        this.userStorage = userStorage;
    }

    public FilmStorage getFilmStorage() {
        return filmStorage;
    }

    public Film add(Film film){
        return filmStorage.add(film);
    }

    public Film update(Film film){
        return filmStorage.update(film);
    }

    public List<Film> getAllFilms() {
        return filmStorage.getAllFilms();
    }

    public void addLike(Integer filmId, Integer userId) {
        if(!((InMemoryUserStorage)userStorage).getUsers().containsKey(userId)){
            throw new NullPointerException("Пользователь не найден");
        }
        if(!((InMemoryFilmStorage) filmStorage).getFilms().containsKey(filmId)){
            throw new NullPointerException("Фильм не найден");
        }
        Film film = ((InMemoryFilmStorage) filmStorage).getFilms().get(filmId);
        film.getLikes().add(userId);
        filmStorage.update(film);
    }

    public void removeLike(Integer filmId, Integer userId) {
        if(!((InMemoryUserStorage)userStorage).getUsers().containsKey(userId)){
            throw new NullPointerException("Пользователь не найден");
        }
        if(!((InMemoryFilmStorage) filmStorage).getFilms().containsKey(filmId)){
            throw new NullPointerException("Фильм не найден");
        }
        Film film = ((InMemoryFilmStorage) filmStorage).getFilms().get(filmId);
        film.getLikes().remove(userId);
        filmStorage.update(film);
    }

    public List<Film> mostPopularFilms() throws NullPointerException{
        TreeSet<Film> mostPopularFilms = new TreeSet<>((f1, f2) ->  f2.getLikes().size() - f1.getLikes().size());
        mostPopularFilms.addAll(filmStorage.getAllFilms());
        List<Film> tenMostPopularFilms = new ArrayList<>(mostPopularFilms);
        if(tenMostPopularFilms.size() > 10){
            tenMostPopularFilms = tenMostPopularFilms.subList(0, 9);
        }
        return  tenMostPopularFilms;
    }
}