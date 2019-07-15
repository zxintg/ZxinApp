package com.zxin.basemodel.util;

import android.content.Context;

import com.zxin.basemodel.BuildConfig;
import com.zxin.basemodel.annot.ApiUrlMode;

import static com.zxin.basemodel.annot.ApiUrlMode.APIURL_MODE_Show;

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
    public String getURLAPI(@ApiUrlMode int api) {
        String url = "";
        switch (api) {
            case ApiUrlMode.APIURL_MODE_Show:
                url = BuildConfig.apiURL_showapi;
                break;

            case ApiUrlMode.APIURL_MODE_DBmeinv:
                url = BuildConfig.apiURL_dbmeinv;
                break;

            case ApiUrlMode.APIURL_MODE_XiuMei99:
                url = BuildConfig.apiURL_xiumei99;
                break;

            case ApiUrlMode.APIURL_MODE_Codekk:
                url = BuildConfig.apiURL_codekk;
                break;

            case ApiUrlMode.APIURL_MODE_YuShangJi:
                url = BuildConfig.apiURL_ynshangji;
                break;

            case ApiUrlMode.APIURL_MODE_ApkBus:
                url = BuildConfig.apiURL_apkbus;
                break;

            case ApiUrlMode.APIURL_MODE_MeiZu:
                url = BuildConfig.apiURL_meizu;
                break;

            case ApiUrlMode.APIURL_MODE_Picasso:
                url = BuildConfig.apiURL_picasso;
                break;

            case ApiUrlMode.APIURL_MODE_RuBaoo:
                url = BuildConfig.apiURL_rubaoo;
                break;

            case ApiUrlMode.APIURL_MODE_Beauty:
                url = BuildConfig.apiURL_beautyreport;
                break;

            case ApiUrlMode.APIURL_MODE_BaiDu:
                url = BuildConfig.apiURL_baidu;
                break;

            case ApiUrlMode.APIURL_MODE_SoGou:
                url = BuildConfig.apiURL_sogou;
                break;
        }
        return url;
    }

}
