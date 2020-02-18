package Servlet;

import entity.Goods;
import util.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

/**
 * Description:更新商品
 *
 * @author: KangWuBin
 * @Date: 2020/2/12
 * @Time: 9:04
 */
@WebServlet("/updateGoods")
public class GoodsUpdateServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("GoodsUpdateServlet");
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String goodsStringId = req.getParameter("goodsID");
        int goodsId = Integer.valueOf(goodsStringId);
        String name = req.getParameter("name");
        String introduce = req.getParameter("introduce");
        int stock = Integer.parseInt(req.getParameter("stock"));
        String unit = req.getParameter("unit");

        String price = req.getParameter("price");
        double doublePrice = Double.valueOf(price);
        int realPrice = new Double(doublePrice * 100).intValue();

        int discount = Integer.parseInt(req.getParameter("discount"));

        //1.查看当前的goodsId是否存在
        Goods goods = this.getGoods(goodsId);
        if (goods == null) {
            System.out.println("没有商品！");
            resp.sendRedirect("index.html");
        } else {
            //2.检查完成之后，如果存在ID,那么进行删除
            goods.setName(name);
            goods.setIntroduce(introduce);
            goods.setStock(stock);
            goods.setUnit(unit);
            goods.setPrice(Integer.valueOf(price));
            goods.setDiscount(discount);

            //把查询的商品，进行更新，随后对数据库进行操作，把当前的goods进行更新；

            boolean effect = this.modifyGoods(goods);
            if (effect) {
                System.out.println("更新成功！");
                resp.sendRedirect("goodsbrowse.html");
            } else {
                System.out.println("更新失败！");
                resp.sendRedirect("index.html");
            }
        }
    }

    private boolean modifyGoods(Goods goods) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        boolean effect = false;
        try {
            String sql = "update goods set name=?,introduce=?,stock=?,unit=?,price=?,discount=? where id=?";
            System.out.println(sql);
            connection = DBUtil.getConnection(true);
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, goods.getName());
            preparedStatement.setString(2, goods.getIntroduce());
            preparedStatement.setInt(3, goods.getStock());
            preparedStatement.setString(4, goods.getUnit());
            preparedStatement.setInt(5, goods.getPriceInt());
            preparedStatement.setInt(6, goods.getDiscount());
            preparedStatement.setInt(7, goods.getId());

            effect = (preparedStatement.executeUpdate() == 1);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection, preparedStatement, null);
        }
        return effect;
    }

    private Goods getGoods(int goodsId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Goods goods = null;
        try {
            String sql = " select * from goods where id=? ";
            connection = DBUtil.getConnection(true);
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, goodsId);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                goods = this.extractGoods(resultSet);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection, preparedStatement, resultSet);
        }
        return goods;
    }

    //解析结果集函数
    private Goods extractGoods(ResultSet resultSet) throws SQLException {
        Goods goods = new Goods();
        goods.setId(resultSet.getInt("id"));
        goods.setName(resultSet.getString("name"));
        goods.setIntroduce(resultSet.getString("introduce"));
        goods.setStock(resultSet.getInt("stock"));
        goods.setUnit(resultSet.getString("unit"));
        goods.setPrice(resultSet.getInt("price"));
        goods.setDiscount(resultSet.getInt("discount"));
        return goods;
    }
}
