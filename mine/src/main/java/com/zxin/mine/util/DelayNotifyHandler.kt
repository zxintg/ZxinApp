package com.zxin.mine.util

import android.os.Handler
import android.os.Message;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

open class DelayNotifyHandler(callback: Callback?) : Handler(callback) {
    //使用延迟加载关键字 kotlin 默认函数（init()）
    lateinit var sInstance: DelayNotifyHandler;
    //静态成员变量 var 表示普通成员变量
    val listeners: HashMap<Int, OnDelayNotifyListener> = HashMap();
    val messages: HashMap<Int, List<String>> = HashMap();

    fun containsMessage(paramInt: Int): Boolean {
        return this.messages.containsKey(paramInt);
    }

    //取消了static 静态成员变量 使用了companion 伴随
    companion object fun getInstance(callback: Callback): DelayNotifyHandler {
        if (sInstance == null) {
            sInstance = DelayNotifyHandler(callback);
        }
        return sInstance;
    }

    fun sendSimpleMessage(handler: DelayNotifyHandler, paramInt: Int, list : List<String>) {
        var localMessage: Message = Message.obtain();
        localMessage.what = paramInt;
        handler.sendRefreshMessage(localMessage,list);
    }

    fun sendSimpleMessageWithPackageName(handler: DelayNotifyHandler, paramInt: Int, paramString: String) {
        var localMessage: Message = Message.obtain();
        localMessage.what = paramInt;
        localMessage.obj = paramString;
        handler.sendRefreshMessageWithPackageName(localMessage);
    }

    fun addListener(paramInt: Int, listener: OnDelayNotifyListener) {
        this.listeners.put(paramInt, listener);
    }

    override fun handleMessage(message: Message) {
        var i: Int = message.what;
        var localList : List<String> = this.messages.remove(i) as List<String>;
        if (this.listeners.get(Integer.valueOf(i)) == null) {
            return;
        }
        var listener: OnDelayNotifyListener = this.listeners.get(i)!!;
        listener.onDelayNotify(localList);
    }

    fun removeListener(paramInt: Int) {
        this.listeners.remove(paramInt);
    }

    fun sendRefreshMessage(paramMessage: Message , list : List<String>) {
        var i: Int = paramMessage.what;
        if (containsMessage(i)) {
            return;
        }
        this.messages.put(i, list);
        sendMessageDelayed(paramMessage, 800L);
    }

    fun sendRefreshMessageWithPackageName(param: Message) {
        var i: Int = param.what;
        var str: String = param.obj as String;
        if (containsMessage(i)) {
            if (!TextUtils.isEmpty(str)) {
                var mesgList: List<String> = this.messages.get(i) as List<String>;
                mesgList.add(param.obj as String);
            }
            return;
        }
        var localArrayList: ArrayList<String> = ArrayList(10);
        if (!TextUtils.isEmpty(str))
            localArrayList.add(param.obj as String);
        this.messages.put(Integer.valueOf(i), localArrayList as List<String>);
        sendMessageDelayed(param, 800L);
    }

    interface OnDelayNotifyListener {
        fun onDelayNotify(paramList: List<String>);
    }
}