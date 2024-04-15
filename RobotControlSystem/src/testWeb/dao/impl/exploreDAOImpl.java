package testWeb.dao.impl;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.*;
import testWeb.dao.*;
import testWeb.Util.DruidUtil;
import testWeb.vo.dto.exploreDto;
import testWeb.vo.userInfo;

import java.math.BigInteger;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class exploreDAOImpl implements exploreDAO{

    @Override
    public List<exploreDto> queryAllExplore(int robotId) throws Exception {
        List<exploreDto> exploreList = new ArrayList<>();
        try {
            QueryRunner runner = new QueryRunner(DruidUtil.getDataSource());
            String sql = "SELECT * FROM exploration t WHERE robotId = ? ORDER BY endTime DESC";
            exploreList = runner.query(sql, new BeanListHandler<>(exploreDto.class), robotId);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return exploreList;
    }

    @Override
    public int Insert(exploreDto exploredInfo, int robotId) throws Exception {
        int flag = 0;
        try {
            // 获取数据库连接
            QueryRunner queryRunner = new QueryRunner(DruidUtil.getDataSource());

            String sql = "INSERT INTO exploration(robotId, mazeId, treasureId, startTime, endTime) VALUES (?, ?, ?, ?, ?)";
            Object[] params = {robotId, exploredInfo.getMazeId(), -1, exploredInfo.getStartTime(), exploredInfo.getStartTime()};
            BigInteger res = queryRunner.insert(sql, new ScalarHandler<>(), params);

            if (res != null) {
                flag = res.intValue();
            } else flag = -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    public int Update(exploreDto explore, int exploreID) {
        int flag = 0;
        try {
            QueryRunner queryRunner = new QueryRunner(DruidUtil.getDataSource());
            String sql = "UPDATE exploration SET treasureId = ?, endTime = ? WHERE explorationId = ?";
            Object[] params = {explore.getTreasureId(), explore.getEndTime(), exploreID};
            int res = queryRunner.update(sql, params);
            if (res > 0) flag = 1; // 更新成功
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }
}
