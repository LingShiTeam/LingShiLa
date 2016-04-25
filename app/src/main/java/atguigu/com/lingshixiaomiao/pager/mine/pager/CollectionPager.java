package atguigu.com.lingshixiaomiao.pager.mine.pager;

import android.app.Activity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import atguigu.com.lingshixiaomiao.R;
import atguigu.com.lingshixiaomiao.base.BasePager;
import atguigu.com.lingshixiaomiao.pager.mine.adapter.CollectionViewPagerAdapter;
import atguigu.com.lingshixiaomiao.pager.mine.base.ContentBasePager;
import atguigu.com.lingshixiaomiao.pager.mine.bean.CollSpecialBean;
import atguigu.com.lingshixiaomiao.pager.mine.bean.CollectGoodsBean;
import atguigu.com.lingshixiaomiao.pager.mine.bean.LoginBean;
import atguigu.com.lingshixiaomiao.pager.mine.bean.RemoveCollGoodsBean;
import atguigu.com.lingshixiaomiao.pager.mine.utils.Constants;
import atguigu.com.lingshixiaomiao.pager.mine.utils.JsonUtils;
import atguigu.com.lingshixiaomiao.pager.mine.utils.LoginUtils;
import atguigu.com.lingshixiaomiao.pager.mine.utils.Url;
import atguigu.com.lingshixiaomiao.pager.mine.view.NoScrollViewPager;

/**
 * Created by lanmang on 2016/4/10.
 */
public class CollectionPager extends ContentBasePager {

    private LinearLayout ll_mine_collection_title;
    private TextView tv_mine_title_complete;
    private TextView tv_mine_collection_left;
    private TextView tv_mine_collection_right;
    private TextView tv_mine_collection_delete;

    private NoScrollViewPager vp_mine_collection;
    private CollectionViewPagerAdapter vpAdapter;
    private List<BasePager> pagers;

    /**
     * 构造方法
     *
     * @param mActivity
     */
    public CollectionPager(Activity mActivity) {
        super(mActivity);
    }

    @Override
    public View initView() {
        View inflate = View.inflate(mActivity, R.layout.mine_collection, null);
        findViewById(inflate);
        return inflate;
    }

