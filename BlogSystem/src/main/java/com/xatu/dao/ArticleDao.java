package com.xatu.dao;

import com.xatu.model.Article;
import com.xatu.model.User;
import com.xatu.util.DB;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Description:
 *
 * @author: KangWuBin
 * @Date: 2020/1/16
 * @Time: 11:29
 */
public class ArticleDao {
    public Article publishArticle(User user, String title, String content) throws SQLException {
        //获取当前时间
        Date now = new Date();
        //将当前时间的格式转化为----yyyy-MM-dd HH:mm:ss
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //操作数据库，插入文章；
        String sql = "INSERT INTO articles (author_id, title, content, published_at) VALUES (?, ?, ?, ?)";
        try (Connection connection = DB.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                statement.setInt(1, user.getId());
                statement.setString(2, title);
                statement.setString(3, content);
                statement.setString(4, dateFormat.format(now));

                statement.executeUpdate();
                try (ResultSet resultSet = statement.getGeneratedKeys()) {
                    resultSet.next();
                    int id = resultSet.getInt(1);
                    return new Article(id, user, title, content, now);
                }
            }
        }
    }
}
