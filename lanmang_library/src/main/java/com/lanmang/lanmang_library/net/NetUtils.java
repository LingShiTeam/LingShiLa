package com.lanmang.lanmang_library.net;

import com.alibaba.fastjson.JSON;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by lanmang on 2016/4/24.
 */
public class NetUtils {

    private OkHttpClient okHttpClient;
    private String url;
    private CallBack callBack;
    private ResponseBody body;
    private Class aClass;

    private NetUtils() {
        okHttpClient = new OkHttpClient();
    }

    private static NetUtils getInstance() {
        return LazyHolder.INSTANCE;
    }

    private static class LazyHolder {
        public static NetUtils INSTANCE = new NetUtils();
    }

    public static void linkForJson(String url, Class aClass, CallBack callBack) {
        getInstance().aClass = aClass;
        getInstance().callBack = callBack;
        getInstance().url = url;
        getInstance().start();
    }

    private void start() {
        Request.Builder builder = new Request.Builder();
        builder.url(url);
        Request request = builder.build();

        try {
            Response response = okHttpClient.newCall(request).execute();

            if (response.isSuccessful()) {
                body = response.body();
                parseJson();
            } else {
                callBack.onError("request fail, Unexpected code = " + response);
            }

        } catch (IOException e) {
            e.printStackTrace();
            callBack.onError("IOException");
        }
    }

    private void parseJson() {
        try {
            String json = body.string();
            Object object = JSON.parseObject(json, aClass);
            callBack.onSuccess(object);
        } catch (Exception e) {
            e.printStackTrace();
            callBack.onError("parseJson with Exception");
        }
    }

}
