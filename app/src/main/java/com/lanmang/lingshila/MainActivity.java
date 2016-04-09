package com.lanmang.lingshila;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.ViewDragHelper;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.lanmang.lingshila.base.BasePager;
import com.lanmang.lingshila.pager.HomePager;
import com.lanmang.lingshila.pager.MinePager;
import com.lanmang.lingshila.pager.SalePager;
import com.lanmang.lingshila.pager.SubjectPager;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 主页面
 */
public class MainActivity extends FragmentActivity {

    private RadioGroup rg_main;
    private DrawerLayout dl_menu;



    private List<BasePager> pagers;

    /**
     * 当前页面的标记 首次进入主页面默认为0 范围:0~3
     */
    private int position = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById();
        loadPager();
        setListener();

        //设置初始化页面 首页
        rg_main.check(R.id.rb_main_home);
        //锁定左侧菜单
        dl_menu.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

    }

    /**
     * 设置监听
     */
    private void setListener() {
        //设置下面四个RadioButton点击切换页面
        rg_main.setOnCheckedChangeListener(new MyOnCheckedChangeListener());
        //设置侧滑菜单监听
        dl_menu.setDrawerListener(new MyDrawListener());
    }

    /**
     * 加载四个页面: 首页 特卖 专题 我的小喵
     */
    private void loadPager() {
        pagers = new ArrayList<>();
        pagers.add(new HomePager(this, dl_menu));
        pagers.add(new SalePager(this));
        pagers.add(new SubjectPager(this));
        pagers.add(new MinePager(this));
    }

    /**
     * 通过id来获得view的对象
     */
    private void findViewById() {
        rg_main = (RadioGroup) findViewById(R.id.rg_main);
        dl_menu = (DrawerLayout) findViewById(R.id.dl_menu);

      //  setDrawerLeftEdgeSize(this, dl_menu, 0.1f);

    }

    /**
     * 利用反射修改侧滑菜单的范围 不确定是否有效
     * @param activity
     * @param drawerLayout
     * @param displayWidthPercentage
     */
    public void setDrawerLeftEdgeSize(Activity activity, DrawerLayout drawerLayout, float displayWidthPercentage) {
        if (activity == null || drawerLayout == null) return;
        try {
            Field leftDraggerField = drawerLayout.getClass().getDeclaredField("mLeftDragger");
            leftDraggerField.setAccessible(true);
            ViewDragHelper leftDragger = (ViewDragHelper) leftDraggerField.get(drawerLayout);
            Field edgeSizeField = leftDragger.getClass().getDeclaredField("mEdgeSize");
            edgeSizeField.setAccessible(true);
            int edgeSize = edgeSizeField.getInt(leftDragger);
            DisplayMetrics dm = new DisplayMetrics();
            activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
            edgeSizeField.setInt(leftDragger, Math.max(edgeSize, (int) (dm.widthPixels * displayWidthPercentage)));
        } catch (Exception e) {
        }
    }

    /**
     * 设置下面四个RadioButton点击切换页面
     */
    private class MyOnCheckedChangeListener implements RadioGroup.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId) {
                case R.id.rb_main_home:
                    position = 0;
                    break;
                case R.id.rb_main_sale:
                    position = 1;
                    break;
                case R.id.rb_main_subject:
                    position = 2;
                    break;
                case R.id.rb_main_mine:
                    position = 3;
                    break;
            }
            setCurrentPage();
        }
    }

    /**
     * 根据position设置当前页面
     */
    private void setCurrentPage() {
        FragmentManager sfm = getSupportFragmentManager();
        FragmentTransaction ft = sfm.beginTransaction();
        ft.replace(R.id.fl_main_pager, new Fragment(){
            @Nullable
            @Override
            public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
                View pagerView = getPagerView();
                return pagerView;
            }
        }).commit();
    }

    /**
     * 根据position得到当前页面的View
     * @return
     */
    private View getPagerView() {
        BasePager pager = pagers.get(position);
        return pager.rootView;
    }

    /**
     * 设置侧滑菜单监听
     */
    private class MyDrawListener implements DrawerLayout.DrawerListener {
        @Override
        public void onDrawerSlide(View drawerView, float slideOffset) {

        }

        @Override
        public void onDrawerOpened(View drawerView) {
            dl_menu.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        }

        @Override
        public void onDrawerClosed(View drawerView) {
            dl_menu.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        }

        @Override
        public void onDrawerStateChanged(int newState) {

        }
    }

    @Override
    public void onDestroy() {
        //解注册EventBus
        for(int i = 0; i < pagers.size(); i++) {
            BasePager pager = pagers.get(i);
            pager.unRegisterEventBus();
        }
        super.onDestroy();
    }
}