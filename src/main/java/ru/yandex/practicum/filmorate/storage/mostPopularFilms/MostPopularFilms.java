package ru.yandex.practicum.filmorate.storage.mostPopularFilms;

import ru.yandex.practicum.filmorate.model.Film;

import java.util.List;

public interface MostPopularFilms {

    List<Film> getMostPopularFilms(Integer count);
}
