package atguigu.com.lingshixiaomiao.pager.mine.base;

import android.app.Activity;
import android.view.View;

import atguigu.com.lingshixiaomiao.base.BasePager;

/**
 * Created by lanmang on 2016/4/10.
 */
public abstract class ContentBasePager extends BasePager {
    /**
     * 构造方法
     *
     * @param mActivity
     */

    public String title;

    public ContentBasePager(Activity mActivity) {
        super(mActivity);
        title = setTitle();
    }

    @Override
    public abstract View initView();

    public abstract String setTitle();

    @Override
    public void initData() {
        super.initData();
    }
}
