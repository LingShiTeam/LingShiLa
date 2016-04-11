package atguigu.com.lingshixiaomiao.pager;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import atguigu.com.lingshixiaomiao.LogUtils;
import atguigu.com.lingshixiaomiao.R;
import atguigu.com.lingshixiaomiao.base.BasePager;
import atguigu.com.lingshixiaomiao.pager.mine.activity.MineContentActivity;
import atguigu.com.lingshixiaomiao.pager.mine.utils.Constants;

/**
 * Created by lanmang on 2016/4/8.
 * 我的小喵页面
 */
public class MinePager extends BasePager implements View.OnClickListener {

    private ImageButton ib_mine_setting;
    private LinearLayout ll_mine_call_service;
    private int position;

    public MinePager(Activity mActivity) {
        super(mActivity);
    }

    @Override
    public View initView() {
        View inflate = View.inflate(mActivity, R.layout.mine_pager, null);
        findViewById(inflate);
        return inflate;
    }

    private void findViewById(View v) {
        ib_mine_setting = (ImageButton) v.findViewById(R.id.ib_mine_setting);
        ll_mine_call_service = (LinearLayout) v.findViewById(R.id.ll_mine_call_service);

        //设置监听
        ib_mine_setting.setOnClickListener(this);
        //联系客服
        ll_mine_call_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startCallService();
            }
        });
    }

    @Override
    public void initData() {
        super.initData();
        LogUtils.loge("我的小喵 数据加载了");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ib_mine_setting://设置界面
                position = 0;
                break;
        }
        startActivity(MineContentActivity.class);
    }

    /**
     * 联系客服
     */
    private void startCallService() {

         View inflate = View.inflate(mActivity, R.layout.mine_call_service, null);

        new AlertDialog.Builder(mActivity)
                .setMessage("客服电话:18686823149")
                .setNegativeButton("取消", null)
                .setPositiveButton("呼叫", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + Constants.serviceNum));
                        mActivity.startActivity(intent);
                    }
                })
                .show();
               /* .setView(inflate);*/

      /*  AlertDialog alertDialog = builder.create();
        Window window = alertDialog.getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        int height = mActivity.getWindowManager().getDefaultDisplay().getHeight();
        attributes.height = (int) (height*0.6);
        window.setAttributes(attributes);
        builder.show();*/
    }

    private void startActivity(Class clazz) {
        Intent intent = new Intent(mActivity, clazz);
        intent.putExtra("pager", position);
        mActivity.startActivity(intent);
    }
}
