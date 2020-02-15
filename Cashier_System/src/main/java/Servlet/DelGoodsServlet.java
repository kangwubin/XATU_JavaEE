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
 * Description:下架商品
 *
 * @author: KangWuBin
 * @Date: 2020/2/12
 * @Time: 11:09
 */
@WebServlet("/deleteGoods")
public class DelGoodsServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("DelGoodsServlet");
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String goodsId = req.getParameter("id");
        int goodsIdInt = Integer.valueOf(goodsId);
        System.out.println(goodsId);
        boolean effect = this.delGoods(goodsIdInt);
        if (effect) {
            System.out.println("商品下架成功！");
        }else {
            System.out.println("商品下架失败！");
        }
    }

    private boolean delGoods(int goodsIdInt) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            String sql = " delete from goods where id=? ";
            connection = DBUtil.getConnection(true);
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, goodsIdInt);
            return (preparedStatement.executeUpdate() == 1);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection, preparedStatement, null);
        }
        return false;
    }
}
