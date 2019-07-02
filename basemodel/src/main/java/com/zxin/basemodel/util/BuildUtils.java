package com.zxin.basemodel.util;

import android.content.Context;

import com.zxin.basemodel.BuildConfig;

/*****
 * 获取gradle 配置信息
 */
public class BuildUtils {

    private static volatile BuildUtils buildUtils = null;
    private Context mContext;

    public enum APIURL {
        Show,
        DBmeinv,
        XiuMei99,
        Codekk,
        YuShangJi,
        ApkBus,
        MeiZu,
        Picasso,
        RuBaoo,
        Beauty,
        BaiDu,
        SoGou
    }

    private BuildUtils(Context mContext) {
        this.mContext = mContext;
    }

    public static BuildUtils getInstance(Context mContext) {
        if (buildUtils == null) {
            synchronized (BuildUtils.class) {
                if (buildUtils == null) {
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
    public String getDbVersion() {
        return BuildConfig.DB_VER;
    }

    /*****
     * 获取数据库名称
     * @return
     */
    public String getDbName() {
        return BuildConfig.DB_Name;
    }

    /*****
     * 网路API
     * @return
     */
    public String getURLAPI(APIURL api) {
        String url = "";
        switch (api) {
            case Show:
                url = BuildConfig.apiURL_showapi;
                break;

            case DBmeinv:
                url = BuildConfig.apiURL_dbmeinv;
                break;

            case XiuMei99:
                url = BuildConfig.apiURL_xiumei99;
                break;

            case Codekk:
                url = BuildConfig.apiURL_codekk;
                break;

            case YuShangJi:
                url = BuildConfig.apiURL_ynshangji;
                break;

            case ApkBus:
                url = BuildConfig.apiURL_apkbus;
                break;

            case MeiZu:
                url = BuildConfig.apiURL_meizu;
                break;

            case Picasso:
                url = BuildConfig.apiURL_picasso;
                break;

            case RuBaoo:
                url = BuildConfig.apiURL_rubaoo;
                break;

            case Beauty:
                url = BuildConfig.apiURL_beautyreport;
                break;

            case BaiDu:
                url = BuildConfig.apiURL_baidu;
                break;

            case SoGou:
                url = BuildConfig.apiURL_sogou;
                break;
        }
        return url;
    }

}
