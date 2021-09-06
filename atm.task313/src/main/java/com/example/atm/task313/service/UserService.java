package com.example.atm.task313.service;



import com.example.atm.task313.model.User;

import java.util.List;


public interface UserService {
    void saveUser(User user);

    void updateUser(User user);

    User findUserById(long id);

    User findUserByEmail(String email);

    void removeUser(long id);

    List<User> findAllUsers();



}
