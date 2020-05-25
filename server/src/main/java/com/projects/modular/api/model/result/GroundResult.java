package com.projects.modular.api.model.result;

import lombok.Data;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 农田表
 * </p>
 *
 * @author demo
 * @since 2020-04-03
 */
@Data
public class GroundResult implements Serializable {

    private static final long serialVersionUID = 1L;


    private Long id;

    /**
     * 农田编号
     */
    private String code;

    private Long areaId;

    /**
     * 长
     */
    private String chang;

    /**
     * 宽
     */
    private String kuan;

    /**
     * 面积
     */
    private String mianji;

    /**
     * 经纬度
     */
    private String mapLaction;

    /**
     * 海拔
     */
    private String haiba;

    /**
     * 土质
     */
    private String tuzhi;

    /**
     * 创建时间
     */
    private Date createTime;

    private Long userId;

}
