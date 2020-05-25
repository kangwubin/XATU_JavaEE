package com.projects.modular.api.model.result;

import com.baomidou.mybatisplus.annotation.TableField;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 农药信息
 * </p>
 *
 * @author demo
 * @since 2020-04-03
 */
@Data
public class NongyaoResult implements Serializable {

    private static final long serialVersionUID = 1L;


    private Long id;

    private String name;

    /**
     * 施肥时间
     */
    private String time;

    /**
     * 肥料重量
     */
    private String flat;

    /**
     * 0是底肥，1是追肥
     */
    private Integer type;

    /**
     * 方法
     */
    private String content;

    private Long userId;

    /**
     * 创建时间
     */
    private Date createTime;
    private Long gid;

    public Long getGid() {
        return gid;
    }

    public void setGid(Long gid) {
        this.gid = gid;
    }
}
