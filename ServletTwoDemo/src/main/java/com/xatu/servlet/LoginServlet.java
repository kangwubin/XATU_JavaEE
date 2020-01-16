package com.xatu.servlet;

import com.xatu.dao.UserDao;
import com.xatu.model.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Description:
 *
 * @author: KangWuBin
 * @Date: 2020/1/15
 * @Time: 10:07
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //从发送过来的请求中获取用户填写的信息
        req.setCharacterEncoding("UTF-8");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        //从持久层获取用户信息
        UserDao userDao = new UserDao();
        User user = null;
        try {
            user = userDao.findUserByUsernameAndPassword(username, password);
        } catch (SQLException e) {
            e.printStackTrace();
            resp.sendError(500, e.getMessage());
        }
        if (user == null) {
            //用户名或者密码错误
            System.out.println("没有这个用户");
            return;
        }
        //把当前登录的用户信息放入session中，“user”是key
        //利用session+cookie做到http协议的会话保持你功能
        //这里的HttpSession会自动设置cookie
        HttpSession session = req.getSession();
        System.out.println(session.getId());
        session.setAttribute("user", user);
        resp.sendRedirect("/success");
    }
}
