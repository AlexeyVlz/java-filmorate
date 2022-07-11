package ru.yandex.practicum.filmorate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.controller.FilmController;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.film.FilmStorage;
import ru.yandex.practicum.filmorate.storage.genres.GenresStorage;
import ru.yandex.practicum.filmorate.storage.likes.LikesStorage;
import ru.yandex.practicum.filmorate.storage.mostPopularFilms.MostPopularFilms;
import ru.yandex.practicum.filmorate.storage.user.UserStorage;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Service
public class FilmService {

    private final FilmStorage filmStorage;
    private final LikesStorage likesStorage;
    private final MostPopularFilms mostPopularFilms;


    @Autowired
    public FilmService(FilmStorage filmStorage, LikesStorage likesStorage, MostPopularFilms mostPopularFilms,
                       GenresStorage genresStorage) {
        this.filmStorage = filmStorage;
        this.likesStorage = likesStorage;
        this.mostPopularFilms = mostPopularFilms;
    }

    public FilmStorage getFilmStorage() {
        return filmStorage;
    }

    public Film add(Film film){
        return filmStorage.add(film);
    }

    public void update(Film film){
        filmStorage.update(film);
    }

    public List<Film> getAllFilms() {
        return filmStorage.getAllFilms();
    }

    public Film getFilmById(Integer id) {
        return filmStorage.getFilmById(id);
    }

    public boolean removeFilmById(Integer id) throws NullPointerException {
        return filmStorage.removeFilmById(id);
    }

    public void addLike(Integer filmId, Integer userId) {
        likesStorage.addLike(filmId, userId);
    }

    public boolean removeLike(Integer filmId, Integer userId) {
        return likesStorage.removeLike(filmId, userId);
    }

    public List<Film> mostPopularFilms(Integer count) throws NullPointerException{
        return mostPopularFilms.getMostPopularFilms(count);
    }




}
