package atguigu.com.lingshixiaomiao.pager.home.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import atguigu.com.lingshixiaomiao.R;
import atguigu.com.lingshixiaomiao.pager.home.bean.HomeTopBean;

/**
 * 轮播图工具类(轮播图自动切换)
 */
public class CarouselUtils {

    private static final int MESSAGE_PAGE_NEXT = 1;
    private final LinearLayout ll_top_points;
    private ImageOptions imageOption;
    private ViewPager viewPager;
    private Context context;
    private List<HomeTopBean.DataEntity.TopicsEntity> topics;

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MESSAGE_PAGE_NEXT:
                    viewPager.setCurrentItem(viewPager.getCurrentItem() + 1, true);
                    //removeMessages(MESSAGE_PAGE_NEXT);
                    handler.removeCallbacksAndMessages(null);
                    sendEmptyMessageDelayed(MESSAGE_PAGE_NEXT, 3000);
                    break;
            }
        }
    };
    // 顶部小圆点
    private List<ImageView> topPoints;

    /**
     * 构造方法
     *
     * @param viewPager
     * @param ll_top_points
     * @param context
     */
    public CarouselUtils(ViewPager viewPager, LinearLayout ll_top_points, Context context) {
        this.viewPager = viewPager;
        this.context = context;
        this.ll_top_points = ll_top_points;
        imageOption = new ImageOptions.Builder()
                //.setImageScaleType(ImageView.ScaleType.FIT_START)//等比例缩小到充满长/宽居中显示, 或原样显示
                .setLoadingDrawableId(R.drawable.default_home_banner_640_270)
                .setFailureDrawableId(R.drawable.default_home_banner_640_270)
                .setConfig(Bitmap.Config.ARGB_4444)
                .build();
    }

    /**
     * 设置顶部轮播图数据
     */
    public void setViewPagerData(List<HomeTopBean.DataEntity.TopicsEntity> topics) {
        this.topics = topics;
        HomeTopViewPagerAdapter homeTopViewPagerAdapter = new HomeTopViewPagerAdapter();
        viewPager.setAdapter(homeTopViewPagerAdapter);

        // 设置顶部小圆点
        setHeadPoint();

        //设置顶部轮播图监听
        viewPager.addOnPageChangeListener(new MyOnPageChangeListener());

        viewPager.setCurrentItem(3000 * topics.size() % topPoints.size());
        handler.sendEmptyMessageDelayed(MESSAGE_PAGE_NEXT, 3000);

    }

    /**
     * 设置顶部小圆点
     */
    private void setHeadPoint() {
        topPoints = new ArrayList<>();
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.leftMargin = 8;
        params.rightMargin = 8;
        ll_top_points.removeAllViews();
        for (int i = 0; i < topics.size(); i++) {
            ImageView imageView = new ImageView(context);
            imageView.setBackgroundResource(R.drawable.top_point_selector);
            topPoints.add(imageView);
            // 将第一个原点的状态设置false
            if (i == 0) {
                imageView.setEnabled(false);
            }
            ll_top_points.addView(imageView, params);
        }
    }

    /**
     * 首页顶部轮播图状态改变监听
     */
    private class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        int preIndex = viewPager.getCurrentItem();

        @Override
        public void onPageSelected(int position) {
            int index = position % topPoints.size();
            topPoints.get(index).setEnabled(false);
            topPoints.get(preIndex % topPoints.size()).setEnabled(true);
            preIndex = position;
        }

        // 判断是否为拖拽状态
        // SCROLL_STATE_IDLE, SCROLL_STATE_DRAGGING, SCROLL_STATE_SETTLING
        boolean isDrag = false;

        @Override
        public void onPageScrollStateChanged(int state) {
            if (state == ViewPager.SCROLL_STATE_DRAGGING) {
                isDrag = true;
                if (handler != null) {
                    handler.removeMessages(MESSAGE_PAGE_NEXT);
                }
            } else if (state == ViewPager.SCROLL_STATE_IDLE && isDrag) {
                isDrag = false;
                if (handler != null) {
                    handler.sendEmptyMessageDelayed(MESSAGE_PAGE_NEXT, 3000);
                }
            }

        }
    }

    /**
     * Created by lanmang on 2016/4/8.
     */
    private class HomeTopViewPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            position = position % topics.size();
            ImageView imageView = new ImageView(context);
            x.image().bind(imageView, topics.get(position).getImg().getImg_url(), imageOption);
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
