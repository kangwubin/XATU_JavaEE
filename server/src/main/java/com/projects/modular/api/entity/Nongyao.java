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
 * 农药信息
 * </p>
 *
 * @author demo
 * @since 2020-04-03
 */
@TableName("db_nongyao")
public class Nongyao implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

    @TableField("name")
    private String name;

    /**
     * 施肥时间
     */
    @TableField("time")
    private String time;

    /**
     * 肥料重量
     */
    @TableField("flat")
    private String flat;

    /**
     * 0是底肥，1是追肥
     */
    @TableField("type")
    private Integer type;

    /**
     * 方法
     */
    @TableField("content")
    private String content;

    @TableField("user_id")
    private Long userId;
    @TableField("gid")
    private Long gid;

    public Long getGid() {
        return gid;
    }

    public void setGid(Long gid) {
        this.gid = gid;
    }
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

    public String getFlat() {
        return flat;
    }

    public void setFlat(String flat) {
        this.flat = flat;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
        return "Nongyao{" + "id=" + id + ", name=" + name + ", time=" + time + ", flat=" + flat + ", type=" + type + ", content=" + content + ", userId=" + userId + ", createTime=" + createTime + "}";
    }
}
