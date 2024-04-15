package testWeb.dao;

import testWeb.vo.*;
import testWeb.vo.dto.robotDto;

import java.util.List;

public interface mazeDAO {
    public List<mazeinfo> queryAllMaze() throws Exception;
    public int UpdateExp(int mazeId);
}
