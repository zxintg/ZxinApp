package com.zxin.basemodel.interceptor;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.zxin.network.interceptor.ZxinBaseInterceptor;
import com.zxin.network.util.NetworkUtil;
import java.io.IOException;
import okhttp3.CacheControl;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Administrator on 2018/3/24.
 */

public class HttpCacheInterceptor extends ZxinBaseInterceptor {

    public HttpCacheInterceptor(Context mContext) {
        super(mContext);
    }

    @Override
    public HttpUrl.Builder interceptRequestByGet(HttpUrl.Builder builder) throws IOException {
        return null;
    }

    @Override
    public FormBody.Builder interceptRequestByPost(RequestBody requestBody) throws IOException {
        return null;
    }

    @Override
    public Response interceptResponse(Chain chain, Request request) throws IOException {
        //有网的时候,读接口上的@Headers里的注解配置
        String cacheControl = request.cacheControl().toString();
        //没有网络并且添加了注解,才使用缓存.
        if (!NetworkUtil.getInstance(getContext()).isNetWorkAviliable() && !TextUtils.isEmpty(cacheControl)) {
            //重置请求体;
            request = request.newBuilder()
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build();
        }

        //如果没有添加注解,则不缓存
        if (TextUtils.isEmpty(cacheControl) || "no-store".contains(cacheControl)) {
            //响应头设置成无缓存
            cacheControl = "no-store";
        } else if (NetworkUtil.getInstance(getContext()).isNetWorkAviliable()) {
            //如果有网络,则将缓存的过期事件,设置为0,获取最新数据
            cacheControl = "public, max-age=" + 0;
        } else {
            //...如果无网络,则根据@headers注解的设置进行缓存.
            // 无网络时，设置缓存过期超时时间为4周
//            int maxStale = 60 * 60 * 24 * 28;
//            cacheControl = "public, only-if-cached, max-stale=" + maxStale;
        }
        Response response = chain.proceed(request);
        Log.d("httpInterceptor", cacheControl);
        return response.newBuilder()
                .header("Cache-Control", cacheControl)
                .removeHeader("Pragma")
                .build();
//        // 无网络时，始终使用本地Cache
//        if (!isValidNetWork()) {
//            request = request.newBuilder()
//                    .cacheControl(CacheControl.FORCE_CACHE)
//                    .build();
//        }
//
//        Response response = chain.proceed(request);
//        if (isValidNetWork()) {
//            // 有网络时，设置缓存过期时间0个小时
//            int maxAge = 0;
//            response.newBuilder()
//                    .header("Cache-Control", "public, max-age=" + maxAge)
//                    .removeHeader("Pragma") // 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
//                    .build();
//        } else {
//
//            response.newBuilder()
//                    .header("Cache-Control", )
//                    .removeHeader("Pragma")
//                    .build();
//        }
//        return response;
    }
}