package testWeb.Servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import testWeb.Service.impl.*;
import testWeb.Service.*;
import testWeb.vo.*;

import testWeb.Util.EncryptUtil;

import static testWeb.Util.EncryptUtil.MD5Encode;

@WebServlet("/userUpdate")
public class UserUpdateServlet  extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws IOException, ServletException{
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws IOException, ServletException {
        HttpSession session = req.getSession();
        String oriPassword = req.getParameter("oriPassword");
        String username = (String) session.getAttribute("username");

        String newPassword = req.getParameter("newPassword");
        String confPassword = req.getParameter("confirmPassword");
        int flag = 0;

        if (!newPassword.equals(confPassword)) {
            String errorMessage = "Two passwords do not match, please try it again!";
            req.setAttribute("errorMessage", errorMessage);
            req.getRequestDispatcher("user_update.jsp").forward(req, res);
        } else if (oriPassword.equals(newPassword)) {
            String errorMessage = "Old and new passwords are the same, please try it again!";
            req.setAttribute("errorMessage", errorMessage);
            req.getRequestDispatcher("user_update.jsp").forward(req, res);
        } else{

            userService userservice = new userServiceImpl();
            try {
                flag = userservice.updatePassword(username,
                                    MD5Encode(oriPassword), MD5Encode(newPassword));
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (flag == 1) {
                String successMessage = "Password updated successfully!";
                req.setAttribute("successMessage", successMessage);
                req.getRequestDispatcher("/user_update.jsp").forward(req, res);
            } else {
                String errorMessage = "The original password does not match, please try it again!";
                req.setAttribute("errorMessage", errorMessage);
                req.getRequestDispatcher("/user_update.jsp").forward(req, res);
            }
        }
    }
}