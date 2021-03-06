package com.zxin.basemodel.gen;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.zxin.basemodel.entity.MeiZiVideo;

import java.util.List;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * DAO for table "MEI_ZI_VIDEO".
 */
@Dao
public interface MeiZiVideoDao {

    @Query("SELECT * FROM tb_video ORDER BY create_time ASC")
    List<MeiZiVideo> getAll();

    @Query("SELECT * FROM tb_video where id = :videoId LIMIT 1")
    MeiZiVideo getVideoById(long videoId);

    @Query("SELECT * FROM tb_video where video_url = :videoUrl LIMIT 1")
    MeiZiVideo getVideoByUrl(String videoUrl);

    @Query("SELECT * FROM tb_video where  vid = :mVid LIMIT 1")
    MeiZiVideo getVideoByVid(long mVid);

    @Query("SELECT * FROM tb_video  ORDER BY create_time DESC LIMIT :current,:count")
    List<MeiZiVideo> getByLimit(int current, int count);

    @Query("SELECT COUNT(*) FROM tb_video")
    int getCount();

    @Insert
    long insert(MeiZiVideo... entities);

    @Delete
    void delete(MeiZiVideo entity);

    @Query("DELETE FROM tb_video")
    void deleteAll();

    @Query("DELETE FROM tb_video where id = :videoId")
    void deleteById(long videoId);

    @Update
    int update(MeiZiVideo entity);

    @Query("UPDATE tb_video SET last_time = :nowLong and play_num =:playNum where id = :mId")
    int update(long mId, long nowLong, int playNum);
}
