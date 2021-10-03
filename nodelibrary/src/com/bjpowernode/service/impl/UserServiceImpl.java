package com.bjpowernode.service.impl;

import com.bjpowernode.bean.User;
import com.bjpowernode.dao.UserDao;
import com.bjpowernode.dao.impl.UserDaoImpl;
import com.bjpowernode.service.UserService;

import java.util.List;

/**
 * 用户服务类
 */
public class UserServiceImpl implements UserService {
    private UserDao userDao=new UserDaoImpl();

    /**
     * 查询
     * @return
     */
    @Override
    public List<User> select() {
        return userDao.select();
    }

    @Override
    public void addUser(User user) {
        userDao.addUser(user);
    }

    @Override
    public void updateUser(User user) {
        userDao.update(user);
    }

    @Override
    public void delete(int id) {
        userDao.delete(id);
    }

    @Override
    public void frozen(int id) {
        userDao.frozen(id);
    }

    @Override
    public void unFrozen(int id) {
        userDao.unFrozen(id);
    }
}
