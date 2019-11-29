package edu.xatu.util;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Description:java实现mysql数据库连接：
 * 注意：1.mysql数据库与java中精度高的数据类型的对应
 * 2.数据库的创建步骤与资源释放的顺序.
 *
 * @author: KangWuBin
 * @Date: 2019/11/23
 * @Time: 10:40
 */
public class DBUtil3Insert {
    //本机的IP：127.0.0.1-----localhost == 127.0.0.1
    private static final String URL = "jdbc:mysql://localhost:3306/test";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "123456";

    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            DataSource ds = new MysqlDataSource();
            ((MysqlDataSource) ds).setURL(URL);
            ((MysqlDataSource) ds).setUser(USERNAME);
            ((MysqlDataSource) ds).setPassword(PASSWORD);
            connection = ds.getConnection();
            System.out.println(connection);
            //?-->表示占位符
            String sqlInsert = "insert into exam_result" +
                    "(id,name,chinese,math,english)  " + "values (?,?,?,?,?)";
            statement = connection.prepareStatement(sqlInsert);
            statement.setInt(1, 8);//第一个占位符，第1条数据.
            statement.setString(2, "KWB");
            statement.setBigDecimal(3, new BigDecimal(90));
            statement.setBigDecimal(4, new BigDecimal(96));
            statement.setBigDecimal(5, new BigDecimal(98));
            //ResultSet类似List<Map<String,Object>>
            int num = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                //释放资源的时候根据创建的时侯的反向释放
                //关闭命令
                if (statement != null) {
                    statement.close();
                }
                //判断是为了防止空指针异常
                //关闭连接命令
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
