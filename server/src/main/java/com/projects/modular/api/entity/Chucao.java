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
 * 除草信息
 * </p>
 *
 * @author demo
 * @since 2020-04-03
 */
@TableName("db_chucao")
public class Chucao implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

    /**
     * 农田id
     */
    @TableField("gid")
    private Long gid;

    /**
     * 除草时间
     */
    @TableField("time")
    private String time;

    /**
     * 除草方法
     */
    @TableField("descriton")
    private String descriton;

    @TableField("user_id")
    private Long userId;

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

    public Long getGid() {
        return gid;
    }

    public void setGid(Long gid) {
        this.gid = gid;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDescriton() {
        return descriton;
    }

    public void setDescriton(String descriton) {
        this.descriton = descriton;
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
        return "Chucao{" +
        "id=" + id +
        ", gid=" + gid +
        ", time=" + time +
        ", descriton=" + descriton +
        ", userId=" + userId +
        ", createTime=" + createTime +
        "}";
    }
}
