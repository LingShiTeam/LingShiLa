package atguigu.com.lingshixiaomiao.pager.home.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;

import atguigu.com.lingshixiaomiao.LogUtils;
import atguigu.com.lingshixiaomiao.R;
import atguigu.com.lingshixiaomiao.pager.home.adapter.SearchResultAdapter;
import atguigu.com.lingshixiaomiao.pager.home.bean.SearchResultBean;
import atguigu.com.lingshixiaomiao.pager.scale.activity.SnackInfomationActivity;
import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/**
 * 搜索结果界面
 */
public class SearchResultActivity extends SwipeBackActivity implements View.OnClickListener {

    private ImageButton ib_left_menu;
    private ImageView iv_search_back;
    private EditText et_search;
    private TextView tv_shopname;
    private ImageView iv_cart;
    private TextView tv_shopcount;
    private GridView search_result_gridview;
    private RelativeLayout search_no_result;
    private TextView widget_no_data_view;
    private String searchResult;
    private SearchResultBean searchResultBean;
    private SearchResultAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        initView();
        initData();
        setListener();
        // 侧滑退出activity
        setSwipeBack();
    }

    /**
     * 设置监听器
     */
    private void setListener() {
        widget_no_data_view.setOnClickListener(this);
        iv_search_back.setOnClickListener(this);
        search_result_gridview.setOnItemClickListener(new MyOnItemClickListener());
    }

    /**
     * gridView的点击监听
     */
    class MyOnItemClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(SearchResultActivity.this, SnackInfomationActivity.class);
            intent.putExtra("snack_id",searchResultBean.getData().getItems().get(position).getId());
            startActivity(intent);
        }
    }

    /**
     * 初始化数据
     */
    private void initData() {
        searchResult = getIntent().getStringExtra("searchResult");
        String searchText = getIntent().getStringExtra("searchText");
        tv_shopname.setText(searchText);
        // 解析数据
        parsedJson(searchResult);
        String rs_code = searchResultBean.getRs_code();
        if(rs_code.equals("1000")) {
            //search_no_result.setVisibility(View.GONE);
            search_result_gridview.setVisibility(View.VISIBLE);
            if(adapter == null) {
                adapter = new SearchResultAdapter(this, searchResultBean);
            }
            search_result_gridview.setAdapter(adapter);
        } else {
            search_no_result.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 解析json数据
     * @param json
     */
    private void parsedJson(String json) {
        searchResultBean = new Gson().fromJson(json, SearchResultBean.class);
        LogUtils.loge("TAG --> " + searchResultBean.toString());
    }

    /**
     * 初始化布局
     */
    private void initView() {
        ib_left_menu = (ImageButton) findViewById(R.id.ib_left_menu);
        iv_search_back = (ImageView) findViewById(R.id.iv_search_back);
        et_search = (EditText) findViewById(R.id.et_search);
        tv_shopname = (TextView) findViewById(R.id.tv_shopname);
        iv_cart = (ImageView) findViewById(R.id.iv_cart);
        tv_shopcount = (TextView) findViewById(R.id.tv_shopcount);
        search_result_gridview = (GridView) findViewById(R.id.search_result_gridview);
        search_no_result = (RelativeLayout) findViewById(R.id.search_no_result);
        widget_no_data_view = (TextView) findViewById(R.id.widget_no_data_view);

        ib_left_menu.setVisibility(View.GONE);
        iv_search_back.setVisibility(View.VISIBLE);
        et_search.setVisibility(View.GONE);
        tv_shopname.setVisibility(View.VISIBLE);
    }

    /**
     * 点击的回调
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.widget_no_data_view:
            case R.id.iv_search_back:
                finish();
                break;
        }
    }

    /**
     * 侧滑退出activity
     */
    private void setSwipeBack() {
        SwipeBackLayout swipeBackLayout = getSwipeBackLayout();
        swipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);
    }
}
