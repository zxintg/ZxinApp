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
    List<City> getCityById(int areaId);

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
