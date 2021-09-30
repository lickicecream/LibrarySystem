package com.bjpowernode.Util;

import com.bjpowernode.bean.Constant;
import com.bjpowernode.bean.PathConstant;
import com.bjpowernode.bean.User;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class InitDataUtil {
    public static void main(String[] args) {
        initUser(null);
    }



    /**
     * 初始化用户数据
     */

    public static void initUser(List<User> userList) {
        ObjectOutputStream oos = null;
        try {
            File directory = new File("user/");
            File file = new File(PathConstant.USER_PATH);

            if (directory.exists() == false) {
                directory.mkdir();
            }

            if (file.exists() == false) {
                file.createNewFile();
                List<User> list = new ArrayList<>();

                //判断userList没有数据的话，我们就自己创建一个
                if (userList == null) {
                    list.add(new User(1001, "李翰勉",
                            Constant.USER_OK, new BigDecimal(200)));
//                    list = userList;
                } else {
                    list = userList;
                }

                oos = new ObjectOutputStream(new FileOutputStream(file));
                oos.writeObject(list);
                oos.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
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

