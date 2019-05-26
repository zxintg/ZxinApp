package com.zxin.jdxsxp.mvp.model;

import com.zxin.basemodel.app.BaseApplication;
import com.zxin.basemodel.network.AbsAPICallback;
import com.zxin.basemodel.util.BuildUtils;
import com.zxin.jdxsxp.api.ZXinJdxsxpApi;
import com.zxin.jdxsxp.bean.ArticleListBean;
import com.zxin.jdxsxp.bean.MZPicModle;
import com.zxin.jdxsxp.bean.MeiZuHome;
import com.zxin.jdxsxp.bean.MeiZuHot;
import com.zxin.jdxsxp.bean.MeiZuMeiZiDetail;
import com.zxin.jdxsxp.bean.MeinvBaogaoBean;
import com.zxin.jdxsxp.bean.MinvBaoGaodetail;
import com.zxin.jdxsxp.bean.SearchBaiduPic;
import com.zxin.jdxsxp.bean.SearchSouGou;
import com.zxin.network.exception.ResultException;
import com.zxin.network.http.RetrofitHelper;
import com.zxin.network.mvp.model.BaseModel;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/5/18.
 */

public class MeiZiMainModel extends BaseModel {

    public void getMainMeiZiApi() {
        ZXinJdxsxpApi api = getZxinWebApi().getZxinAPI(BuildUtils.getInstance(getContext()).getURLAPI(BuildUtils.APIURL.MeiZu));
        api.getMainMeiZiApi()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new AbsAPICallback<MeiZuHome>(getContext(), getTag(), true) {
                    @Override
                    protected void onDone(int tag, MeiZuHome strData) {
                        if (strData.getCode() == 200)
                            getListener(tag).onSuccess(tag, strData.getValue());
                        else
                            getListener(tag).onFailure(tag, strData.getMessage());
                    }

                    @Override
                    public void onResultError(int tag, ResultException ex) {
                        getListener(tag).onFailure(tag, ex.getMessage());
                    }
                });
    }

    public void getMainHotApi(int pageNum) {
        ZXinJdxsxpApi api = getZxinWebApi().getZxinAPI(BuildUtils.getInstance(getContext()).getURLAPI(BuildUtils.APIURL.Picasso));
        api.getMainHotApi(20, pageNum * 20)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new AbsAPICallback<MeiZuHot>(getContext(), getTag(), true) {
                    @Override
                    protected void onDone(int tag, MeiZuHot strData) {
                        if (strData.getCode() == 0)
                            getListener(tag).onSuccess(tag, strData.getRes());
                        else
                            getListener(tag).onFailure(tag, strData.getMsg());
                    }

                    @Override
                    public void onResultError(int tag, ResultException ex) {
                        getListener(tag).onFailure(tag, ex.getMessage());
                    }
                });
    }

    public void getMeiNvListApi(int pageNum) {
        ZXinJdxsxpApi api = getZxinWebApi().getZxinAPI(BuildUtils.getInstance(getContext()).getURLAPI(BuildUtils.APIURL.Picasso));
        api.getMeiNvListApi(20, pageNum * 20)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new AbsAPICallback<MeiZuHot>(getContext(), getTag(), true) {
                    @Override
                    protected void onDone(int tag, MeiZuHot strData) {
                        if (strData.getCode() == 0)
                            getListener(tag).onSuccess(tag, strData.getRes());
                        else
                            getListener(tag).onFailure(tag, strData.getMsg());
                    }

                    @Override
                    public void onResultError(int tag, ResultException ex) {
                        getListener(tag).onFailure(tag, ex.getMessage());
                    }
                });
    }

    public void getMeiNvDetailApi(String meiId, int pageNum) {
        ZXinJdxsxpApi api = getZxinWebApi().getZxinAPI(BuildUtils.getInstance(getContext()).getURLAPI(BuildUtils.APIURL.Picasso));
        api.getMeiNvDetailApi(meiId, 20, pageNum * 20)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new AbsAPICallback<MeiZuMeiZiDetail>(getContext(), getTag(), true) {
                    @Override
                    protected void onDone(int tag, MeiZuMeiZiDetail strData) {
                        if (strData.getCode() == 0)
                            getListener(tag).onSuccess(tag, strData.getRes());
                        else
                            getListener(tag).onFailure(tag, strData.getMsg());
                    }

                    @Override
                    public void onResultError(int tag, ResultException ex) {
                        getListener(tag).onFailure(tag, ex.getMessage());
                    }
                });
    }

    public void getArticleListApi() {
        ZXinJdxsxpApi api = getZxinWebApi().getZxinAPI(BuildUtils.getInstance(getContext()).getURLAPI(BuildUtils.APIURL.RuBaoo));
        api.getArticleListApi()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new AbsAPICallback<ArticleListBean>(getContext(), getTag(), true) {
                    @Override
                    protected void onDone(int tag, ArticleListBean strData) {
                        if (strData.getSuccess() == 1)
                            getListener(tag).onSuccess(tag, strData.getData());
                        else
                            getListener(tag).onFailure(tag, "异常");
                    }

                    @Override
                    public void onResultError(int tag, ResultException ex) {
                        getListener(tag).onFailure(tag, ex.getMessage());
                    }
                });
    }

    public void getWallPaperItemList(String type, int pageNum) {
        ZXinJdxsxpApi api = getZxinWebApi().getZxinAPI(BuildUtils.getInstance(getContext()).getURLAPI(BuildUtils.APIURL.Beauty));
        api.getWallPaperItemList(type + pageNum)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new AbsAPICallback<List<MeinvBaogaoBean>>(getContext(), getTag(), true) {
                    @Override
                    protected void onDone(int tag, List<MeinvBaogaoBean> strData) {
                        if (strData != null)
                            getListener(tag).onSuccess(tag, strData);
                        else
                            getListener(tag).onFailure(tag, "异常");
                    }

                    @Override
                    public void onResultError(int tag, ResultException ex) {
                        getListener(tag).onFailure(tag, ex.getMessage());
                    }
                });
    }

    public void getFindBaiDuList(String keyword, int pageNum) {
        ZXinJdxsxpApi api = getZxinWebApi().getZxinAPI(BuildUtils.getInstance(getContext()).getURLAPI(BuildUtils.APIURL.BaiDu));
        api.getFindBaiDuList(keyword, pageNum * 20)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new AbsAPICallback<SearchBaiduPic>(getContext(), getTag(), true) {
                    @Override
                    protected void onDone(int tag, SearchBaiduPic strData) {
                        if (strData != null)
                            getListener(tag).onSuccess(tag, strData.getData());
                        else
                            getListener(tag).onFailure(tag, "异常");
                    }

                    @Override
                    public void onResultError(int tag, ResultException ex) {
                        getListener(tag).onFailure(tag, ex.getMessage());
                    }
                });
    }

    public void getFind360List(String keyword, int pageNum) {
        ZXinJdxsxpApi api = getZxinWebApi().getZxinAPI(BuildUtils.getInstance(getContext()).getURLAPI(BuildUtils.APIURL.MeiZu));
        api.getFind360List(keyword, pageNum * 20)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new AbsAPICallback<MZPicModle>(getContext(), getTag(), true) {
                    @Override
                    protected void onDone(int tag, MZPicModle strData) {
                        if (strData != null)
                            getListener(tag).onSuccess(tag, strData.getValue());
                        else
                            getListener(tag).onFailure(tag, "异常");
                    }

                    @Override
                    public void onResultError(int tag, ResultException ex) {
                        getListener(tag).onFailure(tag, ex.getMessage());
                    }
                });
    }

    public void getFindSouGouList(String keyword, int pageNum) {
        ZXinJdxsxpApi api = getZxinWebApi().getZxinAPI(BuildUtils.getInstance(getContext()).getURLAPI(BuildUtils.APIURL.SoGou));
        api.getFindSouGouList(keyword, pageNum * 20)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new AbsAPICallback<SearchSouGou>(getContext(), getTag(), true) {
                    @Override
                    protected void onDone(int tag, SearchSouGou strData) {
                        if (strData != null)
                            getListener(tag).onSuccess(tag, strData);
                        else
                            getListener(tag).onFailure(tag, "异常");
                    }

                    @Override
                    public void onResultError(int tag, ResultException ex) {
                        getListener(tag).onFailure(tag, ex.getMessage());
                    }
                });
    }

    public void getPicDetailList(String albumAddress) {
        ZXinJdxsxpApi api = getZxinWebApi().getZxinAPI(BuildUtils.getInstance(getContext()).getURLAPI(BuildUtils.APIURL.Beauty));
        api.getPicDetailList("v4/" + albumAddress)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new AbsAPICallback<MinvBaoGaodetail>(getContext(), getTag(), true) {
                    @Override
                    protected void onDone(int tag, MinvBaoGaodetail strData) {
                        if (strData != null)
                            getListener(tag).onSuccess(tag, strData);
                        else
                            getListener(tag).onFailure(tag, "异常");
                    }

                    @Override
                    public void onResultError(int tag, ResultException ex) {
                        getListener(tag).onFailure(tag, ex.getMessage());
                    }
                });
    }

    @Override
    public RetrofitHelper initHelper() {
        return BaseApplication.getInstance().getRetrofitHelper();
    }
}
