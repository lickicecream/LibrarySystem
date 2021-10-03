package com.bjpowernode.dao;

import com.bjpowernode.bean.User;

import java.util.List;

public interface UserDao  {
    List<User>select();
    void addUser(User user);
    void update(User user);

    void delete(int id);
    void frozen(int id);

    void unFrozen(int id);
}
