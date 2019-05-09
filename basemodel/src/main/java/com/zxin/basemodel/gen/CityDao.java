package com.zxin.basemodel.gen;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.zxin.basemodel.entity.City;

import java.util.List;

/**
 * DAO for table "t_sse_param_area".
 */
@Dao
public interface CityDao {

    @Query("SELECT * FROM t_sse_param_area ORDER BY create_time ASC")
    List<City> getAll();

    @Query("SELECT * FROM t_sse_param_area where area_id = :areaId LIMIT 1")
    City getCityById(String areaId);

    @Query("SELECT * FROM t_sse_param_area where area_name = :areaName LIMIT 1")
    City getCityByName(String areaName);

    @Query("SELECT * FROM t_sse_param_area where parent_id = :parentId ORDER BY area_id ASC")
    List<City> getCityByParentId(String parentId);

    @Query("SELECT * FROM t_sse_param_area where parent_id in  (SELECT area_id FROM t_sse_param_area WHERE parent_id = 0) ORDER BY area_id ASC")
    List<City> getCityByParent();

    //根据上一级的PD和当前区域名字查询得到当前区域名字
    @Query("SELECT * FROM t_sse_param_area where parent_id = :parentId and area_name = :areaName LIMIT 1")
    City getCityByParentIdArea(String parentId, String areaName);

    @Query("SELECT * FROM t_sse_param_area where area_name = :areaName and area_level = :areaLevel LIMIT 1")
    City getCityByAreaLevel(String areaName,String areaLevel);


    @Query("SELECT * FROM t_sse_param_area LIMIT :current,:count ")
    List<City> getByLimit(int current, int count);

    @Query("SELECT COUNT(*) FROM t_sse_param_area")
    int getCount();

    @Insert
    void insert(City... entities);

    @Delete
    void delete(City entity);

    @Query("DELETE FROM t_sse_param_area")
    void deleteAll();

    @Query("DELETE FROM t_sse_param_area where area_id = :areaId")
    void deleteById(int areaId);

    @Update
    void update(City entity);

}
