package atguigu.com.lingshixiaomiao.pager.mine.pager;

import android.app.Activity;
import android.graphics.Bitmap;
import android.view.View;

import com.edmodo.cropper.CropImageView;

import atguigu.com.lingshixiaomiao.R;
import atguigu.com.lingshixiaomiao.pager.mine.base.ContentBasePager;

/**
 * Created by lanmang on 2016/4/10.
 */
public class ResetHeaderPager extends ContentBasePager {

    private CropImageView civ_mine_reset_header;

    /**
     * 构造方法
     *
     * @param mActivity
     */
    public ResetHeaderPager(Activity mActivity) {
        super(mActivity);
    }

    @Override
    public View initView() {
        View inflate = View.inflate(mActivity, R.layout.mine_header_reset, null);
        findViewById(inflate);
        return inflate;
    }

    private void findViewById(View v) {
        civ_mine_reset_header = (CropImageView)v.findViewById(R.id.civ_mine_reset_header);
    }

    @Override
    public String setTitle() {
        return "头像";
    }

    @Override
    public String setComplete() {
        return "完成";
    }

    @Override
    public void initData() {
        super.initData();

    }

    @Override
    public void complete(){
        //收尾
        Bitmap croppedImage = civ_mine_reset_header.getCroppedImage();

       /* RequestParams entity = new RequestParams();
        entity.addParameter("");// request URI: http://v0.api.upyun.com/lingshi/lingshi/avatar/2016/3/1f14c266-bc0d-4b06-a041-89d2911259e5.png
        x.http().get(entity, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                LogUtils.loge("上传图片 = " + result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });*/

        mActivity.finish();
    }

}
