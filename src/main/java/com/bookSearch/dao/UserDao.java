package com.bookSearch.dao;

import com.bookSearch.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserDao {

    // 根据 userId 查询用户信息
    User getUserById(Integer userId);

    User getUserByName(String userName);

    // 查询所有用户信息
    List<User> getAllUsers();

    List<User> getUsersByCondition(String condition);

    // 插入用户信息
    int insertUser(User user);

    // 更新用户信息
    void updateUser(User user);

    // 删除用户信息 by userId
    void deleteUser(Integer userId);
}
