package com.projects.modular.api.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 区域信息
 * </p>
 *
 * @author demo
 * @since 2020-04-03
 */
@TableName("db_area")
public class Area implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

    /**
     * 区域名称
     */
    @TableField("area_name")
    private String areaName;

    /**
     * 大气情况
     */
    @TableField("daqi")
    private String daqi;

    /**
     * 降水情况
     */
    @TableField("jiangshui")
    private String jiangshui;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getDaqi() {
        return daqi;
    }

    public void setDaqi(String daqi) {
        this.daqi = daqi;
    }

    public String getJiangshui() {
        return jiangshui;
    }

    public void setJiangshui(String jiangshui) {
        this.jiangshui = jiangshui;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Area{" +
        "id=" + id +
        ", areaName=" + areaName +
        ", daqi=" + daqi +
        ", jiangshui=" + jiangshui +
        ", createTime=" + createTime +
        "}";
    }
}
