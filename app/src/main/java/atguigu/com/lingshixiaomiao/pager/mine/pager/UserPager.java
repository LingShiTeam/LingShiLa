package atguigu.com.lingshixiaomiao.pager.mine.pager;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import atguigu.com.lingshixiaomiao.R;
import atguigu.com.lingshixiaomiao.pager.mine.activity.MineContentActivity;
import atguigu.com.lingshixiaomiao.pager.mine.base.ContentBasePager;
import atguigu.com.lingshixiaomiao.pager.mine.bean.LoginBean;
import atguigu.com.lingshixiaomiao.pager.mine.utils.Constants;
import atguigu.com.lingshixiaomiao.pager.mine.utils.LoginUtils;

/**
 * Created by lanmang on 2016/4/10.
 */
public class UserPager extends ContentBasePager implements View.OnClickListener {

    private TextView tv_mine_setting_user_nickname;
    private ImageView iv_mine_setting_user_header;
    private LinearLayout ll_mine_setting_user_header;
    private LinearLayout ll_mine_setting_user_nickname;

    private int position;
    private LoginBean.DataEntity data;

    /**
     * 构造方法
     *
     * @param mActivity
     */
    public UserPager(Activity mActivity) {
        super(mActivity);
    }

    @Override
    public View initView() {
        View inflate = View.inflate(mActivity, R.layout.mine_user, null);
        findViewById(inflate);
        return inflate;
    }

    private void findViewById(View v) {
        tv_mine_setting_user_nickname = (TextView) v.findViewById(R.id.tv_mine_setting_user_nickname);
        iv_mine_setting_user_header = (ImageView) v.findViewById(R.id.iv_mine_setting_user_header);
        ll_mine_setting_user_header = (LinearLayout) v.findViewById(R.id.ll_mine_setting_user_header);
        ll_mine_setting_user_nickname = (LinearLayout) v.findViewById(R.id.ll_mine_setting_user_nickname);

        setListener();
    }

    private void setListener() {
        ll_mine_setting_user_nickname.setOnClickListener(this);
        ll_mine_setting_user_header.setOnClickListener(this);
    }

    @Override
    public String setTitle() {
        return "个人资料";
    }

    @Override
    public void initData() {
        super.initData();
        if (LoginUtils.getInstance().isLogin()) {
            data = ((LoginBean) LoginUtils.getInstance().getData()).getData();
            String nickname = data.getNickname();
            tv_mine_setting_user_nickname.setText(nickname);
            String headerUrl = data.getAvatar();
            ImageOptions options = new ImageOptions.Builder().setPlaceholderScaleType(ImageView.ScaleType.CENTER_CROP).build();
            x.image().bind(iv_mine_setting_user_header, headerUrl, options);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_mine_setting_user_nickname:
                position = Constants.CHANGE_NICKNAME_PAGER;
                break;
            case R.id.ll_mine_setting_user_header:
                position = Constants.CHANGE_HEADER_PAGER;
                break;
        }
        Intent intent = new Intent(mActivity, MineContentActivity.class);
        intent.putExtra("pager", position);
        mActivity.startActivity(intent);
    }

    /**
     * 获取修改个人资料返回值
     *
     */
    @Subscribe
    public void onEventMainThread(LoginUtils loginUtils) {
        LoginBean loginBean = (LoginBean) loginUtils.getData();
        String nickname = loginBean.getData().getNickname();
        tv_mine_setting_user_nickname.setText(nickname);
    }

    @Override
    public void registerEventBus() {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public void unRegisterEventBus() {
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }
}
