package com.projects.modular.api.model.params;

import lombok.Data;
import cn.stylefeng.roses.kernel.model.validator.BaseValidatingParam;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 地图点位
 * </p>
 *
 * @author demo
 * @since 2020-04-14
 */
@Data
public class MapLogParam implements Serializable, BaseValidatingParam {

    private static final long serialVersionUID = 1L;


    private Integer id;

    /**
     * 编号
     */
    private String indexId;

    /**
     * 经度
     */
    private Double latitude;

    /**
     * 纬度
     */
    private Double longitude;

    /**
     * 速度
     */
    private Float speed;

    private Float direction;

    private Float accuracy;

    private Integer satellitesNum;

    /**
     * 地址
     */
    private String address;

    /**
     * 城市
     */
    private String city;

    private Date time;

    /**
     * 设备编号
     */
    private Long deviceId;

    @Override
    public String checkParam() {
        return null;
    }

}
