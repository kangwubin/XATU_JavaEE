package task;

import util.DBUtil;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Collections;

/**
 * Description:
 *
 * @author: KangWuBin
 * @Date: 2020/1/4
 * @Time: 16:34
 */
public class DBInit {
    public static void init() {
        try {
            InputStream is = DBInit.class.getClassLoader().getResourceAsStream("init.sql");
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(is, "UTF-8"));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                //忽略注释代码
                int idx = line.indexOf("--");
                if (idx != -1) {
                    line = line.substring(0, idx);
                }
                sb.append(line);
            }
            String[] sqls = sb.toString().split(";");
            Connection connection = null;
            Statement statement = null;
            try {
                for (String sql : sqls) {
                    connection = DBUtil.getConnection();
                    statement = connection.createStatement();
                    statement.executeUpdate(sql);
                }
            } finally {
                DBUtil.close(connection, statement);
            }
            System.out.println(sb);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("数据库初始化异常");
        }

    }

    public static void main(String[] args) {
        init();
    }
}
