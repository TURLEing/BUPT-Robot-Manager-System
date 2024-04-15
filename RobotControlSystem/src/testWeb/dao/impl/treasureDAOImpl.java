package testWeb.dao.impl;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import testWeb.dao.*;
import testWeb.Util.DruidUtil;
import testWeb.vo.dto.treasureDto;

import java.math.BigInteger;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class treasureDAOImpl implements treasureDAO{
    @Override
    public List<treasureDto> queryAllTreasure(int robotId) throws Exception {
        List<treasureDto> treasureList = new ArrayList<>();
        try {
            QueryRunner queryRunner = new QueryRunner(DruidUtil.getDataSource());
            String sql = "SELECT treasureId, mazeID as belongMazeId, endTime as findTime, type" +
                         "  FROM treasure t NATURAL JOIN exploration e" +
                         " WHERE robotId = ? ORDER BY findTime DESC";
            treasureList = queryRunner.query(sql, new BeanListHandler<>(treasureDto.class), robotId);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return treasureList;
    }

    @Override
    public int Insert(int treasureId, String type) throws Exception {
        int flag = 0;
        try {
            // 获取数据库连接
            QueryRunner queryRunner = new QueryRunner(DruidUtil.getDataSource());

            String sql = "INSERT INTO treasure(treasureId, type) VALUES (?, ?)";
            Object[] params = {treasureId, type};
            BigInteger res = queryRunner.insert(sql, new ScalarHandler<>(), params);

            if (res != null) {
                flag = res.intValue();
            } else flag = -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }
}
