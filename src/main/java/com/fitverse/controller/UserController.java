package com.fitverse.controller;

// UserController.java


import com.fitverse.entity.User;
import com.fitverse.exception.UserException;
import com.fitverse.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@Slf4j
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public User registerUser(@RequestBody User user) {
        log.info("Registering new user: {}", user.getName());
        return userService.registerUser(user);
    }

    @GetMapping("/{userId}")
    public User getUser(@PathVariable String userId) throws UserException {
        log.info("Fetching user with ID: {}", userId);
        return userService.getUser(userId);
    }
}
