package ru.yandex.practicum.filmorate.storage.friends;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.user.UserDbStorage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class FriendsDbSrorage implements FriendStorage{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public FriendsDbSrorage(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void addNewFriend(Integer offerFriendshipUser, Integer newFriendId){
        String sqlQuery = "insert into USER_FRIENDS (USER_ID, FRIEND_ID, FRIENDSHIP_STATUS) values (?, ?, ?)";

        jdbcTemplate.update(sqlQuery,
                offerFriendshipUser,
                newFriendId,
                false);
    }

    public void acceptFriendship(Integer offerFriendshipUser, Integer acceptFrendshipUser) {
        String sqlQuery = "update USER_FRIENDS set FRIENDSHIP_STATUS = ? where USER_ID = ? and FRIEND_ID = ?";

        jdbcTemplate.update(sqlQuery,
                true,
                offerFriendshipUser,
                acceptFrendshipUser);
    }


    public boolean deletingFriend(Integer userID, Integer newFriendId) {
        String sqlQuery = "delete from USER_FRIENDS where (USER_ID = ? and FRIEND_ID = ?)";
        return jdbcTemplate.update(sqlQuery, userID, newFriendId) > 0;
    }

    public List<User> mutualFriendsList(Integer userId, Integer otherId) {
        List<User> mutualFriendsList = new ArrayList<>();
        Set<User> firstUser = new HashSet<>(getUserFriends(userId));
        for(User user : getUserFriends(otherId)){
            int firstUserSize = firstUser.size();
            firstUser.add(user);
            if(firstUser.size() == firstUserSize){
                mutualFriendsList.add(user);
            }
        }
        return mutualFriendsList;

    }
    public List<User> getUserFriends(Integer id) {
        String sqlQuery = "select * from " +
                "(select FRIEND_ID from USER_FRIENDS where USER_ID = ? " +
                "UNION " +
                "select USER_ID from USER_FRIENDS where FRIEND_ID = ? and FRIENDSHIP_STATUS = ?) as fr " +
                "left join USERS on fr.FRIEND_ID = USERS.USER_ID";

                        
        return jdbcTemplate.query(sqlQuery, UserDbStorage::makeUser, id, id, true);
    }
}







