package Servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.org.apache.xpath.internal.operations.Or;
import common.OrderStatus;
import entity.Account;
import entity.Order;
import entity.OrderItem;
import util.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 *
 * @author: KangWuBin
 * @Date: 2020/2/14
 * @Time: 9:05
 */
@WebServlet("/orderBrowse")
public class OrderBrowseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("OrderBrowseServlet");
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");

        //1.根据当前用户ID进行订单查找。//写成一个函数
        HttpSession session = req.getSession(true);
        Account account = (Account) session.getAttribute("user");
        //2.查询结果可能是多个订单。List<Order>
        List<Order> orderList = this.queryOrder(account.getId());
        //3.判断查询结果，如果是空的，说明没有订单
        if (orderList == null) {
            System.out.println("没有订单！");
        } else {
            //4.如果不是空的，那么将List转为JSON,发给前端--List里面存的是Order,所以前端的ajax接受的数据就是Order
            ObjectMapper objectMapper = new ObjectMapper();
            PrintWriter pw = resp.getWriter();
            objectMapper.writeValue(pw, orderList);
            Writer writer = resp.getWriter();
            writer.write(pw.toString());
        }
    }

    private List<Order> queryOrder(int accountId) {
        List<Order> list = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            //加@--传的就不是字符串
            String sql = this.getSql("@query_order_by_account");
            connection = DBUtil.getConnection(true);
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, accountId);
            resultSet = preparedStatement.executeQuery();
            Order order = null;
            while (resultSet.next()) {
                //1.订单需要解析
                if (order == null) {
                    order = new Order();
                    this.extractOrder(order, resultSet);
                    list.add(order);
                }
                String orderId = resultSet.getString("order_id");

                if (!orderId.equals(order.getId())) {
                    //不同的订单
                    order = new Order();
                    this.extractOrder(order, resultSet);
                    list.add(order);
                }
                /*Order order = new Order();
                this.extractOrder(order, resultSet);
//                this.extractOrder(resultSet);
                list.add(order);
                */
                //2.订单项需要解析
//                OrderItem orderItem = new OrderItem();
//                this.extractOrderItem(orderItem, resultSet);
                OrderItem orderItem = this.extractOrderItem(resultSet);
                order.orderItemList.add(orderItem);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection, preparedStatement, resultSet);
        }
        return list;
    }

    private void extractOrder(Order order, ResultSet resultSet) throws SQLException {
        // order_id---在sql语句取了别名
        order.setId(resultSet.getString("order_id"));
        order.setAccount_id(resultSet.getInt("account_id"));
        order.setAccount_name(resultSet.getString("account_name"));
        order.setCreate_time(resultSet.getString("create_time"));
        order.setFinish_time(resultSet.getString("finish_time"));
        order.setActual_amount(resultSet.getInt("actual_amount"));
        order.setTotal_money(resultSet.getInt("total_money"));
        order.setOrder_status(OrderStatus.valueOf(resultSet.getInt("order_status")));
    }

    private OrderItem extractOrderItem(ResultSet resultSet) throws SQLException {
        OrderItem orderItem = new OrderItem();
        orderItem.setOrderId(resultSet.getString("order_id"));
        orderItem.setGoodsId(resultSet.getInt("goods_id"));
        orderItem.setGoodsName(resultSet.getString("goods_name"));
        orderItem.setGoodsIntroduce(resultSet.getString("goods_introduce"));
        orderItem.setGoodsNum(resultSet.getInt("goods_num"));
        orderItem.setGoodsUnit(resultSet.getString("goods_unit"));
        orderItem.setGoodsPrice(resultSet.getInt("goods_price"));
        orderItem.setGoodsDiscount(resultSet.getInt("goods_discount"));
        return orderItem;
    }

    /*
     * 生成一条sql--IO流
     * */
    private String getSql(String sqlName) {
        System.out.println(sqlName);
        /*
         * this.getClass()--获得当前的Class对象；
         * getClassLoader()获取类加载器；
         * */
        InputStream in = this.getClass().getClassLoader().
                getResourceAsStream("script/" + sqlName.substring(1) + ".sql");

        if (in == null) {
            throw new RuntimeException("加载SQL文件出错");
        } else {
            //字节流转字符流的桥梁
            InputStreamReader isr = new InputStreamReader(in);

            BufferedReader reader = new BufferedReader(isr);

            try {
                StringBuilder sb = new StringBuilder();
                sb.append(reader.readLine());
                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(" ").append(line);
//                    sb.append(line);---不加空格，from关键字会被连接起来
                }
                System.out.println("sb:" + sb);
                return sb.toString();
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("转化sql语句发生异常！");
            }
        }
    }

}
