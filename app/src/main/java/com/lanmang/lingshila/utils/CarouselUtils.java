package com.lanmang.lingshila.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.lanmang.lingshila.bean.HomeTopBean;

import org.xutils.x;

import java.util.List;

/**
 * Created by lanmang on 2016/4/9.
 * 轮播图工具类
 */
public class CarouselUtils {

    /**
     * 轮播图自动切换
     */
    private static final int MESSAGE_PAGE_NEXT = 1;

    private ViewPager viewPager;
    private Context context;
    private List<HomeTopBean.DataEntity.TopicsEntity> topics;

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MESSAGE_PAGE_NEXT:
                    viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                    removeMessages(MESSAGE_PAGE_NEXT);
                    sendEmptyMessageDelayed(MESSAGE_PAGE_NEXT, 2000);
                    break;
            }
        }
    };

    public CarouselUtils(ViewPager viewPager, Context context) {
        this.viewPager = viewPager;
        this.context = context;
    }

    /**
     * 设置顶部轮播图数据
     */
    public void setViewPagerData(List<HomeTopBean.DataEntity.TopicsEntity> topics) {
        this.topics = topics;
        HomeTopViewPagerAdapter homeTopViewPagerAdapter = new HomeTopViewPagerAdapter();
        viewPager.setAdapter(homeTopViewPagerAdapter);
        viewPager.setCurrentItem(3000000 / topics.size() * topics.size());
        handler.sendEmptyMessageDelayed(MESSAGE_PAGE_NEXT, 2000);

        //设置顶部轮播图监听
        viewPager.addOnPageChangeListener(new MyOnPageChangeListener());
    }

    /**
     * 首页顶部轮播图状态改变监听
     */
    private class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

        }

        private boolean isDrag = false;

        @Override
        public void onPageScrollStateChanged(int state) {
            if (state == ViewPager.SCROLL_STATE_DRAGGING) {
                isDrag = true;
                handler.removeMessages(MESSAGE_PAGE_NEXT);
            } else if (state == ViewPager.SCROLL_STATE_IDLE && isDrag) {
                isDrag = false;
                handler.sendEmptyMessageDelayed(MESSAGE_PAGE_NEXT, 2000);
            }

        }
    }

    /**
     * Created by lanmang on 2016/4/8.
     */
    private class HomeTopViewPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return 6000000;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            position = position % topics.size();
            ImageView imageView;
            imageView = new ImageView(context);
            x.image().bind(imageView, topics.get(position).getImg().getImg_url());
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
