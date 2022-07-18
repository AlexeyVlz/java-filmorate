package ru.yandex.practicum.filmorate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.controller.FilmController;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.FilmGenres;
import ru.yandex.practicum.filmorate.model.FilmMpa;
import ru.yandex.practicum.filmorate.storage.film.FilmStorage;
import ru.yandex.practicum.filmorate.storage.genres.GenresStorage;
import ru.yandex.practicum.filmorate.storage.likes.LikesStorage;
import ru.yandex.practicum.filmorate.storage.mostPopularFilms.MostPopularFilms;
import ru.yandex.practicum.filmorate.storage.mpa.MpaDbStorage;
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
    private final GenresStorage genresStorage;
    private final MpaDbStorage mpaDbStorage;


    @Autowired
    public FilmService(FilmStorage filmStorage, LikesStorage likesStorage, MostPopularFilms mostPopularFilms,
                       GenresStorage genresStorage, MpaDbStorage mpaDbStorage) {
        this.filmStorage = filmStorage;
        this.likesStorage = likesStorage;
        this.mostPopularFilms = mostPopularFilms;
        this.genresStorage = genresStorage;
        this.mpaDbStorage = mpaDbStorage;
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

    public Film getFilmById(Integer id) {
        if(id > (filmStorage.getAllFilms().size() + 1)){
            throw new NullPointerException("Задан неверный id фильма");
        } else {
            return filmStorage.getFilmById(id);
        }
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

    public List<FilmGenres> getAllGenres() {
        return genresStorage.getAllGenres();
    }

    public FilmGenres getGenre(Integer id) {
        if(id > (genresStorage.getAllGenres().size() + 1)){
            throw new NullPointerException("Задан неверный id жанра");
        } else {
            return genresStorage.getGenre(id);
        }
    }
    public List<FilmMpa> getAllMpa() {
        return mpaDbStorage.getAllMpa();
    }

    public FilmMpa getMpaById(Integer id) {
        if((id > mpaDbStorage.getAllMpa().size() + 1)){
            throw new NullPointerException("Задан неверный id MPA");
        } else {
            return mpaDbStorage.getMpaById(id);
        }
    }
}
