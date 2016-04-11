package atguigu.com.lingshixiaomiao.pager.mine.pager;

import android.app.Activity;
import android.view.View;

import atguigu.com.lingshixiaomiao.R;
import atguigu.com.lingshixiaomiao.pager.mine.base.ContentBasePager;

/**
 * Created by lanmang on 2016/4/10.
 */
public class AboutPager extends ContentBasePager {

    /**
     * 构造方法
     *
     * @param mActivity
     */
    public AboutPager(Activity mActivity) {
        super(mActivity);
    }

    @Override
    public View initView() {
        View inflate = View.inflate(mActivity, R.layout.mine_setting_about, null);
        findViewById(inflate);
        return inflate;
    }

    private void findViewById(View v) {

    }

    @Override
    public String setTitle() {
        return "关于零食小喵";
    }

    @Override
    public void initData() {
        super.initData();

    }

}
