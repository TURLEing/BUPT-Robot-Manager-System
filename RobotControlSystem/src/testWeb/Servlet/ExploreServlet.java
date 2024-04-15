package testWeb.Servlet;

import java.io.IOException;
import java.util.ArrayList;
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

@WebServlet("/explore")
public class ExploreServlet extends HttpServlet {

    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws IOException, ServletException{
        doGet(req, res);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws IOException, ServletException {
        HttpSession session=req.getSession();
        int flag = 0;
        List<exploreDto> exploreDtoList = null;
        try {
            exploreDAO DAO = new exploreDAOImpl();
            int robotId = (int)session.getAttribute("robotId");
            exploreDtoList = DAO.queryAllExplore(robotId);
            flag = 1;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        if(flag == 1) {
            session.setAttribute("explores", exploreDtoList);
            res.sendRedirect("./explore.jsp");
        } else {
            res.sendRedirect("./error.jsp");
        }
    }

}
