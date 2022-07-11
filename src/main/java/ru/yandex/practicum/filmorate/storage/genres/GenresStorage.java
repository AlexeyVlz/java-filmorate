package ru.yandex.practicum.filmorate.storage.genres;

import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.FilmGenres;

import java.util.List;

public interface GenresStorage {

    void addGenres(Film film);

    List<FilmGenres> getGenres(Integer id);

}
