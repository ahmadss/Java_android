package com.example.test.service;

import android.content.Context;

import com.example.test.model.User;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;


public class ConnectivityInterceptor implements Interceptor {
    private Context mContext;

    public ConnectivityInterceptor(Context context) {
        mContext = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        if (!NetworkUtil.isOnline(mContext)) {
            throw new NoConnectivityException();
        }

        Request.Builder builder = chain.request().newBuilder();
        builder.tag(new User());
        return chain.proceed(builder.build());
    }
}
