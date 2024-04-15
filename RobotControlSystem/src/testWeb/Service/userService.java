package testWeb.Service;

import testWeb.vo.userInfo;

public interface userService {
    public int updatePassword(String username, String oriPassword, String newPassword) throws Exception;
    public int Register(userInfo user) throws Exception;
}
