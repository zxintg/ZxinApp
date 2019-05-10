package com.zxin.basemodel.util;

import android.app.Dialog;
import android.view.View;
import com.zxin.root.util.UiUtils;

public class GlobalUtil extends com.zxin.root.util.GlobalUtil{

    public static final void dialogTitleLineColor(Dialog dialog, int color) {
        int divierId = UiUtils.getInstance(dialog.getContext()).getResources().getIdentifier("android:id/titleDivider", null, null);
        View divider = dialog.findViewById(divierId);
        divider.setBackgroundColor(color);
    }
}
