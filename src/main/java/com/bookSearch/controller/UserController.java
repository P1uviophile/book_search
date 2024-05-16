package com.bookSearch.controller;

import com.bookSearch.model.User;
import com.bookSearch.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Controller
@RequestMapping(value="/user")
public class UserController {
    private final IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/queryCondition")
    public String QueryCondition(@RequestParam String condition, Model model, HttpSession session){
        if(Objects.equals(condition, "")){
            return defaultHandler(model,session);
        }else {
            // 通过service查询
            List<User> list = userService.getUserCondition(condition);
            // 把查询到的信息绑定在 model 中
            model.addAttribute("list", list);
            // 页面跳转到user.jsp中
            System.out.println(list);
            return "user/user";
        }
    }

    @GetMapping
    public String defaultHandler(Model model, HttpSession session) {
        // 默认查询用户信息
        Object adminAttribute = session.getAttribute("admin");
        if(adminAttribute != null && (Boolean) adminAttribute){
            //System.out.println("userService.getAllUsers():"+userService.getAllUsers());
            model.addAttribute("list",userService.getAllUsers());
        }else {
            List<User> list = new ArrayList<>();
            list.add(userService.getUserById((Integer) session.getAttribute("loginUserId")));
            model.addAttribute("list",list);
        }
        return "user/user";
    }

    @GetMapping("/queryById/{user_id}")
    public String queryById(@PathVariable Integer user_id, Model model) {
        // 通过service查询
        User user = userService.getUserById(user_id);
        // 把查询到的信息绑定在 model 中
        model.addAttribute("user", user);
        // 页面跳转到user.jsp中
        System.out.println(user.toString());
        return "user/userUpdate";
    }

    @GetMapping("/delete/{user_id}")
    public String deleteUser(@PathVariable Integer user_id) {
        // 通过Service处理删除操作
        userService.deleteUser(user_id);
        // 做一个重定向查询所有用户
        return "redirect:/user";
    }

    @PostMapping("/saveOrUpdate")
    public String saveOrUpdateUser(@ModelAttribute User user, Model model) {
        int count = -1;
        if ((user.getUser_id() != null)) {
            // 表示是更新
            System.out.println(user);
            count = userService.updateUser(user);
            if (count == 0) {
                System.out.println("插入成功");
                // 查询成功，再做一次查询操作
                model.addAttribute("list",userService.getAllUsers());
            } else {
                // 表示插入失败
                System.out.println("插入失败QAQ");
                // 跳转到失败页面
                return "wrongOperate";
            }
        } else {
            count = userService.insertUser(user);
            if (count > 0) {
                System.out.println("插入成功");
                // 查询成功，再做一次查询操作
                model.addAttribute("list",userService.getAllUsers());
            } else {
                // 表示插入失败
                System.out.println("插入失败QAQ");
                // 跳转到失败页面
                return "wrongOperate";
            }
        }
        return "user/user";
    }

    @GetMapping("/userUpdate")
    public String showUserUpdatePage() {
        return "user/userUpdate";
    }
}
