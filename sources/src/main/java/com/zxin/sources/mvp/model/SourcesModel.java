package com.zxin.sources.mvp.model;

import com.zxin.basemodel.annot.ApiUrlMode;
import com.zxin.basemodel.app.BaseApplication;
import com.zxin.basemodel.network.AbsAPICallback;
import com.zxin.network.exception.ResultException;
import com.zxin.network.http.RetrofitHelper;
import com.zxin.network.mvp.model.BaseModel;
import com.zxin.sources.api.ZXinSourcesApi;
import com.zxin.basemodel.util.BuildUtils;
import okhttp3.ResponseBody;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/5/18.
 */

public class SourcesModel extends BaseModel {

    public void getCodeKKList(String mesg,int pageNum) {
        ZXinSourcesApi api = getZxinWebApi().getZxinAPI(BuildUtils.getInstance(getContext()).getURLAPI(ApiUrlMode.APIURL_MODE_Codekk));
        api.getCodeKKList(mesg,pageNum)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new AbsAPICallback<ResponseBody>(getContext(),getTag(), true) {
                    @Override
                    protected void onDone(int tag,ResponseBody strData) {
                        getListener(tag).onSuccess(tag,strData);
                    }

                    @Override
                    public void onResultError(int tag,ResultException ex) {
                        getListener(tag).onFailure(tag,"异常");
                    }
                });
    }

    /****
     * 云商
     * @param pageNum
     */
    public void getYunShangList(int pageNum) {
        ZXinSourcesApi api = getZxinWebApi().getZxinAPI(BuildUtils.getInstance(getContext()).getURLAPI(ApiUrlMode.APIURL_MODE_YuShangJi));
        api.getYunShangList(pageNum)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new AbsAPICallback<ResponseBody>(getContext(),getTag(), true) {
                    @Override
                    protected void onDone(int tag,ResponseBody strData) {
                        getListener(tag).onSuccess(tag,strData);
                    }

                    @Override
                    public void onResultError(int tag,ResultException ex) {
                        getListener(tag).onFailure(tag,"异常");
                    }
                });
    }

    /*****
     * 安卓 BUS
     * @param pageNum
     */
    public void getAndroidBusList(int pageNum) {
        ZXinSourcesApi api = getZxinWebApi().getZxinAPI(BuildUtils.getInstance(getContext()).getURLAPI(ApiUrlMode.APIURL_MODE_ApkBus));
        api.getAndroidBusList(pageNum)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new AbsAPICallback<ResponseBody>(getContext(),getTag(), true) {
                    @Override
                    protected void onDone(int tag,ResponseBody strData) {
                        getListener(tag).onSuccess(tag,strData);
                    }

                    @Override
                    public void onResultError(int tag,ResultException ex) {
                        getListener(tag).onFailure(tag,"异常");
                    }
                });
    }

    @Override
    public RetrofitHelper initHelper() {
        return BaseApplication.getInstance().getRetrofitHelper();
    }
}
