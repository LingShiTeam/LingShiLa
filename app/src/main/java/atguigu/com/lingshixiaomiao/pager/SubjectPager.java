package atguigu.com.lingshixiaomiao.pager;


import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

import atguigu.com.lingshixiaomiao.LogUtils;
import atguigu.com.lingshixiaomiao.R;
import atguigu.com.lingshixiaomiao.base.BasePager;
import atguigu.com.lingshixiaomiao.pager.scale.utils.CacheUtil;
import atguigu.com.lingshixiaomiao.pager.subject.adapter.SubjectListAdapter;
import atguigu.com.lingshixiaomiao.pager.subject.bean.SubjectListBean;
import atguigu.com.lingshixiaomiao.pager.subject.bean.SubjectTopBean;
import atguigu.com.lingshixiaomiao.pager.subject.utils.CacheUtils;
import atguigu.com.lingshixiaomiao.pager.subject.utils.JsonUtils;
import atguigu.com.lingshixiaomiao.pager.subject.utils.SubjectNetUtils;
import atguigu.com.lingshixiaomiao.pager.subject.utils.Url;


/**
 * Created by lanmang on 2016/4/8.
 * 专题页面
 */
public class SubjectPager extends BasePager {


    @ViewInject(R.id.listview_subject)
    private PullToRefreshListView listView;

    @ViewInject(R.id.iv_cart)
    private ImageView iv_cart;

    /**
     * 加载中的页面显示的布局
     */
    private LinearLayout ll_subject_loading;
    /**
     * 需要加载的动画
     */
    private ImageView subject_loading;
    /**
     * 下部listview的适配器
     */
    private SubjectListAdapter listAdapter;


    /**
     * 上部girdview的实体类
     */
    private SubjectTopBean topBeans;

    /**
     * gridview 的数据
     */
    private List<SubjectTopBean.DataBean.ItemsBean> itemsBeens;
    /**
     * 下部listview对应的尸体类
     */
    private SubjectListBean subjectListBean;

    /**
     * 下部listview的数据集合
     */
    private List<SubjectListBean.DataBean.ItemsBean> itemsListbeen;

    /**
     * 数据加载中的动画
     */
    private AnimationDrawable loadingAnim;

    /**
     * 下拉刷新的页数
     */
    private int pagerCurrent = 1;


    private String pagerLastId;

