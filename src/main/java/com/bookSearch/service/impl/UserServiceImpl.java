package com.bookSearch.service.impl;

import com.bookSearch.dao.UserDao;
import com.bookSearch.model.User;
import com.bookSearch.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User getUserById(Integer userId) {
        return userDao.getUserById(userId);
    }

    @Override
    public User getUserByName(String userName) {
        System.out.println(userDao.getUserByName(userName));
        return userDao.getUserByName(userName);
    }

    @Override
    public List<User> getUserCondition(String Condition) {
        return userDao.getUsersByCondition(Condition);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public int insertUser(User user) {
        return userDao.insertUser(user);
    }

    @Override
    public int updateUser(User user) {
        userDao.updateUser(user);
        return 0;
    }

    @Override
    public void deleteUser(Integer userId) {
        userDao.deleteUser(userId);
    }
}
