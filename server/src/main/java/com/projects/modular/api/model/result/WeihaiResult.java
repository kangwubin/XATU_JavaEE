package com.projects.modular.api.model.result;

import com.baomidou.mybatisplus.annotation.TableField;

import lombok.Data;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 危害
 * </p>
 *
 * @author demo
 * @since 2020-04-03
 */
@Data
public class WeihaiResult implements Serializable {

    private static final long serialVersionUID = 1L;


    private Long id;

    /**
     * 害虫名称
     */
    private String name;

    private String time;

    /**
     * 危害部位
     */
    private String buwei;

    /**
     * 症状
     */
    private String zhengzhuang;

    /**
     * 损失
     */
    private String sunshi;

    /**
     * 农药名称
     */
    private String yaoName;

    /**
     * 农药用量
     */
    private String yaoFlat;

    /**
     * 农药方法
     */
    private String yaoMethod;

    /**
     * 农药时间
     */
    private String yaoTime;

    /**
     * 农药效果
     */
    private String yaoXiaoguo;

    /**
     * 创建人id
     */
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
