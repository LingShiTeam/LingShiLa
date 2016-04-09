package com.lanmang.lingshila.pager;

import android.app.Activity;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lanmang.lingshila.R;
import com.lanmang.lingshila.base.BasePager;
import com.lanmang.lingshila.bean.HomeTopBean;
import com.lanmang.lingshila.utils.CarouselUtils;
import com.lanmang.lingshila.utils.JsonUtils;
import com.lanmang.lingshila.utils.LogUtils;
import com.lanmang.lingshila.utils.Url;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

/**
 * Created by lanmang on 2016/4/8.
 * 首页
 */
public class HomePager extends BasePager {



    private ImageButton ib_left_menu;
    private TextView tv_search;
    private RelativeLayout rl_cart;
    private LinearLayout lv_left_menu;
    private DrawerLayout dl_menu;
    private ViewPager vp_top_image;

    /**
     * 顶部数据
     */
    private HomeTopBean homeTopBean;

    public HomePager(Activity mActivity, DrawerLayout dl_menu) {
        super(mActivity);
        this.dl_menu = dl_menu;
    }

    @Override
    public View initView() {
        View inflate = View.inflate(mActivity, R.layout.home_pager, null);
        findViewById(inflate);
        setListener();
        return inflate;
    }

    /**
     * 设置监听
     */
    private void setListener() {
        //设置按钮点击弹出侧滑菜单
        ib_left_menu.setOnClickListener(new MyOnClickListener());

    }

    /**
     * 通过id获取view
     *
     * @param parent
     */
    private void findViewById(View parent) {
        ib_left_menu = (ImageButton) parent.findViewById(R.id.ib_left_menu);
        tv_search = (TextView) parent.findViewById(R.id.tv_search);
        rl_cart = (RelativeLayout) parent.findViewById(R.id.rl_cart);
        lv_left_menu = (LinearLayout) parent.findViewById(R.id.lv_left_menu);
        vp_top_image = (ViewPager) parent.findViewById(R.id.vp_top_image);
    }

    @Override
    public void initData() {
        super.initData();
        LogUtils.loge("首页 开始加载首页数据");
        //通过JsonUtils工具类解析url, 并通过EventBus返回数据
        new JsonUtils().loadData(Url.HOME_TOP_URL, HomeTopBean.class);
    }

    /**
     * 使用EventBus接收解析后的数据
     *
     * @param homeTopBean
     */
    @Subscribe
    public void onEventMainThread(HomeTopBean homeTopBean) {
        LogUtils.loge("数据解析成功 : " + homeTopBean.toString());
        this.homeTopBean = homeTopBean;
        List<HomeTopBean.DataEntity.TopicsEntity> topics = homeTopBean.getData().getTopics();
        //设置顶部轮播图
        new CarouselUtils(vp_top_image, mActivity).setViewPagerData(topics);
    }

    /**
     * 点击按钮弹出左侧菜单
     */
    private class MyOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            dl_menu.openDrawer(Gravity.LEFT);
        }
    }

    @Override
    public void registerEventBus() {
        //注册EventBus
        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this);
    }

    @Override
    public void unRegisterEventBus() {
        //解注册EventBus
        if (EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().unregister(this);
    }
}
