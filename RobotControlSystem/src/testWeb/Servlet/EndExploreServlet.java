package testWeb.Servlet;

import java.io.IOException;
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

@WebServlet("/endExplore")
public class EndExploreServlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws IOException, ServletException{
        HttpSession session=req.getSession();
        int RefreshTime = (int)session.getAttribute("RefreshTime");
        session.setAttribute("RefreshTime", RefreshTime);
        doPost(req, res);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws IOException, ServletException {
        // Task.2 更新
        // 向数据库更新探索的结束事件及宝藏编号，插入新的宝藏数据
        int flag1, flag2, flag3, flag = 0;
        HttpSession session=req.getSession();
        try {
            req.setCharacterEncoding("utf-8");
            int robotId = (int)session.getAttribute("robotId");
            int exploreId = (int)session.getAttribute("newExploreId");
            String type = (String)session.getAttribute("newTreasureType");

            exploreDto explore = (exploreDto)session.getAttribute("newExplore");
            explore.setEndTime(new Timestamp(System.currentTimeMillis()));
            if (type != null) {
                explore.setTreasureId(exploreId);
            }

            // 插入数据库
            exploreDAO ExploreDAO = new exploreDAOImpl();
            treasureDAO TreasureDAO = new treasureDAOImpl();
            robotDAO RobotDAO = new robotDAOImpl();
            try {
                flag1 = ExploreDAO.Update(explore, exploreId);
                if (type != null) {
                    flag2 = TreasureDAO.Insert(exploreId, type);
                    flag3 = RobotDAO.UpdateTre(robotId);
                }
                else {
                    flag2 = 1; flag3 = 1;
                }
                if (flag1 <= 0 || flag2 <= 0 || flag3 <= 0)
                    throw new Exception("探索更新出错！");
            } catch (Exception e) {
                e.printStackTrace();
            }
            flag = 1;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        if(flag == 1) {
            session.setAttribute("IsExplore", 0);
            session.setAttribute("newTreasureType", null);
            res.sendRedirect("./goingexplore.jsp");
        } else {
            res.sendRedirect("./error.jsp");
        }
    }

}
