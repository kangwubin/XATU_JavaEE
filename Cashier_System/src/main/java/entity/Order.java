package entity;

import common.OrderStatus;
import lombok.Data;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 *
 * @author: KangWuBin
 * @Date: 2020/2/13
 * @Time: 09:51
 */
@Data
public class Order {
    private String id;
    private Integer account_id;
    private String account_name;
    private String create_time;
    private String finish_time;
    private Integer actual_amount;
    private Integer total_money;
    private OrderStatus order_status;

    //将订单项的内容存储到当前订单内
    public List<OrderItem> orderItemList = new ArrayList<>();

    public double getTotal_money() {
        return total_money * 1.0 / 100;
    }

    public int getTotalMoneyInt() {
        return total_money;
    }

    //浏览订单会用
    public double getActual_amount() {
        return actual_amount * 1.0 / 100;
    }

    public int getActualAmountInt() {
        return actual_amount;
    }

    //优惠金额
    public double getDiscount() {
        return (this.getTotalMoneyInt() - this.getActualAmountInt()) * 1.00 / 100;
    }
}
