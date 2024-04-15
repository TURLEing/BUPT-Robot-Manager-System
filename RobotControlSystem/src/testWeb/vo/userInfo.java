package testWeb.vo;

public class userInfo {

    private int UserId;
    private String username;

    private String nickid;
    private String password;

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickid() {
        return nickid;
    }

    public void setNickid(String nickid) {
        this.nickid = nickid;
    }
}
