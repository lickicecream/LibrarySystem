package com.bjpowernode.service.impl;

import com.bjpowernode.bean.Book;
import com.bjpowernode.dao.BookDao;
import com.bjpowernode.dao.impl.BookDaoImpl;
import com.bjpowernode.service.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {
    BookDao bookDao=new BookDaoImpl();
//    @Override
//    public List<Book> select() {
//        return bookDao.select();
//    }

    @Override
    public List<Book> select(Book book) {
        return bookDao.select(book);
    }

    @Override
    public void addBook(Book book) {
        bookDao.addBook(book);
    }

    @Override
    public void deleteBook(Book book) {
        bookDao.deleteBook(book);
    }

    @Override
    public void modifyBook(Book book) {
        bookDao.modifyBook(book);
    }
}
