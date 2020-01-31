package com.xatu.util;

import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Description:
 *
 * @author: KangWuBin
 * @Date: 2020/1/16
 * @Time: 10:18
 */
public class DB {
    private static DataSource dataSource;

    static {
        MysqlConnectionPoolDataSource mysql = new MysqlConnectionPoolDataSource();
        mysql.setServerName("localhost");
        mysql.setPort(3306);
        mysql.setUser("root");
        mysql.setPassword("123456");
        mysql.setDatabaseName("blog_20200115");
        mysql.setUseSSL(false);
        mysql.setCharacterEncoding("utf8");

        dataSource = mysql;
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
