package com.zxin.meziyowu.mvp.model;

import com.zxin.basemodel.app.BaseApplication;
import com.zxin.basemodel.network.AbsAPICallback;
import com.zxin.meziyowu.api.ZXinYoWuApi;
import com.zxin.meziyowu.bean.YoMeiBean;
import com.zxin.meziyowu.bean.YoMeiDeatilBean;
import com.zxin.meziyowu.bean.YoMeiTagModel;
import com.zxin.meziyowu.bean.YoWuResult;
import com.zxin.network.exception.ResultException;
import com.zxin.network.http.RetrofitHelper;
import com.zxin.network.mvp.model.BaseModel;
import com.zxin.basemodel.util.BuildUtils;
import java.util.List;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/5/18.
 */

public class YoMeiMainModel extends BaseModel {

    public void getYoMeiTagList() {
        ZXinYoWuApi api = getZxinWebApi().getZxinAPI(BuildUtils.getInstance(getContext()).getURLAPI(BuildUtils.APIURL.XiuMei99));
        api.getHomeTagList("")
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new AbsAPICallback<YoWuResult<List<YoMeiTagModel>>>(getContext(),getTag(), true) {
                    @Override
                    protected void onDone(int tag , YoWuResult<List<YoMeiTagModel>> strData) {
                        if (strData.isResult())
                            getListener(tag).onSuccess(tag,strData.getData());
                        else
                            getListener(tag).onFailure(tag,"错误信息");
                    }

                    @Override
                    public void onResultError(int tag ,ResultException ex) {
                        getListener(tag).onFailure(tag,ex.getMessage());
                    }
                });
    }

    public void getYoMeiListByTag(int typeId,int pageNum) {
        ZXinYoWuApi api = getZxinWebApi().getZxinAPI(BuildUtils.getInstance(getContext()).getURLAPI(BuildUtils.APIURL.XiuMei99));
        api.getYoMeiListByTag("0",typeId,pageNum)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new AbsAPICallback<YoWuResult<List<YoMeiBean>>>(getContext(),getTag(), true) {
                    @Override
                    protected void onDone(int tag ,YoWuResult<List<YoMeiBean>> strData) {
                        if (strData.isResult())
                            getListener(tag).onSuccess(tag,strData);
                        else
                            getListener(tag).onFailure(tag,"错误信息");
                    }

                    @Override
                    public void onResultError(int tag ,ResultException ex) {
                        getListener(tag).onFailure(tag,ex.getMessage());
                    }
                });
    }

    public void getYoMeiVideoDetail(String userId,String userKey,String macid,int videoId) {
        ZXinYoWuApi api = getZxinWebApi().getZxinAPI(BuildUtils.getInstance(getContext()).getURLAPI(BuildUtils.APIURL.XiuMei99));
        api.getYoMeiVideoDetail(userId,userKey,macid,videoId)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new AbsAPICallback<YoWuResult<YoMeiDeatilBean>>(getContext(),getTag(), true) {
                    @Override
                    protected void onDone(int tag ,YoWuResult<YoMeiDeatilBean> strData) {
                        if (strData.isResult())
                            getListener(tag).onSuccess(tag,strData.getData());
                        else if (strData.getErrorCode()==501)
                            getListener(tag).onSuccess(tag,strData.getData());
                        else
                            getListener(tag).onFailure(tag,"错误信息");
                    }

                    @Override
                    public void onResultError(int tag ,ResultException ex) {
                        getListener(tag).onFailure(tag,ex.getMessage());
                    }
                });
    }

    public void getYoMeiDetail(String userId,String userKey,int video) {
        ZXinYoWuApi api = getZxinWebApi().getZxinAPI(BuildUtils.getInstance(getContext()).getURLAPI(BuildUtils.APIURL.XiuMei99));
        api.getYoMeiDetail(userId,userKey,video)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new AbsAPICallback<YoWuResult<YoMeiDeatilBean>>(getContext(),getTag(), true) {
                    @Override
                    protected void onDone(int tag ,YoWuResult<YoMeiDeatilBean> strData) {
                        if (strData.isResult())
                            getListener(tag).onSuccess(tag,strData.getData());
                        else
                            getListener(tag).onFailure(tag,"错误信息");
                    }

                    @Override
                    public void onResultError(int tag ,ResultException ex) {
                        getListener(tag).onFailure(tag,ex.getMessage());
                    }
                });
    }

    public void getYoMeiDetailList(int video,int pageNum) {
        ZXinYoWuApi api = getZxinWebApi().getZxinAPI(BuildUtils.getInstance(getContext()).getURLAPI(BuildUtils.APIURL.XiuMei99));
        api.getYoMeiDetailList(video,pageNum)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new AbsAPICallback<YoWuResult<List<YoMeiBean>>>(getContext(),getTag(), true) {
                    @Override
                    protected void onDone(int tag ,YoWuResult<List<YoMeiBean>> strData) {
                        if (strData.isResult())
                            getListener(tag).onSuccess(tag,strData);
                        else
                            getListener(tag).onFailure(tag,"错误信息");
                    }

                    @Override
                    public void onResultError(int tag ,ResultException ex) {
                        getListener(tag).onFailure(tag,ex.getMessage());
                    }
                });
    }

    @Override
    public RetrofitHelper initHelper() {
        return BaseApplication.getInstance().getRetrofitHelper();
    }
}
