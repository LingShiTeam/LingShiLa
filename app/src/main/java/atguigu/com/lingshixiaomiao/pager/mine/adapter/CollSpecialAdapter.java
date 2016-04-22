package atguigu.com.lingshixiaomiao.pager.mine.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.xutils.x;

import java.util.List;

import atguigu.com.lingshixiaomiao.R;
import atguigu.com.lingshixiaomiao.pager.mine.bean.CollSpecialBean;

/**
 * Created by lanmang on 2016/4/20.
 */
public class CollSpecialAdapter extends BaseAdapter{
    private Activity mActivity;
    private List<CollSpecialBean.DataEntity.ItemsEntity> data;
    public CollSpecialAdapter(Activity mActivity, List<CollSpecialBean.DataEntity.ItemsEntity> data) {
        this.mActivity = mActivity;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
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
    public View getView(int position, View c, ViewGroup parent) {
        ViewHolder h;

        final CollSpecialBean.DataEntity.ItemsEntity item = data.get(position);

        if (c == null) {
            h = new ViewHolder();
            c = View.inflate(mActivity, R.layout.mine_coll_special_item, null);
                h.iv_mine_coll_special_image = (ImageView) c.findViewById(R.id.iv_mine_coll_special_image);
                h.tv_mine_coll_special_like = (TextView) c.findViewById(R.id.tv_mine_coll_special_like);
                h.tv_mine_coll_special_des = (TextView) c.findViewById(R.id.tv_mine_coll_special_des);
                h.rl_iv_mine_coll_special = (RelativeLayout) c.findViewById(R.id.rl_iv_mine_coll_special);
                h.cb_mine_coll_special_del = (CheckBox) c.findViewById(R.id.cb_mine_coll_special_del);

            h.rl_iv_mine_coll_special.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mActivity, "跳转详情页面", Toast.LENGTH_SHORT).show();
                }
            });

            h.cb_mine_coll_special_del.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    item.setChecked(isChecked);
                }
            });

            c.setTag(h);
        }else{
            h = (ViewHolder) c.getTag();
        }

        h.tv_mine_coll_special_des.setText(item.getTitle());
        h.tv_mine_coll_special_like.setText(item.getHotindex() + "");
        x.image().bind(h.iv_mine_coll_special_image, item.getImg().getImg_url());

        if (item.isShowDelete()) {
            h.cb_mine_coll_special_del.setVisibility(View.VISIBLE);
        }else{
            h.cb_mine_coll_special_del.setVisibility(View.GONE);
        }

        return c;
    }

    class ViewHolder{
        ImageView iv_mine_coll_special_image;
        TextView tv_mine_coll_special_like;
        TextView tv_mine_coll_special_des;
        RelativeLayout rl_iv_mine_coll_special;
        CheckBox cb_mine_coll_special_del;
    }
}
