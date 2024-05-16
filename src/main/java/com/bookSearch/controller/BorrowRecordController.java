package com.bookSearch.controller;

import com.bookSearch.model.Book;
import com.bookSearch.model.BorrowRecord;
import com.bookSearch.service.IBookService;
import com.bookSearch.service.IBorrowRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

@Controller
@RequestMapping("/borrowRecord")
public class BorrowRecordController {

    private final IBorrowRecordService borrowRecordService;
    private final IBookService bookService;

    @Autowired
    public BorrowRecordController(IBorrowRecordService borrowRecordService, IBookService bookService) {
        this.borrowRecordService = borrowRecordService;
        this.bookService = bookService;
    }

    @GetMapping
    public String defaultHandler(Model model) {
        // 默认查询所有借阅信息
        model.addAttribute("list",borrowRecordService.getAllBorrowRecords());
        return "borrowRecord/borrowRecord";
    }

    @PostMapping("/saveOrUpdate")
    public String saveOrUpdateUser(HttpServletRequest req, HttpServletResponse resp,Model model, HttpSession session) throws IOException {
        BorrowRecord borrowRecord = new BorrowRecord();
        String Borrow_date = req.getParameter("borrow_date");
        String Return_date = req.getParameter("return_date");
        Object adminAttribute = session.getAttribute("admin");
        int returned_root = -1;
        if (req.getParameter("returned_root")!=null) {
            returned_root = Integer.parseInt(req.getParameter("returned_root"));
        }
        if(adminAttribute != null && (Boolean) adminAttribute){}
        else {
            SimpleDateFormat sim1 = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
            SimpleDateFormat sim2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            getDate(sim1, sim2, Borrow_date);
            Borrow_date = getDate(sim1, sim2, Borrow_date);
            Return_date = getDate(sim1, sim2, Return_date);
        }

        if (Borrow_date != null && !Borrow_date.isEmpty()) {
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date parsedDate = dateFormat.parse(Borrow_date);
                java.sql.Date borrow_date = new java.sql.Date(parsedDate.getTime());
                borrowRecord.setBorrow_date(borrow_date);
                System.out.println(borrow_date);
            } catch (ParseException e) {
                // 处理日期转换异常
                e.printStackTrace();
            }
        }

        if (Return_date != null && !Return_date.isEmpty()) {
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date parsedDate = dateFormat.parse(Return_date);
                java.sql.Date return_date = new java.sql.Date(parsedDate.getTime());
                borrowRecord.setReturn_date(return_date);
            } catch (ParseException e) {
                // 处理日期转换异常
                e.printStackTrace();
            }
        }

        try {
            borrowRecord.setUser_id(Integer.valueOf(req.getParameter("user_id")));
            if (req.getParameter("record_id")!=null) {
                borrowRecord.setRecord_id(Integer.valueOf(req.getParameter("record_id")));
            }
            borrowRecord.setBook_id(Integer.parseInt(req.getParameter("book_id")));
            borrowRecord.setReturned(Integer.parseInt(req.getParameter("returned")));
        } catch (Exception e) {
            // 表示插入失败
            System.out.println("插入失败QAQ");
            // 跳转到失败页面
            resp.sendRedirect("/wrongOperate.jsp");
            e.printStackTrace();
        }

        System.out.println("借阅信息"+borrowRecord.toString());
        int count = -1;
        if ((borrowRecord.getRecord_id() != null)) {
            // 表示是更新
            System.out.println(borrowRecord);
            count = borrowRecordService.updateBorrowRecord(borrowRecord);
            if (count > 0) {
                System.out.println("插入成功");
                // 查询成功，再做一次查询操作
                model.addAttribute("list",borrowRecordService.getAllBorrowRecords());
                if (returned_root!=borrowRecord.getReturned()){
                    returned_root -= borrowRecord.getReturned();
                    Book book = bookService.getBookById(borrowRecord.getBook_id());
                    book.setAvailable_copies(book.getAvailable_copies()-returned_root);
                    bookService.updateBook(book);
                }
            } else {
                // 表示插入失败
                System.out.println("插入失败QAQ");
                // 跳转到失败页面
                return "wrongOperate";
            }
        } else {
            count = borrowRecordService.insertBorrowRecord(borrowRecord);
            if (count > 0) {
                System.out.println("插入成功");
                // 查询成功，再做一次查询操作
                model.addAttribute("list",borrowRecordService.getAllBorrowRecords());
                Book book = bookService.getBookById(borrowRecord.getBook_id());
                book.setAvailable_copies(book.getAvailable_copies()-1);
                bookService.updateBook(book);
            } else {
                // 表示插入失败
                System.out.println("插入失败QAQ");
                // 跳转到失败页面
                return "wrongOperate";
            }
        }
        return "borrowRecord/borrowRecord";
    }

    private static String getDate(SimpleDateFormat sim1, SimpleDateFormat sim2, String s1) {
        try {
            Date date = sim1.parse(s1);
            System.out.println(date);
            System.out.println(sim2.format(date));
            return sim2.format(date);
        }catch (ParseException e ){
            e.printStackTrace();
            return null;
        }
    }

    private static java.sql.Date getDate(String orderStartString) {
        if (orderStartString != null && !orderStartString.isEmpty()) {
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date parsedDate = dateFormat.parse(orderStartString);
                java.sql.Date orderStart = new java.sql.Date(parsedDate.getTime());
                System.out.println(orderStart);
                return orderStart;
            } catch (ParseException e) {
                // 处理日期转换异常
                e.printStackTrace();
                return null;
            }
        }else {return null;}
    }

    @GetMapping("/delete/{record_id}")
    public String deleteBorrowRecord(@PathVariable int record_id) {
        // 通过Service处理删除操作
        borrowRecordService.deleteBorrowRecord(record_id);
        return "redirect:/orders";
    }

    @GetMapping("/query")
    public String queryOrdersCondition(@RequestParam String condition,@RequestParam String queryChoose, Model model) {
        // 通过service查询
        List<BorrowRecord> list;
        if(Objects.equals(condition, "")){
            // 默认查询所有借阅信息
            model.addAttribute("list",borrowRecordService.getAllBorrowRecords());
            return "borrowRecord/borrowRecord";
        }else{
            if (Objects.equals(queryChoose, "user")) {
                list = borrowRecordService.getBorrowRecordByUser_id(Integer.valueOf(condition));
            } else{
                list = borrowRecordService.getBorrowRecordByBook_id(Integer.valueOf(condition));
            }
            // 把查询到的信息绑定在 model 中
            model.addAttribute("list", list);
            return "borrowRecord/borrowRecord";
        }
    }

    @GetMapping("/queryById/{record_id}")
    public String queryById(@PathVariable Integer record_id, Model model) {
        // 通过service查询
        BorrowRecord borrowRecord = borrowRecordService.getBorrowRecordById(record_id);
        // 把查询到的信息绑定在 model 中
        model.addAttribute("borrowRecord", borrowRecord);
        return "borrowRecord/borrowRecordUpdate";
    }

    @GetMapping("/borrowRecordUpdate")
    public String showUserUpdatePage() {
        return "borrowRecord/borrowRecordUpdate";
    }
}
