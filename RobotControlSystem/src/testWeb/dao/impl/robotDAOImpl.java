package testWeb.dao.impl;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import testWeb.dao.*;
import testWeb.Util.DruidUtil;
import testWeb.vo.dto.robotDto;
import testWeb.vo.mazeinfo;
import testWeb.vo.robotInfo;
import testWeb.vo.userInfo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

public class robotDAOImpl implements robotDAO {

    @Override
    public robotInfo queryThatRobot(String username) throws Exception {
        robotInfo robotinfo = new robotInfo();
        Connection connection = null;
        try {
            // 获取数据库连接
            connection = DruidUtil.getDataSource().getConnection();

            // 执行匹配用户名和密码的逻辑
            String sql = "SELECT * FROM  users u, robot r " +
                         "WHERE u.UserId = r.belongid AND u.username = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                robotinfo.setRobotId(resultSet.getInt("robotid"));
                robotinfo.setRobotName(resultSet.getString("robotName"));
                robotinfo.setStartTime(resultSet.getDate("startTime"));
                robotinfo.setTotTreasure(resultSet.getInt("totTreasure"));
                robotinfo.setNumExplore(resultSet.getInt("numExplore"));
            }

        } catch (SQLException e) {e.printStackTrace();}
        finally {
            DruidUtil.closeConnection(connection);
        }
        return robotinfo;
    }

    @Override
    public List<robotDto> queryAllRobot() {
        List<robotDto> botList = new ArrayList<>();
        try {
            QueryRunner runner = new QueryRunner(DruidUtil.getDataSource());
            String sql = "SELECT robotName, nickid as belongName, totTreasure, numExplore, startTime" +
                         " FROM  users u, robot r WHERE u.UserId = r.belongid ORDER BY totTreasure DESC ";
            botList = runner.query(sql, new BeanListHandler<>(robotDto.class));
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return botList;
    }

    @Override
    public int Insert(robotInfo robot, int belongId) throws Exception {
        int flag = 0;
        Connection connection = null;
        try {
            // 获取数据库连接
            connection = DruidUtil.getDataSource().getConnection();
            PreparedStatement statement;

            if (flag != -1) {
                String sql = "INSERT INTO robot(robotName, belongid, startTime, totTreasure, numExplore) VALUES (?, ?, ?, 0, 0)";
                statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, robot.getRobotName());
                statement.setInt(2, belongId);
                statement.setDate(3, new Date(robot.getStartTime().getTime()));
                int result = statement.executeUpdate();
                if (result > 0) {
                    ResultSet generatedKeys = statement.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        flag = generatedKeys.getInt(1);
                    }
                } else flag = -1;
            }
        } catch (SQLException e) {e.printStackTrace();}
        finally {
            DruidUtil.closeConnection(connection);
        }
        return flag;
    }

    public int UpdateExp(int robotId) {
        int flag = 0;
        try {
            QueryRunner queryRunner = new QueryRunner(DruidUtil.getDataSource());
            String sql = "UPDATE robot SET numExplore = numExplore + 1 WHERE robotid = ?";
            Object[] params = {robotId};
            int res = queryRunner.update(sql, params);
            if (res > 0) flag = 1; // 更新成功
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    public int UpdateTre(int robotId) {
        int flag = 0;
        try {
            QueryRunner queryRunner = new QueryRunner(DruidUtil.getDataSource());
            String sql = "UPDATE robot SET totTreasure = totTreasure + 1 WHERE robotid = ?";
            Object[] params = {robotId};
            int res = queryRunner.update(sql, params);
            if (res > 0) flag = 1; // 更新成功
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }
}
