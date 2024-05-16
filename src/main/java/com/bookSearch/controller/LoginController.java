package com.bookSearch.controller;

import com.bookSearch.model.User;
import com.bookSearch.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Objects;


@Controller
@RequestMapping(value="/login")
public class LoginController {

    private final IUserService userLoginService;

    @Autowired
    public LoginController(IUserService userLoginService) {
        this.userLoginService = userLoginService;
    }

    @GetMapping
    public String defaultHandler() {
        // 默认跳转登录界面
        return "login/Login";
    }

    /***
     * 用户登录
     * ***/
    @PostMapping ("/userLogin")
    public String userLogin(@ModelAttribute User user, Model model, HttpSession session) {
        System.out.println("Login");
        System.out.println("userName:"+user.getUsername());
        System.out.println("userName:"+user.getPassword());
        // 通过service查询
        User loginUser = userLoginService.getUserByName(user.getUsername());
        if(loginUser!=null&& Objects.equals(loginUser.getPassword(), user.getPassword())){
            // 成功登录
            System.out.println("Login successful");
            // loginUser.setPassword(null);
            System.out.println("session admin: " + Objects.equals(loginUser.getRole(), "1"));
            session.setAttribute("admin", Objects.equals(loginUser.getRole(), "1"));
            session.setAttribute("loginUserId",loginUser.getUser_id());
            session.setAttribute("loginUser","true");
            return "userIndex";
        }else {
            // 登录失败
            System.out.println("Login failed");
            model.addAttribute("msg", "账号密码错误");
            return "login/fail";
        }
    }

    @PostMapping ("/userRegister")
    public String userRegister(@ModelAttribute User user) {
        // 通过service查询
        User registerUser = userLoginService.getUserByName(user.getUsername());
        if(registerUser==null){
            registerUser = new User();
            registerUser.setPassword(user.getPassword());
            registerUser.setRole("0");
            registerUser.setUsername(user.getUsername());
            int flag = userLoginService.insertUser(registerUser);
            if (flag>0)
                System.out.println("register successful");
            else
                System.out.println("register fail");
            return "redirect:/login";
        }else {
            // 失败
            System.out.println("register failed");
            return "login/fail";
        }
    }

    @GetMapping("/register")
    public String register() {
        // 默认跳转
        return "login/register";
    }
}
