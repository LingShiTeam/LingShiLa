package atguigu.com.lingshixiaomiao.pager.subject.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.xutils.x;

import java.util.List;

import atguigu.com.lingshixiaomiao.R;
import atguigu.com.lingshixiaomiao.pager.subject.bean.SubjectListBean;

/**
 * Created by CheungJhonny on 2016/4/11.
 */
public class SubjectListAdapter extends BaseAdapter {

    private final Activity activity;
    private final List<SubjectListBean.DataBean.ItemsBean> listBeans;


//    private ImageOptions imageOptions = new ImageOptions.Builder()
//            // 如果ImageView的大小不是定义为wrap_content, 不要crop.
//            .setCrop(false)
//            // 加载中或错误图片的ScaleType
//            .setPlaceholderScaleType(ImageView.ScaleType.FIT_XY)
//            .setImageScaleType(ImageView.ScaleType.FIT_XY)
//            .setLoadingDrawableId(R.drawable.default_project612_306)
//            .setFailureDrawableId(R.drawable.default_project612_306)
//            .build();


    public SubjectListAdapter(Activity activity, List<SubjectListBean.DataBean.ItemsBean> listBeans) {

        this.activity = activity;
        this.listBeans = listBeans;
    }

    @Override
    public int getCount() {
        return listBeans.size();
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

        ViewHolder holder = null;
        if (convertView == null) {
            convertView = View.inflate(activity, R.layout.item_subject_listview, null);
            holder = new ViewHolder();

            holder.item_subject_img = (ImageView) convertView.findViewById(R.id.item_subject_img);
            holder.item_subject_title = (TextView) convertView.findViewById(R.id.item_subject_title);
            holder.item_subject_collect = (TextView) convertView.findViewById(R.id.item_subject_collect);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();

        }

        holder.item_subject_title.setText(listBeans.get(position).getTitle());

        holder.item_subject_collect.setText(listBeans.get(position).getHotindex() + "");
        x.image().bind(holder.item_subject_img, listBeans.get(position).getImg().getImg_url());
        return convertView;
    }

    class ViewHolder {

        ImageView item_subject_img;// 图篇
        TextView item_subject_title;// 新闻标题
        TextView item_subject_collect; // 收藏的人数

    }

}
