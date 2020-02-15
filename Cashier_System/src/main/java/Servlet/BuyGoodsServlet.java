package Servlet;

import common.OrderStatus;
import entity.Goods;
import entity.Order;
import entity.OrderItem;
import util.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Description:购买商品
 *
 * @author: KangWuBin
 * @Date: 2020/2/13
 * @Time: 10:50
 */
@WebServlet("/buyGoodsServlet")
/* doPost():一般用于前后台数据的交互；
 * doGet():一般用于查询操作；*/
public class BuyGoodsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("BuyGoodsServlet");
        doPost(req, resp);
    }

    //数据库插东西，用doPost
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("BuyGoodsServlet--doPost");

        HttpSession session = req.getSession();
        Order order = (Order) session.getAttribute("order");
        List<Goods> goodsList = (List<Goods>) session.getAttribute("goodsList");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        order.setFinish_time(LocalDateTime.now().format(formatter));

        order.setOrder_status(OrderStatus.OK);

        //提交订单
        boolean effect = this.commitOrder(order);
        //当你插入数据库为真，-》购买-》订单
        //遍历所有的货物，把这些货物的库存进行修改
        if (effect) {
            for (Goods goods : goodsList) {
                boolean isUpdate = updateAfterBuy(goods, goods.getBuyGoodsNum());
                if (isUpdate) {
                    System.out.println("更新库存成功！");
                } else {
                    System.out.println("更新库存失败！");
                }
            }
            resp.sendRedirect("buyGoodsSuccess.html");
        }
    }

    //提交订单
    private boolean commitOrder(Order order) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            String insertOrder = "insert into `order`(id,account_id,create_time,finish_time,actual_amount,total_money,order_status,account_name) values (?,?,now(),now(),?,?,?,?)";
            String insertOrderItem = " insert into order_item(order_id,goods_id,goods_name,goods_introduce,goods_num,goods_unit,goods_price,goods_discount) values (?,?,?,?,?,?,?,?) ";
            //涉及事务的回滚，用手动提交
            connection = DBUtil.getConnection(false);
            preparedStatement = connection.prepareStatement(insertOrder);
            preparedStatement.setString(1, order.getId());
            preparedStatement.setInt(2, order.getAccount_id());
            preparedStatement.setInt(3, order.getActualAmountInt());
            preparedStatement.setInt(4, order.getTotalMoneyInt());
            preparedStatement.setInt(5, order.getOrder_statusDesc().getFlg());
            preparedStatement.setString(6, order.getAccount_name());

            if (preparedStatement.executeUpdate() == 0) {
                throw new RuntimeException("插入订单失败！");
            }

            //插入订单成功，开始插入订单项
            preparedStatement = connection.prepareStatement(insertOrderItem);
            //批量进行插入
            for (OrderItem orderItem : order.orderItemList) {
                preparedStatement.setString(1, order.getId());
                preparedStatement.setInt(2, orderItem.getGoodsId());
                preparedStatement.setString(3, orderItem.getGoodsName());
                preparedStatement.setString(4, orderItem.getGoodsIntroduce());
                preparedStatement.setInt(5, orderItem.getGoodsNum());
                preparedStatement.setString(6, orderItem.getGoodsUnit());
                preparedStatement.setInt(7, orderItem.getGoodsPriceInt());
                preparedStatement.setInt(8, orderItem.getGoodsDiscount());

                //将每一项preparedStatement缓存
                preparedStatement.addBatch();
            }

            //进行批量插入操作
            //1:成功   0：失败
            int[] effects = preparedStatement.executeBatch();
            for (int i : effects) {
                if (i == 0) {
                    throw new RuntimeException("插入订单项失败！");
                }
            }
            //手动进行提交
            connection.commit();

        } catch (Exception e) {
            e.printStackTrace();
            //抛出RuntimeException异常，进行事务回滚
            //如果抛出SQLException，就会断开连接，不会进行回滚
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
            return false;
        } finally {
            DBUtil.close(connection, preparedStatement, null);
        }
        return true;
    }

    //更新库存--更新goods这张表
    private boolean updateAfterBuy(Goods goods, int goodsBuyNum) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        boolean effect = false;

        try {
            String sql = "update goods set stock=? where id=? ";
            connection = DBUtil.getConnection(true);
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, goods.getStock() - goodsBuyNum);
            preparedStatement.setInt(2, goods.getId());
            if (preparedStatement.executeUpdate() == 1) {
                effect = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return effect;
    }

}
