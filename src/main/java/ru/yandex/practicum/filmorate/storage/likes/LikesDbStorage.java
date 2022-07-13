package ru.yandex.practicum.filmorate.storage.likes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.Film;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class LikesDbStorage implements LikesStorage {

    JdbcTemplate jdbcTemplate;

    @Autowired
    public LikesDbStorage(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public void addLike(Integer filmId, Integer userId) {
        String sqlQuery = "insert into FILM_LIKES (FILM_ID, USER_LIKE) values (?, ?)";
        jdbcTemplate.update(sqlQuery
                            , filmId
                            , userId);
    }
    @Override
    public boolean removeLike(Integer filmId, Integer userId) {
        String sqlQuery = "delete from FILM_LIKES where FILM_ID = ? and USER_LIKE = ? ";
        return jdbcTemplate.update(sqlQuery, filmId, userId) > 0;
    }

    public int getCountLikes(Integer filmId) {
        String sqlQuery = "select count(USER_LIKE) as COUNT from FILM_LIKES group by ?";
        return jdbcTemplate.query(sqlQuery, LikesDbStorage::makeCount, filmId).get(0);

    }

    static int makeCount(ResultSet rs, int rowNum) throws SQLException {
        return rs.getInt("COUNT");
    }


}
