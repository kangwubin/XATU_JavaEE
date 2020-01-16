package com.xatu.util;

import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;

import javax.sql.DataSource;

/**
 * Description:
 *
 * @author: KangWuBin
 * @Date: 2020/1/15
 * @Time: 10:11
 */
public class Database {
    private static DataSource instance = null;

    static {
        MysqlConnectionPoolDataSource mysqlDataSource = new MysqlConnectionPoolDataSource();
        mysqlDataSource.setServerName("localhost");
        mysqlDataSource.setPort(3306);
        mysqlDataSource.setUser("root");
        mysqlDataSource.setPassword("123456");
        mysqlDataSource.setDatabaseName("blog_20200115");
        mysqlDataSource.setUseSSL(false);
        mysqlDataSource.setCharacterEncoding("utf8");

        instance = mysqlDataSource;
    }

    public static DataSource getInstance() {
        return instance;
    }
}
