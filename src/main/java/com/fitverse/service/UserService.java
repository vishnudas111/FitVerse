package com.fitverse.service;

// UserService.java

import com.fitverse.dao.UserDao;
import com.fitverse.entity.User;
import com.fitverse.exception.UserException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {
    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public User registerUser(User user) {
        log.info("Registering user: {}", user.getName());
        userDao.addUser(user);
        return user;
    }

    public User getUser(String userId) throws UserException {
        log.info("Fetching user: {}", userId);
        return userDao.getUser(userId)
                .orElseThrow(() -> new UserException("User not found"));
    }
}
