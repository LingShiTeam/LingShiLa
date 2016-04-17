package atguigu.com.lingshixiaomiao.pager.mine.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import atguigu.com.lingshixiaomiao.LogUtils;
import atguigu.com.lingshixiaomiao.R;
import atguigu.com.lingshixiaomiao.pager.mine.base.ContentBasePager;
import atguigu.com.lingshixiaomiao.pager.mine.pager.AboutPager;
import atguigu.com.lingshixiaomiao.pager.mine.pager.AddressPager;
import atguigu.com.lingshixiaomiao.pager.mine.pager.EditAddressPager;
import atguigu.com.lingshixiaomiao.pager.mine.pager.LoginPager;
import atguigu.com.lingshixiaomiao.pager.mine.pager.ResetHeaderPager;
import atguigu.com.lingshixiaomiao.pager.mine.pager.ResetNicknamePager;
import atguigu.com.lingshixiaomiao.pager.mine.pager.SettingPager;
import atguigu.com.lingshixiaomiao.pager.mine.pager.UserPager;
import atguigu.com.lingshixiaomiao.pager.mine.pager.WebPager;
import atguigu.com.lingshixiaomiao.pager.mine.utils.Constants;
import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/**
 * 我的小喵页面中的那些侧滑退出页面
 */
public class MineContentActivity extends SwipeBackActivity implements View.OnClickListener {

    private ImageButton ib_mine_back;
    private TextView tv_mine_title;
    private FrameLayout fl_mine_view;
    private ContentBasePager pager;
    private TextView tv_mine_title_complete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_content);
        findViewById();
        loadPagerView();
        //设置侧滑退出activity
        setSwipeBack();
    }

    private void loadPagerView() {
        int position = getIntent().getIntExtra("pager", 0);
        Bundle bundle = getIntent().getExtras();
        switch (position) {
            case Constants.SETTING_PAGER:
                pager = new SettingPager(this);//设置界面
                break;
            case Constants.ABOUT_PAGER:
                pager = new AboutPager(this);//关于零食小喵界面
                break;
            case Constants.ADDRESS_PAGER:
                pager = new AddressPager(this);//管理收货地址界面
                tv_mine_title_complete.setVisibility(View.VISIBLE);
                break;
            case Constants.USER_PAGER:
                pager = new UserPager(this);//用户信息界面
                break;
            case Constants.PUSH_PAGER:
                pager = new WebPager(this, bundle, true);//推送界面
                break;
            case Constants.LOGIN_PAGER:
                pager = new LoginPager(this);//登录界面
                break;
            case Constants.CHANGE_HEADER_PAGER:
                pager = new ResetHeaderPager(this);//修改头像界面
                tv_mine_title_complete.setVisibility(View.VISIBLE);
                break;
            case Constants.CHANGE_NICKNAME_PAGER:
                pager = new ResetNicknamePager(this);//修改昵称界面
                break;
            case Constants.WEBVIEW_PAGER:
                pager = new WebPager(this, bundle, false);//普通界面
                break;
            case Constants.EDITADDRESS_PAGER:
                tv_mine_title_complete.setVisibility(View.VISIBLE);
                boolean isEdit = getIntent().getBooleanExtra("edit", false);
                pager = new EditAddressPager(this, bundle, isEdit);//编辑地址页面
                break;
        }
        loadViews();
    }

    private void loadViews() {
        tv_mine_title.setText(pager.title);
        tv_mine_title_complete.setText(pager.complete);
        fl_mine_view.removeAllViews();
        fl_mine_view.addView(pager.rootView);
    }

    /**
     * 侧滑退出activity
     */
    private void setSwipeBack() {
        SwipeBackLayout swipeBackLayout = getSwipeBackLayout();
        swipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);
    }

    private void findViewById() {
        ib_mine_back = (ImageButton) findViewById(R.id.ib_mine_back);
        tv_mine_title = (TextView) findViewById(R.id.tv_mine_title);
        fl_mine_view = (FrameLayout) findViewById(R.id.fl_mine_view);
        tv_mine_title_complete = (TextView) findViewById(R.id.tv_mine_title_complete);

        ib_mine_back.setOnClickListener(this);
        tv_mine_title_complete.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ib_mine_back:
                //侧滑返回上一个页面
                getSwipeBackLayout().scrollToFinishActivity();
                break;
            case R.id.tv_mine_title_complete:
                //完成
                pager.complete();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //处理扫描结果（在界面上显示）
        if (resultCode == Constants.RESULT_OK) {
            Bundle bundle = data.getExtras();
            String scanResult = bundle.getString("result");
            LogUtils.loge("scanResult = " + scanResult);

            Intent intent = new Intent(this, MineContentActivity.class);
            bundle = new Bundle();
            bundle.putString("title", "扫描结果");
            bundle.putString("url", scanResult);
            intent.putExtras(bundle);
            intent.putExtra("pager", Constants.WEBVIEW_PAGER);
            startActivity(intent);

        }
    }
}
