package testWeb.dao.impl;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import testWeb.dao.*;
import testWeb.Util.DruidUtil;
import testWeb.vo.dto.exploreDto;
import testWeb.vo.mazeinfo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class mazeDAOImpl implements mazeDAO{

    @Override
    public List<mazeinfo> queryAllMaze() throws Exception {
        List<mazeinfo> mazeList = new ArrayList<>();
        try {
            QueryRunner runner = new QueryRunner(DruidUtil.getDataSource());
            String sql = "SELECT * FROM maze";
            mazeList = runner.query(sql, new BeanListHandler<>(mazeinfo.class));
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return mazeList;
    }

    @Override
    public int UpdateExp(int mazeId) {
        int flag = 0;
        try {
            QueryRunner queryRunner = new QueryRunner(DruidUtil.getDataSource());
            String sql = "UPDATE maze SET totExplored = totExplored + 1 WHERE mazeID = ?";
            Object[] params = {mazeId};
            int res = queryRunner.update(sql, params);
            if (res > 0) flag = 1; // 更新成功
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }
}
