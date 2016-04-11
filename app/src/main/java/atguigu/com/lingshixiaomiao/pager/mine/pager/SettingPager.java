package atguigu.com.lingshixiaomiao.pager.mine.pager;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import atguigu.com.lingshixiaomiao.R;
import atguigu.com.lingshixiaomiao.pager.mine.activity.MineContentActivity;
import atguigu.com.lingshixiaomiao.pager.mine.base.ContentBasePager;
import atguigu.com.lingshixiaomiao.pager.mine.utils.MineUtils;

/**
 * Created by lanmang on 2016/4/10.
 */
public class SettingPager extends ContentBasePager implements View.OnClickListener {

    private LinearLayout ll_mine_setting_about;
    private LinearLayout ll_mine_setting_address;
    private LinearLayout ll_setting_version;
    private LinearLayout ll_mine_setting_private;
    private LinearLayout ll_setting_clear_cache;
    private CheckBox cb_mine_setting_push;

    /**
     * 构造方法
     *
     * @param mActivity
     */
    public SettingPager(Activity mActivity) {
        super(mActivity);
    }

    @Override
    public View initView() {
        View inflate = View.inflate(mActivity, R.layout.mine_setting, null);
        findViewById(inflate);
        return inflate;
    }

    private void findViewById(View v) {
        ll_mine_setting_about = (LinearLayout) v.findViewById(R.id.ll_mine_setting_about);
        ll_setting_version = (LinearLayout) v.findViewById(R.id.ll_setting_version);
        ll_setting_clear_cache = (LinearLayout) v.findViewById(R.id.ll_setting_clear_cache);
        ll_mine_setting_address = (LinearLayout) v.findViewById(R.id.ll_mine_setting_address);
        ll_mine_setting_private = (LinearLayout) v.findViewById(R.id.ll_mine_setting_private);
        cb_mine_setting_push = (CheckBox)v.findViewById(R.id.cb_mine_setting_push);
        //设置当前版本
        setVersion();

        //设置监听
        //关于零食小喵
        ll_mine_setting_about.setOnClickListener(this);
        //地址管理
        ll_mine_setting_address.setOnClickListener(this);
        //个人资料
        ll_mine_setting_private.setOnClickListener(this);
        //获取更新 当前版本
        ll_setting_version.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mActivity, "点击更新", Toast.LENGTH_SHORT).show();
            }
        });
        //清除缓存
        ll_setting_clear_cache.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mActivity, "点击清除缓存", Toast.LENGTH_SHORT).show();
            }
        });
        //是否打开推送
        cb_mine_setting_push.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(mActivity, "开启推送", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(mActivity, "关闭推送", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * 设置当前版本号
     */
    private void setVersion() {
        TextView tv_mine_setting_version = (TextView) ll_setting_version.findViewById(R.id.tv_mine_setting_version);
        tv_mine_setting_version.setText(MineUtils.getVersion(mActivity));
    }

    @Override
    public String setTitle() {
        return "设置";
    }

    @Override
    public void initData() {
        super.initData();

    }

    private int position;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_mine_setting_about://关于零食小喵
                position = 11;
                break;
            case R.id.ll_mine_setting_address://管理地址
                position = 12;
                break;
            case R.id.ll_mine_setting_private://个人资料
                position = 13;
                break;
        }
        startActivity(MineContentActivity.class);
    }

    private void startActivity(Class clazz) {
        Intent intent = new Intent(mActivity, clazz);
        intent.putExtra("pager", position);
        mActivity.startActivity(intent);
    }


}
