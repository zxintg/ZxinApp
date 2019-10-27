package com.zxin.app;

import android.content.Context;
import android.os.Process;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;
import com.bugtags.library.Bugtags;
import com.bugtags.library.BugtagsOptions;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.tencent.smtt.sdk.QbSdk;
import com.zxin.basemodel.util.GlobalUtil;
import com.zxin.router.Configuration;
import com.zxin.router.Router;
import com.zxin.root.BuildConfig;
import com.zxin.root.exception.CrashHandler;
import com.zxin.network.PoolThread;

/**
 * Created by hy on 2017/9/22.
 */
public class MyApplication extends MultiDexApplication {
    private static Context mContext;
    private static volatile MyApplication application = null;

    //私有化，防止外部调取再次初始化
    private MyApplication(){

    }

    public static synchronized MyApplication getInstance() {
        if (application==null)
            application = new MyApplication();
        return application;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        mContext = this;
        GlobalUtil.init(mContext);
        //初始化线程池管理器
        GlobalUtil.initThreadPool();
        GlobalUtil.createDaos();
        GlobalUtil.createLeakCanary(application);
        GlobalUtil.createRetrofitHelper();
        GlobalUtil.createSubLooper();
        new Thread(new Runnable() {
            @Override
            public void run() {
                //设置线程优先级，不与主线程抢资源
                Process.setThreadPriority(Process.THREAD_PRIORITY_BACKGROUND);
                //全局错误信息
                Thread.setDefaultUncaughtExceptionHandler(CrashHandler.getInstance().init(application));
                //腾讯X5
                QbSdk.initX5Environment(mContext, null);
                //在这里初始化
                Fresco.initialize(mContext);
                GlobalUtil.copyDatasToDb();
            }
        }).start();
        BugtagsOptions options = new BugtagsOptions.Builder().
                trackingLocation(true).//是否获取位置
                trackingCrashLog(true).//是否收集crash
                trackingConsoleLog(true).//是否收集console log
                trackingUserSteps(true).//是否收集用户操作步骤
                enableCapturePlus(true).
                build();

        Bugtags.addUserStep("custom step");
        Bugtags.start(BuildConfig.DEBUG ? "e8c283891f44c4c44f3e3f12a32bc3be" : "e8c283891f44c4c44f3e3f12a32bc3be", this, Bugtags.BTGInvocationEventBubble, options);
        // 初始化路由（https://github.com/chenenyu/Router）
        Router.initialize(new Configuration.Builder()
                // 调试模式，开启后会打印log
                .setDebuggable(BuildConfig.DEBUG)
                // 模块名(即project.name)，每个使用Router的module都要在这里注册
                .registerModules("sources", "app","mine")
                .build());
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onLowMemory() {
        //ToastUtil.showDefaultGravityToast("低内存警告");
//		//如果内存不够用，就清空Activities，释放掉Activity的引用
        //AppManager.getAppManager().finishOtherButCurrentActivity();
        super.onLowMemory();

    }
}
