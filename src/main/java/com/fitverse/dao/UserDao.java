package com.fitverse.dao;

// UserDao.java

import com.fitverse.entity.User;

import java.util.Optional;

public interface UserDao {
    void addUser(User user);
    Optional<User> getUser(String userId);
}