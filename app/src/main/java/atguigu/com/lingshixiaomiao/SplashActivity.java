package atguigu.com.lingshixiaomiao;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import atguigu.com.lingshixiaomiao.pager.home.utils.SPUtils;

/**
 * 欢迎界面
 */
public class SplashActivity extends Activity {

    private RelativeLayout rl_splash;
    private ImageView iv_splash_bg;
    private ImageView iv_splash_logo;
    private ImageView iv_splash_text;
    private AnimationSet set;
    private MyAnimationListener mAnimationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        initView();
        setAnimation();
        toGuideActivity();
    }

    /**
     * 启动引导界面
     */
    private void toGuideActivity() {
        // 动画监听
        if (mAnimationListener == null) {
            mAnimationListener = new MyAnimationListener();
        }
        set.setAnimationListener(mAnimationListener);
    }

    class MyAnimationListener implements Animation.AnimationListener {
        // 动画开始
        @Override
        public void onAnimationStart(Animation animation) {

        }

        // 动画结束
        @Override
        public void onAnimationEnd(Animation animation) {
            // 判断启动引导界面
            boolean isEnter = SPUtils.getBoolean(SplashActivity.this, SPUtils.FIRST_SPLASH, false);
            Intent intent = null;
            if(!isEnter) {
                intent = new Intent(SplashActivity.this, GuideActivity.class);
            } else {
                intent = new Intent(SplashActivity.this, MainActivity.class);
            }
            startActivity(intent);
            finish();
        }

        // 动画重复
        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }

    /**
     * 设置动画
     */
    private void setAnimation() {
        // 透明度
        AlphaAnimation aa = new AlphaAnimation(0, 1);
        aa.setDuration(2000);
        aa.setFillAfter(true);
        // 缩放
        ScaleAnimation sa = new ScaleAnimation(0, 1, 0, 1, Animation.RELATIVE_TO_SELF,
                0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        sa.setDuration(2000);
        sa.setFillAfter(true);
        // 动画集
        set = new AnimationSet(false);
        set.addAnimation(aa);
        set.addAnimation(sa);
        // 启动动画
        if(rl_splash != null) {
            rl_splash.startAnimation(set);
        }
    }

    /**
     * 初始化视图
     */
    private void initView() {
        rl_splash = (RelativeLayout) findViewById(R.id.rl_splash);
        iv_splash_bg = (ImageView) findViewById(R.id.iv_splash_bg);
        iv_splash_logo = (ImageView) findViewById(R.id.iv_splash_logo);
        iv_splash_text = (ImageView) findViewById(R.id.iv_splash_text);
    }
}
