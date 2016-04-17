package atguigu.com.lingshixiaomiao.pager.mine.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by lanmang on 2016/4/15.
 */
public class InterceptLinearLayout extends LinearLayout {
    public InterceptLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;
    }
}
