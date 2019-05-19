package com.zxin.basemodel.interceptor;

import android.content.Context;
import com.zxin.network.interceptor.ZxinBaseInterceptor;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Administrator on 2018/3/24.
 */

public class HttpHeaderInterceptor extends ZxinBaseInterceptor {

    private Map<String, String> mHeaderParamsMap = new HashMap<>();

    public HttpHeaderInterceptor(Context mContext) {
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
        // 添加新的参数，添加到url 中
     /* HttpUrl.Builder authorizedUrlBuilder = oldRequest.url()                .newBuilder()
         .scheme(oldRequest.url().scheme())
             .host(oldRequest.url().host());*/
        // 新的请求
        Request.Builder builder = request.newBuilder();
        builder.method(request.method(),
                request.body());

        //添加公共参数,添加到header中
        if (mHeaderParamsMap.size() > 0) {
            for (Map.Entry<String, String> params : mHeaderParamsMap.entrySet()) {
                builder.header(params.getKey(), params.getValue());
            }
        }
        return chain.proceed(builder.build());
    }

}
