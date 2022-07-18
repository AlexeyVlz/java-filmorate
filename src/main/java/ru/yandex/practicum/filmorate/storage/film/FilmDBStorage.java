package ru.yandex.practicum.filmorate.storage.film;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.FilmMpa;
import ru.yandex.practicum.filmorate.storage.genres.GenresStorage;

import java.sql.*;
import java.util.List;

@Component
public class FilmDBStorage implements FilmStorage{

    JdbcTemplate jdbcTemplate;
    private final GenresStorage genresStorage;

    @Autowired
    public FilmDBStorage(JdbcTemplate jdbcTemplate, GenresStorage genresStorage) {
        this.jdbcTemplate = jdbcTemplate;
        this.genresStorage = genresStorage;
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
            stmt.setInt(5, film.getMpa().getId());
            return stmt;
        }, keyHolder);
        film.setId(keyHolder.getKey().intValue());
        if(film.getGenres() != null) {
            genresStorage.addGenres(film);
        }
        return film;
    }

    @Override
    public Film update(Film film){
        String sqlQuery = "update FILMS set " +
                "FILM_NAME = ?, " +
                "DESCRIPTION = ?, " +
                "RELEASEdATE = ?, " +
                "DURATION = ?, " +
                "MPA_ID = ? " +
                "where FILM_ID = ?";
        jdbcTemplate.update(sqlQuery
                , film.getName()
                , film.getDescription()
                , film.getReleaseDate()
                , film.getDuration()
                , film.getMpa().getId()
                , film.getId());
        if(film.getGenres() == null || film.getGenres().size() == 0){
            genresStorage.deleteFilmGenres(film);
        } else {
            genresStorage.deleteFilmGenres(film);
            genresStorage.addGenres(film);
        }

        return film;
    }
    @Override
    public List<Film> getAllFilms(){
        final String sqlQuery = "select * from FILMS left join MPA M on M.MPA_ID = FILMS.MPA_ID";
        return jdbcTemplate.query(sqlQuery, FilmDBStorage::makeFilm);
    }
    @Override
    public Film getFilmById(Integer id){
        final String sqlQuery = "select * from FILMS " +
                "left join MPA M on M.MPA_ID = FILMS.MPA_ID " +
                //"left join FILM_GENRES FG on FILMS.FILM_ID = FG.FILM_ID " +
                //"left join GENRES G on G.GENRE_ID = FG.GENRE_ID " +
                "where FILMS.FILM_ID = ?";
        final List<Film> films = jdbcTemplate.query(sqlQuery, FilmDBStorage::makeFilm, id);
        if(films.size() != 1){
            // TODO not found
        }
        return films.get(0).toBuilder().genres(genresStorage.getFilmGenres(id)).build();
    }
    @Override
    public boolean removeFilmById(Integer id){
        String sqlQuery = "delete from FILMS where FILM_ID = ?";
        return jdbcTemplate.update(sqlQuery, id) > 0;
    }

    public static Film makeFilm(ResultSet rs, int rowNum) throws SQLException {
        return Film.builder()
                .id(rs.getInt("FILM_ID"))
                .name(rs.getString("FILM_NAME"))
                .description(rs.getString("DESCRIPTION"))
                .releaseDate(rs.getDate("RELEASEdATE"))
                .duration(rs.getInt("DURATION"))
                .mpa(new FilmMpa(rs.getInt("MPA_ID"), rs.getString("TITLE")))
                .build();
    }
}
