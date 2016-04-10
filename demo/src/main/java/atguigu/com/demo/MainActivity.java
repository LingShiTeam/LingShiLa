package atguigu.com.demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private RefreshLayout refreshlayout;
    private ListView listview;
    private List<String> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        refreshlayout = (RefreshLayout)findViewById(R.id.refreshlayout);
        listview = (ListView)findViewById(R.id.listview);

        data = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            data.add("-------------------" + i);
        }

        listview.setAdapter(new MyAdapter());
        refreshlayout.setOnLoadListener(new RefreshLayout.OnLoadListener() {
            @Override
            public void onLoad() {
                //refreshlayout.setLoading(false);
            }
        });

    }

    class MyAdapter extends BaseAdapter {

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
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = View.inflate(MainActivity.this, R.layout.item, null);
            TextView textview = (TextView) convertView.findViewById(R.id.textview);
            textview.setText(data.get(position));
            return convertView;
        }
    }
}
