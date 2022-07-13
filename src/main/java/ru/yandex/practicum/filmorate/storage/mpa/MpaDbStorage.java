package ru.yandex.practicum.filmorate.storage.mpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.FilmMpa;
import ru.yandex.practicum.filmorate.storage.genres.GenresDBStorage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class MpaDbStorage implements MpaStorage{

    JdbcTemplate jdbcTemplate;

    @Autowired
    public MpaDbStorage(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public FilmMpa getMpaById(Integer id) {
        final String sqlQuery = "select * from MPA where MPA_ID = ?";
        List<FilmMpa> mpa = jdbcTemplate.query(sqlQuery, MpaDbStorage::makeMpa, id);
        return mpa.get(0);
    }

    @Override
    public List<FilmMpa> getAllMpa() {
        final String sqlQuery = "select * from MPA";
        return jdbcTemplate.query(sqlQuery, MpaDbStorage::makeMpa);
    }

    static FilmMpa makeMpa(ResultSet rs, int rowNum) throws SQLException {
        return new FilmMpa(rs.getInt("MPA_ID"), rs.getString("TITLE"));
    }
}
