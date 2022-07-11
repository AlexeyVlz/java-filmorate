package ru.yandex.practicum.filmorate.storage.genres;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.FilmGenres;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.user.UserDbStorage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class GenresDBStorage implements GenresStorage{
    JdbcTemplate jdbcTemplate;

    @Autowired
    public GenresDBStorage(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void addGenres(Film film){
        for(int i = 0; i < film.getGenres().size(); i++) {
        String sqlQuery = "insert into FILM_GENRES (FILM_ID, GENRE_ID) values (?, ?)";
        jdbcTemplate.update(sqlQuery
                , film.getId()
                , film.getGenres().get(i).getId());
        }
    }

    public List<FilmGenres> getGenres(Integer id) {
        final String sqlQuery = "select FILM_GENRES.GENRE_ID, G.TITLE " +
                                "from FILM_GENRES " +
                                "left join GENRES G on G.GENRE_ID = FILM_GENRES.GENRE_ID " +
                                "where FILM_ID = ?";
        return jdbcTemplate.query(sqlQuery, GenresDBStorage::makeGenre, id);
    }

    static FilmGenres makeGenre(ResultSet rs, int rowNum) throws SQLException {
        return new FilmGenres(rs.getInt("GENRE_ID"), rs.getString("TITLE"));
    }
}
