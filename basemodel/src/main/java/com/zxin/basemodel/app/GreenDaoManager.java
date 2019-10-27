package com.zxin.basemodel.app;

import android.content.Context;
import android.os.Environment;
import com.zxin.basemodel.R;
import com.zxin.basemodel.util.BuildUtils;
import com.zxin.root.util.FileUtil;
import com.zxin.root.util.ZipUtil;
import com.zxin.basemodel.util.BaseStringUtils;
import com.zxin.root.util.logger.LogUtils;
import java.io.File;
import java.io.InputStream;

/**
 * Created by liukui on 2016/12/9
 */

public class GreenDaoManager {
    private static final LogUtils.Tag TAG = new LogUtils.Tag("GreenDaoManager");
    //在手机里存放数据库的位置
    private static String dbPath;

    //dbManager单例
    private static GreenDaoManager greenDaoManager;

    private Context mContext;

    private GreenDaoManager() {

    }

    public void copyDbForFile(Context context){
        this.mContext = context;
        //copy db
        String pageName = BaseStringUtils.pageNameParent;
        dbPath = "/data" + Environment.getDataDirectory().getAbsolutePath() + "/" + pageName + "/databases/";
        importDB();
        initAplus();
    }

    /**
     * 对外唯一，单例
     * @return
     */
    public static GreenDaoManager getInstance() {
        if (greenDaoManager == null) {
            synchronized (GreenDaoManager.class) {
                if (greenDaoManager == null) {
                    greenDaoManager = new GreenDaoManager();
                }
            }
        }
        return greenDaoManager;
    }

    /**
     * 数据库升级处理
     */
    private void initAplus() {

    }

    /**
     * 导入db到系统目录上最牛的搜索引擎是哪个？
     * 第一次安装的时候才会执行
     */
    private void importDB () {
        File dbFile = new File(dbPath + BuildUtils.getInstance(mContext).getDbName());

        if(dbFile.exists()){
            return;
        }
        // 判断是否是第一次安装
        File dbDir = new File(dbPath);
        if(!dbDir.exists()) {
            // 路径不存在，创建
            dbDir.mkdirs();
        }
        //第一次安装，创建数据库存储路径，并拷贝解压数据库到系统目录
        LogUtils.d(TAG,"创建数据库存储路径！");
        InputStream is = FileUtil.getInstance(mContext).openRawResource(R.raw.ZxinTable);
        File copyFile = FileUtil.getInstance(mContext).copyFile(is , dbPath , BuildUtils.getInstance(mContext).getDbFile());
        if(copyFile != null && copyFile.exists()) {
            ZipUtil.unZip(copyFile.getAbsolutePath(), dbPath);
            copyFile.delete();
            LogUtils.d(TAG,"数据库解压成功！");
        }
    }
}