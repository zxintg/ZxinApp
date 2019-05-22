package com.zxin.mvp.model;

import com.zxin.app.MyApplication;
import com.zxin.basemodel.network.AbsAPICallback;
import com.zxin.network.exception.ResultException;
import com.zxin.network.http.RetrofitHelper;
import com.zxin.network.mvp.model.BaseModel;
import com.zxin.sources.api.ZXinSourcesApi;
import okhttp3.ResponseBody;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/5/18.
 */

public class TestOneModel extends BaseModel {

    public void getMeiziJsonList(String type, int pageNum) {
        ZXinSourcesApi sourcesApi = getZxinWebApi().getZxinAPI("http://route.showapi.com/");
        sourcesApi.getHuaBanMeizi("20", String.valueOf(pageNum), "15314", type, "d424376f51f1467da1b8c75debebf148")
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new AbsAPICallback<ResponseBody>(getContext(), getTag(), true) {
                    @Override
                    protected void onResultError(int tag, ResultException ex) {
                        getListener(tag).onFailure(tag, "异常");
                    }

                    @Override
                    protected void onDone(int tag, ResponseBody strData) {
                        getListener(tag).onSuccess(tag, strData);
                    }
                });
    }

    public void getMeiziHtmlList(int pageNum) {
        ZXinSourcesApi sourcesApi = getZxinWebApi().getZxinAPI("http://www.dbmeinv.com/dbgroup/");
        sourcesApi.getDoubanMeizi(7, pageNum)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new AbsAPICallback<ResponseBody>(getContext(), getTag(),true) {
                    @Override
                    protected void onDone(int tag,ResponseBody strData) {
                        getListener(tag).onSuccess(tag, strData);
                    }

                    @Override
                    public void onResultError(int tag,ResultException ex) {
                        getListener(tag).onFailure(tag, "异常");
                    }
                });
    }

    @Override
    public RetrofitHelper initHelper() {
        return MyApplication.getInstance().getRetrofitHelper();
    }
}
