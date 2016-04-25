package atguigu.com.lingshixiaomiao;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.EdgeEffectCompat;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import atguigu.com.lingshixiaomiao.pager.home.utils.DensityUtil;
import atguigu.com.lingshixiaomiao.pager.home.utils.SPUtils;

/**
 * 引导界面
 */
public class GuideActivity extends Activity {

    private ViewPager viewpager_guide;
    private LinearLayout ll_guide_point;
    private ImageView iv_red_point;
    private int[] drawables = {R.drawable.bg_guide_1, R.drawable.bg_guide_2, R.drawable.bg_guide_3,
            R.drawable.bg_guide_4, R.drawable.bg_guide_5};
    private List<ImageView> images;
    private int distence;
    private int pointWidth;
    private EdgeEffectCompat leftEdge;
    private EdgeEffectCompat rightEdge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        findView();
        initView();
        setListener();
    }

    /**
     * 设置监听
     */
    private void setListener() {
        // 视图树观察者
        ll_guide_point.getViewTreeObserver().addOnGlobalLayoutListener(new MyOnGlobalLayoutListener());
        // viewpager的滑动监听
        viewpager_guide.setOnPageChangeListener(new MyOnPageChangeListener());
        viewpager_guide.setAdapter(new MyPagerAdapter());
    }

    /**
     * viewpager的适配器
     */
    class MyPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return images != null ? images.size() : 0;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            ImageView imageView = images.get(position);
            container.addView(imageView);

            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            //super.destroyItem(container, position, object);
            container.removeView((View) object);
        }
    }

    class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            // 小红点滑动的距离
            float left = distence * position + distence * positionOffset;
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(pointWidth, pointWidth);
            params.leftMargin = (int) left;
            iv_red_point.setLayoutParams(params);
        }

        private int position;

        @Override
        public void onPageSelected(int position) {
            this.position = position;
            // ViewPager在拖拽到左边和右边的时候，禁止显示黄色或者蓝色的渐变图片的解决方法
            if (leftEdge != null && rightEdge != null) {
                leftEdge.finish();
                rightEdge.finish();
                leftEdge.setSize(0, 0);
                rightEdge.setSize(0, 0);
            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {
            // 方法一:效果不好
           /* if(position == images.size() - 1 && state == ViewPager.SCROLL_STATE_DRAGGING) {
                Intent intent = new Intent(GuideActivity.this, MainActivity.class);
                startActivity(intent);
            }*/
            // 方法二:推荐
            if (rightEdge != null && !rightEdge.isFinished()) {//到了最后一张并且还继续拖动，出现蓝色限制边条了
                // 保存状态
                SPUtils.putBoolean(GuideActivity.this, SPUtils.FIRST_SPLASH, true);
                startActivity(new Intent(GuideActivity.this, MainActivity.class));
                GuideActivity.this.finish();
            }
        }
    }

    class MyOnGlobalLayoutListener implements ViewTreeObserver.OnGlobalLayoutListener {

        @Override
        public void onGlobalLayout() {
            ll_guide_point.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            distence = ll_guide_point.getChildAt(1).getLeft() - ll_guide_point.getChildAt(0).getLeft();
        }
    }

    /**
     * 初始化视图
     */
    private void initView() {
        images = new ArrayList<>();
        for (int i = 0; i < drawables.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setBackgroundResource(drawables[i]);
            images.add(imageView);
        }
        // 初始化小圆点
        setPoints();

        try {
            Field leftEdgeField = viewpager_guide.getClass().getDeclaredField("mLeftEdge");
            Field rightEdgeField = viewpager_guide.getClass().getDeclaredField("mRightEdge");
            if (leftEdgeField != null && rightEdgeField != null) {
                leftEdgeField.setAccessible(true);
                rightEdgeField.setAccessible(true);
                leftEdge = (EdgeEffectCompat) leftEdgeField.get(viewpager_guide);
                rightEdge = (EdgeEffectCompat) rightEdgeField.get(viewpager_guide);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 初始化小圆点
     */
    private void setPoints() {

        pointWidth = DensityUtil.dip2px(this, 10);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(pointWidth, pointWidth);
        params.rightMargin = DensityUtil.dip2px(this, 15);
        //params.leftMargin = DensityUtil.dip2px(this, 5);
        for (int i = 0; i < images.size(); i++) {
            ImageView imageView = new ImageView(this);
            imageView.setBackgroundResource(R.drawable.normal_guide_point);
            ll_guide_point.addView(imageView, params);

        }
    }

    private void findView() {
        viewpager_guide = (ViewPager) findViewById(R.id.viewpager_guide);
        ll_guide_point = (LinearLayout) findViewById(R.id.ll_guide_point);
        iv_red_point = (ImageView) findViewById(R.id.iv_red_point);
    }
}
