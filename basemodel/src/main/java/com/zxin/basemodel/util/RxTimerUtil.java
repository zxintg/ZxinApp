package com.zxin.basemodel.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.RectF;
import com.zxin.root.util.logger.LogUtils;
import java.util.concurrent.TimeUnit;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/******
 * RX 工具类
 */
public class RxTimerUtil {
    private final static LogUtils.Tag TAG = new LogUtils.Tag("RxTimerUtil");
    private static Disposable mPollingDisposable;
    private static Disposable mDelayDisposable;
    private static final long TIME_OUT = 7000;

    private static volatile RxTimerUtil util = null;

    private RxTimerUtil() {

    }

    public static RxTimerUtil getInstance() {
        if (util == null) {
            synchronized (RxTimerUtil.class) {
                if (util == null) {
                    util = new RxTimerUtil();
                }
            }
        }
        return util;
    }


    /*****
     * 轮询 执行
     * @param timmer 轮询时间
     */
    public void schedulePollingTimer(int timmer) {
        Observable.interval(timmer, TimeUnit.SECONDS, Schedulers.io()).
                subscribeOn(Schedulers.io())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable disposable) {
                        LogUtils.i(TAG, "polling task : schedule");
                        mPollingDisposable = disposable;
                    }

                    @Override
                    public void onNext(Long aLong) {
                        LogUtils.i(TAG, "polling task : onNext");

                    }

                    @Override
                    public void onError(Throwable throwable) {
                        LogUtils.printStackTrace(TAG, throwable);
                        cancelPollingTimer();
                    }

                    @Override
                    public void onComplete() {
                        LogUtils.i(TAG, "polling task: complete");
                        cancelPollingTimer();
                    }
                });
    }

    /*****
     * 单次执行 延迟
     * @param delay 延迟时间
     */
    public void scheduleDelayTimer(int delay) {
        Observable.timer(delay, TimeUnit.MILLISECONDS, Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable disposable) {
                        LogUtils.i(TAG, "delay task: schedule");
                        mDelayDisposable = disposable;
                    }

                    @Override
                    public void onNext(Long aLong) {
                        LogUtils.i(TAG, "delay task: onNext");
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        LogUtils.printStackTrace(TAG, throwable);
                        cancelDelayTimer();
                    }

                    @Override
                    public void onComplete() {
                        LogUtils.i(TAG, "delay task : complete");
                        cancelDelayTimer();
                    }
                });
    }

    public void cancelPollingTimer() {
        if (mPollingDisposable != null && !mPollingDisposable.isDisposed()) {
            mPollingDisposable.dispose();
            LogUtils.i(TAG, "polling : cancelPollingTimer");
        }
    }

    public void cancelDelayTimer() {
        if (mDelayDisposable != null && !mDelayDisposable.isDisposed()) {
            mDelayDisposable.dispose();
            LogUtils.i(TAG, "delay : cancelDelayTimer");
        }
    }
}