package com.zxin.basemodel.util;

import android.app.Dialog;
import android.view.View;
import com.zxin.root.util.UiUtils;
import java.math.BigDecimal;

public class GlobalUtil extends com.zxin.root.util.GlobalUtil{

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
}