    private void findViewById(View v) {
        ll_mine_collection_title = (LinearLayout) mActivity.findViewById(R.id.ll_mine_collection_title);
        tv_mine_title_complete = (TextView) mActivity.findViewById(R.id.tv_mine_title_complete);
        tv_mine_collection_left = (TextView) mActivity.findViewById(R.id.tv_mine_collection_left);
        tv_mine_collection_right = (TextView) mActivity.findViewById(R.id.tv_mine_collection_right);

        tv_mine_collection_delete = (TextView) v.findViewById(R.id.tv_mine_collection_delete);
        vp_mine_collection = (NoScrollViewPager) v.findViewById(R.id.vp_mine_collection);

        ll_mine_collection_title.setVisibility(View.VISIBLE);
        tv_mine_title_complete.setVisibility(View.VISIBLE);
        tv_mine_collection_left.setSelected(true);
        tv_mine_collection_right.setSelected(false);

        tv_mine_collection_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                delete();
                hindDelete();
            }
        });
    }

    @Override
    public void registerEventBus() {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public void unRegisterEventBus() {
        if (EventBus.getDefault().isRegistered(this)) {
            for(int i = 0; i < pagers.size(); i++) {
                pagers.get(i).unRegisterEventBus();
            }
            EventBus.getDefault().unregister(this);
        }
    }

    private List<Integer> removePosition = new ArrayList<>();

    private void delete() {
        StringBuffer removeIds = new StringBuffer();
        if (position == 0) {
            GoodsPager goodsPager = (GoodsPager) pagers.get(0);
            List<CollectGoodsBean.DataEntity.ItemsEntity> data = goodsPager.data;

            for (int i = 0; i < data.size(); i++) {
                boolean checked = data.get(i).isChecked();
                if (checked) {
                    int id = data.get(i).getId();
                    if (removeIds.length() > 0) {
                        removeIds.append(",");
                    }
                    removeIds.append(id);
                    //data.remove(i--);
                    removePosition.add(id);
                }
            }
            removeCollGoods(removeIds);
            // goodsPager.parseData();
        } else {
            SpecialPager specialPager = (SpecialPager) pagers.get(1);
            List<CollSpecialBean.DataEntity.ItemsEntity> data = specialPager.data;
            for (int i = 0; i < data.size(); i++) {
                boolean checked = data.get(i).isChecked();
                if (checked) {
                    int id = data.get(i).getId();
                    if (removeIds.length() > 0) {
                        removeIds.append(",");
                    }
                    removeIds.append(id);
                    //data.remove(i--);
                    removePosition.add(id);
                }
            }
            removeCollGoods(removeIds);
            // specialPager.loadPager();
        }
    }

    private void removeCollGoods(StringBuffer removeIds) {
        if (removeIds.length() > 0) {
            String[] u = Url.REMOVE_COLLECTION_GOODS_URL;
            LoginBean loginBean = (LoginBean) LoginUtils.getInstance().getData();
            String url = u[0] + loginBean.getData().getUid() + u[1] + removeIds + u[2] + position + u[3];
            new JsonUtils().loadData(url, RemoveCollGoodsBean.class);
        }
    }

    @Subscribe
    public void onEventMainThread(RemoveCollGoodsBean removeCollGoodsBean) {
        if (Constants.SUCCESS.equals(removeCollGoodsBean.getRs_code())) {
            Toast.makeText(mActivity, "删除成功", Toast.LENGTH_SHORT).show();
            if (position == 0) {
                GoodsPager goodsPager = (GoodsPager) pagers.get(0);
                List<CollectGoodsBean.DataEntity.ItemsEntity> data = goodsPager.data;
                for (int i = 0; i < data.size(); i++) {
                    if (removePosition.contains(data.get(i).getId())) {
                        data.remove(i--);
                    }
                }
                goodsPager.parseData();
            } else {
                SpecialPager specialPager = (SpecialPager) pagers.get(1);
                List<CollSpecialBean.DataEntity.ItemsEntity> data = specialPager.data;
                for (int i = 0; i < data.size(); i++) {
                    if (removePosition.contains(data.get(i).getId())) {
                        data.remove(i--);
                    }
                }
                specialPager.loadPager();
            }
        } else {
            Toast.makeText(mActivity, "删除失败", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public String setTitle() {
        return "收藏";
    }

    @Override
    public String setComplete() {
        return "编辑";
    }

    @Override
    public void initData() {
        super.initData();
        loadPagers();
        loadViewPager();
    }

    private void loadPagers() {
        pagers = new ArrayList<>();
        pagers.add(new GoodsPager(mActivity));
        pagers.add(new SpecialPager(mActivity));
    }

    private void loadViewPager() {
        vpAdapter = new CollectionViewPagerAdapter(mActivity, pagers);
        vp_mine_collection.setAdapter(vpAdapter);

        setListener();
    }

    private int position = 0;

    private void setListener() {
        vp_mine_collection.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                CollectionPager.this.position = position;
                if (position == 0) {
                    tv_mine_collection_left.setSelected(true);
                    tv_mine_collection_right.setSelected(false);
                } else {
                    tv_mine_collection_left.setSelected(false);
                    tv_mine_collection_right.setSelected(true);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private boolean isDelete = false;

    @Override
    public void complete() {
        isDelete = !isDelete;
        if (isDelete) {
            showDelete();
        } else {
            hindDelete();
        }
    }

    private void hindDelete() {
        vp_mine_collection.setPagingEnabled(true);
        tv_mine_title_complete.setText("编辑");
        tv_mine_collection_delete.clearAnimation();
        tv_mine_collection_delete.setVisibility(View.GONE);
        showOrHindCheck(false);
    }

    private void showDelete() {
        vp_mine_collection.setPagingEnabled(false);
        tv_mine_title_complete.setText("取消");
        tv_mine_collection_delete.clearAnimation();
        tv_mine_collection_delete.setVisibility(View.VISIBLE);

        TranslateAnimation animation = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0,
                Animation.RELATIVE_TO_SELF, 1, Animation.RELATIVE_TO_SELF, 0);
        animation.setDuration(300);
        animation.setFillAfter(true);
        tv_mine_collection_delete.startAnimation(animation);

        showOrHindCheck(true);
    }

    private void showOrHindCheck(boolean isShow) {
        if (position == 0) {
            GoodsPager goodsPager = (GoodsPager) pagers.get(position);
            List<CollectGoodsBean.DataEntity.ItemsEntity> data = goodsPager.data;
            if (data == null) {
                return;
            }
            for (int i = 0; i < data.size(); i++) {
                data.get(i).setShowDelete(isShow);
            }
            goodsPager.adapter.notifyDataSetChanged();
        } else {
            SpecialPager specialPager = (SpecialPager) pagers.get(1);
            List<CollSpecialBean.DataEntity.ItemsEntity> data = specialPager.data;
            if (data == null) {
                return;
            }
            for (int i = 0; i < data.size(); i++) {
                data.get(i).setShowDelete(isShow);
            }
            specialPager.adapter.notifyDataSetChanged();
        }
    }

}
