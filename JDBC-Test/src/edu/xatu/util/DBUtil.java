package edu.xatu.util;

import java.math.BigDecimal;
import java.sql.*;

/**
 * Description:java实现mysql数据库连接：
 * 注意：1.mysql数据库与java中精度高的数据类型的对应
 * 2.数据库的创建步骤与资源释放的顺序.
 *
 * @author: KangWuBin
 * @Date: 2019/11/23
 * @Time: 10:40
 */
public class DBUtil {
    //本机的IP：127.0.0.1-----localhost == 127.0.0.1
    private static final String URL = "jdbc:mysql://localhost:3306/test";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "123456";

    public static void main(String[] args) {
        Connection connection = null;
        ResultSet resultSet = null;
        Statement statement = null;
        try {
            //1.建立数据库连接
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println(connection);
            //2.创建操作命令
            statement = connection.createStatement();
            String sql = "select id,name,chinese,math,english " + "from exam_result";
            //3.执行sql语句
            //ResultSet类似List<Map<String,Object>>
            resultSet = statement.executeQuery(sql);
            //4.处理结果集
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                //使用精度高的BigDecimal
                BigDecimal chinese = resultSet.getBigDecimal("chinese");
                BigDecimal math = resultSet.getBigDecimal("math");
                BigDecimal english = resultSet.getBigDecimal("english");
                //%s表示占位符，其中id=%s，因为int可以转为String.
                System.out.printf("id=%s,name=%s,chinese=%s,math=%s" +
                        ",english=%s,", id, name, chinese, math, english);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                //4.释放资源
                //释放资源的时候根据创建的时侯的反向释放
                //关闭结果集
                if (resultSet != null) {
                    resultSet.close();
                }
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
