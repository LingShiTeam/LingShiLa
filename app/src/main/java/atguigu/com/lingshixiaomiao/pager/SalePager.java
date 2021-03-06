package atguigu.com.lingshixiaomiao.pager;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.viewpagerindicator.TabPageIndicator;

import java.util.ArrayList;
import java.util.List;

import atguigu.com.lingshixiaomiao.MainActivity;
import atguigu.com.lingshixiaomiao.R;
import atguigu.com.lingshixiaomiao.base.BasePager;
import atguigu.com.lingshixiaomiao.pager.scale.activity.ShoppingCarActivity;
import atguigu.com.lingshixiaomiao.pager.scale.base.ScaleBasePager;
import atguigu.com.lingshixiaomiao.pager.scale.bean.ScallingBean;
import atguigu.com.lingshixiaomiao.pager.scale.detailpager.ScaleBeginPager;
import atguigu.com.lingshixiaomiao.pager.scale.detailpager.ScallingPager;


/**
 * Created by lanmang on 2016/4/8.
 * 特卖页面
 */
public class SalePager extends BasePager {

    private TabPageIndicator indicator_scale;
    private ViewPager vp_scale;
   /* private ImageView iv_scale_back;
    private ImageView iv_scale_shoppingcar;*/

    //vp页面的集合
    private List<ScaleBasePager> detailPagers;

    //特卖中lv数据集合
    private List<ScallingBean.DataEntity.ItemsEntity> itemsEntities;
    private ImageButton ib_left_menu;
    private ImageView iv_search_back;
    private EditText et_search;
    private TextView tv_shopname;
    private RelativeLayout rl_cart;
    private TextView tv_shopcount;


    public SalePager(Activity mActivity) {
        super(mActivity);
    }

    @Override
    public View initView() {

        View rootView = View.inflate(mActivity, R.layout.scale_pager, null);

        indicator_scale = (TabPageIndicator) rootView.findViewById(R.id.indicator_scale);
        vp_scale = (ViewPager) rootView.findViewById(R.id.vp_scale);
       /* iv_scale_back = (ImageView) rootView.findViewById(R.id.iv_scale_back);
        iv_scale_shoppingcar = (ImageView) rootView.findViewById(R.id.iv_scale_shoppingcar);*/

        ib_left_menu = (ImageButton) rootView.findViewById(R.id.ib_left_menu);
        iv_search_back = (ImageView) rootView.findViewById(R.id.iv_search_back);
        et_search = (EditText) rootView.findViewById(R.id.et_search);
        tv_shopname = (TextView) rootView.findViewById(R.id.tv_shopname);
        rl_cart = (RelativeLayout) rootView.findViewById(R.id.rl_cart);
        tv_shopcount = (TextView) rootView.findViewById(R.id.tv_shopcount);

        iv_search_back.setOnClickListener(new MyOnclickListener());
        rl_cart.setOnClickListener(new MyOnclickListener());

        return rootView;
    }

    @Override
    public void initData() {

        getDataFromNet();

        detailPagers = new ArrayList<>();

        detailPagers.add(new ScallingPager(mActivity));
        detailPagers.add(new ScaleBeginPager(mActivity));

        //vp设置适配器
        vp_scale.setAdapter(new MyPagerAdapter());

        //设置ViewPager
        indicator_scale.setViewPager(vp_scale);

        //如果设置ViewPager后，监听页面的改变是由TabPagerIndicator
        indicator_scale.setOnPageChangeListener(new MyPageChangeListener());
    }

    /**
     * 从网络获取listview的数据
     */
    private void getDataFromNet() {

    }

    /**
     * vp的适配器
     */
    class MyPagerAdapter extends PagerAdapter {

        private String[] pagerTitles = new String[]{"特卖中", "即将开始"};

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            ScaleBasePager scaleBasePager = detailPagers.get(position);
            scaleBasePager.initData();
            container.addView(scaleBasePager.rootView);

            return scaleBasePager.rootView;
        }

        @Override
        public int getCount() {
            return detailPagers.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return pagerTitles[position];
        }
    }

    /**
     * vp页面切换监听
     */
    private class MyPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    /**
     * 点击监听
     */
    private class MyOnclickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {

            switch (v.getId()) {

                case R.id.iv_search_back:

                    //进入首页
                    MainActivity mainActivity = (MainActivity) mActivity;
                    RadioGroup rg_main = (RadioGroup) mainActivity.findViewById(R.id.rg_main);
                    rg_main.check(R.id.rb_main_home);
                    break;

                case R.id.rl_cart:

                    //进入购物车页面
                    Intent intent = new Intent(mActivity, ShoppingCarActivity.class);
                    mActivity.startActivity(intent);
                    break;
            }
        }
    }
}
