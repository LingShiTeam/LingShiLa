package com.zxing.extra;

import android.content.Context;
import android.content.Intent;

import com.zxing.activity.CaptureActivity;

/**
 * Created by lanmang on 2016/4/21.
 */
public class Scan {

    private static class LazyHolder{
        public static Scan INSTANCE = new Scan();
    }

    private Scan(){

    }

    public static Scan getInstance(){
        return LazyHolder.INSTANCE;
    }

    public static void setScan(Context context, Start start) {

        //跳转到拍照界面扫描二维码
        Intent intent = new Intent(context, CaptureActivity.class);
        context.startActivity(intent);

        //start.getScanResult();
    }


    public static abstract class Start{
        public abstract void getScanResult(String scanResult);
    }
}
