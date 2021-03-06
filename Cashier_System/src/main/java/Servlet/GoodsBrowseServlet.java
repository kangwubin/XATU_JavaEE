package Servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Goods;
import util.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Description:浏览商品
 *
 * @author: KangWuBin
 * @Date: 2020/2/12
 * @Time: 9:03
 */
@WebServlet("/GoodsBrowse")
public class GoodsBrowseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("GoodsBrowseServlet");

        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Goods> list = new ArrayList<>();
        try {
            String sql = " select id,name,introduce,stock,unit,price,discount from goods ";
            connection = DBUtil.getConnection(true);
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            //解析结果集里的内容
            while (resultSet.next()) {
                Goods goods = this.extractGoods(resultSet);
                if (goods != null) {
                    list.add(goods);
                }
            }
            System.out.println("list:" + list);

            //把list转换为json,发送给前端
            //可以方便的将模型对象转换为JSON
            ObjectMapper mapper = new ObjectMapper();
            //将响应包推送给浏览器
            PrintWriter pw = resp.getWriter();
            //将list转换为字符串，并将json字符串填充到PrintWriter
            mapper.writeValue(pw, list);

            Writer writer = resp.getWriter();
            writer.write(pw.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection, preparedStatement, resultSet);
        }
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
