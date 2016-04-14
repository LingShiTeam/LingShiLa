package atguigu.com.lingshixiaomiao.pager;


import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

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
import atguigu.com.lingshixiaomiao.pager.subject.adapter.SubjectListAdapter;
import atguigu.com.lingshixiaomiao.pager.subject.adapter.SubjectTopAdapter;
import atguigu.com.lingshixiaomiao.pager.subject.bean.SubjectListBean;
import atguigu.com.lingshixiaomiao.pager.subject.bean.SubjectTopBean;
import atguigu.com.lingshixiaomiao.pager.subject.utils.CacheUtils;
import atguigu.com.lingshixiaomiao.pager.subject.utils.JsonUtils;
import atguigu.com.lingshixiaomiao.pager.subject.utils.SubjectNetUtils;
import atguigu.com.lingshixiaomiao.pager.subject.utils.Url;
import atguigu.com.lingshixiaomiao.pager.subject.view.NoscrollGridView;


/**
 * Created by lanmang on 2016/4/8.
 * 专题页面
 */
public class SubjectPager extends BasePager {


    @ViewInject(R.id.listview_subject)
    private ListView listView;

    @ViewInject(R.id.iv_cart)
    private ImageView iv_cart;


    @ViewInject(R.id.gridview_top)
    private NoscrollGridView gridView_top;


    /**
     * 上部gridview的适配器
     */
    private SubjectTopAdapter subjectTopAdapter;
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

        View topView = View.inflate(mActivity, R.layout.subject_pager_topgridview, null);
        //gridView_top = (NoscrollGridView) topView.findViewById(R.id.gridview_top);
        x.view().inject(this, topView);

        listView.addHeaderView(topView);

        registerEventBus();
        return view;

    }

    @Override
    public void initData() {
        super.initData();


//        List<String> beans = new ArrayList<String>();
//        for (int i = 0 ; i<4;i++) {
//            String bean = "grid" + i;
//            beans.add(bean);
//        }


        //联网获取数据
        //这里需要判断网络状态
        if (!SubjectNetUtils.isNetworkConnected()) {
            Toast.makeText(mActivity, "请检查网络", Toast.LENGTH_SHORT).show();
        }
        if (CacheUtils.getString(mActivity,CacheUtils.SUBJECT_TOP_DATA) != ""){
            processData(CacheUtils.getString(mActivity,CacheUtils.SUBJECT_TOP_DATA));

        }

        getDataFormNet();


        new JsonUtils().loadData(Url.SBUJECT_LISTVIEW, SubjectListBean.class);
        //获取下部listview的数据

//        List<String> listBeans = new ArrayList<String>();
//        for (int i = 0; i < 20; i++) {
//            String listBean = "list" + i;
//            listBeans.add(listBean);
//        }
//        listAdapter = new SubjectListAdapter(mActivity, listBeans);
//        listView.setAdapter(listAdapter);

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
     * @param json
     */
    private void processData(String json) {
        topBeans = (SubjectTopBean) parseJson(json);

        LogUtils.loge("topBeans = " +topBeans);

        itemsBeens = topBeans.getData().getItems();

        subjectTopAdapter = new SubjectTopAdapter(mActivity, itemsBeens);
        gridView_top.setAdapter(subjectTopAdapter);
    }

    /**
     * 使用 json解析
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
        loadAdapter();
        Log.d("TAG", "subjectListBean == null:" + (subjectListBean == null));
    }

    /**
     * 加载 数据中
     */
    private void loadAdapter() {

        itemsListbeen = subjectListBean.getData().getItems();
       // Log.d("TAG","专题页面"+ "itemsListbeen.size():" + itemsListbeen.size());

        if (listAdapter == null) {
            listAdapter = new SubjectListAdapter(mActivity, itemsListbeen);
            Log.d("TAG","SubjectPager"+  "走到这里");
        }
            listView.setAdapter(listAdapter);

    }


}
