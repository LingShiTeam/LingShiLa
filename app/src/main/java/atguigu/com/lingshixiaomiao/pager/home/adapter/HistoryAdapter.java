package atguigu.com.lingshixiaomiao.pager.home.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import atguigu.com.lingshixiaomiao.R;
import greendao.searchbean.SearchHistory;
import greendao.searchbean.SearchHistoryDao;

/**
 * Created by Liu_haiwei on 2016/4/12.
 * 搜索记录的适配器
 */
public class HistoryAdapter extends BaseAdapter {

    private final Context context;
    private final List<SearchHistory> lists;
    private final SearchHistoryDao dao;
    private final ListView listView;

    public HistoryAdapter(Context context, List<SearchHistory> lists, SearchHistoryDao dao, ListView listView) {
        this.context = context;
        this.lists = lists;
        this.dao = dao;
        this.listView = listView;
    }

    @Override
    public int getCount() {
        return lists != null ? lists.size() : 0;
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

        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(context, R.layout.history_list_item, null);
            holder.tv_history_record = (TextView) convertView.findViewById(R.id.tv_history_record);
            holder.tv_history_time = (TextView) convertView.findViewById(R.id.tv_history_time);
            holder.iv_history_delete = (ImageView) convertView.findViewById(R.id.iv_history_delete);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        SearchHistory data = lists.get(position);

        holder.tv_history_record.setText(data.getRecord());
        holder.tv_history_time.setText(data.getTime());
        holder.iv_history_delete.setOnClickListener(new MyOnClickListener(position));

        return convertView;
    }

    static class ViewHolder {
        TextView tv_history_record;
        TextView tv_history_time;
        ImageView iv_history_delete;
    }

    /**
     * 点击事件监听
     */
    class MyOnClickListener implements View.OnClickListener {

        private final int position;

        public MyOnClickListener(int position) {
            this.position = position;
        }

        @Override
        public void onClick(View v) {
            if (lists != null && dao != null) {
                dao.delete(lists.get(position));
                lists.remove(position);
                if(lists.size() == 0) {
                   if(listView.isShown()) {
                       listView.setVisibility(View.GONE);
                   }
                }
            }
            notifyDataSetChanged();
        }
    }
}
