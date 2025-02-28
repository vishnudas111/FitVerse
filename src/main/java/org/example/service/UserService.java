package org.example.service;

import org.example.model.User;
import org.example.repository.UserRepository;


public class UserService {

    public void registerUser(String name, int age, String city){
        User user = new User();
        user.setUserId(String.valueOf(UserRepository.getNextUserId(city)));
        user.setAge(age);
        user.setName(name);
        user.setCity(city);
        UserRepository.addUser(user);
        System.out.println("Successfully registered user for : " + name);
    }
}
