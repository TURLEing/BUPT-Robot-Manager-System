package testWeb.Servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import testWeb.Service.impl.*;
import testWeb.Service.*;
import testWeb.vo.*;

import static testWeb.Util.EncryptUtil.MD5Encode;

public class RegisterServlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws IOException, ServletException{
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws IOException, ServletException {

        req.setCharacterEncoding("utf-8");
        String username = req.getParameter("username");
        String nickname = req.getParameter("nickname");
        String robotname = req.getParameter("robotname");
        String password = req.getParameter("password");
        String passwordConfirm = req.getParameter("passwordConfirm");

        if (!password.equals(passwordConfirm)) {
            String errorMessage = "两次密码不匹配！";
            req.setAttribute("errorMessage", errorMessage);
            req.getRequestDispatcher("register.jsp").forward(req, res);
        } else {
            userInfo userinfo = new userInfo();
            robotInfo robotinfo = new robotInfo();

            // 初始化用户信息
            userinfo.setUsername(username);
            userinfo.setNickid(nickname);
            userinfo.setPassword(MD5Encode(password));

            // 初始化机器人信息
            robotinfo.setRobotName(robotname);
            robotinfo.setStartTime(new Date());

            // 导入数据库
            userService uService = new userServiceImpl();
            robotService rService = new robotServiceImpl();

            int flag = 0;
            try {
                int userId = uService.Register(userinfo);
                if (userId > 0) {
                    userinfo.setUserId(userId);
                    flag = rService.Register(robotinfo, userinfo);
                }
                else throw new Exception("用户插入出错！"+flag);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (flag > 0) {
                HttpSession session = req.getSession();
                session.setAttribute("username", userinfo.getUsername());
                req.getRequestDispatcher("/main").forward(req, res);
            } else {
                String errorMessage = "Error: Duplicate username!";
                req.setAttribute("errorMessage", errorMessage);
                req.getRequestDispatcher("register.jsp").forward(req, res);
            }
        }
    }
}
