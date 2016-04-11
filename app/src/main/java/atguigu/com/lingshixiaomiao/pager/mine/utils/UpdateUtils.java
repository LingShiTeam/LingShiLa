package atguigu.com.lingshixiaomiao.pager.mine.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

import atguigu.com.lingshixiaomiao.pager.mine.bean.UpdateBean;

/**
 * Created by lanmang on 2016/4/11.
 * 更新工具类
 */
public class UpdateUtils {

    private AlertDialog dialog;

    private Context context;

    private UpdateBean updateBean;

    public UpdateUtils(Context context) {
        this.context = context;
    }

    /**
     * 检查更新
     */
    public void checkUpdate() {
        String[] updates = {"旧版本的请求", "最新版的请求"};
        dialog = new AlertDialog.Builder(context)
                .setSingleChoiceItems(updates, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String url = null;
                        switch (which) {
                            case 0://旧版本的请求
                                url = Url.MINE_UPDATE_OLD;
                                break;
                            case 1:// 新版本的请求
                                url = Url.MINE_UPDATE_NEW;
                                break;
                        }

                        isUpdate(url);
                    }
                })
                .show();
    }

    /**
     * 判断是否需要更新
     *
     * @param url
     */
    private void isUpdate(String url) {
        new JsonUtils<UpdateBean>().loadData(url, UpdateBean.class);
        dialog.dismiss();
    }

    /**
     * 显示更新对话框
     *
     * @param updateBean
     */
    public void showUpdateDialog(UpdateBean updateBean) {
        this.updateBean = updateBean;
        UpdateBean.DataEntity data = updateBean.getData();
        new AlertDialog.Builder(context)
                .setTitle(data.getTitle())
                .setMessage(data.getDesc())
                .setPositiveButton("下载更新", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startDownloadUpdate();
                    }
                })
                .setNegativeButton("下次再说", null)
                .show();
    }

    /**
     * 开始下载更新
     */
    private void startDownloadUpdate() {
        String upd_url = updateBean.getData().getUpd_url();
        Toast.makeText(context, "开始下载更新 url = " + upd_url, Toast.LENGTH_SHORT).show();
    }

}
