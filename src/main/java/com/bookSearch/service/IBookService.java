package com.bookSearch.service;

import com.bookSearch.model.Book;
import com.bookSearch.model.User;

import java.util.List;

public interface IBookService {

    // 根据 bookId 查询图书信息
    Book getBookById(Integer bookId);

    // 查询所有图书信息
    List<Book> getAllBooks();

    // 插入图书信息
    int insertBook(Book book);

    // 更新图书信息
    int updateBook(Book book);

    // 删除图书信息 by bookId
    void deleteBook(Integer bookId);

    List<User> getBooksCondition(String Condition);
}
