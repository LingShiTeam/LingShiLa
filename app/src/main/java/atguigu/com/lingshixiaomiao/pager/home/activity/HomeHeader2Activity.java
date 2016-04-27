package atguigu.com.lingshixiaomiao.pager.home.activity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.xutils.x;

import atguigu.com.lingshixiaomiao.LogUtils;
import atguigu.com.lingshixiaomiao.R;
import atguigu.com.lingshixiaomiao.pager.home.adapter.HomeHeaderAdapter2;
import atguigu.com.lingshixiaomiao.pager.home.bean.HomeHeader2Bean;
import atguigu.com.lingshixiaomiao.pager.home.utils.DataUtils;
import atguigu.com.lingshixiaomiao.pager.home.utils.JsonUtils;
import atguigu.com.lingshixiaomiao.pager.home.utils.Url;
import atguigu.com.lingshixiaomiao.pager.home.view.HeaderGridView;
import atguigu.com.lingshixiaomiao.pager.mine.utils.CartUtils;
import atguigu.com.lingshixiaomiao.pager.scale.activity.SnackInfomationActivity;
import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/**
 * home第二个header的页面
 */
public class HomeHeader2Activity extends SwipeBackActivity implements View.OnClickListener {

    private RelativeLayout loading_dialog;
    private ImageView widget_loading_pb;
    private AnimationDrawable ad;
    private ImageButton ib_left_menu;
    private ImageView iv_search_back;
    private ImageView iv_cart;
    private TextView tv_shopcount;
    private HomeHeader2Bean homeHeader2Bean;
    private ImageView item_home_promotion_img;
    private TextView item_home_promotion_desc;
    private TextView item_home_promotion_tag_time;
    private HomeHeaderAdapter2 adapter;
    private HeaderGridView grid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_header2);

        registerEventBus();
        initView();
        initData();
        setListener();
        // 侧滑退出activity
        setSwipeBack();
    }

    /**
     * 设置监听
     */
    private void setListener() {
        iv_search_back.setOnClickListener(this);
        grid.setOnItemClickListener(new MyOnItemClickListener());
    }

    /**
     * gridview每一项item的点击监听
     */
    class MyOnItemClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(HomeHeader2Activity.this, SnackInfomationActivity.class);
            intent.putExtra("snack_id", homeHeader2Bean.getData().getGoodses().get(position).getId());
            startActivity(intent);
        }
    }

    /**
     * 初始化数据
     */
    private void initData() {
        startLoading();
        new JsonUtils().loadData(Url.HOMEHEADERTWO, HomeHeader2Bean.class);
    }

    /**
     * 开始加载
     */
    private void startLoading() {
        loading_dialog.setVisibility(View.VISIBLE);
        grid.setVisibility(View.GONE);
        ad.start();
    }

    /**
     * 使用EventBus接收解析后的数据-ShoppingListBean
     *
     * @param homeHeader2Bean
     */
    @Subscribe
    public void onEventMainThread(HomeHeader2Bean homeHeader2Bean) {
        this.homeHeader2Bean = homeHeader2Bean;
        LogUtils.loge("TAG", "HomeHeader2Bean数据请求成功 : " + homeHeader2Bean.toString());
        if (homeHeader2Bean != null) {
            initGridView();
        }
        stopLoading();
        unRegisterEventBus();
    }

    /**
     * gridview的初始化
     */
    private void initGridView() {
        View view = View.inflate(this, R.layout.home_header2_gridview, null);
        findHeaderBView(view);
        setHeaderViewData();
        if (adapter == null) {
            adapter = new HomeHeaderAdapter2(this, homeHeader2Bean);
        }
        grid.addHeaderView(view);
        grid.setAdapter(adapter);
    }

    /**
     * 初始化头部视图数据
     */
    private void setHeaderViewData() {
        x.image().bind(item_home_promotion_img, homeHeader2Bean.getData().getBrand().getImg().getImg_url());
        item_home_promotion_desc.setText(homeHeader2Bean.getData().getBrand().getTitle());
        // 倒计时
        new CountDownTimer(homeHeader2Bean.getData().getBrand().getTime() * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                long lsatTime = homeHeader2Bean.getData().getBrand().getTime() - System.currentTimeMillis() / 1000;
                item_home_promotion_tag_time.setText("剩余时间:" + DataUtils.formatTime(lsatTime));
            }

            @Override
            public void onFinish() {
            }
        }.start();
    }

    /**
     * 初始化头布局
     *
     * @param view
     */
    private void findHeaderBView(View view) {
        item_home_promotion_img = (ImageView) view.findViewById(R.id.item_home_promotion_img);
        item_home_promotion_desc = (TextView) view.findViewById(R.id.item_home_promotion_desc);
        item_home_promotion_tag_time = (TextView) view.findViewById(R.id.item_home_promotion_tag_time);
    }

    /**
     * 停止加载
     */
    private void stopLoading() {
        ad.stop();
        loading_dialog.setVisibility(View.GONE);
        grid.setVisibility(View.VISIBLE);
    }

    /**
     * 侧滑退出activity
     */
    private void setSwipeBack() {
        SwipeBackLayout swipeBackLayout = getSwipeBackLayout();
        swipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);
    }

    /**
     * 初始化视图
     */
    private void initView() {
        grid = (HeaderGridView) findViewById(R.id.grid);

        loading_dialog = (RelativeLayout) findViewById(R.id.loading_dialog);
        widget_loading_pb = (ImageView) findViewById(R.id.widget_loading_pb);
        ad = (AnimationDrawable) widget_loading_pb.getBackground();

        ib_left_menu = (ImageButton) findViewById(R.id.ib_left_menu);
        iv_search_back = (ImageView) findViewById(R.id.iv_search_back);
        EditText et_search = (EditText) findViewById(R.id.et_search);
        TextView tv_shopname = (TextView) findViewById(R.id.tv_shopname);
        iv_cart = (ImageView) findViewById(R.id.iv_cart);
        tv_shopcount = (TextView) findViewById(R.id.tv_shopcount);

        ib_left_menu.setVisibility(View.GONE);
        iv_search_back.setVisibility(View.VISIBLE);
        et_search.setVisibility(View.GONE);
        tv_shopname.setVisibility(View.VISIBLE);
        tv_shopname.setText("坚果大本营");

        if(CartUtils.getInstance().getGoodsNum() != 0) {
            tv_shopcount.setVisibility(View.VISIBLE);
            tv_shopcount.setText(CartUtils.getInstance().getGoodsNum() + "");
        } else {
            tv_shopcount.setVisibility(View.GONE);
        }
    }

    /**
     * 注册EventBus
     */
    public void registerEventBus() {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    /**
     * 解注册EventBus
     */
    public void unRegisterEventBus() {
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_search_back:
                finish();
                break;
        }
    }

}
