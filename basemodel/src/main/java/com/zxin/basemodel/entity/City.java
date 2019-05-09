package com.zxin.basemodel.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.PrimaryKey;
import android.text.TextUtils;

/**
 * Created by liukui on 2016/12/12 0012.
 */
@Entity(tableName = "t_sse_param_area")//定义外键
public class City {
    @PrimaryKey(autoGenerate = true) //定义主键
    @ColumnInfo(name = "area_id")
    public int areaId;

    @ColumnInfo(name = "parent_id")//定义数据表中的字段名
    public int parentId;

    @ColumnInfo(name = "area_name")//定义数据表中的字段名
    public String areaName;

    @ColumnInfo(name = "short_name")//定义数据表中的字段名
    public String shortName;

    @ColumnInfo(name = "english_name")//定义数据表中的字段名
    public String englishName;

    @ColumnInfo(name = "area_level")//定义数据表中的字段名
    public String areaLevel;

    @ColumnInfo(name = "create_user")//定义数据表中的字段名
    public String createUser;

    @ColumnInfo(name = "create_time")//定义数据表中的字段名
    public String createTime;

    @ColumnInfo(name = "remark")//定义数据表中的字段名
    public String remark;


    public int getAreaId() {
        return areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getAreaLevel() {
        return areaLevel;
    }

    public void setAreaLevel(String areaLevel) {
        this.areaLevel = areaLevel;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        City that = (City) obj;
        return areaId == that.areaId &&
                parentId == that.parentId &&
                TextUtils.equals(areaName, that.areaName) &&
                TextUtils.equals(shortName, that.shortName) &&
                TextUtils.equals(englishName, that.englishName) &&
                TextUtils.equals(areaLevel, that.areaLevel) &&
                TextUtils.equals(createUser, that.createUser) &&
                TextUtils.equals(createTime, that.createTime) &&
                TextUtils.equals(remark, that.remark);
    }

    @Override
    public String toString() {
        return "City{" +
                "areaId=" + areaId +
                ", parentId='" + parentId + '\'' +
                ", areaName='" + areaName + '\'' +
                ", shortName='" + shortName + '\'' +
                ", englishName='" + englishName + '\'' +
                ", areaLevel='" + areaLevel + '\'' +
                ", createUser='" + createUser + '\'' +
                ", createTime='" + createTime + '\'' +
                ", remark=" + remark +
                '}';
    }


}
