package com.fitverse.dao;

// UserDaoImpl.java

import com.fitverse.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
@Slf4j
public class UserDaoImpl implements UserDao {
    private final Map<String, User> users = new HashMap<>();

    @Override
    public void addUser(User user) {
        log.debug("Adding user: {}", user.getUserId());
        users.put(user.getUserId(), user);
    }

    @Override
    public Optional<User> getUser(String userId) {
        log.debug("Fetching user: {}", userId);
        return Optional.ofNullable(users.get(userId));
    }
}