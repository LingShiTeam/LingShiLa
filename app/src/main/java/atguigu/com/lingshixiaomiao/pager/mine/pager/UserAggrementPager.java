package atguigu.com.lingshixiaomiao.pager.mine.pager;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import atguigu.com.lingshixiaomiao.R;
import atguigu.com.lingshixiaomiao.pager.mine.base.ContentBasePager;

/**
 * Created by lanmang on 2016/4/10.
 */
public class UserAggrementPager extends ContentBasePager {

    private TextView tv_useraggrement;

    /**
     * 构造方法
     *
     * @param mActivity
     * @param bundle
     */
    public UserAggrementPager(Activity mActivity, Bundle bundle) {
        super(mActivity);
        String useraggrement = bundle.getString("useraggrement");
        tv_useraggrement.setText(useraggrement);
    }

    @Override
    public View initView() {
        View inflate = View.inflate(mActivity, R.layout.mine_useraggrement, null);
        findViewById(inflate);
        return inflate;
    }

    private void findViewById(View v) {
        tv_useraggrement = (TextView)v.findViewById(R.id.tv_useraggrement);
    }

    @Override
    public String setTitle() {
        return "用户协议";
    }

    @Override
    public void initData() {
        super.initData();

    }

}
