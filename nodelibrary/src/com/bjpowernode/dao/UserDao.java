package com.bjpowernode.dao;

import com.bjpowernode.bean.User;

import java.util.List;

public interface UserDao  {
    List<User>select();
    void addUser(User user);
    void update(User user);
}
