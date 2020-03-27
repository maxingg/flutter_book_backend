package com.maxingg.flutter_book_backend.service;

import com.maxingg.flutter_book_backend.dao.entity.User;
import java.util.List;

public interface UserService {
    List<User> queryAllUsers();
    boolean addUser(User user);

    User findUserById(String userId);

    User findByUsername(User user);
}
