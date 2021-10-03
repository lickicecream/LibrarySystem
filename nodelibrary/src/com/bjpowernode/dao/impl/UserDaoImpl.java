package com.bjpowernode.dao.impl;

import com.bjpowernode.Util.InitDataUtil;
import com.bjpowernode.bean.Constant;
import com.bjpowernode.bean.PathConstant;
import com.bjpowernode.bean.User;
import com.bjpowernode.dao.UserDao;
import com.bjpowernode.global.util.Alerts;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户Dao层
 */

public class UserDaoImpl implements UserDao {

    /**
     * 删除用户，通过id删除
     *
     * @param id
     */
    @Override
    public void delete(int id) {
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(new File(PathConstant.USER_PATH)));
            List<User> list = (List<User>) ois.readObject();
            if (list != null) {
//                User user = list.stream().filter(u -> u.getId() == id).findFirst().get();
//                list.remove(user);
                for (User u : list) {
                    if (u.getId() == id) {
                        list.remove(u);
                        break;
                    }
                }
            }
            oos = new ObjectOutputStream(new FileOutputStream(new File(PathConstant.USER_PATH)));
            oos.writeObject(list);
            oos.flush();
        } catch (Exception e) {
            throw new RuntimeException("删除失败请联系管理员");
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 查询用户信息
     *
     * @return
     */
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

    /**
     * 增加新用户
     *
     * @param user
     */
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
            throw new RuntimeException("操作失败请联系管理员");
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

    /**
     * 更新用户数据
     *
     * @param user
     */
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
                User originUser = list.stream().filter(u -> u.getId() == user.getId()).findFirst().get();
                originUser.setName(user.getName());
                originUser.setMoney(user.getMoney());

            } else {
                list = new ArrayList<>();
                list.add(user);
                System.out.println("元数据为空");
            }
            oos = new ObjectOutputStream(new FileOutputStream(new File(PathConstant.USER_PATH)));
            oos.writeObject(list);
            oos.flush();
        } catch (Exception e) {
            throw new RuntimeException("操作失败请联系管理员");
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

    /**
     * 冻结用户
     *
     * @param id
     */
    @Override
    public void frozen(int id) {
        ObjectInputStream ois = null;
        ObjectOutputStream oos = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(PathConstant.USER_PATH));
            List<User> list = (List<User>) ois.readObject();
            if (list != null) {
                for (User user : list) {
                    if (user.getId() == id) {
                        if (user.getStatus() == Constant.USER_FROZEN)
                            Alerts.warning("已冻结", "该用户已冻结");
                        else
                            user.setStatus(Constant.USER_FROZEN);
                        break;
                    }
                }
                oos = new ObjectOutputStream(new FileOutputStream(new File(PathConstant.USER_PATH)));
                oos.writeObject(list);
//                throw new RuntimeException("数据库元数据出错，请联系管理员");
            } else {
                throw new RuntimeException("数据库元数据出错，请联系管理员");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("");
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

    /**
     * 反冻结
     *
     * @param id
     */
    @Override
    public void unFrozen(int id) {
        ObjectInputStream ois = null;
        ObjectOutputStream oos = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(PathConstant.USER_PATH));
            List<User> list = (List<User>) ois.readObject();
            if (list != null) {
                for (User user : list) {
                    if (user.getId() == id) {
                        if (user.getStatus() == Constant.USER_OK)
                            ;
//                            Alerts.warning("已冻结", "该用户已冻结");
                        else
                            user.setStatus(Constant.USER_OK);
                        break;
                    }
                }
                oos = new ObjectOutputStream(new FileOutputStream(new File(PathConstant.USER_PATH)));
                oos.writeObject(list);
//                throw new RuntimeException("数据库元数据出错，请联系管理员");
            } else {
                throw new RuntimeException("数据库元数据出错，请联系管理员");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
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