    /**
     * 构造方法
     *
     * @param mActivity
     */
    public SubjectPager(Activity mActivity) {
        super(mActivity);
    }


    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.subject_pager, null);
        //listView = (ListView) view.findViewById(R.id.listview_subject);
        //iv_cart = (ImageView) view.findViewById(R.id.iv_cart);
        x.view().inject(this, view);

        ll_subject_loading = (LinearLayout) view.findViewById(R.id.ll_subject_loading);
        subject_loading = (ImageView) view.findViewById(R.id.subject_loading);


        // loadingAnim = (AnimationDrawable) mActivity.getResources().getDrawable(R.drawable.ic_page_loading);

        registerEventBus();


        return view;

    }


    @Override
    public void initData() {
        super.initData();


        //显示数据加载中的动画
        loadingAnim = (AnimationDrawable) subject_loading.getBackground();
        if (loadingAnim != null && !loadingAnim.isRunning()) {
            loadingAnim.start();
        }

        //联网获取数据
        //这里需要判断网络状态
        if (!SubjectNetUtils.isNetworkConnected()) {
            Toast.makeText(mActivity, "请检查网络", Toast.LENGTH_SHORT).show();

        }
        //获取存储的json数据(top Gridview)
        if (CacheUtils.getString(mActivity, CacheUtils.SUBJECT_TOP_DATA) != "") {
            processData(CacheUtils.getString(mActivity, CacheUtils.SUBJECT_TOP_DATA));

        }
        //获取保存的listview的数据
        if (CacheUtil.getString(mActivity, CacheUtils.SUBJECT_LIST_DATA) != "") {
            String listJson = CacheUtils.getString(mActivity, CacheUtils.SUBJECT_LIST_DATA);
            subjectListBean = (SubjectListBean) parseListJson(listJson);
            //subjectListBean = new JsonUtils<SubjectListBean>().parseJson(listJson);
            loadAdapter();
        }

        getDataFormNet();

        new JsonUtils().loadData(Url.SBUJECT_LISTVIEW, SubjectListBean.class);
        //设置listview的下拉和上拉刷新
        listView.setMode(PullToRefreshBase.Mode.BOTH);
        //设置pullToRefresh 刷新状态的更新
        initRefreshListview();

        listView.getRefreshableView().setHeaderDividersEnabled(true);
        listView.getRefreshableView().setFooterDividersEnabled(true);
        //下拉刷新和上拉加载更多的监听
        listView.setOnRefreshListener(new MyOnRefreshListener());

        //设置listview的点击监听事件
        listView.setOnItemClickListener(new MyOnItemClickListener());
    }


    /**
     * item 的点击监听事件
     * */
    class MyOnItemClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if (0 != position) {


            } else {

            }
        }
    }
    /**
     * 设置pulltoRefresh 刷新的状态显示
     */
    private void initRefreshListview() {

        //下拉刷新 的状态显示
        ILoadingLayout topLabel = listView.getLoadingLayoutProxy(true, false);
        topLabel.setRefreshingLabel("精选全宇宙美食 100%正品保证");
        topLabel.setPullLabel("精选全宇宙美食 100%正品保证");
        topLabel.setReleaseLabel("精选全宇宙美食 100%正品保证");

        //上拉加载更多 的状态显示
        ILoadingLayout listLabel = listView.getLoadingLayoutProxy(false, true);
        listLabel.setRefreshingLabel(null);
        listLabel.setPullLabel(null);
        listLabel.setReleaseLabel(null);

    }


    class MyOnRefreshListener implements PullToRefreshBase.OnRefreshListener2<ListView> {

        /**
         * 下拉刷新时 监听
         *
         * @param refreshView
         */
        @Override
        public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
            //Gridvewi 请求数据
            getDataFormNet();
            //listview 的请求数据
            new JsonUtils().loadData(Url.SBUJECT_LISTVIEW, SubjectListBean.class);


        }

        /**
         * 上拉加载更多
         *
         * @param refreshView
         */
        @Override
        public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {

            getMoreDataFormData();


        }
    }

    /**
     * 上拉加载更多
     */
    private void getMoreDataFormData() {
        //获取当前 数据 最后一个item的id
        List<SubjectListBean.DataBean.ItemsBean> been = subjectListBean.getData().getItems();
        pagerLastId = been.get(been.size() - 1).getId() + "";
        LogUtils.loge("TAG",pagerLastId);
        RequestParams params = new RequestParams(Url.SBUJECT_LISTVIEW_MORE + "pg_cur=" +
                pagerCurrent + "&pg_size=20&since_id=" + pagerLastId);

        params.setConnectTimeout(5000);

        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                LogUtils.loge("TAG" ," subject 专题页面的上拉加载更多数据 加载成功onSuccess " );

                LogUtils.loge("TAG","subject 上拉加载更多数据 " + result);
                if (result != null) {
                    //解析获取的json数据
                    processMoreData(result);
                }

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                LogUtils.loge("TAG" ," subject 专题页面的上拉加载更多数据 加载失败 onError" );

            }

            @Override
            public void onCancelled(CancelledException cex) {
                LogUtils.loge("TAG" ," subject 专题页面的上拉加载更多数据  onCancelled" );

            }

            @Override
            public void onFinished() {
                LogUtils.loge("TAG" ," subject 专题页面的上拉加载更多数据 onFinished " );

            }
        });
    }

    /**
     * 解析加载更多获取的数据
     * @param moreJson
     */
    private void processMoreData(String moreJson) {

        SubjectListBean listMoreBean = parseListJson(moreJson);
        List<SubjectListBean.DataBean.ItemsBean> listMoreItems = listMoreBean.getData().getItems();

        itemsListbeen.addAll(listMoreItems);
        //更新适配器
        listAdapter.notifyDataSetChanged();

        pagerCurrent++;

        listView.onRefreshComplete();

    }


    /**
     * listview json 数据的解析
     *
     * @param json
     */
    private SubjectListBean parseListJson(String json) {
        return new Gson().fromJson(json, SubjectListBean.class);
    }


    /**
     * 联网请求网络数据
     */
    private void getDataFormNet() {
        RequestParams params = new RequestParams(Url.SBUJECT_GRIDVIEW);
        params.setConnectTimeout(5000);

        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                if (result != null) {
                    processData(result);
                    //缓存数据
                    CacheUtils.putString(mActivity, CacheUtils.SUBJECT_TOP_DATA, result);

                    Log.d("TAG", "subjectlistview的数据解析成功" + result);
                } else {
                    Toast.makeText(mActivity, "未找到数据,请检查网络", Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                LogUtils.loge("TAG", "onError" + "请求数据失败");
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    /**
     * 解析数据
     *
     * @param json
     */
    private void processData(String json) {
        topBeans = (SubjectTopBean) parseJson(json);

        LogUtils.loge("topBeans = " + topBeans);

        itemsBeens = topBeans.getData().getItems();

//        subjectTopAdapter = new SubjectTopAdapter(mActivity, itemsBeens);
//        gridView_top.setAdapter(subjectTopAdapter);
    }

    /**
     * 使用 json解析
     *
     * @param json
     */
    private Object parseJson(String json) {
        return new Gson().fromJson(json, SubjectTopBean.class);
    }

    /*
      注册Eventbus*/

    @Override
    public void registerEventBus() {
        if (!EventBus.getDefault().isRegistered(this)) {//判断是否注册
            EventBus.getDefault().register(this);
        }
    }


    @Override
    public void unRegisterEventBus() {
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);

        }
    }


    @Subscribe
    public void onEventMainThread(SubjectListBean subjectListBean) {
        this.subjectListBean = subjectListBean;
        LogUtils.loge("subjectListBean = " + subjectListBean);
        LogUtils.loge("subjectListBean = " + subjectListBean);

        pagerCurrent++;

        loadAdapter();
    }

    /**
     * 加载 数据中
     */
    private void loadAdapter() {

        itemsListbeen = subjectListBean.getData().getItems();
        // Log.d("TAG","专题页面"+ "itemsListbeen.size():" + itemsListbeen.size());



        if (listAdapter == null) {
            listAdapter = new SubjectListAdapter(mActivity, itemsListbeen, itemsBeens);
            Log.d("TAG", "SubjectPager" + "走到这里");
        }
        listView.setAdapter(listAdapter);

        //数据请求完成
        listView.onRefreshComplete();

        if (listAdapter != null) {

            loadingAnim.stop();  //停止动画
            ll_subject_loading.setVisibility(View.GONE);
            listView.setVisibility(View.VISIBLE);
        }
    }


}
