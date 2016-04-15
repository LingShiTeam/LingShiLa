package atguigu.com.lingshixiaomiao.pager.mine.pager;

import android.app.Activity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import atguigu.com.lingshixiaomiao.LogUtils;
import atguigu.com.lingshixiaomiao.R;
import atguigu.com.lingshixiaomiao.pager.mine.base.ContentBasePager;
import atguigu.com.lingshixiaomiao.pager.mine.bean.LoginBean;
import atguigu.com.lingshixiaomiao.pager.mine.bean.NicknameBean;
import atguigu.com.lingshixiaomiao.pager.mine.utils.CacheUtils;
import atguigu.com.lingshixiaomiao.pager.mine.utils.Constants;
import atguigu.com.lingshixiaomiao.pager.mine.utils.JsonUtils;
import atguigu.com.lingshixiaomiao.pager.mine.utils.LoginUtils;
import atguigu.com.lingshixiaomiao.pager.mine.utils.Url;

/**
 * Created by lanmang on 2016/4/10.
 */
public class ResetNicknamePager extends ContentBasePager {

    private EditText rt_mine_change_nickname;
    private Button btn_mine_change_nickname_complete;
    private LoginBean loginBean;

    /**
     * 构造方法
     *
     * @param mActivity
     */
    public ResetNicknamePager(Activity mActivity) {
        super(mActivity);
    }

    @Override
    public View initView() {
        View inflate = View.inflate(mActivity, R.layout.mine_user_change_nickname, null);
        findViewById(inflate);
        return inflate;
    }

    private void findViewById(View v) {
        rt_mine_change_nickname = (EditText) v.findViewById(R.id.rt_mine_change_nickname);
        rt_mine_change_nickname.requestFocus();
        btn_mine_change_nickname_complete = (Button) v.findViewById(R.id.btn_mine_change_nickname_complete);

        rt_mine_change_nickname.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    mActivity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                }
            }
        });

        btn_mine_change_nickname_complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                compl();
            }
        });
    }

    public void compl() {
        if (LoginUtils.getInstance().isLogin() && rt_mine_change_nickname.getText().toString().length() > 0) {
            String newNickname = rt_mine_change_nickname.getText().toString();
            loginBean = (LoginBean) LoginUtils.getInstance().getData();
            String uid = loginBean.getData().getUid();
            String url = Url.CHANGE_NICKNAME_URL[0] + uid + Url.CHANGE_NICKNAME_URL[1] + newNickname;
            new JsonUtils().loadData(url, NicknameBean.class);
        } else {
            Toast.makeText(mActivity, "昵称修改失败", Toast.LENGTH_SHORT).show();
        }

    }

    @Subscribe
    public void onEventMainThread(NicknameBean nicknameBean) {
        LogUtils.loge("nickname = " + nicknameBean);
        if (LoginUtils.getInstance().isLogin() && Constants.SUCCESS.equals(nicknameBean.getRs_code())) {
            loginBean.getData().setNickname(rt_mine_change_nickname.getText().toString());
            LoginUtils.getInstance().loginRequestSuccess(loginBean);
            String login = new Gson().toJson(loginBean);
            CacheUtils.setCache(CacheUtils.getSmallFile(mActivity, "login"), login);
        }
        mActivity.finish();
    }

    @Override
    public String setTitle() {
        return "修改昵称";
    }

    @Override
    public void initData() {
        super.initData();
        LoginBean loginBean = (LoginBean) LoginUtils.getInstance().getData();
        String nickname = loginBean.getData().getNickname();
        rt_mine_change_nickname.setHint(nickname);

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
