package com.zxin.basemodel.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.text.TextUtils;

import com.zxin.root.util.logger.LogUtils;

/**
 * Created by zxin
 * Goal: To receive the network status
 */
public class NetworkStateReceiver extends BroadcastReceiver {

    private static final LogUtils.Tag TAG = new LogUtils.Tag("NetworkStateReceiver");
    private static final String BOOLEAN_EXTRA_KEY = "status";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent != null) {
            if (TextUtils.equals(intent.getAction(), ConnectivityManager.NET_STATUS_ACTION)) {
                L.i(TAG, "Network State Changed");
                boolean status = intent.getBooleanExtra(BOOLEAN_EXTRA_KEY, false);
                L.i(TAG, "network working status:" + status);
                if (status) {
                    //网络连接
                }else {
                    //网络断开
                }
            }
        } else {
            L.e(TAG, "intent is null");
        }
    }
}
