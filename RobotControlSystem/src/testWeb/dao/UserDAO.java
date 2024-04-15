package testWeb.dao;

import testWeb.vo.*;
public interface UserDAO {
    public int queryByUserInfo (userInfo userinfo) throws Exception;
    public userInfo getThatUser(String username) throws Exception;
    public int Insert(userInfo userinfo) throws Exception;
    public int Update(userInfo userinfo) throws Exception;
}
