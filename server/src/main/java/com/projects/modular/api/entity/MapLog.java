package com.projects.modular.api.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 地图点位
 * </p>
 *
 * @author demo
 * @since 2020-04-14
 */
@TableName("db_map_log")
public class MapLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 编号
     */
    @TableField("indexId")
    private String indexId;

    /**
     * 经度
     */
    @TableField("latitude")
    private Double latitude;

    /**
     * 纬度
     */
    @TableField("longitude")
    private Double longitude;

    /**
     * 速度
     */
    @TableField("speed")
    private Float speed;

    @TableField("direction")
    private Float direction;

    @TableField("accuracy")
    private Float accuracy;

    @TableField("satellitesNum")
    private Integer satellitesNum;

    /**
     * 地址
     */
    @TableField("address")
    private String address;

    /**
     * 城市
     */
    @TableField("city")
    private String city;

    @TableField("time")
    private Date time;

    /**
     * 设备编号
     */
    @TableField("device_id")
    private Long deviceId;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIndexId() {
        return indexId;
    }

    public void setIndexId(String indexId) {
        this.indexId = indexId;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Float getSpeed() {
        return speed;
    }

    public void setSpeed(Float speed) {
        this.speed = speed;
    }

    public Float getDirection() {
        return direction;
    }

    public void setDirection(Float direction) {
        this.direction = direction;
    }

    public Float getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(Float accuracy) {
        this.accuracy = accuracy;
    }

    public Integer getSatellitesNum() {
        return satellitesNum;
    }

    public void setSatellitesNum(Integer satellitesNum) {
        this.satellitesNum = satellitesNum;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    @Override
    public String toString() {
        return "MapLog{" +
        "id=" + id +
        ", indexId=" + indexId +
        ", latitude=" + latitude +
        ", longitude=" + longitude +
        ", speed=" + speed +
        ", direction=" + direction +
        ", accuracy=" + accuracy +
        ", satellitesNum=" + satellitesNum +
        ", address=" + address +
        ", city=" + city +
        ", time=" + time +
        ", deviceId=" + deviceId +
        "}";
    }
}
