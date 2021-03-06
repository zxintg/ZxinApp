package com.zxin.basemodel.util;

import android.app.AlarmManager;
import android.app.Application;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.HandlerThread;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.view.View;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import com.zxin.basemodel.app.GreenDaoManager;
import com.zxin.basemodel.app.LogCallback;
import com.zxin.basemodel.factory.ResponseConverterFactory;
import com.zxin.basemodel.gen.DataBaseUtil;
import com.zxin.basemodel.interceptor.CacheInterceptor;
import com.zxin.basemodel.interceptor.CustomParamsInterceptor;
import com.zxin.basemodel.interceptor.HttpCacheInterceptor;
import com.zxin.basemodel.interceptor.HttpHeaderInterceptor;
import com.zxin.network.PoolThread;
import com.zxin.network.http.RetrofitHelper;
import com.zxin.root.util.UiUtils;
import com.zxin.root.util.logger.LogUtils;
import com.google.gson.Gson;
import java.math.BigDecimal;
import java.util.Date;

import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class GlobalUtil extends com.zxin.root.util.GlobalUtil{
    private static final LogUtils.Tag TAG = new LogUtils.Tag("GlobalUtil");

    private static final int DEF_DIV_SCALE = 110;
    private static DataBaseUtil dataBaseUtil = null;
    //保存一些状态数据的SharedPreferences
    private static SharedPreferences mSettings;
    //Application为整个应用保存全局的RefWatcher
    private static RefWatcher refWatcher;
    private static RetrofitHelper retrofitHelper = null;
    private static final int timeOut = 20;
    private static PoolThread executor;

    /****
     * 初始化数据
     * @param context
     */
    public static void init(Context context) {
        setContext(context);
        BuildUtils utils = BuildUtils.getInstance(getContext());
        setLogPath(utils.getLogPath());
        setRecordLog(utils.isRecodLog());
        setDayToDeleteLog(utils.getRecodLogDay());
        setAppCache(utils.getCachePath());
        setSdPath(utils.getAppSDPath());
        initSharedPreference();
    }

    public static void dialogTitleLineColor(Dialog dialog, int color) {
        int divierId = UiUtils.getInstance(dialog.getContext()).getResources().getIdentifier("android:id/titleDivider", null, null);
        View divider = dialog.findViewById(divierId);
        divider.setBackgroundColor(color);
    }

    /**
     * 提供精確的加法運算
     */
    public static double add(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2).doubleValue();
    }

    /**
     * 提供了精確的減法運算
     *
     */
    public static double sub(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.subtract(b2).doubleValue();
    }

    /**
     * 提供了精確的乘法運算
     */
    public static double mul(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.multiply(b2).doubleValue();
    }

    /**
     * 提供了(相對)精確的除法運算，當發生除不儘的情況時，精確到
     * 小數點以後１10位
     */
    public static double div(double v1, double v2) {
        return div(v1, v2, DEF_DIV_SCALE);
    }

    /**
     * 提供了(相對)精確的除法運算，當發生除不儘的情況時，由scale參數指定
     * 精度，以後的數字四捨五入
     */
    public static double div(double v1, double v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 提供了精確的小數位四捨五入處理
     */

    public static double round(double v, int scale) {
        if (scale<0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b = new BigDecimal(Double.toString(v));
        BigDecimal one = new BigDecimal("1");
        return b.divide(one, scale, BigDecimal.ROUND_HALF_DOWN).doubleValue();
    }

    /****
     * 判断是否在当前时间是之前
     * @param time
     * @return
     */
    public static boolean isValidTime(long time) {
        if (time <= 0) {
            return false;
        }
        long current = new Date().getTime();
        if (time <= current) {
            return true;
        }
        return false;
    }

    /****
     * 判断是否在当前时间是之后
     * @param time
     * @return
     */
    public static boolean isValidEndTime(long time) {
        if (time > 0) {
            return false;
        }
        long current = new Date().getTime();
        if (current <= time) {
            return true;
        }
        return false;
    }

    /****
     * 获取闹钟实例
     * @param context
     * @return
     *
     * RTC_WAKEUP :表示闹钟在睡眠状态下唤醒系统并执行提示功能，绝对时间。
     * RTC : 睡眠状态下不可用，绝对时间。
     * ELAPSED_REALTIME_WAKEUP : 睡眠状态下可用，相对时间。
     * ELAPSED_REALTIME : 睡眠状态下不可用，相对时间。
     *
     */
    public static AlarmManager getAlarmManager(Context context) {
        return (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
    }

    /****
     * json 解析
     * @return
     */
    public static <T> T jsonToBean(Class<T> clazz, String jsonStr) {
        if (jsonStr == null
                || jsonStr.trim().equals("")
                || jsonStr.trim().equals("null")) {
            LogUtils.d(TAG, "upDateCarLog jsonStr is null");
            return null;
        }
        LogUtils.d(TAG, "jsonToBean jsonStr is : " + jsonStr);
        return new Gson().fromJson(jsonStr, clazz);
    }

    public static void createDaos() {
            dataBaseUtil = new DataBaseUtil.Build(getContext()).build();
            dataBaseUtil.create();
    }

    public static void copyDatasToDb(){
        //初始化DB(拷贝数据到数据库)
        GreenDaoManager.getInstance().copyDbForFile(getContext());
    }

    public static DataBaseUtil getDataBaseUtil(){
        return dataBaseUtil;
    }

    /**
     * 获取DefaultSharedPreference实例
     *
     * @return
     */
    private static SharedPreferences initSharedPreference() {
        if (mSettings == null) {
            mSettings = PreferenceManager.getDefaultSharedPreferences(getContext());
        }
        return mSettings;
    }

    //内存泄漏检测
    public static void createLeakCanary(Application application) {
        if (LeakCanary.isInAnalyzerProcess(getContext())) {
            refWatcher = RefWatcher.DISABLED;
        }
        refWatcher = LeakCanary.install(application);
    }

    public static RefWatcher getRefWatcher() {
        return refWatcher;
    }

    /****
     * 初始化网络信息
     */
    public static synchronized void createRetrofitHelper() {
        retrofitHelper = new RetrofitHelper.Builder(getContext())
                .addTimeOut(timeOut)
                .addInterceptors(new CacheInterceptor(getContext())
                        ,new CustomParamsInterceptor(getContext())
                        ,new HttpCacheInterceptor(getContext())
                        ,new HttpHeaderInterceptor(getContext()))
                .addFactorys(ScalarsConverterFactory.create()
                        , ResponseConverterFactory.create()
                        , GsonConverterFactory.create())
                .build();
        retrofitHelper.create();
    }

    public static RetrofitHelper getRetrofitHelper(){
        return retrofitHelper;
    }


    private static Looper mLooper = null;
    private static final Object mLook = new Object();
    public static void createSubLooper() {
        if (mLooper == null){
            synchronized (mLook){
                if (mLooper == null){
                    HandlerThread thread = new HandlerThread("ZxinHandler");
                    thread.start();
                    mLooper = thread.getLooper();
                }
            }
        }
    }

    public static Looper getSubLooper(){
        return mLooper;
    }

    /**
     * 初始化线程池管理器
     */
    public static void initThreadPool() {
        // 创建一个独立的实例进行使用
        executor = PoolThread.ThreadBuilder
                .createFixed(5)
                .setPriority(Thread.MAX_PRIORITY)
                .setCallback(new LogCallback())
                .build();
    }

    /**
     * 获取线程池管理器对象，统一的管理器维护所有的线程池
     * @return executor对象
     */
    public static PoolThread getExecutor(){
        return executor;
    }

}
