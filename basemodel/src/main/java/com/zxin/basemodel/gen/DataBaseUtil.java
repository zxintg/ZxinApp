package com.zxin.basemodel.gen;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;
import android.support.annotation.NonNull;
import com.zxin.basemodel.entity.City;
import com.zxin.basemodel.entity.HttpUrl;
import com.zxin.basemodel.entity.MeiZiCollect;
import com.zxin.basemodel.entity.MeiZiVideo;
import com.zxin.basemodel.util.BuildUtils;
import com.zxin.root.util.logger.LogUtils;

@Database(entities = {City.class, HttpUrl.class, MeiZiCollect.class, MeiZiVideo.class}, version = 1, exportSchema = false)
public abstract class DataBaseUtil extends RoomDatabase {

    public enum Mode {
        CityMode,
        HttpUrlMode,
        CollectMode,
        VideoMode
    }

    private static final LogUtils.Tag TAG = new LogUtils.Tag("DataBaseUtil");

    private static volatile DataBaseUtil instance = null;

    private CityDao cityDao;
    private HttpUrl httpUrlDao;
    private MeiZiCollect collectDao;
    private MeiZiVideo videoDao;

    public static DataBaseUtil getInstance(final Context mContext) {
        if (instance == null) {
            synchronized (DataBaseUtil.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(mContext, DataBaseUtil.class, BuildUtils.getInstance(mContext).getDbName()).addCallback(new RoomDatabase.Callback() {
                        @Override
                        public void onCreate(@NonNull SupportSQLiteDatabase db) {
                            super.onCreate(db);
                            LogUtils.d(TAG, "onCreate:" + db.getPath());
                        }

                        @Override
                        public void onOpen(@NonNull SupportSQLiteDatabase db) {
                            super.onOpen(db);
                            LogUtils.d(TAG, "onOpen:" + db.getPath());
                        }
                    }).addMigrations(ONE_TO_TWO_MIG)
                            .allowMainThreadQueries().build();
                }
            }
        }
        return instance;
    }

    /*****
     * 初始化数据库
     */
    public void initDaos() {
        cityDao = initCityDao();
        httpUrlDao = initHttpUrlDao();
        collectDao = initMeiZiCollectDao();
        videoDao = initMeiZiVideoDao();
    }

    /*****
     * 获取具体DAO实例
     * @param mode
     * @param <T>
     * @return
     */
    public <T> T getDao(Mode mode) {

        switch (mode) {

            case CityMode:
                return (T) cityDao;

            case HttpUrlMode:
                return (T) httpUrlDao;

            case CollectMode:
                return (T) collectDao;

            case VideoMode:
                return (T) videoDao;

        }
        return null;
    }

    //初始化city Dao
    public abstract CityDao initCityDao();

    //初始化HttpUrl Dao
    public abstract HttpUrl initHttpUrlDao();

    //初始化MeiZiCollect Dao
    public abstract MeiZiCollect initMeiZiCollectDao();

    //初始化MeiZiVideo Dao
    public abstract MeiZiVideo initMeiZiVideoDao();

    //数据库升级用的
    private static Migration ONE_TO_TWO_MIG = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase supportSQLiteDatabase) {
            //Do nothing.
        }
    };
}
