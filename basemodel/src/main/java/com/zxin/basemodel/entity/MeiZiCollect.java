package com.zxin.basemodel.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.PrimaryKey;
import android.text.TextUtils;

/**
 * Created by Administrator on 2018/5/11.
 */
//指示数据表实体类
@Entity(tableName = "tb_collect")//定义外键
public class MeiZiCollect {
    @PrimaryKey(autoGenerate = true) //定义主键
    public Long id;
    //缩略图
    @ColumnInfo(name = "cover")//定义数据表中的字段名
    public String cover;
    @ColumnInfo(name = "url")//定义数据表中的字段名
    public String url;
    @ColumnInfo(name = "name")//定义数据表中的字段名
    public String name;
    @ColumnInfo(name = "create_time")//定义数据表中的字段名
    public Long createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        MeiZiCollect that = (MeiZiCollect) obj;
        return id == that.id &&
                TextUtils.equals(cover, that.cover) &&
                TextUtils.equals(url, that.url) &&
                TextUtils.equals(name, that.name) &&
                createTime == that.createTime;
    }

    @Override
    public String toString() {
        return "MeiZiCollect{" +
                "id=" + id +
                ", cover='" + cover + '\'' +
                ", url='" + url + '\'' +
                ", name='" + name + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
