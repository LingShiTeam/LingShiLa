package atguigu.com.lingshixiaomiao.pager.mine.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import atguigu.com.lingshixiaomiao.R;


public class SettingActivity extends Activity implements View.OnClickListener{

    private ImageButton ib_mine_setting_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        findViewById();
    }

    private void findViewById() {
        ib_mine_setting_back = (ImageButton)findViewById(R.id.ib_mine_setting_back);

        ib_mine_setting_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ib_mine_setting_back:
                finish();
                break;
        }
    }
}
