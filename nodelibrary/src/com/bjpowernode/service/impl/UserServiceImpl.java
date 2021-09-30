package com.bjpowernode.service.impl;

import com.bjpowernode.bean.User;
import com.bjpowernode.dao.UserDao;
import com.bjpowernode.dao.impl.UserDaoImpl;
import com.bjpowernode.service.UserService;

import java.util.List;

/**
 * �û�������
 */
public class UserServiceImpl implements UserService {
    private UserDao userDao=new UserDaoImpl();

    /**
     * ��ѯ
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


}
