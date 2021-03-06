package com.zxin.basemodel.gen;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import com.zxin.basemodel.db.ZxinDB;
import com.zxin.basemodel.entity.City;
import java.util.List;

@Dao
public interface ZxinDBDao {
    @Query("SELECT * FROM tb_sys_zxindb ORDER BY create_time DESC")
    List<ZxinDB> getAll();

    @Query("SELECT * FROM tb_sys_zxindb where id = :mId LIMIT 1")
    ZxinDB getZxinDBById(int mId);

    @Query("SELECT * FROM tb_sys_zxindb ORDER BY create_time DESC LIMIT 1")
    ZxinDB getZxinDBLast();

    @Query("SELECT * FROM tb_sys_zxindb LIMIT :current,:count ")
    List<ZxinDB> getByLimit(int current, int count);

    @Query("SELECT COUNT(*) FROM tb_sys_zxindb")
    int getCount();

    @Insert
    Long insert(ZxinDB zxinDB);

    @Delete
    int delete(ZxinDB zxinDB);

    @Query("DELETE FROM tb_sys_zxindb")
    int deleteAll();

    @Query("DELETE FROM tb_sys_zxindb where id = :mId")
    int deleteById(int mId);

    @Update
    int update(City entity);


    @Query("UPDATE tb_sys_zxindb SET modify_time = :modifyTime where id = :mId")
    int updateById(int mId,String modifyTime);
}
