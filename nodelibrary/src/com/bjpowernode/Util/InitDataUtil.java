package com.bjpowernode.Util;

import com.bjpowernode.bean.Book;
import com.bjpowernode.bean.Constant;
import com.bjpowernode.bean.PathConstant;
import com.bjpowernode.bean.User;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InitDataUtil {
    public static void main(String[] args) {
        List<User>userList=new ArrayList<>(50);
        userList.add(new User(1001, "李翰勉",
                        Constant.USER_OK, new BigDecimal(200)));
        initData(PathConstant.USER_PATH,userList);
        List<Book>bookList=new ArrayList<>(50);
        bookList.add(new Book(1001, "bible",
                "lihanmian", Constant.TYPE_LITERATURE,
                "123-5", "北方出版社", Constant.STATUS_LEND));
//        bookList.add(new Book(1002, "bible",
//                "lihanmian", Constant.TYPE_LITERATURE,
//                "123-6", "北方出版社", Constant.STATUS_LEND));

        initData(PathConstant.BOOK_PATH,bookList);
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
            } else {

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

    public static void initBook(List<Book> list) {
        ObjectOutputStream oos = null;
        try {
            File direcotory = new File("book/");
            File file = new File(PathConstant.BOOK_PATH);
            List<Book> bookList = new ArrayList<>();
            if (!direcotory.exists()) {
                direcotory.mkdir();
            }
            if (!file.exists()) {
                file.createNewFile();

                if (list == null) {
                    bookList.add(new Book(1001, "bible",
                            "lihanmian", Constant.TYPE_LITERATURE,
                            "123-5", "北方出版社", Constant.STATUS_LEND));
                } else {
                    bookList = list;
                }
                oos = new ObjectOutputStream(new FileOutputStream(file));
                oos.writeObject(bookList);
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


    public static void initData(String path, List<?> list) {
        ObjectOutputStream oos = null;
        try {
            File directory = new File(path.split("/")[0]+"/");
            File file = new File(path);

            if (!directory.exists())
                directory.mkdir();
            if (!file.exists()) {
                file.createNewFile();
                oos = new ObjectOutputStream(new FileOutputStream(file));
                oos.writeObject(list);
                oos.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(oos!=null){
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }
}

