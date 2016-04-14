package atguigu.com.lingshixiaomiao.pager.home.activity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import atguigu.com.lingshixiaomiao.LogUtils;
import atguigu.com.lingshixiaomiao.R;
import atguigu.com.lingshixiaomiao.pager.home.adapter.HistoryAdapter;
import atguigu.com.lingshixiaomiao.pager.home.utils.NetWorkUtils;
import atguigu.com.lingshixiaomiao.pager.home.utils.Url;
import de.greenrobot.dao.query.QueryBuilder;
import greendao.searchbean.DaoMaster;
import greendao.searchbean.DaoSession;
import greendao.searchbean.SearchHistory;
import greendao.searchbean.SearchHistoryDao;
import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/**
 * 搜索界面
 */
public class SearchActivity extends SwipeBackActivity implements View.OnClickListener {

    private ImageView iv_search_back;
    private EditText et_search;
    private TextView tv_search;
    private ListView listview_search_history;
    /**
     * 数据库的相关变量
     */
    private SQLiteDatabase db;
    private DaoMaster daoMaster;
    private DaoSession daoSession;
    private SearchHistoryDao dao;
    private List<SearchHistory> historyLists;
    private HistoryAdapter adapter;
    /**
     * 指定日期格式 yyyy-MM-dd HH:mm:ss
     */
    public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private View footerView;
    private TextView btn_clear_history;
    private RelativeLayout loading_dialog;
    private ImageView widget_loading_pb;
    private AnimationDrawable ad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        initView();
        initGreenDao();
        initData();

        setListener();
        // 侧滑退出activity
        setSwipeBack();
    }

    /**
     * 初始化数据库操作
     */
    private void initGreenDao() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "searchrecord-db", null);
        db = helper.getWritableDatabase();
        daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
        dao = daoSession.getSearchHistoryDao();
    }

    /**
     * 初始化数据
     */
    private void initData() {

        // 判断是否有历史搜索记录
        QueryBuilder<SearchHistory> queryBuilder = dao.queryBuilder();
        // 历史数据集合
        historyLists = queryBuilder.list();

        LogUtils.loge("TAG", historyLists.size() + "");

        if (historyLists.size() != 0) { // 有数据
            listview_search_history.setVisibility(View.VISIBLE);
            if (adapter == null) {
                adapter = new HistoryAdapter(this, historyLists, dao, listview_search_history);
            }
            listview_search_history.setAdapter(adapter);

        } else { // 没有数据
            listview_search_history.setVisibility(View.GONE);
        }

    }

    /**
     * 设置监听
     */
    private void setListener() {
        // 返回点击监听
        iv_search_back.setOnClickListener(this);
        // 监听文本变化
        et_search.addTextChangedListener(new MyTextWatcher());
        // 搜索点击监听
        tv_search.setOnClickListener(this);
        // 清空历史搜索记录
        btn_clear_history.setOnClickListener(this);
    }

    /**
     * 监听文本变化
     */
    class MyTextWatcher implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (et_search.length() != 0) {
                tv_search.setText("搜索");
            } else {
                tv_search.setText("取消");
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }

    /**
     * 初始化视图
     */
    private void initView() {
        listview_search_history = (ListView) findViewById(R.id.listview_search_history);
        iv_search_back = (ImageView) findViewById(R.id.iv_search_back);
        et_search = (EditText) findViewById(R.id.et_search);
        tv_search = (TextView) findViewById(R.id.tv_search);
        loading_dialog = (RelativeLayout) findViewById(R.id.loading_dialog);
        widget_loading_pb = (ImageView) findViewById(R.id.widget_loading_pb);
        loading_dialog.setVisibility(View.GONE);


        footerView = View.inflate(this, R.layout.item_search_history_footer, null);
        listview_search_history.addFooterView(footerView);
        btn_clear_history = (TextView) footerView.findViewById(R.id.btn_clear_history);
    }

    /**
     * 侧滑退出activity
     */
    private void setSwipeBack() {
        SwipeBackLayout swipeBackLayout = getSwipeBackLayout();
        swipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);
    }


    /**
     * 点击监听
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // 返回按钮
            case R.id.iv_search_back:
                onBackPressed();
                break;
            // 搜索按钮
            case R.id.tv_search:
                if (et_search.length() != 0) {
                    String searchText = et_search.getText().toString();
                    et_search.setText("");
                    SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
                    SearchHistory searchHistory = new SearchHistory(null, searchText, sdf.format(new Date()));
                    historyLists.add(searchHistory);
                    dao.insert(searchHistory);
                    if (!listview_search_history.isShown()) {
                        listview_search_history.setVisibility(View.VISIBLE);
                        if (adapter == null) {
                            adapter = new HistoryAdapter(this, historyLists, dao, listview_search_history);
                        }
                        listview_search_history.setAdapter(adapter);
                    }

                    adapter.notifyDataSetChanged();
                    LogUtils.loge("TAG", searchText);
                    // 联网请求数据
                    if (NetWorkUtils.isNetworkConnected()) {
                        loading_dialog.setVisibility(View.VISIBLE);
                        ad = (AnimationDrawable) widget_loading_pb.getBackground();
                        ad.start();
                        searchFromNet(searchText);
                    } else {
                        Toast.makeText(SearchActivity.this, "网络不可用,请检查网络", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    finish();
                }
                break;
            // 清空搜索历史记录
            case R.id.btn_clear_history:
                dao.deleteAll();
                if (historyLists.size() != 0) {
                    for (int i = 0; i < historyLists.size(); i++) {
                        historyLists.remove(i);
                    }
                }
                adapter.notifyDataSetChanged();
                listview_search_history.setVisibility(View.GONE);
                break;
        }
    }

    /**
     * 联网搜索内容
     *
     * @param searchText
     */
    private void searchFromNet(final String searchText) {
        try {
            final String searchUrl = Url.HOME_SEARCH_BASE + NetWorkUtils.toBrowserCode(searchText, "utf-8");

            RequestParams params = new RequestParams(searchUrl);
            x.http().get(params, new Callback.CommonCallback<String>() {
                @Override
                public void onSuccess(String result) {
                    LogUtils.loge("result" + result);
                    //SearchResultBean searchResultBean = new Gson().fromJson(result, SearchResultBean.class);
                    //LogUtils.loge("result--->" + searchResultBean.toString());
                    // 开启搜索结果界面
                    Intent intent = new Intent(SearchActivity.this, SearchResultActivity.class);
                    intent.putExtra("searchResult", result);
                    intent.putExtra("searchText", searchText);
                    startActivity(intent);
                    loading_dialog.setVisibility(View.GONE);
                }

                @Override
                public void onError(Throwable ex, boolean isOnCallback) {
                    LogUtils.loge("onError" + ex.toString());
                    Toast.makeText(SearchActivity.this, "搜索君,跑路了,请稍后重试", Toast.LENGTH_SHORT).show();
                    loading_dialog.setVisibility(View.GONE);
                }

                @Override
                public void onCancelled(CancelledException cex) {
                    LogUtils.loge("onCancelled" + cex.toString());
                    loading_dialog.setVisibility(View.GONE);
                }

                @Override
                public void onFinished() {
                    LogUtils.loge("onFinished");
                    loading_dialog.setVisibility(View.GONE);
                }
            });
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
