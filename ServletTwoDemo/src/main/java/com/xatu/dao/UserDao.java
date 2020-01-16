package com.xatu.dao;

import com.xatu.model.User;
import com.xatu.util.Database;

import java.security.MessageDigest;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 *
 * @author: KangWuBin
 * @Date: 2020/1/15
 * @Time: 10:11
 */
public class UserDao {
    private static String secret = "朕就是皇上";

    public User registerUser(String username, String nickname, String password) throws SQLException {
        String sql = "INSERT INTO users (username, nickname, password) VALUES (?, ?, ?)";

        try (Connection connection = Database.getInstance().getConnection()) {
            // Statement.RETURN_GENERATED_KEYS--固定用法，表示返回插入的自增 id 值
            try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, username);
                statement.setString(2, nickname);
                statement.setString(3, password);

                statement.executeUpdate();
                try (ResultSet rs = statement.getGeneratedKeys()) {
                    rs.next();
                    int id = rs.getInt(1);
                    return new User(id, username, nickname);
                }
            }
        } catch (SQLException e) {
            // 如果是用户名重复异常，属于客户端的问题，特殊处理下
            if (e.getMessage().contains("Duplicate entry")) {
                return null;
            }

            throw e;
        }
    }

    public User findUserByUsernameAndPassword(String username, String password) throws SQLException {
        String sql = "SELECT id, username, nickname FROM users WHERE username = ? AND password = ?";

        // try-with-resource
        try (Connection connection = Database.getInstance().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, username);
                statement.setString(2, password);

                try (ResultSet rs = statement.executeQuery()) {
                    // 因为我们只期望有两个情况：1. 有一行 2. 一行都没有
                    // 所以，通过 if 而不需要 while 就可以区分出来了
                    if (!rs.next()) {
                        return null;
                    }

                    int id = rs.getInt(1);
                    // username 我们有了，不需要取
                    String nickname = rs.getString(3);

                    return new User(id, username, nickname);
                }
            }
        }

        /*
        Connection connection;
        try {
            connection = Database.getInstance().getConnection();
        } finally {
            connection.close();
        }
        */
    }

    //给密码做摘要
    private String encrypted(String password) {
        password = password + secret;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] input = password.getBytes("UTF-8");
            byte[] output = messageDigest.digest(input);
            StringBuilder sb = new StringBuilder();
            for (byte b : output) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();

        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
