package atguigu.com.lingshixiaomiao.pager.home.activity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import atguigu.com.lingshixiaomiao.LogUtils;
import atguigu.com.lingshixiaomiao.R;
import atguigu.com.lingshixiaomiao.pager.home.adapter.HistoryAdapter;
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

    private ListView listview_search_result;
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
                adapter = new HistoryAdapter(this, historyLists, dao);
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
        /// 搜索点击监听
        tv_search.setOnClickListener(this);
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
        listview_search_result = (ListView) findViewById(R.id.listview_search_result);
        listview_search_history = (ListView) findViewById(R.id.listview_search_history);
        iv_search_back = (ImageView) findViewById(R.id.iv_search_back);
        et_search = (EditText) findViewById(R.id.et_search);
        tv_search = (TextView) findViewById(R.id.tv_search);

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
                            adapter = new HistoryAdapter(this, historyLists, dao);
                        }
                        listview_search_history.setAdapter(adapter);
                    }

                    adapter.notifyDataSetChanged();
                    LogUtils.loge("TAG", searchText);
                    // 联网请求数据


                } else {
                    finish();
                }
                break;
        }
    }

}
