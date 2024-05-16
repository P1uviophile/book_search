package com.bookSearch.dao;

import com.bookSearch.model.Book;
import com.bookSearch.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookDao {

    // 根据 bookId 查询图书信息
    Book getBookById(Integer book_id);

    // 查询所有图书信息
    List<Book> getAllBooks();

    // 插入图书信息
    int insertBook(Book book);

    // 更新图书信息
    int updateBook(Book book);

    // 删除图书信息 by bookId
    void deleteBook(Integer book_id);

    List<User> getBooksByCondition(String condition);
}
