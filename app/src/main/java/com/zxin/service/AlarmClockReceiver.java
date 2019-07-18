package com.zxin.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.zxin.root.util.logger.LogUtils;

/*****
 * 闹钟广播接收服务
 *
 * create by kui.liu 2019/07/11 09:30
 */
public class AlarmClockReceiver extends BroadcastReceiver {
    private static final LogUtils.Tag TAG = new LogUtils.Tag("AlarmClockReceiver");

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent == null || intent.getAction() == null) {
            return;
        }
        LogUtils.d(TAG, "onReceive action:" + intent.getAction());
    }

}
