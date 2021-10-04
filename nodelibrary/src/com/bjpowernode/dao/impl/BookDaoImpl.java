package com.bjpowernode.dao.impl;

import com.bjpowernode.Util.BeanUtil;
import com.bjpowernode.bean.Book;
import com.bjpowernode.bean.Constant;
import com.bjpowernode.bean.PathConstant;
import com.bjpowernode.dao.BookDao;
import com.bjpowernode.global.util.Alerts;
import javafx.scene.control.Alert;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BookDaoImpl implements BookDao {
    @Override
    public List<Book> select() {
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(PathConstant.BOOK_PATH));
            List<Book> list = (List<Book>) ois.readObject();
            if (list == null) {
                list = new ArrayList<>();
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<Book>();
    }

    @Override
    public List<Book> select(Book book) {
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(new File(PathConstant.BOOK_PATH)));
            List<Book> list = (List<Book>) ois.readObject();
            if (list != null) {
                if (book == null || ("".equals(book.getBookName()) && "".equals(book.getIsbn()))) {
                    //��ѯȫ������
                    return list;
                } else {
                    //������ѯ
                    List<Book> bookList = new ArrayList<>();
                    if (!"".equals(book.getBookName())) {
                        //ɸѡ�����������鼮�����ҽ���������һ��list
                        if (!"".equals(book.getIsbn())) {
                            bookList = list.stream().filter(b -> b.getBookName().equals(book.getBookName()) && b.getIsbn().equals(book.getIsbn())).collect(Collectors.toList());
                        } else
                            bookList = list.stream().filter(b -> b.getBookName().equals(book.getBookName())).collect(Collectors.toList());
                    } else if (!"".equals(book.getIsbn())) {
                        bookList = list.stream().filter(b -> b.getIsbn().equals(book.getIsbn())).collect(Collectors.toList());
                    }
                    return bookList;
                }
            } else {
                Alerts.warning("����", "���ݿ�Ϊ�գ�����ϵ����Ա");
                return null;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void addBook(Book book) {
        ObjectInputStream ois = null;
        ObjectOutputStream oos = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(PathConstant.BOOK_PATH));
            List<Book> list = (List<Book>) ois.readObject();

            if (list == null) {
                book.setId(1001);
//                list=new ArrayList<>();
                list.add(book);
            } else {
                Book lastBook = list.get(list.size() - 1);
                book.setId(lastBook.getId() + 1);
                list.add(book);
            }
            oos = new ObjectOutputStream(new FileOutputStream(PathConstant.BOOK_PATH));
            oos.writeObject(list);
            oos.flush();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("�����쳣������ϵ����Ա");
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

    @Override
    public void deleteBook(Book book) {
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(new File(PathConstant.BOOK_PATH)));
            List<Book> list = (List<Book>) ois.readObject();
            if (list == null) {
                throw new IOException("�����쳣��ϵ����Ա");
            } else {
                list.remove(book);
            }
            oos = new ObjectOutputStream(new FileOutputStream(new File(PathConstant.BOOK_PATH)));
            oos.writeObject(list);
            oos.flush();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("�����쳣��ϵ����Ա");
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

    @Override
    public void modifyBook(Book book) {
        ObjectInputStream ois = null;
        ObjectOutputStream oos = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(PathConstant.BOOK_PATH));
            List<Book> list = (List<Book>) ois.readObject();
            if (list != null) {
                Book modifyBook = list.stream().filter(b -> b.getId() == book.getId()).findFirst().get();
                //����Ϊ��ͳ������̫����
//                modifyBook.setBookName(book.getBookName());
//                modifyBook.setIsbn(book.getIsbn());
//                modifyBook.setAuthor(book.getAuthor());
//                modifyBook.setPublisher(book.getPublisher());
//                modifyBook.setType(book.getType());

                //�·������÷����ȡ���ԣ�Ч��ֱ�ӷ���
                BeanUtil.populate(book,modifyBook);

                oos = new ObjectOutputStream(new FileOutputStream(PathConstant.BOOK_PATH));
                oos.writeObject(list);
            }else{
                throw new RuntimeException("ѡ���鼮����Ϊ��");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("�����쳣,����ϵ����Ա");
        } finally {
            try {
                if (ois != null) {
                    ois.close();
                }
                if (oos != null) {
                    oos.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
