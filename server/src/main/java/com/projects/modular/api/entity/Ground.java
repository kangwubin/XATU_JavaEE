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
 * 农田表
 * </p>
 *
 * @author demo
 * @since 2020-04-03
 */
@TableName("db_ground")
public class Ground implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

    /**
     * 农田编号
     */
    @TableField("code")
    private String code;

    @TableField("area_id")
    private Long areaId;

    /**
     * 长
     */
    @TableField("chang")
    private String chang;

    /**
     * 宽
     */
    @TableField("kuan")
    private String kuan;

    /**
     * 面积
     */
    @TableField("mianji")
    private String mianji;

    /**
     * 经纬度
     */
    @TableField("map_laction")
    private String mapLaction;

    /**
     * 海拔
     */
    @TableField("haiba")
    private String haiba;

    /**
     * 土质
     */
    @TableField("tuzhi")
    private String tuzhi;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    @TableField("user_id")
    private Long userId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }

    public String getChang() {
        return chang;
    }

    public void setChang(String chang) {
        this.chang = chang;
    }

    public String getKuan() {
        return kuan;
    }

    public void setKuan(String kuan) {
        this.kuan = kuan;
    }

    public String getMianji() {
        return mianji;
    }

    public void setMianji(String mianji) {
        this.mianji = mianji;
    }

    public String getMapLaction() {
        return mapLaction;
    }

    public void setMapLaction(String mapLaction) {
        this.mapLaction = mapLaction;
    }

    public String getHaiba() {
        return haiba;
    }

    public void setHaiba(String haiba) {
        this.haiba = haiba;
    }

    public String getTuzhi() {
        return tuzhi;
    }

    public void setTuzhi(String tuzhi) {
        this.tuzhi = tuzhi;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Ground{" +
        "id=" + id +
        ", code=" + code +
        ", areaId=" + areaId +
        ", chang=" + chang +
        ", kuan=" + kuan +
        ", mianji=" + mianji +
        ", mapLaction=" + mapLaction +
        ", haiba=" + haiba +
        ", tuzhi=" + tuzhi +
        ", createTime=" + createTime +
        ", userId=" + userId +
        "}";
    }
}
