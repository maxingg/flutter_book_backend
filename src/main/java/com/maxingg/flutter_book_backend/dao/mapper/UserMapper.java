package com.maxingg.flutter_book_backend.dao.mapper;

import com.maxingg.flutter_book_backend.dao.entity.User;
import java.util.List;

public interface UserMapper {
    List<User> queryAllUsers();
    User findUserById(int id);

    User findByUsername(String userName);

    boolean addUser(User user);
}
