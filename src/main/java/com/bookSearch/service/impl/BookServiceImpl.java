package com.bookSearch.service.impl;

import com.bookSearch.dao.BookDao;
import com.bookSearch.model.Book;
import com.bookSearch.model.User;
import com.bookSearch.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements IBookService {

    private final BookDao bookDao;

    @Autowired
    public BookServiceImpl(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public Book getBookById(Integer bookId) {
        return bookDao.getBookById(bookId);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookDao.getAllBooks();
    }

    @Override
    public int insertBook(Book book) {
        return bookDao.insertBook(book);
    }

    @Override
    public int updateBook(Book book) {
        return bookDao.updateBook(book);
    }

    @Override
    public void deleteBook(Integer bookId) {
        bookDao.deleteBook(bookId);
    }

    @Override
    public List<User> getBooksCondition(String Condition) {
        return bookDao.getBooksByCondition(Condition);
    }
}
