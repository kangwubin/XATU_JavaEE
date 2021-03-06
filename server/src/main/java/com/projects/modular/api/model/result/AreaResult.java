package com.projects.modular.api.model.result;

import lombok.Data;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 区域信息
 * </p>
 *
 * @author demo
 * @since 2020-04-03
 */
@Data
public class AreaResult implements Serializable {

    private static final long serialVersionUID = 1L;


    private Long id;

    /**
     * 区域名称
     */
    private String areaName;

    /**
     * 大气情况
     */
    private String daqi;

    /**
     * 降水情况
     */
    private String jiangshui;

    /**
     * 创建时间
     */
    private Date createTime;

}
