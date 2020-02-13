package entity;

import lombok.Data;

/**
 * Description:
 *
 * @author: KangWuBin
 * @Date: 2020/2/13
 * @Time: 09:51
 */
@Data
public class OrderItem {
    private Integer id;
    private String orderId;
    private Integer goodsId;
    private String goodsName;
    private String goodsIntroduce;
    private Integer goodsNum;
    private String goodsUnit;
    private Integer goodsPrice;
    private Integer goodsDiscount;

    //单位：元--前端显示的值为元
    public double getPrice() {
        return goodsPrice * 1.0 / 100;
    }

    //存入数据库的值是以分为单位
    public int getGoodsPriceInt() {
        return goodsPrice;
    }

}
