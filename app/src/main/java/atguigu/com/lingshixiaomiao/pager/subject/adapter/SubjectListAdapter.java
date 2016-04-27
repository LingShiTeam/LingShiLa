package atguigu.com.lingshixiaomiao.pager.subject.adapter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.xutils.common.util.DensityUtil;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.List;

import atguigu.com.lingshixiaomiao.LogUtils;
import atguigu.com.lingshixiaomiao.R;
import atguigu.com.lingshixiaomiao.pager.subject.bean.SubjectListBean;
import atguigu.com.lingshixiaomiao.pager.subject.bean.SubjectTopBean;
import atguigu.com.lingshixiaomiao.pager.subject.ui.SubjectTopActivity;
import atguigu.com.lingshixiaomiao.pager.subject.view.NoscrollGridView;

/**
 * Created by CheungJhonny on 2016/4/11.
 */
public class SubjectListAdapter extends BaseAdapter {
    /**
     * 根据position 来显示item的不同类型
     */
    private static final int TYPE_ITEM_GRIDVIEW = 0; //position= 0 时候item的类型
    private static final int TYEP_ITEM_LISTVIEW = 1; //position != 0 时 item的类型

    private final Activity activity;
    /**
     * listview 的数据对象
     */
    private final List<SubjectListBean.DataBean.ItemsBean> listBeans;
    /**
     * 上部 gridview的集合数据
     */
    private final List<SubjectTopBean.DataBean.ItemsBean> itemsBeens;

    private final ImageView iv_subject_tiptop;

    //GridView
    private SubjectTopAdapter subjectTopAdapter;

    private ImageOptions imageOptions = new ImageOptions.Builder()
            //.setSize(DensityUtil.dip2px(120), DensityUtil.dip2px(70))
            .setRadius(DensityUtil.dip2px(5))
            // 如果ImageView的大小不是定义为wrap_content, 不要crop.
            .setCrop(false)
            // 加载中或错误图片的ScaleType
            .setPlaceholderScaleType(ImageView.ScaleType.CENTER_CROP)
            .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
            .setConfig(Bitmap.Config.ARGB_4444)
           // .setLoadingDrawableId(R.drawable.default_project612_306)
           // .setFailureDrawableId(R.drawable.de)
            .build();


    private NoscrollGridView gridview_top;


    public SubjectListAdapter(Activity activity, List<SubjectListBean.DataBean.ItemsBean> listBeans,
                              List<SubjectTopBean.DataBean.ItemsBean> itemsBeens, ImageView iv_subject_tiptop) {

        this.activity = activity;
        this.listBeans = listBeans;
        this.itemsBeens = itemsBeens;
        this.iv_subject_tiptop = iv_subject_tiptop;

    }

    @Override
    public int getCount() {
        return listBeans.size() + 1;
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
    public int getItemViewType(int position) {
       return position == 0 ? TYPE_ITEM_GRIDVIEW : TYEP_ITEM_LISTVIEW;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;
        int type = getItemViewType(position);
        if (convertView == null) {
            holder = new ViewHolder();
            switch (type) {
                case TYPE_ITEM_GRIDVIEW:
                    convertView = View.inflate(activity, R.layout.subject_pager_topgridview, null);
                    gridview_top = (NoscrollGridView) convertView.findViewById(R.id.gridview_top);
                    convertView.setTag(true);
                    break;

                case TYEP_ITEM_LISTVIEW:
                    convertView = View.inflate(activity, R.layout.item_subject_listview, null);
                    //holder = new ViewHolder();
                    holder.item_subject_img = (ImageView) convertView.findViewById(R.id.item_subject_img);
                    holder.item_subject_title = (TextView) convertView.findViewById(R.id.item_subject_title);
                    holder.item_subject_collect = (TextView) convertView.findViewById(R.id.item_subject_collect);
                    convertView.setTag(holder);
                    break;
            }


        } else {
            if (position != 0) {
                Object tag = convertView.getTag();

                if (tag instanceof Boolean) {
                    holder = new ViewHolder();
                    convertView = View.inflate(activity, R.layout.item_subject_listview, null);
                    //holder = new ViewHolder();
                    holder.item_subject_img = (ImageView) convertView.findViewById(R.id.item_subject_img);
                    holder.item_subject_title = (TextView) convertView.findViewById(R.id.item_subject_title);
                    holder.item_subject_collect = (TextView) convertView.findViewById(R.id.item_subject_collect);
                    convertView.setTag(holder);
                }else{
                    holder = (ViewHolder) convertView.getTag();
                }

            }else{
                convertView = View.inflate(activity, R.layout.subject_pager_topgridview, null);
                gridview_top = (NoscrollGridView) convertView.findViewById(R.id.gridview_top);
                convertView.setTag(true);
            }

        }


        if (position >= 10) {
            iv_subject_tiptop.setVisibility(View.VISIBLE);
        } else {
            iv_subject_tiptop.setVisibility(View.GONE);
        }


        switch (type) {
            case TYPE_ITEM_GRIDVIEW:

                subjectTopAdapter = new SubjectTopAdapter(activity, itemsBeens);
                gridview_top.setAdapter(subjectTopAdapter);
                break;
            case TYEP_ITEM_LISTVIEW:

                    holder.item_subject_title.setText(listBeans.get(position-1).getTitle());

                    holder.item_subject_collect.setText(listBeans.get(position - 1).getHotindex() + "");
                    String url = listBeans.get(position - 1).getImg().getImg_url();
                    Log.d("SubjectListAdapter", "url != null:" + (url != null));

                    LogUtils.loge("imageUrl = " + url);
                    x.image().bind(holder.item_subject_img, url,imageOptions);

                break;
        }
        //设置顶部item 的点击监听事件
        gridview_top.setOnItemClickListener(new MyOnItemClickListener());
        return convertView;
    }

    /**
     * top GridView 的点击监听
     */
    class MyOnItemClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            Intent intent = new Intent(activity, SubjectTopActivity.class);

            intent.putExtra("topUrl", itemsBeens.get(position).getAction().getInfo());


            activity.startActivity(intent);
        }
    }

    class ViewHolder {

        ImageView item_subject_img;// 图篇
        TextView item_subject_title;// 新闻标题
        TextView item_subject_collect; // 收藏的人数

    }




}
