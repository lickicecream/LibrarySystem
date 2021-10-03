package com.bjpowernode.dao;

import com.bjpowernode.bean.Book;

import java.util.List;

public interface BookDao {
    public List<Book> select();

    public List<Book>select(Book book);

    public void addBook(Book book);

    public void deleteBook(Book book);

    void modifyBook(Book book);
}
