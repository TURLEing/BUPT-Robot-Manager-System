package testWeb.dao;

import testWeb.vo.dto.*;
import testWeb.vo.userInfo;

import java.util.List;

public interface exploreDAO {
    public List<exploreDto> queryAllExplore(int robotId) throws Exception;
    public int Insert(exploreDto exploredInfo, int robotId) throws Exception;
    public int Update(exploreDto explore, int exploreID) ;
}
