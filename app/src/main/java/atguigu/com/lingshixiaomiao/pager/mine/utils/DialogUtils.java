package atguigu.com.lingshixiaomiao.pager.mine.utils;

import android.app.AlertDialog;
import android.app.ProgressDialog;

import atguigu.com.lingshixiaomiao.application.MyApplication;

/**
 * Created by Liu_haiwei on 2016/4/23.
 * 提示框工具类
 */
public class DialogUtils {

    private static ProgressDialog dialog;

    /**
     * 提示小文本
     *
     * @param msg
     */
    public static void showDialog(String msg) {
        new AlertDialog.Builder(MyApplication.getContext())
                .setMessage(msg)
                .setPositiveButton("确定", null)
                .show();
    }

    /**
     * 开始加载Dialog
     */
    public static void startLogin() {
        dialog = new ProgressDialog(MyApplication.getContext());
        dialog.show();
    }

    /**
     * 停止加载Dialog
     */
    public static void endLogin(){
        if(dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

}
