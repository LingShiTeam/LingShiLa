package atguigu.com.lingshixiaomiao.pager.subject.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by CheungJhonny on 2016/4/11.
 */
public class SubjectListAdapter extends BaseAdapter{

    private final Activity activity;
    private final List<String> listBeans;

    public SubjectListAdapter(Activity activity, List<String> listBeans) {

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

        TextView textView = new TextView(activity);
        textView.setTextSize(20);
        textView.setTextColor(Color.BLACK);

        textView.setText(listBeans.get(position));
        return textView;
    }
}
