package com.bjpowernode.dao.impl;

import com.bjpowernode.Util.InitDataUtil;
import com.bjpowernode.bean.Constant;
import com.bjpowernode.bean.PathConstant;
import com.bjpowernode.bean.User;
import com.bjpowernode.dao.UserDao;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户Dao层
 */

public class UserDaoImpl implements UserDao {
    @Override
    public List<User> select() {
//        InitDataUtil.initUser();

        try (ObjectInputStream objectInputStream
                     = new ObjectInputStream(new FileInputStream(PathConstant.USER_PATH))) {

            List<User> list = (List<User>) objectInputStream.readObject();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<User>();
    }

    @Override
    public void addUser(User user) {
        ObjectInputStream ois = null;
        ObjectOutputStream oos = null;
        try {
            //获取文件中的User list对象
            ois = new ObjectInputStream(new FileInputStream(PathConstant.USER_PATH));
            Object o = ois.readObject();
            List<User> list = (List<User>) o;
            if (list != null) {
                //添加新用户
                User lastUser = list.get(list.size() - 1);
                user.setId(lastUser.getId() + 1);
                list.add(user);

            } else {
                list = new ArrayList<>();
                user.setId(1001);
                list.add(user);
            }
            oos = new ObjectOutputStream(new FileOutputStream(PathConstant.USER_PATH));
            oos.writeObject(list);
            oos.flush();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (ois != null)
                    ois.close();
                if (oos != null)
                    oos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void update(User user) {
        //将list数据从文件中查询出来
        ObjectInputStream ois = null;
        ObjectOutputStream oos = null;

        try {
            ois = new ObjectInputStream(new FileInputStream(new File(PathConstant.USER_PATH)));
            List<User> list = (List<User>) ois.readObject();
            if (list != null) {
                //lambda表达式
                User originUser=list.stream().filter(u->u.getId()==user.getId()).findFirst().get();
                originUser.setName(user.getName());
                originUser.setMoney(user.getMoney());

            }else{
                list=new ArrayList<>();
                list.add(user);
                System.out.println("元数据为空");
            }
            oos = new ObjectOutputStream(new FileOutputStream(new File(PathConstant.USER_PATH)));
            oos.writeObject(list);
            oos.flush();
        } catch (Exception e) {

        } finally {
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
