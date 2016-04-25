package atguigu.com.lingshixiaomiao.pager.mine.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by lanmang on 2016/4/19.
 * 禁止滑动的GridView
 */
public class NoScrollListView extends ListView {


    public NoScrollListView(Context context, AttributeSet attrs, int defStyle) {

        super(context, attrs, defStyle);

        // TODO Auto-generated constructor stub

    }


    public NoScrollListView(Context context, AttributeSet attrs) {

        super(context, attrs);

        // TODO Auto-generated constructor stub

    }


    public NoScrollListView(Context context) {

        super(context);

        // TODO Auto-generated constructor stub

    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }

}
