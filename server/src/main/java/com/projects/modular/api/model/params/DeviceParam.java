package com.projects.modular.api.model.params;

import lombok.Data;
import cn.stylefeng.roses.kernel.model.validator.BaseValidatingParam;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 设备
 * </p>
 *
 * @author demo
 * @since 2020-04-14
 */
@Data
public class DeviceParam implements Serializable, BaseValidatingParam {

    private static final long serialVersionUID = 1L;


    private Long id;

    private String name;

    private String description;

    private Date createTime;

    /**
     * 状态
     */
    private Integer status;

    @Override
    public String checkParam() {
        return null;
    }

}
