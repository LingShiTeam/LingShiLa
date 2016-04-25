package atguigu.com.lingshixiaomiao.application;

import com.umeng.socialize.UMShareAPI;

import atguigu.com.lingshixiaomiao.MainActivity;
import atguigu.com.lingshixiaomiao.pager.mine.bean.DefaultAddressBean;

/**
 * Created by Liu_haiwei on 2016/4/22.
 * 全局变量
 */
public class GlobalVariables {
    // 友盟第三方登录的shareapi
    public static UMShareAPI mShareAPI;
    // mainactivity
    public static MainActivity mainActivity;
    // uid
    public static String uid;
    //默认地址
    public static DefaultAddressBean defaultAddressBean;
}
