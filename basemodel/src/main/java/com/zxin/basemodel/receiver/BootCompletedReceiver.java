package com.zxin.basemodel.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.zxin.root.util.logger.LogUtils;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by guzc on 2017/2/16.
 * Modified by zxin on 05/02/2019.
 */
public class BootCompletedReceiver extends BroadcastReceiver {
    private static final LogUtils.Tag TAG = new LogUtils.Tag("BootReceiver");

    public static final String ACTION_HU_BOOT_COMPLETED = "zxin.intent.action.HU_BOOT_COMPLETED";
    public static final String ACTION_CLEAR_SERVER_DATA = "zxin.intent.action.CLEAR_SERVER_DATA";
    public static final String ACTION_NOMI_EVENT = "com.zxin.event";

    private static boolean mBooted;
    private static boolean mTimeSynchronized;

    private static List<IBootCompleteListener> mBootCompleteListener = new CopyOnWriteArrayList<>();

    public interface IBootCompleteListener {
        void onBootComplete();
    }

    public static void addBootCompleteListener(IBootCompleteListener listener) {
        if (mBooted && mTimeSynchronized) {
            listener.onBootComplete();
        } else {
            if (!mBootCompleteListener.contains(listener)) {
                mBootCompleteListener.add(listener);
            }
        }
    }

    public static void removeBootCompleteListener(IBootCompleteListener listener) {
        mBootCompleteListener.remove(listener);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent == null || intent.getAction() == null) {
            return;
        }

        L.d(TAG, "action:" + intent.getAction());
        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
            L.d(TAG, "Boot start service");
            NavService.startSelf(context.getApplicationContext());
            //ShowSystemDialogHelper.getInstance().receiveBootCompleteMsg();
        } else if (ACTION_CLEAR_SERVER_DATA.equalsIgnoreCase(intent.getAction())) {
            //UserDataCollection.getInstance().uploadClearFavoriteAndHistory();
        } else if (ACTION_HU_BOOT_COMPLETED.equalsIgnoreCase(intent.getAction())){
            //ShowSystemDialogHelper.getInstance().receiveBootCompleteMsg();
            mBooted = true;

            if (mTimeSynchronized) {
                for (IBootCompleteListener listener : mBootCompleteListener) {
                    listener.onBootComplete();
                }
            }
        } else if (ACTION_NOMI_EVENT.equalsIgnoreCase(intent.getAction())) {
            int nomi = intent.getIntExtra("event", 0);
            if(nomi == 1) {
               // NioSasOfflineData.getInstance().updateOfflinePause();
            } else {
                //NioSasOfflineData.getInstance().updateOfflineResume();
            }
            L.d(TAG, "nomi = "+nomi);
        } else if (intent.getAction().equals("android.intent.action.DATE_CHANGED") ||
                  intent.getAction().equals("android.intent.action.init_system_time")) {
            mTimeSynchronized = true;

            if (mBooted) {
                for (IBootCompleteListener listener : mBootCompleteListener) {
                    listener.onBootComplete();
                }
            }
        }
    }
}
