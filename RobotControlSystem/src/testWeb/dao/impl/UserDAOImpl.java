package testWeb.dao.impl;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.*;
import testWeb.dao.UserDAO;
import testWeb.vo.userInfo;
import testWeb.Util.DruidUtil;

import java.math.BigInteger;
import java.sql.*;


public class UserDAOImpl implements UserDAO {
    @Override
    public int queryByUserInfo(userInfo userinfo) throws Exception {
        int flag = 0;
        try {
            // 获取数据库连接, 创建QueryRunner对象
            QueryRunner queryRunner = new QueryRunner(DruidUtil.getDataSource());

            // 执行匹配用户名和密码的逻辑
            String sql = "SELECT COUNT(*) FROM users WHERE username = ? AND password = ?";
            Object[] params = {userinfo.getUsername(), userinfo.getPassword()};

            // 调用 query 方法执行 SQL 查询，并使用常数容器指定查询结果，params 传递参数
            Long count = queryRunner.query(sql, new ScalarHandler<>(), params);

            if (count != null && count > 0) { flag = 1; }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }


    public userInfo getThatUser(String username) throws Exception {
        userInfo user = new userInfo();
        try {
            QueryRunner queryRunner = new QueryRunner(DruidUtil.getDataSource());
            String sql = "SELECT * FROM users WHERE username = ?";

            // 调用 query 方法执行 SQL 查询，并使用 Javabean 容器指定查询结果，params 传递参数
            user = queryRunner.query(sql, new BeanHandler<>(userInfo.class), username);
        } catch (SQLException e) {e.printStackTrace();}
        return user;
    }

    @Override
    public int Insert(userInfo userinfo) throws Exception {
        int flag = 0;
        try {
            // 获取数据库连接
            QueryRunner queryRunner = new QueryRunner(DruidUtil.getDataSource());

            String sql = "INSERT INTO users (username, nickid, password) VALUES (?, ?, ?)";
            Object[] params = {userinfo.getUsername(), userinfo.getNickid(), userinfo.getPassword()};
            BigInteger res = queryRunner.insert(sql, new ScalarHandler<>(), params);

            if (res != null) {
                flag = res.intValue();
            } else flag = -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    public int Update(userInfo userinfo) {
        int flag = 0;
        try {
            QueryRunner queryRunner = new QueryRunner(DruidUtil.getDataSource());
            String sql = "UPDATE users SET password = ? WHERE username = ?";
            Object[] params = {userinfo.getPassword(), userinfo.getUsername()};
            int res = queryRunner.update(sql, params);
            if (res > 0) flag = 1; // 更新成功
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

}
