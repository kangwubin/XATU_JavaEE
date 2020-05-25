package com.projects.modular.api.model.result;

import lombok.Data;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 报修
 * </p>
 *
 * @author demo
 * @since 2020-03-20
 */
@Data
public class FixResult implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 主键状态
     */
    private Long id;

    /**
     * 报修物品
     */
    private String name;

    /**
     * 报修备注
     */
    private String description;

    /**
     * 报修用户
     */
    private Long userId;

    /**
     * 报修时间
     */
    private Date createTime;

    /**
     * 维修时间
     */
    private Date repairTime;

    /**
     * 维修状态
     */
    private Integer status;

    /**
     * 报修地址
     */
    private String address;

}
