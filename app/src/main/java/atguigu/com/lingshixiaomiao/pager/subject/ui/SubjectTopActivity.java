package atguigu.com.lingshixiaomiao.pager.subject.ui;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

import atguigu.com.lingshixiaomiao.LogUtils;
import atguigu.com.lingshixiaomiao.R;
import atguigu.com.lingshixiaomiao.pager.subject.bean.SubTopShopBean;
import atguigu.com.lingshixiaomiao.pager.subject.utils.Url;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/**
 * Created by CheungJhonny on 2016/4/19.
 *  专题  顶部商品列表界面
 */
public class SubjectTopActivity extends SwipeBackActivity {

    private ImageView iv_back_sub;
    private ImageView iv_cart;

    private GridView gd_shop;
    /**
     * 专题详情页面传递的url 关键词
     */
    private String topUrL;

    private SubShopAdapter adapter;
    /**
     * shop 的实体类
     */
    private SubTopShopBean shopBean;
    /**
     * shop 数据的集合
     */
    private List<SubTopShopBean.DataBean.ItemsBean> shopItems;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_top);

        //初始化视图文件
        initView();
        //联网获请求数据
        initData();
    }

    /**
     * 获取数据
     */
    private void initData() {
        //获取传来的地址关键地址
        topUrL = getIntent().getStringExtra("topUrL");

        getDataFormNet();




        //虚拟数据
        adapter = new SubShopAdapter();
        gd_shop.setAdapter(adapter);

    }

    /**
     * 联网获取数据
     */
    private void getDataFormNet() {
        RequestParams params = new RequestParams(Url.SUBJECT_TOP_DATA + "id=" + topUrL + "&since_id=0");
        params.setConnectTimeout(5000);

        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                LogUtils.loge("TAG","专题 商品列表的数据 onSuccess " + result);

                if (result != null) {

                    //解析获取的json数据
                    processData(result);
                } else {
                    Toast.makeText(SubjectTopActivity.this, "获取数据失败", Toast.LENGTH_SHORT).show();
                }

            }


            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                LogUtils.loge("TAG","专题 商品列表的数据 onError " + isOnCallback);

            }

            @Override
            public void onCancelled(CancelledException cex) {
                LogUtils.loge("TAG","专题 商品列表的数据 onCancelled " + cex);

            }

            @Override
            public void onFinished() {
                LogUtils.loge("TAG","专题 商品列表的数据 onFinished ");

            }
        });


    }

    /**
     * 解析获取的数据
     * @param json
     */
    private void processData(String json) {
        shopBean = parseJson(json);

        shopItems = shopBean.getData().getItems();





    }

    /**
     * 解析json数据
     * @param json
     */
    private SubTopShopBean parseJson(String json) {
        return new Gson().fromJson(json, SubTopShopBean.class);

    }

    /**
     * 初始化视图文件
     */
    private void initView() {

        iv_back_sub = (ImageView)findViewById(R.id.iv_back_sub);
        iv_cart = (ImageView)findViewById(R.id.iv_cart);

        gd_shop = (GridView)findViewById(R.id.gd_shop);


    }


    class SubShopAdapter extends BaseAdapter {



        @Override
        public int getCount() {
            return 20;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            for (int i = 0 ; i < 20 ; i++ ) {

                TextView textView = new TextView(SubjectTopActivity.this);
                textView.setText("商品");
                textView.setTextSize(20);

                convertView = textView;
            }


            return convertView;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        topUrL = null;


    }
}
