package com.lanmang.lingshila.application;

import android.app.Application;

import com.lanmang.lingshila.utils.Constants;

import org.xutils.x;

/**
 * Created by lanmang on 2016/4/8.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(Constants.isLog);
    }
}
