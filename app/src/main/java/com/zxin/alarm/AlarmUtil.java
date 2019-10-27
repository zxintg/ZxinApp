package com.zxin.alarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import com.zxin.root.util.logger.LogUtils;
import com.zxin.basemodel.util.GlobalUtil;
import com.zxin.util.StringUtils;

/****
 * 闹铃工具类
 */
public class AlarmUtil {
    private static final LogUtils.Tag TAG = new LogUtils.Tag("AlarmUtil");
    private static final String carLogoDefault = "CarLogo_Default";
    public static final int Alarm_Request_One = 1;//闹钟1
    public static final int Alarm_Request_Two = 2;//闹钟2

    private static volatile AlarmUtil instance = null;

    private AlarmUtil() {

    }

    /****
     * 获取AlarmUtil 实例
     * @return
     */
    public static AlarmUtil getInstance() {
        if (instance == null) {
            synchronized (AlarmUtil.class) {
                if (instance == null) {
                    instance = new AlarmUtil();
                }
            }
        }
        return instance;
    }

    /*****
     * 更新自车车标 必须等待地图加载初始化加载完成
     */
    public void alarmDispense(Context mContext, String jsonStr) {
        AlarmBean alarmBean = GlobalUtil.jsonToBean(AlarmBean.class, jsonStr);
        if (alarmBean == null
                || alarmBean.getCarLogo() == null) {
            LogUtils.d(TAG, "alarmDispense AlarmBean is null");
            return;
        }
    }

    /*****
     * 更新数据 无数据源
     * @param mContext
     */
    public void upDateCarLogo(Context mContext) {
        String jsonStr = "";
        if (jsonStr.equals(carLogoDefault)) {
            LogUtils.d(TAG, "upDateCarLog jsonStr is out time");
            return;
        }
        AlarmBean alarmBean = new AlarmBean();
        if (StringUtils.isNull(jsonStr)) {
            alarmBean.getDefaultCarLogo();
        } else {
            alarmBean = GlobalUtil.jsonToBean(AlarmBean.class, jsonStr);
        }
        setCarLogo(mContext, alarmBean);
    }

    /*****
     * 根据 alarmBean 设置carlogo 信息
     * @param mContext
     * @param alarmBean
     */
    private void setCarLogo(Context mContext, AlarmBean alarmBean) {
        if (alarmBean.getCarLogo() == null) {
            LogUtils.d(TAG, "upDateCarLog setCarLogo is null");
            return;
        }
        if (alarmBean.getCarLogo().getStartTime() <= 0
                && alarmBean.getCarLogo().getEndTime() <= 0
                || alarmBean.getCarLogo().getStartTime() >= alarmBean.getCarLogo().getEndTime()) {
            //无效时间
            LogUtils.d(TAG, "upDateCarLog time range is invalid");
            cancelCarLogoAlarm(mContext);
            return;
        }
        if (GlobalUtil.isValidTime(alarmBean.getCarLogo().getEndTime())) {
            //已过期
            LogUtils.d(TAG, "upDateCarLog time range is out end");
            //关闭闹钟
            cancelCarLogoAlarm(mContext);
            return;
        }
        if (GlobalUtil.isValidTime(alarmBean.getCarLogo().getStartTime())) {
            LogUtils.d(TAG, "upDateCarLog time is in range");
            //在有效期期间
            setAlarmClock(mContext, alarmBean);//启动失效闹钟
            return;
        }
        LogUtils.d(TAG, "upDateCarLog time range is out start");
        //还没有到开始有效期
        setAlarmClock(mContext, alarmBean);
    }

    /*****
     * //如果新请求的 PendingIntent 发现已经存在时，取消已存在的，用新的 PendingIntent 替换
     * int FLAG_CANCEL_CURRENT
     *
     * //如果新请求的 PendingIntent 发现已经存在时，忽略新请求的，继续使用已存在的。日常开发中很少使用
     * int FLAG_NO_CREATE
     *
     * //表示 PendingIntent 只能使用一次，如果已使用过，那么 getXXX(...) 将会返回 NULL
     * //也就是说同类的通知只能使用一次，后续的通知单击后无法打开。
     * int FLAG_ONE_SHOT
     *
     * //如果新请求的 PendingIntent 发现已经存在时, 如果 Intent 有字段改变了，这更新已存在的 PendingIntent
     * int FLAG_UPDATE_CURRENT
     *
     * @param mContext
     * @param alarmBean
     *
     */
    public void setAlarmClock(Context mContext, AlarmBean alarmBean) {
        if (alarmBean.getCarLogo().getStartTime() <= 0) {
            return;
        }
        LogUtils.d(TAG, "setAlarmClock AlarmBean is ： " + alarmBean.toString());
        Intent intent = new Intent(StringUtils.INTENT_ALARM_CLOCK);
        intent.putExtra(StringUtils.EXTRA_TYPE, alarmBean.getMesgTye());
        intent.putExtra(StringUtils.EXTRA_MESSAGE, alarmBean);
        PendingIntent pi = PendingIntent.getBroadcast(mContext, Alarm_Request_One, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        AlarmManager manager = GlobalUtil.getAlarmManager(mContext);
        if (GlobalUtil.isValidTime(alarmBean.getCarLogo().getStartTime()) && !GlobalUtil.isValidTime(alarmBean.getCarLogo().getEndTime())) {
            //当前时间在有效期内。 只需要设置失效闹钟（期间） 闹铃一次
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                manager.setWindow(AlarmManager.RTC_WAKEUP, alarmBean.getCarLogo().getEndTime(),
                        0, pi);
            }else{
                manager.set(AlarmManager.RTC_WAKEUP, alarmBean.getCarLogo().getEndTime(), pi);
            }
            LogUtils.d(TAG, "setAlarmClock AlarmBean clock is end");
            return;
        }
        if (!GlobalUtil.isValidTime(alarmBean.getCarLogo().getStartTime())) {
            //当前时间还没到有效期 。需要设置闹铃有效时间(开始)，闹铃两次
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//API 19以后没有重复闹钟了。启动两个
                manager.setWindow(AlarmManager.RTC_WAKEUP, alarmBean.getCarLogo().getStartTime(),
                        0, pi);
                PendingIntent twoPi = PendingIntent.getBroadcast(mContext, Alarm_Request_Two, intent, PendingIntent.FLAG_CANCEL_CURRENT);
                manager.setWindow(AlarmManager.RTC_WAKEUP, alarmBean.getCarLogo().getEndTime(),
                        0, twoPi);
            }else{
                manager.setRepeating(AlarmManager.RTC_WAKEUP, alarmBean.getCarLogo().getStartTime(), alarmBean.getCarLogo().getEndTime(), pi);
            }
            LogUtils.d(TAG, "setAlarmClock AlarmBean clock is start and end");
            return;
        }
    }

    /*****
     *取消闹钟服务
     */
    public void cancelCarLogoAlarm(Context mContext){
        LogUtils.d(TAG, "time is out cancelAlarm");
        Intent intent = new Intent(StringUtils.INTENT_ALARM_CLOCK);
        AlarmManager am = GlobalUtil.getAlarmManager(mContext);
        PendingIntent piOne = PendingIntent.getBroadcast(mContext, Alarm_Request_One, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        PendingIntent piTwo = PendingIntent.getBroadcast(mContext, Alarm_Request_Two, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        am.cancel(piOne);
        am.cancel(piTwo);
    }

}
