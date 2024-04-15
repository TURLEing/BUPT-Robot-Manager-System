package testWeb.dao;

import testWeb.vo.*;
import testWeb.vo.dto.robotDto;

import java.util.List;

public interface robotDAO {
    public robotInfo queryThatRobot (String username) throws Exception;
    public List<robotDto> queryAllRobot() throws Exception;
    public int Insert(robotInfo robot, int belongid) throws Exception;
    public int UpdateTre(int robotId);
    public int UpdateExp(int robotId);
}
