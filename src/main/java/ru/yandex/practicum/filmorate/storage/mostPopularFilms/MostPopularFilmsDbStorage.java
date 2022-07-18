package ru.yandex.practicum.filmorate.storage.mostPopularFilms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.film.FilmDBStorage;

import java.util.List;

@Component
public class MostPopularFilmsDbStorage implements MostPopularFilms {

    JdbcTemplate jdbcTemplate;

    @Autowired
    public MostPopularFilmsDbStorage(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Film> getMostPopularFilms(Integer count) {
        String sqlQuery = "select FILMS.FILM_ID, FILM_NAME, DESCRIPTION, RELEASEDATE, DURATION, FILMS.MPA_ID, " +
        "M.MPA_ID, M.TITLE, count(USER_LIKE) as like_count from FILMS left join FILM_LIKES on FILM_LIKES.FILM_ID = FILMS.FILM_ID " +
        "left outer join MPA M on M.MPA_ID = FILMS.MPA_ID " +
        "left join FILM_GENRES FG on FILMS.FILM_ID = FG.FILM_ID " +
        "left join GENRES G on G.GENRE_ID = FG.GENRE_ID " +
        "group by FILMS.FILM_ID, FILM_NAME, DESCRIPTION, RELEASEDATE, DURATION, FILMS.MPA_ID, FILM_LIKES.FILM_ID, " +
        "FG.FILM_ID, FG.GENRE_ID, G.GENRE_ID, G.TITLE " +
        "order by like_count desc limit ?";
        return jdbcTemplate.query(sqlQuery, FilmDBStorage::makeFilm, count);
    }
}
