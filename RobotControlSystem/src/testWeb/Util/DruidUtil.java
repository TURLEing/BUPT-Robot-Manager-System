package testWeb.Util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * JDBC工具类 ：使用 Druid (阿里巴巴) 数据库连接池技术操作 mysql
 */
public class DruidUtil {
    private static DataSource ds;

    static {

        try {
            //1.加载配置文件
            Properties pro = new Properties();
            pro.load(DruidUtil.class.getResourceAsStream("/druid.properties"));

            //2.初始化连接池对象
            ds = DruidDataSourceFactory.createDataSource(pro);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取连接池对象
     */
    public static DataSource getDataSource() {
        return ds;
    }

    /**
     * 获取连接Connection对象
     */
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    /**
     * 关闭数据库连接
     * @param con
     */
    public static void closeConnection(Connection con) {
        try {
            if(con != null) {
                con.close();
            }
        }catch(Exception e) {
            e.printStackTrace();
        }

    }
}
