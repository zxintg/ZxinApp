package com.zxin.basemodel.dao;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zxin.basemodel.entity.MeiZiCollect;
import com.zxin.basemodel.entity.MeiZiVideo;
import com.zxin.basemodel.gen.DataBaseUtil;
import com.zxin.basemodel.gen.MeiZiCollectDao;
import com.zxin.basemodel.gen.MeiZiVideoDao;
import com.zxin.basemodel.util.GlobalUtil;
import com.zxin.root.util.ToastUtil;

import java.util.Calendar;
import java.util.List;

/**
 * Created by Administrator on 2018/5/11.
 */

public class MeiZiVideoDaoUtil {

    private static volatile MeiZiVideoDaoUtil meiZiVideoDaoUtil = null;
    private static MeiZiVideoDao meiZiVideoDao;
    private static MeiZiCollectDao meiZiCollectDao;
    private Context mContext;

    private MeiZiVideoDaoUtil(Context mContext) {
        this.mContext = mContext;
        DataBaseUtil dataBaseUtil = GlobalUtil.getDataBaseUtil();
        meiZiVideoDao = dataBaseUtil.getDao(DataBaseUtil.Mode.VideoMode);
        meiZiCollectDao = dataBaseUtil.getDao(DataBaseUtil.Mode.CollectMode);
    }

    public static MeiZiVideoDaoUtil getInstance(Context mContext) {
        if (meiZiVideoDaoUtil == null) {
            synchronized (MeiZiVideoDaoUtil.class) {
                if (meiZiVideoDaoUtil == null) {
                    meiZiVideoDaoUtil = new MeiZiVideoDaoUtil(mContext);
                }
            }
        }
        return meiZiVideoDaoUtil;
    }

    /**
     * 增加数据
     */
    public boolean addMeiZiVideo(String thumbUrl, String videoUrl, String nickname, String userId, String topic, int vid) {
        MeiZiVideo meiZiVideo = new MeiZiVideo();
        meiZiVideo.setThumbUrl(thumbUrl);
        meiZiVideo.setVideoUrl(videoUrl);
        meiZiVideo.setNickName(nickname);
        meiZiVideo.setUserId(userId);
        meiZiVideo.setTopic(topic);
        meiZiVideo.setVid(vid);

        Calendar now = Calendar.getInstance();
        long nowLong = now.getTime().getTime();
        meiZiVideo.setCreateTime(nowLong);
        meiZiVideo.setLastTime(nowLong);
        meiZiVideo.setPlayNum(0);
        return meiZiVideoDao.insert(meiZiVideo) > 0;
    }

    public boolean addMeiZiVideoJsonArray(String json) {
        Gson gson = new Gson();
        MeiZiVideo[] collectList = gson.fromJson(json, new TypeToken<MeiZiVideo[]>() {
        }.getType());
        if (collectList == null || collectList.length == 0) {
            ToastUtil.getInstance(mContext).showShort("视频数据有问题，请检查！");
            return false;
        }
        return meiZiVideoDao.insert(collectList) > 0;
    }

    public boolean addMeiZiCollectJsonArray(String json) {
        Gson gson = new Gson();
        MeiZiCollect[] collectList = gson.fromJson(json, new TypeToken<MeiZiCollect[]>() {
        }.getType());
        if (collectList == null || collectList.length == 0) {
            ToastUtil.getInstance(mContext).showShort("收藏数据有问题，请检查！");
            return false;
        }
        return meiZiCollectDao.insert(collectList) > 0;
    }

    public MeiZiCollect addMeiZiCollect(long id, String cover, String url, String userName) {
        MeiZiCollect meiZiVideo = new MeiZiCollect();
        meiZiVideo.setId(Long.parseLong(String.valueOf(id)));
        meiZiVideo.setCover(cover);
        meiZiVideo.setUrl(url);
        meiZiVideo.setName(userName);
        meiZiVideo.setCreateTime(Calendar.getInstance().getTime().getTime());
        meiZiCollectDao.insert(meiZiVideo);
        return meiZiCollectDao.getCollectById(id);
    }

    /**
     * 根据主键删除
     */
    public void deleteMeiZiVideo(long id) {
        meiZiVideoDao.deleteById(id);
    }

    public void deleteMeiZiCollect(long id) {
        meiZiCollectDao.deleteById(id);
    }

    /**
     * 更改数据
     */
    public boolean updateMeiZiVideo(long id, int playNum) {
        long nowLong = Calendar.getInstance().getTime().getTime();
        return meiZiVideoDao.update(id,nowLong,playNum) > 0;
    }

    /**
     * 查找数据
     */
    public List<MeiZiVideo> getMeiZiVideoList(int pageNum, int pageSize) {
        return meiZiVideoDao.getByLimit((pageNum - 1) * pageSize, pageSize);
    }

    public String getMeiZiVideoList() {
        return new Gson().toJson(meiZiVideoDao.getAll());
    }

    public List<MeiZiCollect> getMeiZiCollectList(int pageNum, int pageSize) {
        return meiZiCollectDao.getByLimit((pageNum - 1) * pageSize, pageSize);
    }

    public String getMeiZiCollectList() {
        return new Gson().toJson(meiZiCollectDao.getAll());
    }

    /****
     * 获取具体实体
     * @param vid
     * @return
     */
    public MeiZiVideo getMeiZiVideo(long vid) {
        return meiZiVideoDao.getVideoByVid(vid);
    }

    public boolean hasMeiZiVideo(long vid) {
        return getMeiZiVideo(vid) != null;
    }

    public MeiZiCollect getMeiZiCollect(String userName) {
        return meiZiCollectDao.getCollectByName(userName);
    }

    /*****
     * 更新封面图片
     * @param collect
     */
    public void updateMeiZiCollectImage(MeiZiCollect collect) {
        meiZiCollectDao.update(collect);
    }
}
