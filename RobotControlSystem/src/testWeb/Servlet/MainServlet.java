package testWeb.Servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import testWeb.Service.impl.userServiceImpl;
import testWeb.Service.userService;
import testWeb.vo.*;
import testWeb.vo.dto.*;
import testWeb.dao.*;
import testWeb.dao.impl.*;

import testWeb.Util.EncryptUtil;

import static testWeb.Util.EncryptUtil.MD5Encode;

@WebServlet("/main")
public class MainServlet extends HttpServlet {

    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws IOException, ServletException{
        doGet(req, res);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws IOException, ServletException{
        int flag = 0;
        userInfo user = new userInfo();
        robotInfo robot = new robotInfo();
        List<robotDto> robotDtoList = new ArrayList<robotDto>();

        try {
            robotDAO roDAO = new robotDAOImpl();
            UserDAO usDAO = new UserDAOImpl();

            robot = roDAO.queryThatRobot(req.getParameter("username"));
            user = usDAO.getThatUser(req.getParameter("username"));
            robotDtoList = roDAO.queryAllRobot();
            flag = 1;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if(flag == 1) {
            HttpSession session=req.getSession();
            int days = (int)((new Date().getTime() - robot.getStartTime().getTime()) / (1000*3600*24)) + 1;
            session.setAttribute("robotId", robot.getRobotId());
            session.setAttribute("nickname", user.getNickid());
            session.setAttribute("username", user.getUsername());
            session.setAttribute("userId", user.getUserId());
            session.setAttribute("robot", robot);
            session.setAttribute("lastDay",days);
            session.setAttribute("robotTops", robotDtoList);
            res.sendRedirect("./homepage.jsp");
        } else {
            res.sendRedirect("./error.jsp");
        }
    }

}
