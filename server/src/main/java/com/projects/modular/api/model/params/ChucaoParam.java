package com.projects.modular.api.model.params;

import lombok.Data;
import cn.stylefeng.roses.kernel.model.validator.BaseValidatingParam;

import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 除草信息
 * </p>
 *
 * @author demo
 * @since 2020-04-03
 */
@Data
public class ChucaoParam implements Serializable, BaseValidatingParam {

    private static final long serialVersionUID = 1L;


    private Long id;

    /**
     * 农田id
     */
    private Long gid;

    /**
     * 除草时间
     */
    private String time;

    /**
     * 除草方法
     */
    private String descriton;

    private Long userId;

    /**
     * 创建时间
     */
    private Date createTime;

    @Override
    public String checkParam() {
        return null;
    }

}
