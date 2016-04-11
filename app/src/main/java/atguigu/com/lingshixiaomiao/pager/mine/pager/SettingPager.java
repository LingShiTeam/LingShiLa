package atguigu.com.lingshixiaomiao.pager.mine.pager;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.File;
import java.util.List;

import atguigu.com.lingshixiaomiao.LogUtils;
import atguigu.com.lingshixiaomiao.R;
import atguigu.com.lingshixiaomiao.application.MyApplication;
import atguigu.com.lingshixiaomiao.pager.mine.activity.MineContentActivity;
import atguigu.com.lingshixiaomiao.pager.mine.base.ContentBasePager;
import atguigu.com.lingshixiaomiao.pager.mine.bean.UpdateBean;
import atguigu.com.lingshixiaomiao.pager.mine.utils.CacheClearUtils;
import atguigu.com.lingshixiaomiao.pager.mine.utils.CacheUtils;
import atguigu.com.lingshixiaomiao.pager.mine.utils.MineUtils;
import atguigu.com.lingshixiaomiao.pager.mine.utils.PushUtils;
import atguigu.com.lingshixiaomiao.pager.mine.utils.UpdateUtils;

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
        cb_mine_setting_push = (CheckBox) v.findViewById(R.id.cb_mine_setting_push);
        //设置当前版本
        setVersion();

        //设置监听
        setListener();

    }

    /**
     * 设置监听
     */
    private void setListener() {
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
                new UpdateUtils(mActivity).checkUpdate();
            }
        });
        //清除缓存
        ll_setting_clear_cache.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showClearCacheDialog();
            }
        });
        //是否打开推送
        cb_mine_setting_push.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    CacheUtils.setCache(CacheUtils.getSmallFile(mActivity, "push"), "是");
                } else {
                    CacheUtils.setCache(CacheUtils.getSmallFile(mActivity, "push"), "否");
                }
                //重置Jpush 极光推送
                MyApplication application = (MyApplication) mActivity.getApplication();
                application.setJpush();
            }
        });
    }

    @Subscribe
    public void onEventMainThread(UpdateBean updateBean) {
        LogUtils.loge("更新 = " + updateBean);
        String rs_code = updateBean.getRs_code();
        if ("1000".equals(rs_code)) {
            new UpdateUtils(mActivity).showUpdateDialog(updateBean);
        }else if("1004".equals(rs_code)){
            Toast.makeText(mActivity, "您的小喵已经是最新版本了哦~", Toast.LENGTH_SHORT).show();
        }
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

    private void showClearCacheDialog() {
        new AlertDialog.Builder(mActivity)
                .setMessage("是否清除所有缓存数据?")
                .setPositiveButton("清除", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        clearCache();
                    }
                })
                .setNegativeButton("取消", null)
                .show();
    }

    /**
     * 清除缓存
     */
    private void clearCache() {
        //清除内部cache文件夹数据
        CacheClearUtils.cleanInternalCache(mActivity);
        //清楚外部cache文件夹数据
        CacheClearUtils.cleanExternalCache(mActivity);
        Toast.makeText(mActivity, "缓存已经清空", Toast.LENGTH_SHORT).show();
        loadCacheSize();
    }

    /**
     * 获取缓存大小
     */
    private void loadCacheSize() {
        TextView tv_mine_setting_clear_cache = (TextView) ll_setting_clear_cache.findViewById(R.id.tv_mine_setting_clear_cache);
        List<File> cacheFiles = CacheUtils.getCacheFiles(mActivity);
        long folderSizes = 0;
        for (int i = 0; i < cacheFiles.size(); i++) {
            long folderSize = CacheClearUtils.getFolderSize(cacheFiles.get(position));
            LogUtils.loge("缓存数据大小 = " + CacheClearUtils.getFormatSize(folderSize));
            folderSizes += folderSize;
        }
        String cacheSizeStr = CacheClearUtils.getFormatSize(folderSizes);
        tv_mine_setting_clear_cache.setText(cacheSizeStr);
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
        //获取缓存大小
        loadCacheSize();
        //获取是否开启推送
        cb_mine_setting_push.setChecked(PushUtils.isPush(mActivity));
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
