package com.zxin.basemodel.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.PrimaryKey;
import android.text.TextUtils;

/**
 * Created by Administrator on 2018/5/11.
 * <p>
 * //指示数据表实体类
 *
 * @Entity(tableName = "tb_student",//定义表名
 * indices = @Index(value = {"class_id", "number"}),//定义索引
 * foreignKeys = {@ForeignKey(entity = ClassEntity.class,
 * parentColumns = "id",
 * childColumns = "class_id")})//定义外键
 */
//指示数据表实体类
@Entity(tableName = "tb_video")//定义外键
public class MeiZiVideo {
    @PrimaryKey(autoGenerate = true) //定义主键
    public Long id;
    //缩略图
    @ColumnInfo(name = "thumb_url")//定义数据表中的字段名
    public String thumbUrl;
    //播放地址
    @ColumnInfo(name = "video_url")
    public String videoUrl;
    //昵称
    @ColumnInfo(name = "nick_name")
    public String nickName;
    //用户名称
    @ColumnInfo(name = "user_id")
    public String userId;
    //创建时间
    @ColumnInfo(name = "create_time")
    public Long createTime;
    //最近播放时间
    @ColumnInfo(name = "last_time")
    public Long lastTime;
    //话题
    @ColumnInfo(name = "topic")
    public String topic;
    //Vid
    @ColumnInfo(name = "vid")
    public int vid;
    //播放次数
    @ColumnInfo(name = "play_num")
    public int playNum;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getThumbUrl() {
        return thumbUrl;
    }

    public void setThumbUrl(String thumbUrl) {
        this.thumbUrl = thumbUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getLastTime() {
        return lastTime;
    }

    public void setLastTime(Long lastTime) {
        this.lastTime = lastTime;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public int getVid() {
        return vid;
    }

    public void setVid(int vid) {
        this.vid = vid;
    }

    public int getPlayNum() {
        return playNum;
    }

    public void setPlayNum(int playNum) {
        this.playNum = playNum;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        MeiZiVideo that = (MeiZiVideo) obj;
        return id == that.id &&
                TextUtils.equals(thumbUrl, that.thumbUrl) &&
                TextUtils.equals(videoUrl, that.videoUrl) &&
                TextUtils.equals(nickName, that.nickName) &&
                TextUtils.equals(userId, that.userId) &&
                createTime == that.createTime &&
                lastTime == that.lastTime &&
                TextUtils.equals(topic, that.topic) &&
                vid == that.vid &&
                playNum == that.playNum;
    }

    @Override
    public String toString() {
        return "MeiZiVideo{" +
                "id=" + id +
                ", thumbUrl='" + thumbUrl + '\'' +
                ", videoUrl='" + videoUrl + '\'' +
                ", nickName='" + nickName + '\'' +
                ", userId='" + userId + '\'' +
                ", createTime='" + createTime + '\'' +
                ", lastTime='" + lastTime + '\'' +
                ", topic='" + topic + '\'' +
                ", vid='" + vid + '\'' +
                ", playNum=" + playNum +
                '}';
    }
}
