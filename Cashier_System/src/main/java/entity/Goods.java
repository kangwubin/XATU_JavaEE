package entity;

import lombok.Data;

/**
 * Description:
 *
 * @author: KangWuBin
 * @Date: 2020/2/9
 * @Time: 16:51
 */
@Data
public class Goods {
    private Integer id;
    private String name;
    private String introduce;
    private Integer stock;
    private String unit;
    private Integer price;//存入数据库为整数
    private Integer discount;

    private Integer buyGoodsNum;//记录当前购买商品的数量

    //前端显示的值为元
    public double getPrice() {
        return price * 1.0 / 100;
    }
    //存入数据库的值是以分为单位
    public int getPriceInt() {
        return price;
    }
}
