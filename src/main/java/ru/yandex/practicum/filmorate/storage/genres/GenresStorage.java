package ru.yandex.practicum.filmorate.storage.genres;

import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.FilmGenres;

import java.util.List;

public interface GenresStorage {

    Film addGenres(Film film);

    List<FilmGenres> getFilmGenres(Integer id);

    List<FilmGenres> getAllGenres();

    FilmGenres getGenre(Integer id);

    boolean deleteFilmGenres(Film film);

}
