package common;

import lombok.Getter;
import lombok.ToString;

/**
 * Description:
 *
 * @author: KangWuBin
 * @Date: 2020/2/13
 * @Time: 9:54
 */
@Getter
@ToString
public enum OrderStatus {
    PLAYING(1, "待支付"), OK(2, "支付完成");
    private int flg;
    private String desc;

    //构造是默认私有的
    private OrderStatus(int flg, String desc) {
        this.flg = flg;
        this.desc = desc;
    }

    //查找相应的状态
    //返回对象，将对象转为数组
    public static OrderStatus valueOf(int flg) {
        for (OrderStatus orderStatus : OrderStatus.values()) {
            if (orderStatus.flg == flg) {
                return orderStatus;
            }
        }
        throw new RuntimeException("orderStatus is not found!");
    }
}
