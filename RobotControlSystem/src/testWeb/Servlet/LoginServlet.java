package testWeb.Servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import testWeb.vo.*;
import testWeb.dao.UserDAO;
import testWeb.dao.impl.*;

import static testWeb.Util.EncryptUtil.MD5Encode;

public class LoginServlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws IOException, ServletException{
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws IOException, ServletException{
        userInfo userinfo = new userInfo();
        userinfo.setUsername(req.getParameter("username"));
        userinfo.setPassword(MD5Encode(req.getParameter("password")));

        UserDAO dao = new UserDAOImpl();
        int flag = 0;
        try {
            flag = dao.queryByUserInfo(userinfo);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if(flag == 1) {
            HttpSession session=req.getSession();
            session.setAttribute("username", userinfo.getUsername());
            session.setAttribute("IsExplore", 0);
            req.getRequestDispatcher("/main").forward(req, res);
        } else {
            String errorMessage = "The username or password is incorrect, please try it again!";
            req.setAttribute("errorMessage", errorMessage);
            req.getRequestDispatcher("login.jsp").forward(req, res);
        }
    }

}
