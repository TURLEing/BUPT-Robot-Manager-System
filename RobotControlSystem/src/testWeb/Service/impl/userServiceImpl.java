package testWeb.Service.impl;

import testWeb.Service.userService;
import testWeb.dao.UserDAO;
import testWeb.dao.impl.UserDAOImpl;
import testWeb.vo.userInfo;

import java.sql.SQLException;

public class userServiceImpl implements userService {
    public int updatePassword(String username, String oriPassword, String newPassword) throws Exception{

        int flag = 0;
        UserDAO dao = new UserDAOImpl();
        userInfo user = new userInfo();
        user.setUsername(username);
        user.setPassword(oriPassword);

        try {
            // 判断原密码是否一致
            if (dao.queryByUserInfo(user) == 1) {
                user.setPassword(newPassword);
                flag = dao.Update(user); // 一致时更新密码；
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public int Register(userInfo user) throws Exception{
        int flag = 0;
        UserDAO dao = new UserDAOImpl();

        try {
            // 判断数据库是否有重名用户
            userInfo userDB = dao.getThatUser(user.getUsername());
            if (userDB == null) {
                flag = dao.Insert(user); // 创建新用户，插入数据库
            }
            else throw new Exception("用户数据库查询失败："+user.getUsername());

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return flag;
    }
}
