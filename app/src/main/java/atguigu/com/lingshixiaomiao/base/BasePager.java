package atguigu.com.lingshixiaomiao.base;

import android.app.Activity;
import android.view.View;

/**
 * Created by lanmang on 2016/4/7.
 * 基本页
 */
public abstract class BasePager {
    /**
     * 上下文
     */
    public Activity mActivity;
    /**
     * 子页面的视图
     */
    public View rootView;
    /**
     * 判断子页面的数据是否已经初始化
     */
    public boolean isInitData = false;

    /**
     * 构造方法
     * @param mActivity
     */
    public BasePager(Activity mActivity) {
        this.mActivity = mActivity;
        rootView = initView();
        if (!isInitData){ // 屏蔽二次加载数据
            isInitData = true;
            initData();

        }

        registerEventBus();

    }

    /**
     * 初始化子页面的视图
     *
     * @return
     */
    public abstract View initView();

    /**
     * 初始化子页面的数据,可以根据需要重写并调用
     */
    public void initData() {

    }

    /**
     * 注册EventBus
     */
    public void registerEventBus() {

    }

    /**
     * 解注册EventBus
     */
    public void unRegisterEventBus(){

    }

}
