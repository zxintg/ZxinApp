package com.zxin.app;

import android.util.Log;

import com.zxin.network.callback.ThreadCallback;
import com.zxin.root.util.logger.LogUtils;


/**
 * <pre>
 *     @author zxin
 *     time  : 2019/02/02
 *     desc  : 回调数据
 *     revise:
 * </pre>
 */
public class LogCallback implements ThreadCallback {

    private final LogUtils.Tag TAG = new LogUtils.Tag("LogCallback");

    @Override
    public void onError(String name, Throwable t) {
        LogUtils.e(TAG, "LogCallback"+"------onError"+"-----"+name+"----"+Thread.currentThread()+"----"+t.getMessage());
    }

    @Override
    public void onCompleted(String name) {
        LogUtils.d(TAG, "LogCallback"+"------onCompleted"+"-----"+name+"----"+Thread.currentThread());
    }

    @Override
    public void onStart(String name) {
        LogUtils.d(TAG, "LogCallback"+"------onStart"+"-----"+name+"----"+Thread.currentThread());
    }
}
