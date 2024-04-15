package testWeb.Service.impl;

import testWeb.Service.*;
import testWeb.dao.robotDAO;
import testWeb.dao.impl.robotDAOImpl;
import testWeb.vo.*;

import java.sql.SQLException;

public class robotServiceImpl implements robotService{
    @Override
    public int Register(robotInfo robot, userInfo user) throws Exception{
        int flag = 0;
        robotDAO dao = new robotDAOImpl();

        try {
            // 判断数据库是否有重名用户
            robotInfo robotDB = dao.queryThatRobot(user.getUsername());
            if (robotDB.getRobotId() == 0) {
                flag = dao.Insert(robot, user.getUserId());
                // 创建新机器人，插入数据库
            }
            else throw new Exception("机器人数据库查询失败："+user.getUsername());

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return flag;
    }
}
