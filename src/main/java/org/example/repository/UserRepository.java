package org.example.repository;

import org.example.model.User;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UserRepository {
    private static Map<String, User> users = new ConcurrentHashMap<>();
    private static Map<String, Integer> userCounter = new ConcurrentHashMap<>(); //Keeping counter for users in the city

    public static void addUser(User user) {
        users.put(user.getUserId(), user);
    }

    public static User getUser(String userId) {
        return users.get(userId);
    }
    public static int getNextUserId(String cityName) {
        int nextId = userCounter.getOrDefault(cityName, 0) + 1;
        userCounter.put(cityName, nextId);
        return nextId;
    }
}
