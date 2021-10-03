package com.bjpowernode.service;

import com.bjpowernode.bean.User;

import java.util.List;

public interface UserService {
    List<User>select();
    void addUser(User user);
    void updateUser(User user);
    void delete(int id);
    void frozen(int id);

    void unFrozen(int id);
}
