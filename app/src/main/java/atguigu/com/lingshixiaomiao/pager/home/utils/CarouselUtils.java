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
import atguigu.com.lingshixiaomiao.pager.home.bean.HomeTopBean;

/**
 * 轮播图工具类(轮播图自动切换)
 */
public class CarouselUtils {

    private static final int MESSAGE_PAGE_NEXT = 1;
    private LinearLayout ll_top_points;
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
    private TextView home_item_title_left;
    private TextView home_item_title_right;
    private HomeTopBean homeTopBean;
    // 第一个
    ImageView item_home_promotion_img;
    ImageView item_goods_empty;
    TextView item_home_promotion_discount;
    TextView item_home_promotion_desc;
    TextView item_home_promotion_tag_time;
    // 第二个
    ImageView btn_lover;
    ImageView btn_movie;
    ImageView btn_tea;
    ImageView btn_beer;

    TextView home_item_title_left2;
    TextView home_item_title_right2;

    /**
     * 构造方法
     * @param headerView
     * @param context
     */
    public CarouselUtils(View headerView, Context context) {
        this.context = context;
        // headview子控件
        initHeadView(headerView);

        imageOption = new ImageOptions.Builder()
                //.setImageScaleType(ImageView.ScaleType.FIT_START)//等比例缩小到充满长/宽居中显示, 或原样显示
                .setLoadingDrawableId(R.drawable.default_home_banner_640_270)
                .setFailureDrawableId(R.drawable.default_home_banner_640_270)
                .setConfig(Bitmap.Config.ARGB_4444)
                .build();
    }

    /**
     * headview子控件
     * @param headerView
     */
    private void initHeadView(View headerView) {
        // 轮播图
        viewPager = (ViewPager) headerView.findViewById(R.id.vp_top_image);
        ll_top_points = (LinearLayout) headerView.findViewById(R.id.ll_top_points);
        home_item_title_left = (TextView) headerView.findViewById(R.id.home_item_title_left);
        home_item_title_right = (TextView) headerView.findViewById(R.id.home_item_title_right);
        // 第一个视图
        item_home_promotion_img = (ImageView) headerView.findViewById(R.id.item_home_promotion_img);
        item_goods_empty = (ImageView) headerView.findViewById(R.id.item_goods_empty);
        item_home_promotion_discount = (TextView) headerView.findViewById(R.id.item_home_promotion_discount);
        item_home_promotion_desc = (TextView) headerView.findViewById(R.id.item_home_promotion_desc);
        item_home_promotion_tag_time = (TextView) headerView.findViewById(R.id.item_home_promotion_tag_time);
        // 第二个视图
        btn_lover = (ImageView) headerView.findViewById(R.id.btn_lover);
        btn_movie = (ImageView) headerView.findViewById(R.id.btn_movie);
        btn_tea = (ImageView) headerView.findViewById(R.id.btn_tea);
        btn_beer = (ImageView) headerView.findViewById(R.id.btn_beer);
        home_item_title_left2 = (TextView) headerView.findViewById(R.id.home_item_title_left);
        home_item_title_right2 = (TextView) headerView.findViewById(R.id.home_item_title_right);
    }

    /**
     * 设置顶部轮播图数据
     */
    public void setViewPagerData(HomeTopBean homeTopBean) {
        this.homeTopBean = homeTopBean;
        topics = homeTopBean.getData().getTopics();
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
            HomeTopBean.DataEntity.TopicsEntity topicsEntity = topics.get(position);
            x.image().bind(imageView, topicsEntity.getImg().getImg_url(), imageOption);
            home_item_title_left.setText(homeTopBean.getData().getBrands_title_big());
            home_item_title_right.setText(homeTopBean.getData().getBrands_title_sml());

            // 第一个
            List<HomeTopBean.DataEntity.BrandsEntity> brands = homeTopBean.getData().getBrands();
            x.image().bind(item_home_promotion_img, brands.get(0).getImg().getImg_url());
            item_home_promotion_discount.setText(brands.get(0).getDiscount());
            item_home_promotion_desc.setText(brands.get(0).getTitle());
            // 第二个
            List<HomeTopBean.DataEntity.SpecialsEntity> specials = homeTopBean.getData().getSpecials();
            x.image().bind(btn_lover, specials.get(0).getImg().getImg_url());
            x.image().bind(btn_movie, specials.get(1).getImg().getImg_url());
            x.image().bind(btn_tea, specials.get(2).getImg().getImg_url());
            x.image().bind(btn_beer, specials.get(3).getImg().getImg_url());
            home_item_title_left2.setText(homeTopBean.getData().getNew_title_big());
            home_item_title_right2.setText(homeTopBean.getData().getNew_title_sml());

            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
