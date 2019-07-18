package com.zxin.basemodel.db;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.PrimaryKey;
import android.text.TextUtils;

/**
 * Created by liukui on 2016/12/12 0012.
 */
@Entity(tableName = "tb_sys_zxindb")//定义外键
public class ZxinDB {
    @PrimaryKey(autoGenerate = true) //定义主键,自增
    @ColumnInfo(name = "id")
    public int id;

    @ColumnInfo(name = "version_code")//版本编号 (v 第一版本号：第二版本号：第三版本号)
    public String version_code;

    @ColumnInfo(name = "create_time")//创建时间
    public String create_time;

    @ColumnInfo(name = "modify_time")//修改时间
    public String modify_time;

    @ColumnInfo(name = "is_compel")//是否强行更新 （1：强行更新(表结构发生变化) 2：视情况更新(表结构没有变化，只有数据变化) 3：无需更新（没有任何变化））
    public int is_compel;

    @ColumnInfo(name = "explain")//说明
    public String explain;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVersion_code() {
        return version_code;
    }

    public void setVersion_code(String version_code) {
        this.version_code = version_code;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getModify_time() {
        return modify_time;
    }

    public void setModify_time(String modify_time) {
        this.modify_time = modify_time;
    }

    public int getIs_compel() {
        return is_compel;
    }

    public void setIs_compel(int is_compel) {
        this.is_compel = is_compel;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ZxinDB that = (ZxinDB) obj;
        return id == that.id &&
                TextUtils.equals(version_code, that.version_code) &&
                TextUtils.equals(create_time, that.create_time) &&
                TextUtils.equals(modify_time, that.modify_time) &&
                is_compel == that.is_compel &&
                TextUtils.equals(explain, that.explain);
    }

    @Override
    public String toString() {
        return "ZxinDB{" +
                "id=" + id +
                ", version_code='" + version_code + '\'' +
                ", create_time='" + create_time + '\'' +
                ", modify_time='" + modify_time + '\'' +
                ", is_compel='" + is_compel + '\'' +
                ", explain='" + explain + '\'' +
                '}';
    }


}
