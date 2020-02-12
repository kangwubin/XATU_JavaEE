package Servlet;

import util.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Description:
 *
 * @author: KangWuBin
 * @Date: 2020/2/12
 * @Time: 11:09
 */
@WebServlet("/deleteGoods")
public class DelGoodsServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("DelGoodsServlet");
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html: charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String goodsId = req.getParameter("id");
        int goodsIdInt = Integer.valueOf(goodsId);
        System.out.println(goodsId);

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            String sql = " delete from goods where id=? ";
            connection = DBUtil.getConnection(true);
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, goodsIdInt);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection, preparedStatement, null);
        }
    }

    public boolean delGoods() {
        return false;
    }
}
