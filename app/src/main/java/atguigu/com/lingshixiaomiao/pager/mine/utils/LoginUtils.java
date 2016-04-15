package atguigu.com.lingshixiaomiao.pager.mine.utils;

import org.greenrobot.eventbus.EventBus;

import atguigu.com.lingshixiaomiao.LogUtils;

/**
 * Created by lanmang on 2016/4/12.
 * 登陆的工具类
 */
public class LoginUtils<T> {

    private T data;

    private boolean isLogin = false;

    /**
     * 懒汉式单例模式
     */
    private static class LazyHolder {
        private static final LoginUtils INSTANCE = new LoginUtils();
    }

    public static final LoginUtils getInstance() {
        return LazyHolder.INSTANCE;
    }

    /**
     * 登录成功,进入用户账户
     *
     * @param data
     */
    public void loginRequestSuccess(T data) {
        LogUtils.loge("登陆请求成功, 开始登录");
        this.data = data;
        LogUtils.loge("LoginUtils 工具类 获取的数据 = " + data.toString());
        setLogin(true);
    }

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean isLogin) {
        this.isLogin = isLogin;
        EventBus.getDefault().post(getInstance());
    }

    public T getData() {
        return data;
    }
}
