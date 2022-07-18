package ru.yandex.practicum.filmorate.storage.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.yandex.practicum.filmorate.model.User;

import java.sql.*;
import java.sql.Date;
import java.util.*;

@Repository
public class UserDbStorage implements UserStorage{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDbStorage(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public User save(User user) {
        String sqlQuery = "insert into USERS (EMAIL, USER_LOGIN, USER_NAME, BIRTHDAY) values (?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement stmt = connection.prepareStatement(sqlQuery, new String[]{"USER_ID"});
            stmt.setString(1, user.getEmail());
            stmt.setString(2, user.getLogin());
            stmt.setString(3, user.getName());
            final Date birthday = user.getBirthday();
            if(birthday == null) {
                stmt.setNull(4, Types.DATE);
            } else {
                stmt.setDate(4, birthday);
            }
            return stmt;
        }, keyHolder);
        user.setId(keyHolder.getKey().intValue());
        return user;
    }



    public User getUserById(Integer id) {
        final String sqlQuery = "select * from USERS where USER_ID = ?";
        final List<User> users = jdbcTemplate.query(sqlQuery, UserDbStorage::makeUser, id);
        if(users.size() != 1){
            // TODO not found
        }
        return users.get(0);
    }
    public List<User> getAllUsers() {
        final String sqlQuery = "select * from USERS";
        return jdbcTemplate.query(sqlQuery, UserDbStorage::makeUser);
    }

    public User update(User user) {
        String sqlQuery = "update USERS set " +
                            "EMAIL = ?, " +
                            "USER_LOGIN = ?, " +
                            "USER_NAME = ?, " +
                            "BIRTHDAY = ?" +
                            "where USER_ID = ?";
        jdbcTemplate.update(sqlQuery
                            , user.getEmail()
                            , user.getLogin()
                            , user.getName()
                            , user.getBirthday()
                            , user.getId());
        return user;
    }

    public boolean removeUserById(Integer id) {
        String sqlQuery = "delete from USERS where USER_ID = ?";
        return jdbcTemplate.update(sqlQuery, id) > 0;
    }

    public static User makeUser(ResultSet rs, int rowNum) throws SQLException {
        return User.builder()
                .id(rs.getInt("USER_ID"))
                .email(rs.getString("EMAIL"))
                .login(rs.getString("USER_LOGIN"))
                .name(rs.getString("USER_NAME"))
                .birthday(rs.getDate("BIRTHDAY"))
                .build();
    }
}
