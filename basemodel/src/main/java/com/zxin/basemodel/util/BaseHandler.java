package com.zxin.basemodel.util;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.zxin.network.mvp.presenter.BasePresenter;
import com.zxin.root.util.logger.LogUtils;
import java.lang.ref.WeakReference;

/******
 * handle 信息处理嘞
 */
public abstract class BaseHandler<P extends BasePresenter> extends Handler {
    private static final LogUtils.Tag TAG = new LogUtils.Tag("BaseHandler");
    private WeakReference<P> mWeakReference;

    protected BaseHandler(P p) {
        super(Looper.getMainLooper());
        mWeakReference = new WeakReference<>(p);
    }

    @Override
    public void handleMessage(Message msg) {
        LogUtils.d(TAG, "BaseHandler handleMessage:" + msg.what);
        if (mWeakReference == null) {
            return;
        }
        P pp = mWeakReference.get();
        if (pp == null){
            return;
        }
        resultMessage(pp, msg);
    }

    public abstract void resultMessage(P presenter, Message message);

    /***
     * 发送消息
     * @param message
     */
    public void sendEmptyMesg(int message) {
        sendEmptyMesg(message, 0);
    }

    /****
     * 发送消息
     * @param message
     */
    public void sendMesg(Message message) {
        sendMesg(message, 0);
    }

    /****
     * 发送延迟消息
     * @param message
     * @param delayMillis
     */
    public void sendEmptyMesg(int message, int delayMillis) {
        LogUtils.d(TAG, "sendEmptyMesg  message is : " + message + " and delayMillis is : " + delayMillis);
        sendEmptyMessageDelayed(message, delayMillis);
    }

    /****
     * 发送延迟消息
     * @param message
     * @param delayMillis
     */
    public void sendMesg(Message message, int delayMillis) {
        LogUtils.d(TAG, "sendMesg  message is : " + message + " and delayMillis is : " + delayMillis);
        sendMessageDelayed(message, delayMillis);
    }

    /****
     * 取消计消息
     */
    public void removeMessage(int message) {
        //取消7秒计时器
        LogUtils.d(TAG, "removeMessage  ...");
        removeMessages(message);
    }

    /****
     * 判断是否包含消息
     * @return
     */
    public boolean isHasMessages(int message) {
        return hasMessages(message);
    }

    /****
     * 销毁该实例
     */
    public void destory() {
        if (mWeakReference == null) {
            return;
        }
        mWeakReference.clear();
        mWeakReference = null;
    }

}
