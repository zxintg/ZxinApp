package com.zxin.activity.test;

import android.view.View;

import com.zxin.R;
import com.zxin.base.BaseActivity;
import com.zxin.root.view.RefreshCommonView;

import butterknife.BindView;
import butterknife.OnClick;

public class StudySourceActivity extends BaseActivity {
    @BindView(R.id.rcv_source_commonlayout)
    RefreshCommonView mRefreshCommonView;

    @Override
    public void initData() {

    }

    @Override
    public int setLayout() {
        return R.layout.activity_test_source;
    }

    @Override
    public void clearAllDatas() {

    }

    @Override
    public void saveAllDatas() {

    }

    @OnClick({R.id.common_bar_leftBtn})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.common_bar_leftBtn:
                onBackPressed();
                break;
        }
    }
}
