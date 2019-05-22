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

    private ZxinDBDao zxinDBDao;
    private CityDao cityDao;
    private HttpUrlDao httpUrlDao;
    private MeiZiCollectDao collectDao;
    private MeiZiVideoDao videoDao;
    private Context mContext;

    private void init (Context mContext) {
        this.mContext = mContext;
    }

    /*****
     * 初始化数据库
     */
    public void create() {
        zxinDBDao = initZxinDBDao();
        cityDao = initCityDao();
        httpUrlDao = initHttpUrlDao();
        collectDao = initMeiZiCollectDao();
        videoDao = initMeiZiVideoDao();
    }

    public ZxinDBDao getZxinDBDao(){
        return zxinDBDao;
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

    //初始化ZxinDB Dao
    public abstract ZxinDBDao initZxinDBDao();

    //初始化city Dao
    public abstract CityDao initCityDao();

    //初始化HttpUrl Dao
    public abstract HttpUrlDao initHttpUrlDao();

    //初始化MeiZiCollect Dao
    public abstract MeiZiCollectDao initMeiZiCollectDao();

    //初始化MeiZiVideo Dao
    public abstract MeiZiVideoDao initMeiZiVideoDao();

    //数据库升级用的
    private static Migration ONE_TO_TWO_MIG = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase supportSQLiteDatabase) {
            //Do nothing.
        }
    };

    public static class Build{
        private Context mContext;

        public Build(Context mContext){
            this.mContext = mContext;
        }

        public DataBaseUtil build(){
            DataBaseUtil util = Room.databaseBuilder(mContext, DataBaseUtil.class, BuildUtils.getInstance(mContext).getDbName()).addCallback(new RoomDatabase.Callback() {
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
                    //如果真要把操作放在ui线程中，就必须加个allowMainThreadQueries()方法，这样数据库的操作就可以在ui线程中使用了！
                    .allowMainThreadQueries()
                    .build();
            util.init(mContext);
            return util;
        }

    }
}
