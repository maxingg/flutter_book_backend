package com.maxingg.flutter_book_backend.service.impl;

import com.maxingg.flutter_book_backend.dao.entity.User;
import com.maxingg.flutter_book_backend.dao.mapper.UserMapper;
import com.maxingg.flutter_book_backend.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> queryAllUsers() {
        return userMapper.queryAllUsers();
    }


    @Override
    public boolean addUser(User user) {
        return userMapper.addUser(user);
    }

    @Override
    public User findUserById(String userId) {
        return userMapper.findUserById(Integer.parseInt(userId));
    }

    @Override
    public User findByUsername(User user) {
        return userMapper.findByUsername(user.getUserName());
    }
}
