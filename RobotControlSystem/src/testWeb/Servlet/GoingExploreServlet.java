package testWeb.Servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import testWeb.vo.*;
import testWeb.vo.dto.*;
import testWeb.dao.*;
import testWeb.dao.impl.*;

@WebServlet("/goingExplore")
public class GoingExploreServlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws IOException, ServletException{
        HttpSession session=req.getSession();
        int isExplore = (int)session.getAttribute("IsExplore");
        if (isExplore == 1) res.sendRedirect("./displayexplore.jsp");
        else res.sendRedirect("./goingexplore.jsp");
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws IOException, ServletException {
        // Task.1 显示迷宫类型，选中迷宫类型并开始一个探索；
        // 向数据库插入新的探索数据，向 Bot 数据库更新探索次数，向
        int flag = 0;
        HttpSession session=req.getSession();
        exploreDto newExplore = new exploreDto();
        int exploreId = 0;
        try {
            req.setCharacterEncoding("utf-8");
            int robotId = (int)session.getAttribute("robotId");
            String strMazeId = req.getParameter("mazeid");
            newExplore.setMazeId(Integer.parseInt(strMazeId));
            newExplore.setStartTime(new Timestamp(System.currentTimeMillis()));

            // 运行自动化脚本
            try {
                String command = "cmd /c start auto.bat";
                Process process = Runtime.getRuntime().exec(command);
            } catch (Exception e) {
                e.printStackTrace();
            }

            // 插入数据库
            exploreDAO DAO = new exploreDAOImpl();
            mazeDAO mazeDao = new mazeDAOImpl();
            robotDAO RobotDAO = new robotDAOImpl();
            try {
                exploreId = DAO.Insert(newExplore, robotId);
                int flag1 = RobotDAO.UpdateExp(robotId);
                int flag2 = mazeDao.UpdateExp(newExplore.getMazeId());
                if (exploreId <= 0) throw new Exception("探索插入出错！");
                if (flag1 <= 0)     throw new Exception("萝卜更新出错！");
                if (flag2 <= 0)     throw new Exception("地图更新出错！");
            } catch (Exception e) {
                e.printStackTrace();
            }
            flag = 1;

        } catch (Exception e) {
            e.printStackTrace();
        }

        if(flag == 1) {
            session.setAttribute("newExplore", newExplore);
            session.setAttribute("newExploreId", exploreId);
            session.setAttribute("IsExplore", 1);
            res.sendRedirect("./displayexplore.jsp");
        } else {
            res.sendRedirect("./error.jsp");
        }
    }

}
