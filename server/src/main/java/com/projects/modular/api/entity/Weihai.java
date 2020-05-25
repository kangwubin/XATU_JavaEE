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
 * 危害
 * </p>
 *
 * @author demo
 * @since 2020-04-03
 */
@TableName("db_weihai")
public class Weihai implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

    /**
     * 害虫名称
     */
    @TableField("name")
    private String name;

    @TableField("time")
    private String time;

    /**
     * 危害部位
     */
    @TableField("buwei")
    private String buwei;

    /**
     * 症状
     */
    @TableField("zhengzhuang")
    private String zhengzhuang;

    /**
     * 损失
     */
    @TableField("sunshi")
    private String sunshi;

    /**
     * 农药名称
     */
    @TableField("yao_name")
    private String yaoName;

    /**
     * 农药用量
     */
    @TableField("yao_flat")
    private String yaoFlat;

    /**
     * 农药方法
     */
    @TableField("yao_method")
    private String yaoMethod;

    /**
     * 农药时间
     */
    @TableField("yao_time")
    private String yaoTime;

    /**
     * 农药效果
     */
    @TableField("yao_xiaoguo")
    private String yaoXiaoguo;

    /**
     * 创建人id
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    @TableField("gid")
    private Long gid;

    public Long getGid() {
        return gid;
    }

    public void setGid(Long gid) {
        this.gid = gid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getBuwei() {
        return buwei;
    }

    public void setBuwei(String buwei) {
        this.buwei = buwei;
    }

    public String getZhengzhuang() {
        return zhengzhuang;
    }

    public void setZhengzhuang(String zhengzhuang) {
        this.zhengzhuang = zhengzhuang;
    }

    public String getSunshi() {
        return sunshi;
    }

    public void setSunshi(String sunshi) {
        this.sunshi = sunshi;
    }

    public String getYaoName() {
        return yaoName;
    }

    public void setYaoName(String yaoName) {
        this.yaoName = yaoName;
    }

    public String getYaoFlat() {
        return yaoFlat;
    }

    public void setYaoFlat(String yaoFlat) {
        this.yaoFlat = yaoFlat;
    }

    public String getYaoMethod() {
        return yaoMethod;
    }

    public void setYaoMethod(String yaoMethod) {
        this.yaoMethod = yaoMethod;
    }

    public String getYaoTime() {
        return yaoTime;
    }

    public void setYaoTime(String yaoTime) {
        this.yaoTime = yaoTime;
    }

    public String getYaoXiaoguo() {
        return yaoXiaoguo;
    }

    public void setYaoXiaoguo(String yaoXiaoguo) {
        this.yaoXiaoguo = yaoXiaoguo;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Weihai{" + "id=" + id + ", name=" + name + ", time=" + time + ", buwei=" + buwei + ", zhengzhuang=" + zhengzhuang + ", sunshi=" + sunshi + ", yaoName=" + yaoName + ", yaoFlat=" + yaoFlat + ", yaoMethod=" + yaoMethod + ", yaoTime=" + yaoTime + ", yaoXiaoguo=" + yaoXiaoguo + ", userId=" + userId + ", createTime=" + createTime + "}";
    }
}
