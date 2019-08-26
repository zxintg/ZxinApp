package com.zxin.mine.fragment

import com.zxin.basemodel.fragment.BaseFragment

abstract class YoukuPayFragment : BaseFragment() {

    var currentPaymentPosition :Int = 0;

    abstract fun hidePayFilterPopView();

    abstract fun setPayResult(paramBoolean1 : Boolean, paramBoolean2 : Boolean, paramBoolean3 : Boolean);

    abstract fun updatePayLayout(paramInt : Int);
}