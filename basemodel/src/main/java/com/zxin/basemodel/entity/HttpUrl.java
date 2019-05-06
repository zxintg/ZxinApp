package com.zxin.basemodel.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.PrimaryKey;
import android.text.TextUtils;

/**
 * Created by Administrator on 2018/5/11.
 */

@Entity(tableName = "tb_httpurl")//定义外键
public class HttpUrl {
    @PrimaryKey(autoGenerate = true) //定义主键
    private Long id;
    @ColumnInfo(name = "create_timer")//定义数据表中的字段名
    private String createTimer;
    @ColumnInfo(name = "name")//定义数据表中的字段名
    private String name;
    @ColumnInfo(name = "lable")//定义数据表中的字段名
    private String lable;
    @ColumnInfo(name = "url")//定义数据表中的字段名
    private String url;
    @ColumnInfo(name = "last_time")//定义数据表中的字段名
    private String lastTime;
    @ColumnInfo(name = "times")//定义数据表中的字段名
    private long times;
    @ColumnInfo(name = "order_num")//定义数据表中的字段名
    private int orderNum;
    @ColumnInfo(name = "is_effective")//定义数据表中的字段名
    private int isEffective;
    @ColumnInfo(name = "modify_time")//定义数据表中的字段名
    private String modifyTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCreateTimer() {
        return createTimer;
    }

    public void setCreateTimer(String createTimer) {
        this.createTimer = createTimer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLable() {
        return lable;
    }

    public void setLable(String lable) {
        this.lable = lable;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLastTime() {
        return lastTime;
    }

    public void setLastTime(String lastTime) {
        this.lastTime = lastTime;
    }

    public long getTimes() {
        return times;
    }

    public void setTimes(long times) {
        this.times = times;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public int getIsEffective() {
        return isEffective;
    }

    public void setIsEffective(int isEffective) {
        this.isEffective = isEffective;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        HttpUrl that = (HttpUrl) obj;
        return id == that.id &&
                TextUtils.equals(createTimer, that.createTimer) &&
                TextUtils.equals(name, that.name) &&
                TextUtils.equals(lable, that.lable) &&
                TextUtils.equals(url, that.url) &&
                TextUtils.equals(lastTime, that.lastTime) &&
                times == that.times &&
                orderNum == that.orderNum &&
                isEffective == that.isEffective &&
                TextUtils.equals(modifyTime, that.modifyTime);
    }

    @Override
    public String toString() {
        return "HttpUrl{" +
                "id=" + id +
                ", createTimer='" + createTimer + '\'' +
                ", name='" + name + '\'' +
                ", lable='" + lable + '\'' +
                ", url='" + url + '\'' +
                ", lastTime='" + lastTime + '\'' +
                ", times='" + times + '\'' +
                ", orderNum='" + orderNum + '\'' +
                ", isEffective='" + isEffective + '\'' +
                ", modifyTime=" + modifyTime +
                '}';
    }
}
