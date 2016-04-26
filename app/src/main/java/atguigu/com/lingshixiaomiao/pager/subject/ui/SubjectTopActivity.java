package atguigu.com.lingshixiaomiao.pager.subject.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

import atguigu.com.lingshixiaomiao.LogUtils;
import atguigu.com.lingshixiaomiao.R;
import atguigu.com.lingshixiaomiao.pager.subject.adapter.SubShopAdapter;
import atguigu.com.lingshixiaomiao.pager.subject.bean.SubTopShopBean;
import atguigu.com.lingshixiaomiao.pager.subject.utils.Url;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/**
 * Created by CheungJhonny on 2016/4/19.
 * 专题  顶部商品列表界面
 */
public class SubjectTopActivity extends SwipeBackActivity {

    private ImageView iv_back_sub;
    private ImageView iv_cart;

    private PullToRefreshGridView gd_shop;
    /**
     * 专题详情页面传递的url的关键词
     */
    private String topUrl;

    private SubShopAdapter adapter;
    /**
     * shop 的实体类
     */
    private SubTopShopBean shopBean;
    /**
     * shop 数据的集合
     */

    private List<SubTopShopBean.DataBean.ItemsBean> shopItems;
    /**
     * 页面的请求地址
     */
    private String shopUrl;
    /**
     * 加载更多的请求地址
     */
    private String moreShopUrl;

    private int pagerNum = 1;
    /**
     * 每次加载时候最后一个的id
     */
    private int id;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_top);

        //初始化视图文件
        initView();
        //联网获请求数据
        initData();
        //设置监听
        setListener();
    }

    /**
     * 设置 监听
     */
    private void setListener() {
       iv_back_sub.setOnClickListener(new MyOnClickListener());
        //设置下拉和上拉的监听
        gd_shop.setOnRefreshListener(new MyOnRefreshListener());

        //设置item的点击监听事件
        gd_shop.setOnItemClickListener(new MyOnItemClickListener());

    }

    /**
     * item的点击监听事件
     */
    class MyOnItemClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


            Intent intent = new Intent(SubjectTopActivity.this,DelicacyDetailsActivity.class);
            intent.putExtra("DelicacyDetails", shopItems.get(position).getId() + "");
            startActivity(intent);
        }
    }


    /**
     * 返回键的点击事件
     */
    class MyOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            finish();
        }
    }


    /**
     *  刷新状态的监听
     */
    class MyOnRefreshListener implements PullToRefreshBase.OnRefreshListener2 {

        /**
         * 下拉刷新
         *
         * @param refreshView
         */
        @Override
        public void onPullDownToRefresh(PullToRefreshBase refreshView) {
            pagerNum = 1;
            getDataFormNet(shopUrl);

        }

        @Override
        public void onPullUpToRefresh(PullToRefreshBase refreshView) {
            //int id = shopItems.get(shopItems.size() - 1).getId();
            Log.d("TAG", "id:" + id);
            moreShopUrl = Url.SUBJECT_TOP_DATA + pagerNum + "&pg_size=20&subject_" + "id=" + topUrl +
                    "&since_id=" + id;
            Log.d("TAG", "sub上拉加载更多 pagernum" + pagerNum);
            Log.d("TAG", "sub上拉加载更多 pagernum" + moreShopUrl);
            getDataFormNet(moreShopUrl);

        }
    }


    /**
     * 获取数据
     */
    private void initData() {
        //获取传来的地址关键地址
        topUrl = getIntent().getStringExtra("topUrl");
        //需要判断网络状态
        Log.d("TAG", "topUrL -----" + topUrl);
        shopUrl = Url.SUBJECT_TOP_DATA + "1" + "&pg_size=20&subject_" + "id=" + topUrl + "&since_id=0";
        getDataFormNet(shopUrl);

    }

    /**
     * 设置Gridview的刷新状态
     */
    private void initRefreshGridView() {

        //下拉刷新时候的状态
        ILoadingLayout topLabel = gd_shop.getLoadingLayoutProxy(true, false);
        topLabel.setRefreshingLabel("精选全宇宙美食 100%正品保证");
        topLabel.setPullLabel("精选全宇宙美食 100%正品保证");
        topLabel.setReleaseLabel("精选全宇宙美食 100%正品保证");

        //上拉加载更多
        ILoadingLayout endLabel = gd_shop.getLoadingLayoutProxy(false, true);
        endLabel.setRefreshingLabel(null);
        endLabel.setPullLabel(null);
        endLabel.setReleaseLabel(null);

    }

    /**
     * 联网获取数据
     */
    private void getDataFormNet(String url) {

        RequestParams params = new RequestParams(url);
        params.setConnectTimeout(5000);

        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                LogUtils.loge("TAG", "专题 商品列表的数据 onSuccess " + result);

                if (result != null) {
                    //解析获取的json数据
                    processData(result);
                } else {
                    Toast.makeText(SubjectTopActivity.this, "获取数据失败", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                LogUtils.loge("TAG", "专题 商品列表的数据 onError " + isOnCallback);


                Toast.makeText(SubjectTopActivity.this, "未获取数据 请检查网络", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(CancelledException cex) {
                LogUtils.loge("TAG", "专题 商品列表的数据 onCancelled " + cex);
            }

            @Override
            public void onFinished() {
                LogUtils.loge("TAG", "专题 商品列表的数据 onFinished ");
            }
        });


    }

    /**
     * 解析获取的数据
     *
     * @param json
     */
    private void processData(String json) {
        SubTopShopBean shopBean = parseJson(json);

        if(!"1000".equals(shopBean.getRs_code())) {
            //添加个布局
            gd_shop.onRefreshComplete();
            Toast.makeText(this, "已经没有存货了", Toast.LENGTH_SHORT).show();
            return;
        }
        //获取数据的集合
        List<SubTopShopBean.DataBean.ItemsBean> list = shopBean.getData().getItems();
        id = list.get(list.size() - 1).getId();

        if (1 < pagerNum) {
            shopItems.addAll(list);
            Log.d("TAG", "shopItems.size():" + shopItems.size());
            adapter.notifyDataSetChanged();
            pagerNum++;
        } else {
            this.shopBean = shopBean;
            shopItems = shopBean.getData().getItems();

            //适配器
            if (adapter == null) {
                adapter = new SubShopAdapter(this, shopBean,shopItems);
            }
            gd_shop.setAdapter(adapter);
            //加载数据完成
            pagerNum++;
        }

        gd_shop.onRefreshComplete();
    }

    /**
     * 解析json数据
     *
     * @param json
     */
    private SubTopShopBean parseJson(String json) {
        return new Gson().fromJson(json, SubTopShopBean.class);

    }

    /**
     * 初始化视图文件
     */
    private void initView() {

        iv_back_sub = (ImageView) findViewById(R.id.iv_back_sub);
        iv_cart = (ImageView) findViewById(R.id.iv_cart);

        gd_shop = (PullToRefreshGridView) findViewById(R.id.gd_shop);
        //设置GridView的刷新状态
        initRefreshGridView();

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
