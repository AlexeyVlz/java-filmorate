package ru.yandex.practicum.filmorate.storage.friends;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.user.UserDbStorage;

import java.util.List;

@Component
public class FriendsDbSrorage implements FriendStorage{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public FriendsDbSrorage(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void addNewFriend(Integer userId, Integer newFriendId){
        String sqlQuery = "insert into USER_FRIENDS (USER_ID, FRIEND_ID, FRIENDSHIP_STATUS) values (?, ?, ?)";

        jdbcTemplate.update(sqlQuery,
                userId,
                newFriendId,
                false);
    }

    public boolean deletingFriend(Integer userID, Integer newFriendId) {
        String sqlQuery = "delete from USER_FRIENDS where (USER_ID = ?, FRIEND_ID = ?)";
        return jdbcTemplate.update(sqlQuery, userID, newFriendId) > 0;
    }

    public List<User> mutualFriendsList(Integer userId, Integer otherId) {
        String sqlQuery = "select uf1.FRIEND_ID " +
        "from (SELECT * from USER_FRIENDS " +
                "where USER_FRIENDS.USER_ID = ? and USER_FRIENDS.FRIENDSHIP_STATUS = true) as uf1 " +
        "join (select * from USER_FRIENDS " +
                "where USER_FRIENDS.USER_ID = ? and USER_FRIENDS.FRIENDSHIP_STATUS = true) as uf2 " +
        "ON uf1.FRIEND_ID = uf2.FRIEND_ID";

        return jdbcTemplate.query(sqlQuery, UserDbStorage::makeUser, userId, otherId);
    }
    public List<User> getUserFriends(Integer id) {
        String sqlQuery = "select * from USER_FRIENDS where USER_ID = ?";
        return jdbcTemplate.query(sqlQuery, UserDbStorage::makeUser, id);
    }
}







