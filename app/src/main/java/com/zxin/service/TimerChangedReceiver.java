package com.zxin.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.zxin.root.util.logger.LogUtils;

/*****
 * 监听系统日期更改
 */
public class TimerChangedReceiver extends BroadcastReceiver {
    private static final LogUtils.Tag TAG = new LogUtils.Tag("TimerChangedReceiver");

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent == null || intent.getAction() == null) {
            return;
        }
        LogUtils.d(TAG, "onReceive action:" + intent.getAction());
        if (TextUtils.equals(intent.getAction(), Intent.ACTION_TIME_CHANGED)) {//包含日期和时间更改的
            //日期更改了
            LogUtils.d(TAG, "onReceive action send TimerChangedEvent" );
        }
    }

}
