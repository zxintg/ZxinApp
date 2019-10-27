package com.zxin.basemodel.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Process;
import android.preference.PreferenceManager;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import com.zxin.basemodel.gen.DataBaseUtil;
import com.zxin.basemodel.interceptor.CacheInterceptor;
import com.zxin.basemodel.interceptor.CustomParamsInterceptor;
import com.zxin.basemodel.interceptor.HttpCacheInterceptor;
import com.zxin.basemodel.interceptor.HttpHeaderInterceptor;
import com.zxin.basemodel.factory.ResponseConverterFactory;
import com.zxin.basemodel.util.GlobalUtil;
import com.zxin.network.http.RetrofitHelper;
import com.zxin.root.util.AppManager;

import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by hy on 2017/9/22.
 */

public abstract class BaseApplication extends MultiDexApplication { //解决64K限制问题
    private static BaseApplication mApplication;
    public static Context contextApp;
    //保存一些状态数据的SharedPreferences
    protected SharedPreferences mSettings;
    //Application为整个应用保存全局的RefWatcher
    private RefWatcher refWatcher;

    private DataBaseUtil dataBaseUtil = null;

    private RetrofitHelper retrofitHelper = null;

    private int timeOut = 20;

    public static BaseApplication getInstance() {
        return mApplication;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }


    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
        contextApp = getApplicationContext();
        GlobalUtil.init(contextApp);
        //内存泄漏检测
        refWatcher = setupLeakCanary();
        // 初始化mSettings
        getDefaultSharedPreference();

        initRetrofitHelper();
        createRetrofitHelper();

        new Thread(new Runnable() {
            @Override
            public void run() {
                //设置线程优先级，不与主线程抢资源
                Process.setThreadPriority(Process.THREAD_PRIORITY_BACKGROUND);
                initDaos();
                //初始化DB(拷贝数据到数据库)
                GreenDaoManager.getInstance().init(contextApp);
            }
        }).start();
    }

    private void initDaos() {
        dataBaseUtil = new DataBaseUtil.Build(contextApp).build();
        dataBaseUtil.create();
    }

    /****
     * 初始化网络信息
     */
    private synchronized void initRetrofitHelper() {
        retrofitHelper = new RetrofitHelper.Builder(contextApp)
                .addTimeOut(timeOut)
                .addInterceptors(new CacheInterceptor(contextApp)
                        ,new CustomParamsInterceptor(contextApp)
                        ,new HttpCacheInterceptor(contextApp)
                        ,new HttpHeaderInterceptor(contextApp))
                .addFactorys(ScalarsConverterFactory.create()
                        ,ResponseConverterFactory.create()
                        , GsonConverterFactory.create())
                .build();
    }

    private void createRetrofitHelper(){
        retrofitHelper.create();
        //retrofitHelper.addZxinAPIs(new String[]{});
    }

    /**
     * 获取DefaultSharedPreference实例
     *
     * @return
     */
    public SharedPreferences getDefaultSharedPreference() {
        if (mSettings == null) {
            mSettings = PreferenceManager.getDefaultSharedPreferences(this);
        }
        return mSettings;
    }

    @Override
    public void onLowMemory() {
        //ToastUtil.showDefaultGravityToast("低内存警告");
//		//如果内存不够用，就清空Activities，释放掉Activity的引用
        //AppManager.getAppManager().finishOtherButCurrentActivity();
        super.onLowMemory();

    }

    //内存泄漏检测
    protected RefWatcher setupLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return RefWatcher.DISABLED;
        }
        return LeakCanary.install(this);
    }

    public static RefWatcher getRefWatcher(Context context) {
        BaseApplication application = (BaseApplication) context.getApplicationContext();
        return application.refWatcher;
    }

    public Context getContext() {
        return contextApp;
    }

    public DataBaseUtil getDataBaseUtil(){
        if (dataBaseUtil==null){
            initDaos();
        }
        return dataBaseUtil;
    }

    public RetrofitHelper getRetrofitHelper(){
        if(retrofitHelper==null){
            initRetrofitHelper();
        }
        return retrofitHelper;
    }

    private Looper mLooper = null;
    private static final Object mLook = new Object();
    public Looper getSubLooper() {
        if (mLooper == null){
            synchronized (mLook){
                if (mLooper == null){
                    HandlerThread thread = new HandlerThread("ZxinHandler");
                    thread.start();
                    mLooper = thread.getLooper();
                }
            }
        }
        return mLooper;
    }
}
