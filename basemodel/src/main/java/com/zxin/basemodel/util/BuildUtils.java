package com.zxin.basemodel.util;

import android.content.Context;

import com.zxin.basemodel.BuildConfig;

/*****
 * 获取gradle 配置信息
 */
public class BuildUtils {

    private static volatile BuildUtils buildUtils = null;
    private Context mContext;

    private BuildUtils(Context mContext){
        this.mContext = mContext;
    }

    public static BuildUtils getInstance(Context mContext){
        if (buildUtils ==null){
            synchronized (BuildUtils.class){
                if (buildUtils ==null){
                    buildUtils = new BuildUtils(mContext);
                }
            }
        }
        return buildUtils;
    }

    /*****
     * 获取数据库版本
     * @return
     */
    public String getDbVersion(){
        return BuildConfig.DB_VER;
    }

    /*****
     * 获取数据库名称
     * @return
     */
    public String getDbName(){
        return BuildConfig.DB_Name;
    }


}
