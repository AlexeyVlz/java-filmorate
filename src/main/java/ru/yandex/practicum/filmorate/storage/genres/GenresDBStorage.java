package ru.yandex.practicum.filmorate.storage.genres;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.FilmGenres;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Component
public class GenresDBStorage implements GenresStorage{
    JdbcTemplate jdbcTemplate;

    @Autowired
    public GenresDBStorage(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Film addGenres(Film film){
        if(film.getGenres() != null) {
            removeDuplicate(film);
            for (FilmGenres genres : film.getGenres()) {
                String sqlQuery = "insert into FILM_GENRES (FILM_ID, GENRE_ID) values (?, ?) ";
                jdbcTemplate.update(sqlQuery
                        , film.getId()
                        , genres.getId());
            }
        }
        return film;
    }

    

    public boolean deleteFilmGenres(Film film) {
        String sqlQuery = "delete from FILM_GENRES where FILM_ID = ?";
        return jdbcTemplate.update(sqlQuery, film.getId()) > 0;
    }

    public List<FilmGenres> getFilmGenres(Integer id) {
        final String sqlQuery = "select FILM_GENRES.GENRE_ID, G.TITLE " +
                                "from FILM_GENRES " +
                                "left join GENRES G on G.GENRE_ID = FILM_GENRES.GENRE_ID " +
                                "where FILM_ID = ?";
        return jdbcTemplate.query(sqlQuery, GenresDBStorage::makeGenre, id);
    }

    public List<FilmGenres> getAllGenres(){
        final String sqlQuery = "select * from GENRES";
        return jdbcTemplate.query(sqlQuery, GenresDBStorage::makeGenre);
    }

    public FilmGenres getGenre(Integer id){
        final String sqlQuery = "select * from GENRES where GENRE_ID = ?";
        List<FilmGenres> filmGenres =  jdbcTemplate.query(sqlQuery, GenresDBStorage::makeGenre, id);
        return filmGenres.get(0);
    }

    static FilmGenres makeGenre(ResultSet rs, int rowNum) throws SQLException {
        return new FilmGenres(rs.getInt("GENRE_ID"), rs.getString("TITLE"));
    }

    private Film removeDuplicate(Film film) {
        int genresSize = film.getGenres().size();
        Set<Integer> genresId = new HashSet<>();
        for(int i = 0; i < genresSize; i++){
            int sizeSet = genresId.size();
            genresId.add(film.getGenres().get(i).getId());
            if(genresId.size() == sizeSet){
                film.getGenres().remove(i);
            }
        }
        return film;
    }
}
