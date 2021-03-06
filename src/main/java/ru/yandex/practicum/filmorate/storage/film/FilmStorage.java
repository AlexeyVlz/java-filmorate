package ru.yandex.practicum.filmorate.storage.film;

import ru.yandex.practicum.filmorate.model.Film;

import java.util.List;

public interface FilmStorage {

    Film add(Film film);

    Film update(Film film);

    List<Film> getAllFilms();

    Film getFilmById(Integer id);

    boolean removeFilmById(Integer id);


}
