package com.bookSearch.service;

import com.bookSearch.model.User;

import java.util.List;

public interface IUserService {

    // 根据 userId 查询用户信息
    User getUserById(Integer userId);

    // 根据 userName 查询用户信息
    User getUserByName(String userName);

    List<User> getUserCondition(String Condition);

    // 查询所有用户信息
    List<User> getAllUsers();

    // 插入用户信息
    int insertUser(User user);

    // 更新用户信息
    int updateUser(User user);

    // 删除用户信息 by userId
    void deleteUser(Integer userId);
}
