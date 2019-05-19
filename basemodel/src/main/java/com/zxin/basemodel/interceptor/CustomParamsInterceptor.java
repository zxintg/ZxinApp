package com.zxin.basemodel.interceptor;

import android.content.Context;
import android.util.ArrayMap;
import com.google.gson.Gson;
import com.zxin.network.interceptor.ZxinBaseInterceptor;

import java.io.IOException;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Administrator on 2018/3/24.
 */

public class CustomParamsInterceptor extends ZxinBaseInterceptor {
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    public CustomParamsInterceptor(Context mContext) {
        super(mContext);
    }

    @Override
    public HttpUrl.Builder interceptRequestByGet(HttpUrl.Builder builder) {
        return builder;
    }

    @Override
    public FormBody.Builder interceptRequestByPost(RequestBody requestBody) throws IOException {
        return null;
    }

    @Override
    public Response interceptResponse(Chain chain, Request request) throws IOException {
        ArrayMap paramsMap = new ArrayMap();
        paramsMap.put("version", "1.0");
        paramsMap.put("token", "");
        paramsMap.put("device", "Android");
        FormBody oldBody = (FormBody) request.body();
        for (int i = 0; i < oldBody.size(); i++) {
            paramsMap.put(oldBody.encodedName(i), oldBody.encodedValue(i));
        }
        return chain.proceed(request.newBuilder().post(RequestBody.create(JSON, new Gson().toJson(paramsMap))).build());
    }

}
