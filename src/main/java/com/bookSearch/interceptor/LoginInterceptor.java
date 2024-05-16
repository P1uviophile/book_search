package com.bookSearch.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Objects;

/**
 * 用户登录状态拦截器
 */
public class LoginInterceptor implements HandlerInterceptor {
    /**
     * 该方法将在Controller处理之前进行调用
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        // 获取session
        HttpSession session = request.getSession();
        if(request.getRequestURI().contains("login")||request.getRequestURI().contains("register")||request.getRequestURI().contains("logout")||request.getRequestURI().contains("Login")){
            return true;
        }
        // 检查session中的某个值是否为true
        String isLoggedIn = (String) session.getAttribute("loginUser");
        if (Objects.equals(isLoggedIn, "true")) {
            // 用户已登录，允许访问
            return true;
        } else {
            // 用户未登录，拦截请求，可以跳转到登录页面或返回错误信息
            System.out.println("逮捕!");
            response.sendRedirect("/book_search_war_exploded/login"); // 重定向到登录页面
            return false;
        }
    }

    /**
     * @Description  该方法将在Controller处理之后进行调用
     **/
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
    }

    /**
     * @Description  处理完请求后带调用
     **/
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
    }
}