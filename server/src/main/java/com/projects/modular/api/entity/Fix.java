package com.projects.modular.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;

/**
 * <p>
 * 报修
 * </p>
 *
 * @author demo
 * @since 2020-03-20
 */
public class Fix implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键状态
     */
    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

    /**
     * 报修物品
     */
    @TableField("name")
    private String name;
    /**
     * 报修物品
     */
    @TableField("pic")
    private String pic;
    /**
     * 报修备注
     */
    @TableField("description")
    private String description;

    /**
     * 报修用户
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 报修时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 维修时间
     */
    @TableField("repair_time")
    private Date repairTime;

    /**
     * 维修状态
     */
    @TableField("status")
    private Integer status;

    /**
     * 报修地址
     */
    @TableField("address")
    private String address;

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Date getRepairTime() {
        return repairTime;
    }

    public void setRepairTime(Date repairTime) {
        this.repairTime = repairTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Fix{" +
                "id=" + id +
                ", name=" + name +
                ", description=" + description +
                ", userId=" + userId +
                ", createTime=" + createTime +
                ", repairTime=" + repairTime +
                ", status=" + status +
                ", address=" + address +
                "}";
    }
}
