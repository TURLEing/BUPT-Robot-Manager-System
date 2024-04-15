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
import testWeb.dao.*;
import testWeb.dao.impl.*;

@WebServlet("/mazeinfo")
public class MazeServlet extends HttpServlet {

    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws IOException, ServletException{
        doGet(req, res);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws IOException, ServletException {
        List<Integer> numValues = new ArrayList<>();
        List<Float> timeValues = new ArrayList<>();
        int flag = 0;

        try {
            mazeDAO DAO = new mazeDAOImpl();
            List<mazeinfo> mazes = DAO.queryAllMaze();

            for (mazeinfo maze: mazes) {
                 numValues.add(maze.getTotExplored());
                timeValues.add(maze.getAverageTime());
            }

            flag = 1;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        if(flag == 1) {
            HttpSession session=req.getSession();
            session.setAttribute("timeValues", timeValues);
            session.setAttribute("numValues", numValues);
            res.sendRedirect("./mazeinfo.jsp");
        } else {
            res.sendRedirect("./error.jsp");
        }
    }

}
