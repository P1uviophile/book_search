package com.bookSearch.controller;

import com.bookSearch.model.Book;
import com.bookSearch.model.BorrowRecord;
import com.bookSearch.model.User;
import com.bookSearch.service.IBookService;
import com.bookSearch.service.IBorrowRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;
import java.util.Objects;


@Controller
@RequestMapping(value="/book")
public class BookController {
    private final IBookService bookService;
    private final IBorrowRecordService borrowRecordService;
    private final BorrowRecord borrowRecord = new BorrowRecord();

    @Autowired
    public BookController(IBookService bookService,IBorrowRecordService borrowRecordService) {
        this.bookService = bookService;
        this.borrowRecordService = borrowRecordService;
    }

    @GetMapping("/queryCondition")
    public String QueryCondition(@RequestParam String condition, Model model){
        if(Objects.equals(condition, "")){
            return defaultHandler(model);
        }else {
            // 通过service查询
            List<User> list = bookService.getBooksCondition(condition);
            // 把查询到的信息绑定在 model 中
            model.addAttribute("list", list);
            // 页面跳转到book.jsp中
            System.out.println(list);
            return "book/book";
        }
    }

    @GetMapping
    public String defaultHandler(Model model) {
        model.addAttribute("list",bookService.getAllBooks());
        return "book/book";
    }

    @GetMapping("/queryById/{book_id}")
    public String queryById(@PathVariable Integer book_id, Model model) {
        // 通过service查询
        Book book = bookService.getBookById(book_id);
        // 把查询到的信息绑定在 model 中
        model.addAttribute("book", book);
        // 页面跳转到user.jsp中
        System.out.println(book.toString());
        return "book/bookUpdate";
    }

    @RequestMapping(params = "type=delete")
    public String deleteUser(@RequestParam("book_id") Integer book_id) {
        // 通过Service处理删除操作
        bookService.deleteBook(book_id);
        return "redirect:/book";
    }

    @PostMapping("/save")
    public String saveCommodity(@ModelAttribute Book book,Model model) {
        bookService.insertBook(book);
        // 通过service查询所有的用户信息
        List<Book> list = bookService.getAllBooks();
        model.addAttribute("list", list);
        return "/book/book";
    }

    @GetMapping("/update/{book_id}")
    public String showUpdateForm(@PathVariable Integer book_id, Model model) {
        Book book = bookService.getBookById(book_id);
        model.addAttribute("book", book);
        return "book/bookUpdate";
    }

    @PostMapping("/update")
    public String updateCommodity(@ModelAttribute Book book,Model model) {
        bookService.updateBook(book);
        // 通过service查询所有的用户信息
        List<Book> list = bookService.getAllBooks();
        model.addAttribute("list", list);
        return "/book/book";
    }

    @RequestMapping(params = "type=borrow")
    public String borrowBook(
            @RequestParam("book_id") Integer book_id,
            @RequestParam("user_id") int userId,
            @RequestParam("borrow_day") int borrow_day,
            @RequestParam("return_day") int return_day,
            Model model) {
        System.out.println("测试");
        Book book = bookService.getBookById(book_id);
        borrowRecord.setBook_id(book_id);
        borrowRecord.setUser_id(userId);
        borrowRecord.setReturned(0);
        LocalDate today = LocalDate.now();
        LocalDate borrowDate = today.plusDays(borrow_day);
        LocalDate returnDate = today.plusDays(return_day);

        borrowRecord.setBorrow_date(new java.sql.Date(borrowDate.atStartOfDay().toEpochSecond(ZoneOffset.UTC) * 1000));  // 转换为 java.sql.Date
        borrowRecord.setReturn_date(new java.sql.Date(returnDate.atStartOfDay().toEpochSecond(ZoneOffset.UTC) * 1000));  // 转换为 java.sql.Date
        int flag = borrowRecordService.insertBorrowRecord(borrowRecord);

        if (flag > 0) {
            System.out.println("借阅成功");
            book.setAvailable_copies(book.getAvailable_copies()-1);
            // 借阅成功，重新查询所有书籍
            bookService.updateBook(book);
            List<Book> list = bookService.getAllBooks();
            model.addAttribute("list", list);
            return "book/book";
        } else {
            // 购买失败，可能需要处理失败的逻辑
            System.out.println("借阅失败");
            return "wrongOperate"; // 返回错误页面
        }
    }

    @GetMapping("/bookUpdate")
    public String showUserUpdatePage() {
        return "book/bookUpdate";
    }
}
