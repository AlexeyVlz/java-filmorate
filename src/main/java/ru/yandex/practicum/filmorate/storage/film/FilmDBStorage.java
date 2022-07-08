package ru.yandex.practicum.filmorate.storage.film;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.FilmMpa;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.user.UserDbStorage;

import java.sql.*;
import java.util.List;

@Component
public class FilmDBStorage implements FilmStorage{

    JdbcTemplate jdbcTemplate;

    @Autowired
    public FilmDBStorage(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Film add(Film film){
        String sqlQuery = "insert into FILMS (FILM_NAME, DESCRIPTION, RELEASEdATE, DURATION, MPA_ID) " +
                            "values (?, ?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement stmt = connection.prepareStatement(sqlQuery, new String[]{"FILM_ID"});
            stmt.setString(1, film.getName());
            stmt.setString(2, film.getDescription());
            stmt.setDate(3, film.getReleaseDate());
            stmt.setInt(4, film.getDuration());
            stmt.setInt(5, film.getRatingMpa().getId());
            return stmt;
        }, keyHolder);
        film.setId(keyHolder.getKey().intValue());

        return film;

    }

    @Override
    public void update(Film film){
        String sqlQuery = "update FILMS set " +
                "FILM_NAME = ?, " +
                "DESCRIPTION = ?, " +
                "RELEASEdATE = ?, " +
                "DURATION = ?" +
                "where FILM_ID = ?";
        jdbcTemplate.update(sqlQuery
                , film.getName()
                , film.getDescription()
                , film.getReleaseDate()
                , film.getDuration()
                , film.getId());

    }
    @Override
    public List<Film> getAllFilms(){
        final String sqlQuery = "select * from FILMS";
        return jdbcTemplate.query(sqlQuery, FilmDBStorage::makeFilm);
    }
    @Override
    public Film getFilmById(Integer id){
        final String sqlQuery = "select * from FILMS where FILM_ID = ?";
        final List<Film> films = jdbcTemplate.query(sqlQuery, FilmDBStorage::makeFilm, id);
        if(films.size() != 1){
            // TODO not found
        }
        return films.get(0);
    }
    @Override
    public boolean removeFilmById(Integer id){
        String sqlQuery = "delete from FILMS where FILM_ID = ?";
        return jdbcTemplate.update(sqlQuery, id) > 0;
    }

    static Film makeFilm(ResultSet rs, int rowNum) throws SQLException {
        return Film.builder()
                .id(rs.getInt("FILM_ID"))
                .name(rs.getString("FILM_NAME"))
                .description(rs.getString("DESCRIPTION"))
                .releaseDate(rs.getDate("RELEASEdATE"))
                .duration(rs.getInt("DURATION"))
                .ratingMpa(new FilmMpa(rs.getInt("MPA_ID")))
                .build();
    }
}
