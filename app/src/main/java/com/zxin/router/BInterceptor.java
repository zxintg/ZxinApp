package com.zxin.router;

import android.content.Context;
import android.widget.Toast;

import com.zxin.router.annotation.Interceptor;


/**
 * Created by zxin on 2018/5/18.
 */
@Interceptor("BInterceptor")
public class BInterceptor implements RouteInterceptor {
    @Override
    public boolean intercept(Object source, RouteRequest routeRequest) {
        Toast.makeText((Context) source, String.format("Intercepted: {uri: %s, interceptor: %s}",
                routeRequest.getUri().toString(), BInterceptor.class.getName()),
                Toast.LENGTH_LONG).show();
        return true;
    }
}