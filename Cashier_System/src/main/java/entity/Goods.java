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
    private Integer price;
    private Integer discount;
}
