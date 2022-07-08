package ru.yandex.practicum.filmorate.storage.mostPopularFilms;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.Film;

import java.util.List;

@Component
public class DbMostPopularFilms implements MostPopularFilms {

    @Override
    public List<Film> getMostPopularFilms(Integer count) {
        return null;
    }
}
