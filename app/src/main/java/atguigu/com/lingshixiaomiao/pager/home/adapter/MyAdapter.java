package atguigu.com.lingshixiaomiao.pager.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import atguigu.com.lingshixiaomiao.R;


/**
 * Created by Liu_haiwei on 2016/3/31.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private static final int TYPE_HEADER = 0, TYPE_ITEM = 1, TYPE_BRAND = 2;
    private final Context context;
    private List<String> data;
    //private MyOnClickListener myOnClickListener;
    private View headView;
    private int headViewSize = 0;
    private boolean isAddHead = false;
    private int type;

    public MyAdapter(Context context, List<String> data) {
        this.context = context;
        this.data = data;
    }

    public void addHeadView(View view) {
        headView = view;
        headViewSize = 1;
        isAddHead = true;
    }

    @Override
    public int getItemViewType(int position) {
        type = TYPE_ITEM;
        if (headViewSize == 1 && position == 0) {
            type = TYPE_HEADER;
        }
        return type;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        switch (viewType) {
            case TYPE_HEADER:
                view = headView;
                break;
            case TYPE_ITEM:
                view = View.inflate(parent.getContext(), R.layout.linear_manager_item, null);
                break;
        }

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        switch (type) {
            case TYPE_HEADER:

                break;
            case TYPE_ITEM:
                holder.textView.setText(data.get(position - 1));
                break;
        }

        /*holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myOnClickListener.onClick(holder.view, position);
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return data.size() + headViewSize;
    }

    /*public void setMyOnClickListener(MyOnClickListener myOnClickListener) {
        this.myOnClickListener = myOnClickListener;
    }*/

    /*public interface MyOnClickListener {
        public void onClick(View view, int position);
    }
*/

    /**
     * 自定义的ViewHolder类,用于初始化item中的控件
     */
    class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        View view;

        public ViewHolder(View itemView) {
            super(itemView);
            this.view = itemView;
            textView = (TextView) itemView.findViewById(R.id.tv_item_content);
        }

    }
}
