package com.zxin.basemodel.interceptor;

import android.content.Context;
import com.zxin.network.interceptor.ZxinBaseInterceptor;
import com.zxin.network.util.NetworkUtil;
import java.io.IOException;
import okhttp3.CacheControl;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/****
 * 缓存拦截器
 */
public class CacheInterceptor extends ZxinBaseInterceptor {

    public  CacheInterceptor(Context mContext){
        super(mContext);
    }

    @Override
    public HttpUrl.Builder interceptRequestByGet(HttpUrl.Builder builder) {
        return null;
    }

    @Override
    public FormBody.Builder interceptRequestByPost(RequestBody requestBody) {
        return null;
    }

    @Override
    public Response interceptResponse(Chain chain, Request request) {
        if (!NetworkUtil.getInstance(getContext()).isNetWorkAviliable()) {
            request = request.newBuilder()
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build();
        }
        try {
            Response response = chain.proceed(request);
            if (NetworkUtil.getInstance(getContext()).isNetWorkAviliable()) {
                int maxAge = 0;
                // 有网络时 设置缓存超时时间0个小时 ,意思就是不读取缓存数据,只对get有用,post没有缓冲
                response.newBuilder()
                        .header("Cache-Control", "public, max-age=" + maxAge)
                        .removeHeader("Retrofit")// 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                        .build();
            } else {
                // 无网络时，设置超时为4周  只对get有用,post没有缓冲
                int maxStale = 60 * 60 * 24 * 28;
                response.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=" +
                                maxStale)
                        .removeHeader("nyn")
                        .build();
            }
            return response;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
