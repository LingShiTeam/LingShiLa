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
import android.widget.TextView;

import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import atguigu.com.lingshixiaomiao.R;
import atguigu.com.lingshixiaomiao.pager.home.bean.HomeBean;

/**
 * 轮播图工具类(轮播图自动切换)
 */
public class CarouselUtilsHome {

    private static final int MESSAGE_PAGE_NEXT = 1;
    private LinearLayout ll_top_points;
    private ImageOptions imageOption;
    private ViewPager viewPager;
    private Context context;

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
    private TextView home_item_title_left;
    private TextView home_item_title_right;
    private HomeBean homeBean;
    private List<HomeBean.DataEntity.TopicsEntity> topics;

    /**
     * 构造方法
     *
     * @param headerView
     * @param context
     */
    public CarouselUtilsHome(View headerView, Context context) {
        this.context = context;
        // headview子控件
        initHeadView(headerView);
        imageOption = new ImageOptions.Builder()
                //.setImageScaleType(ImageView.ScaleType.FIT_START)//等比例缩小到充满长/宽居中显示, 或原样显示
                .setLoadingDrawableId(R.drawable.default_home_banner_640_270)
                .setFailureDrawableId(R.drawable.default_home_banner_640_270)
                .setConfig(Bitmap.Config.ARGB_4444)
                .build();

        //setData();
    }

    /**
     * headview子控件
     *
     * @param headerView
     */
    private void initHeadView(View headerView) {
        // 轮播图
        viewPager = (ViewPager) headerView.findViewById(R.id.vp_top_image);
        ll_top_points = (LinearLayout) headerView.findViewById(R.id.ll_top_points);
        home_item_title_left = (TextView) headerView.findViewById(R.id.home_item_title_left);
        home_item_title_right = (TextView) headerView.findViewById(R.id.home_item_title_right);
    }

    /**
     * 设置顶部轮播图数据
     */
    public void setViewPagerData(HomeBean homeBean) {
        this.homeBean = homeBean;
        topics = this.homeBean.getData().getTopics();
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

    private boolean flag = true;

    /**
     * viewpager的适配器
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
            // 轮播图
            HomeBean.DataEntity.TopicsEntity topicsEntity = topics.get(position);
            x.image().bind(imageView, topicsEntity.getImg().getImg_url(), imageOption);
            home_item_title_left.setText(homeBean.getData().getBrands_title_big());
            home_item_title_right.setText(homeBean.getData().getBrands_title_sml());

            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

}
