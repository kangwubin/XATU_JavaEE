package util;

import org.sqlite.SQLiteConfig;
import org.sqlite.SQLiteDataSource;

import javax.sql.DataSource;
import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Description:
 *
 * @author: KangWuBin
 * @Date: 2020/1/4
 * @Time: 15:06
 */
public class DBUtil {
    //单例模式实现数据库连接类
    private static volatile DataSource DATA_SOURCE;

    private DBUtil() {
    }

    /*获取SQlLite的URL*/
    private static String getUrl() throws URISyntaxException {
        String dbName = "Everything.db";
        //getClassLoader--获取类加载器
        URL url = DBUtil.class.getClassLoader().getResource(".");
        return "jdbc:sqlite://" + (new File(url.toURI()).getParent() + File.separator + dbName);
    }

    /*获取数据库连接池---单例模式(面试重点)*/
    private static DataSource getDataSource() throws URISyntaxException {
        if (DATA_SOURCE == null) {
            synchronized (DBUtil.class) {
                if (DATA_SOURCE == null) {
                    //mysql：yyyy--MM--dd HH:mm:ss
                    //sqlite:
                    SQLiteConfig config = new SQLiteConfig();
                    config.setDateStringFormat(Util.DATE_PATTERN);
                    DATA_SOURCE = new SQLiteDataSource();
                    ((SQLiteDataSource) DATA_SOURCE).setUrl(getUrl());
                }
            }
        }
        return DATA_SOURCE;
    }

    /*获取数据库连接；
     * 1.class.forName("驱动类全名",DriverManger.getConnection)；
     * 2.DataSource*/
    public static Connection getConnection() {
        try {
            return getDataSource().getConnection();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("数据库连接获取失败");
        }
    }

    /*释放数据库资源；*/
    public static void close(Connection connection, Statement statement, ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("释放数据库资源错误");
        }
    }

    public static void close(Connection connection, Statement statement) {
        close(connection, statement, null);
    }

    //初始化数据库
    public static void main(String[] args) throws URISyntaxException {
        /*URL url = DBUtil.class.getClassLoader().getResource("./init.sql");
        System.out.println(new File(url.toURI()).getParent());*/
        Connection connection = getConnection();
    }
}
