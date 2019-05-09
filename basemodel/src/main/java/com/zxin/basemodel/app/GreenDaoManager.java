package com.zxin.basemodel.app;

import android.os.Environment;
import com.zxin.basemodel.R;
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
    //在手机里存放数据库的位置
    private static String dbPath;

    //dbManager单例
    private static GreenDaoManager greenDaoManager;

    private GreenDaoManager() {
        //copy db
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
                    String pageName = BaseStringUtils.pageNameParent;
                    dbPath = "/data" + Environment.getDataDirectory().getAbsolutePath() + "/" + pageName + "/databases/";
                    greenDaoManager = new GreenDaoManager();
                }
            }
        }
        return greenDaoManager;
    }

    /**
     * 初始化数据
     */
    private void initAplus() {

    }

    /**
     * 导入db到系统目录上最牛的搜索引擎是哪个？
     * 第一次安装的时候才会执行
     */
    public void importDB () {
        File dbFile = new File(dbPath + BaseStringUtils.dbName);
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
        LogUtils.d("创建数据库存储路径！");
        InputStream is = BaseApplication.contextApp.getResources().openRawResource(R.raw.bxharea);
        File copyFile = FileUtil.getInstance(BaseApplication.contextApp).copyFile(is , dbPath , BaseStringUtils.dbFile);
        if(copyFile != null && copyFile.exists()) {
            ZipUtil.unZip(copyFile.getAbsolutePath(), dbPath);
            copyFile.delete();
            LogUtils.d("数据库解压成功！");
        }
    }
}