package atguigu.com.lingshixiaomiao.pager.mine.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import atguigu.com.lingshixiaomiao.R;
import atguigu.com.lingshixiaomiao.pager.mine.base.ContentBasePager;
import atguigu.com.lingshixiaomiao.pager.mine.pager.AboutPager;
import atguigu.com.lingshixiaomiao.pager.mine.pager.AddressPager;
import atguigu.com.lingshixiaomiao.pager.mine.pager.PushPager;
import atguigu.com.lingshixiaomiao.pager.mine.pager.SettingPager;
import atguigu.com.lingshixiaomiao.pager.mine.pager.UserPager;
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
        switch (position) {
            case 0 :
                pager = new SettingPager(this);
                break;
            case 11:
                pager = new AboutPager(this);
                break;
            case 12:
                pager = new AddressPager(this);
            break;
            case 13:
                pager = new UserPager(this);
                break;
            case 2:
                Bundle bundle = getIntent().getExtras();
                pager = new PushPager(this, bundle);
                break;
        }
        loadViews();
    }

    private void loadViews() {
        tv_mine_title.setText(pager.title);
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

        ib_mine_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ib_mine_back:
                //侧滑返回上一个页面
                getSwipeBackLayout().scrollToFinishActivity();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
