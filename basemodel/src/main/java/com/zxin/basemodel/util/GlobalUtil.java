package com.zxin.basemodel.util;

import android.app.AlarmManager;
import android.app.Dialog;
import android.content.Context;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import com.zxin.root.util.UiUtils;
import com.zxin.root.util.logger.LogUtils;
import com.google.gson.Gson;
import java.io.File;
import java.math.BigDecimal;
import java.util.Date;

public class GlobalUtil extends com.zxin.root.util.GlobalUtil{
    private static final LogUtils.Tag TAG = new LogUtils.Tag("GlobalUtil");
    
    private static final String TYPE_DRAWABLE = "drawable";
    private static final String TYPE_LAYOUT = "layout";
    private static final String TYPE_STRING = "string";
    private static final String TYPE_DIMEN = "dimen";
    private static final String TYPE_ANIM = "anim";
    private static final String TYPE_ID = "id";
    
    private static final int DEF_DIV_SCALE = 110;

    public static void dialogTitleLineColor(Dialog dialog, int color) {
        int divierId = UiUtils.getInstance(dialog.getContext()).getResources().getIdentifier("android:id/titleDivider", null, null);
        View divider = dialog.findViewById(divierId);
        divider.setBackgroundColor(color);
    }

    /**
     * 提供精確的加法運算
     */
    public static double add(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2).doubleValue();
    }

    /**
     * 提供了精確的減法運算
     *
     */
    public static double sub(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.subtract(b2).doubleValue();
    }

    /**
     * 提供了精確的乘法運算
     */
    public static double mul(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.multiply(b2).doubleValue();
    }

    /**
     * 提供了(相對)精確的除法運算，當發生除不儘的情況時，精確到
     * 小數點以後１10位
     */
    public static double div(double v1, double v2) {
        return div(v1, v2, DEF_DIV_SCALE);
    }

    /**
     * 提供了(相對)精確的除法運算，當發生除不儘的情況時，由scale參數指定
     * 精度，以後的數字四捨五入
     */
    public static double div(double v1, double v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 提供了精確的小數位四捨五入處理
     */

    public static double round(double v, int scale) {
        if (scale<0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b = new BigDecimal(Double.toString(v));
        BigDecimal one = new BigDecimal("1");
        return b.divide(one, scale, BigDecimal.ROUND_HALF_DOWN).doubleValue();
    }


    /**
     * 递归删除目录下的所有文件及子目录下所有文件
     *
     * @param dir 将要删除的文件目录
     * @return boolean Returns "true" if all deletions were successful.
     * If a deletion fails, the method stops attempting to
     * delete and returns "false".
     */
    public static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        // 目录此时为空，可以删除
        return dir.delete();
    }

    /**
     * 设置textView显示局部字体颜色改变
     *
     * @param builder 源数据
     * @param color   字体颜色
     * @param start   文字开始下标
     * @param len     修改文字长度
     * @return 修改后的数据源
     */
    public static SpannableStringBuilder setTextPartColor(SpannableStringBuilder builder
            , int color
            , int start
            , int len) {
        ForegroundColorSpan csp = new ForegroundColorSpan(color);
        builder.setSpan(csp, start, start + len, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        return builder;
    }

    /*****
     * 设置textView显示局部字体点击事件
     * @param builder 源数据
     * @param listener 回调函数
     * @param start 文字开始下标
     * @param len 修改文字长度
     * @return 修改后的数据源
     */
    public static SpannableStringBuilder setTextPartClick(final SpannableStringBuilder builder
            , final View.OnClickListener listener
            , int start
            , int len) {

        //设置局部可点击事件
        builder.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View view) {
                //局部点击的响应事件
                if (listener == null) {
                    return;
                }
                listener.onClick(view);
            }
        }, start, start + len, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        return builder;
    }

    /****
     * 获取当前地图整数等级
     * @param mapLevel
     * @return
     */
    public static int getMapIntLevel(float mapLevel) {
        int level = Math.round(mapLevel);//四舍五入获取整数缩放等级  与ScaleView 中对应
        if (level <= mapLevel) {//排除掉相同的。舍去的
            return -1;
        }
        return level;
    }

    /****
     * 判断是否在当前时间是之前
     * @param time
     * @return
     */
    public static boolean isValidTime(long time) {
        if (time <= 0) {
            return false;
        }
        long current = new Date().getTime();
        if (time <= current) {
            return true;
        }
        return false;
    }

    /****
     * 判断是否在当前时间是之后
     * @param time
     * @return
     */
    public static boolean isValidEndTime(long time) {
        if (time > 0) {
            return false;
        }
        long current = new Date().getTime();
        if (current <= time) {
            return true;
        }
        return false;
    }

    /****
     * 获取闹钟实例
     * @param context
     * @return
     *
     * RTC_WAKEUP :表示闹钟在睡眠状态下唤醒系统并执行提示功能，绝对时间。
     * RTC : 睡眠状态下不可用，绝对时间。
     * ELAPSED_REALTIME_WAKEUP : 睡眠状态下可用，相对时间。
     * ELAPSED_REALTIME : 睡眠状态下不可用，相对时间。
     *
     */
    public static AlarmManager getAlarmManager(Context context) {
        return (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
    }

    /****
     * json 解析
     * @return
     */
    public static <T> T jsonToBean(Class<T> clazz, String jsonStr) {
        if (jsonStr == null
                || jsonStr.trim().equals("")
                || jsonStr.trim().equals("null")) {
            LogUtils.d(TAG, "upDateCarLog jsonStr is null");
            return null;
        }
        LogUtils.d(TAG, "jsonToBean jsonStr is : " + jsonStr);
        return new Gson().fromJson(jsonStr, clazz);
    }
    
    /****
     * 根据ID获取Dimen
     * @param resId 资源id
     * @return
     */
    public static int getDimensionById(int resId) {
        return getResources().getDimensionPixelSize(resId);
    }

    /****
     * 根据name获取Dimen
     * @param resName
     * @return
     */
    public static int getDimensionByName(String resName) {
        int resId = getDimenId(resName);
        return getDimensionById(resId);
    }

    /****
     * 获取布局
     * @param id
     * @param <V>
     * @return
     */
    public static <V extends View> V getViewById(int id) {
        return (V) LayoutInflater.from(NioRootApp.getApp().getApplicationContext()).inflate(id, null);
    }

    /*****
     * 厘米转换px
     * 72dpi 1厘米
     * @param cm 单位 厘米
     * @return
     */
    public static float cm2px(float cm){
        float scale = GlobalUtil.getResources().getDisplayMetrics().density;
        return cm * 72 * scale + 0.5f;
    }
    
    public static int getIdentifierId(Context context, String name) {
        return getResourceId(context, name, TYPE_ID);
    }

    public static int getDrawableId(Context context, String name) {
        return getResourceId(context, name, TYPE_DRAWABLE);
    }

    public static int getStringId(Context context, String name) {
        return getResourceId(context, name, TYPE_STRING);
    }

    public static int getLayoutId(Context context, String name) {
        return getResourceId(context, name, TYPE_LAYOUT);
    }

    public static int getDimenId(Context context, String name) {
        return getResourceId(context, name, TYPE_DIMEN);
    }

    public static int getAnimId(Context context, String name) {
        return getResourceId(context, name, TYPE_ANIM);
    }

    public static int getResourceId(Context context, String name, String defType) {
        return getResources().getIdentifier(name,defType,context.getPackageName());
    }
}
