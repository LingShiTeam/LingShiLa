package atguigu.com.lingshixiaomiao.pager.mine.utils;

import android.app.Activity;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * Created by Liu_haiwei on 2016/4/21.
 * 分享工具类
 */
public class ShareUtils {
    public static void showShare(Activity mActivity) {

        ShareSDK.initSDK(mActivity);
        OnekeyShare oks = new OnekeyShare();
        // 分享时Notification的图标和文字
        // oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        oks.setText(mActivity.getIntent().getStringExtra("snack_title"));
        oks.setImageUrl(mActivity.getIntent().getStringExtra("image_url"));
        // 启动分享GUI
        oks.show(mActivity);
    }
}
