package com.zxin.sources.mvp.presenter;

import android.view.View;

import com.zxin.network.MvpCallback;
import com.zxin.network.mvp.presenter.BasePresenter;
import com.zxin.sources.mvp.model.SourcesModel;
import com.zxin.sources.mvp.view.CodeKKContract;
import com.zxin.root.util.IntegerUtil;
import com.zxin.root.util.ToastUtil;
import com.zxin.sources.util.HtmlOperatorUtil;

import java.io.IOException;
import okhttp3.ResponseBody;

/**
 * Created by Administrator on 2018/5/21.
 */

public class CodeKKPresenter extends BasePresenter<CodeKKContract, SourcesModel> implements MvpCallback{

    public void initDatas(CodeKKContract.CodeView listener,Object... parameter) {
        getView().setP(this);
        getView().setCodeViewListener(listener);
        getView().setParameter(parameter);
        getView().initDatas();
    }

    public void getTestMeiZi(String tag,int pageNum) {
        getModel().setListener(this);
        getModel().setTag(IntegerUtil.WEB_API_CodeKK);
        getModel().getCodeKKList(tag,pageNum);
    }

    @Override
    public void onSuccess(int tage, Object data) {
        switch (tage){
            case IntegerUtil.WEB_API_CodeKK:
                ResponseBody body = (ResponseBody) data;
                try {
                    getView().onResultSuccess(HtmlOperatorUtil.getInstance().getCodeKKList(body.string()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    @Override
    public void onFailure(int tage, String msg) {
        switch (tage){
            case IntegerUtil.WEB_API_CodeKK:
                ToastUtil.getInstance(getModel().getContext()).showShort(msg);
                break;
        }
    }

    @Override
    public void onComplete(int tager) {
        getView().onComplete();
    }


    @Override
    public void loadDatas() {
        getView().loadDatas();
    }

    @Override
    public void OnClick(View v) {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onRestart() {

    }

    @Override
    public void restartNetWork() {

    }
}
