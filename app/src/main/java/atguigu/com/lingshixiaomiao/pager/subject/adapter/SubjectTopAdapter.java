package atguigu.com.lingshixiaomiao.pager.subject.adapter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import org.xutils.common.util.DensityUtil;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.List;

import atguigu.com.lingshixiaomiao.R;
import atguigu.com.lingshixiaomiao.pager.subject.bean.SubjectTopBean;

/**
 * Created by CheungJhonny on 2016/4/11.
 *
 */
public class SubjectTopAdapter extends BaseAdapter {

    private List<SubjectTopBean.DataBean.ItemsBean> ItemsBeans;
    private Activity activity;

    private ImageOptions imageOptions = new ImageOptions.Builder()
            //.setSize(DensityUtil.dip2px(120), DensityUtil.dip2px(70))
            .setRadius(DensityUtil.dip2px(5))
            // 如果ImageView的大小不是定义为wrap_content, 不要crop.
            .setCrop(false)
            // 加载中或错误图片的ScaleType
            //.setPlaceholderScaleType(ImageView.ScaleType.CENTER_INSIDE)
            .setImageScaleType(ImageView.ScaleType.CENTER_INSIDE)
            .setConfig(Bitmap.Config.ARGB_4444)
            //.setLoadingDrawableId(R.drawable.default_projects_small300_150)
            //.setFailureDrawableId(R.drawable.default_projects_small300_150)
            .build();

    public SubjectTopAdapter(Activity activity, List<SubjectTopBean.DataBean.ItemsBean> ItemsBeans) {
        this.activity = activity;
        this.ItemsBeans = ItemsBeans;
    }

    @Override
    public int getCount() {
        return 4;
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
        convertView = View.inflate(activity, R.layout.item_subjecttop, null);
        ImageView imageView = (ImageView) convertView;
        x.image().bind(imageView, ItemsBeans.get(position).getImg().getImg_url());

        return imageView;
    }
}
