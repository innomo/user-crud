package com.user.usercrud.service;

import com.user.usercrud.model.User;

import java.util.List;

public interface IUserService {

    User addUser(User user);
    List<User> getUsers();
    User updateUser(User user, Long userId);
    User getUserById(Long UserId);
    void deleteUser(Long userId);
}
