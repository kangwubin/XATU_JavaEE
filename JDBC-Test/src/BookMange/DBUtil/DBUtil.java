package BookMange.DBUtil;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Description:设计JDBC数据库连接代码的复用
 *
 * @author: KangWuBin
 * @Date: 2019/11/30
 * @Time: 16:00
 */
public class DBUtil {
    private static DataSource DATA_SOURCE;
    private static final String URL = "jdbc:mysql://localhost:3306/ebook";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "123456";

    private DBUtil() {
    }

    //获取数据源方法--单例模式
    //DataSource--java标准库的sql数据源，为数据库的迁移提供方便。
    public static DataSource getDataSource() {
        if (DATA_SOURCE == null) {
            DATA_SOURCE = new MysqlDataSource();
            ((MysqlDataSource) DATA_SOURCE).setURL(URL);
            ((MysqlDataSource) DATA_SOURCE).setUser(USERNAME);
            ((MysqlDataSource) DATA_SOURCE).setPassword(PASSWORD);
        }
        return DATA_SOURCE;
    }

    //获取数据库连接
    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = getDataSource().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    //关闭数据库
    public static void close(Connection connection, PreparedStatement ps, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println(getConnection());
    }
}
