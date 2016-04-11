package atguigu.com.lingshixiaomiao.pager.subject.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by CheungJhonny on 2016/4/10.
 * 自定义Gridview  不可以滑动
 */
public class NoscrollGridView extends GridView {

    public NoscrollGridView(Context context) {
        super(context);
    }

    public NoscrollGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    /**
     *  自定义listview 中 取消 gridview 的滑动
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expendSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);

        super.onMeasure(widthMeasureSpec, expendSpec);
    }
}
