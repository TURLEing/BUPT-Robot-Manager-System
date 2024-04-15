package testWeb.dao;

import testWeb.vo.*;
import testWeb.vo.dto.*;

import java.util.List;

public interface treasureDAO {
    public List<treasureDto> queryAllTreasure(int robotId) throws Exception;
    int Insert(int treasureId, String type) throws Exception;
}
