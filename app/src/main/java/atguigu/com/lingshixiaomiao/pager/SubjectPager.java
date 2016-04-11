package atguigu.com.lingshixiaomiao.pager;


import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import atguigu.com.lingshixiaomiao.R;
import atguigu.com.lingshixiaomiao.base.BasePager;
import atguigu.com.lingshixiaomiao.pager.subject.adapter.SubjectListAdapter;
import atguigu.com.lingshixiaomiao.pager.subject.adapter.SubjectTopAdapter;
import atguigu.com.lingshixiaomiao.pager.subject.bean.SubjectTopBean;
import atguigu.com.lingshixiaomiao.pager.subject.utils.JsonUtils;
import atguigu.com.lingshixiaomiao.pager.subject.utils.Url;
import atguigu.com.lingshixiaomiao.pager.subject.view.NoscrollGridView;


/**
 * Created by lanmang on 2016/4/8.
 * 专题页面
 */
public class SubjectPager extends BasePager {


    private JsonUtils jsonUtils;
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
        getDataFormNet();



        List<String> listBeans = new ArrayList<String>();
        for (int i = 0; i < 20; i++) {
            String listBean = "list" + i;
            listBeans.add(listBean);
        }
        listAdapter = new SubjectListAdapter(mActivity, listBeans);
        listView.setAdapter(listAdapter);

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


//    /**
//     * 注册Eventbus
//     */
//    @Override
//    public void registerEventBus() {
//        if (!EventBus.getDefault().isRegistered(this)) {//判断是否注册
//            EventBus.getDefault().register(this);
//        }
//    }
//
//
//    @Override
//    public void unRegisterEventBus() {
//        if (EventBus.getDefault().isRegistered(this)) {
//            EventBus.getDefault().unregister(this);
//
//        }
//    }
}
