package atguigu.com.lingshixiaomiao.pager.home;

import android.content.Context;
import android.view.View;

/**
 * Created by Liu_haiwei on 2016/4/17.
 * 左侧菜单详情页面的基类
 */
public abstract class TabBasePager {
    public Context mContent;
    public View rootView;
    public boolean isInit = false;

    public TabBasePager(Context mContent) {
        this.mContent = mContent;
        rootView = initView();
    }

    /**
     * 初始化视图
     * @return
     */
    public abstract View initView();

    /**
     * 初始化数据
     */
    public void initData() {
    }
}
