package com.projects.modular.api.model.params;

import com.baomidou.mybatisplus.annotation.TableField;

import lombok.Data;
import cn.stylefeng.roses.kernel.model.validator.BaseValidatingParam;
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
public class FixParam implements Serializable, BaseValidatingParam {

    private static final long serialVersionUID = 1L;


    /**
     * 主键状态
     */
    private Long id;

    /**
     * 报修物品
     */
    private String name;
    private String pic;
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

    @Override
    public String checkParam() {
        return null;
    }

}
