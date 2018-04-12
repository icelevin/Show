package com.icelevin.www.show;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.hb.utils.engine.PermissionsManager;
import com.hb.utils.view.activity.BaseActivity;
import com.icelevin.www.show.common.Constants;
import com.icelevin.www.show.ui.MainFragment;

import java.security.Permission;
import java.util.Timer;
import java.util.TimerTask;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobConfig;
import cn.ittiger.player.PlayerManager;


public class MainActivity extends BaseActivity {

    public static MainActivity activity;
    private PermissionsManager permissionsManager;
    private boolean isExit = false;

    public MainActivity getInstance() {
        return activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = this;
//        全透明状态栏
//        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            Window window=getWindow();
//            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN|View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
//            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.setStatusBarColor(Color.TRANSPARENT);
//        }
        //第一：默认初始化

        // 注:自v3.5.2开始，数据sdk内部缝合了统计sdk，开发者无需额外集成，传渠道参数即可，不传默认没开启数据统计功能
//        Bmob.initialize(this, "4a034bb2d1a5bdbdc91eba847c147f80");

        //第二：自v3.4.7版本开始,设置BmobConfig,允许设置请求超时时间、文件分片上传时每片的大小、文件的过期时间(单位为秒)，
        BmobConfig config = new BmobConfig.Builder(this)
                ////设置appkey
                .setApplicationId(Constants.BOMB_APPLICATION_ID)
                ////请求超时时间（单位为秒）：默认15s
                .setConnectTimeout(30)
                ////文件分片上传时每片的大小（单位字节），默认512*1024
                .setUploadBlockSize(512 * 1024)
                //文件的过期时间(单位为秒)：默认1800s
                .setFileExpiration(2500)
                .build();
        Bmob.initialize(config);

//        setWindowStatusBarColor(this, R.color.bk_title_red);

        setContentView(R.layout.activity_main);
        permissionsManager = new PermissionsManager(activity);
        if (permissionsManager.checkPermissions()) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            if (getSupportFragmentManager().findFragmentByTag(MainFragment.class.getName()) == null) {

                MainFragment mainFragment = new MainFragment();
                transaction.add(R.id.fragment_parent, mainFragment, MainFragment.class.getName()).commitNowAllowingStateLoss();
            }

        }

    }

    @Override
    public void onBackPressed() {
        Timer tExit = null;
        if (isExit == false) {
            isExit = true;
            showTost("再按一次退出应用");
            tExit = new Timer();
            tExit.schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false;//取消退出
                }
            }, 2000);//如果2秒内没有按下返回键,则启动定时器取消掉刚才执行的任务
            PlayerManager.getInstance().onBackPressed();
        } else {
            //跳到桌面
            try {
                Intent home = new Intent(Intent.ACTION_MAIN);
                home.addCategory(Intent.CATEGORY_HOME);
                startActivity(home);

            } catch (Exception e) {
                e.printStackTrace();
                finish();
                System.exit(0);
            }
        }
    }

    public static void setWindowStatusBarColor(Activity activity, int colorResId) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = activity.getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(activity.getResources().getColor(colorResId));

                //底部导航栏
                //window.setNavigationBarColor(activity.getResources().getColor(colorResId));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        boolean doNext = permissionsManager.doNext(requestCode, grantResults);
        if (!doNext) {
            return;
        }

        if (requestCode == PermissionsManager.WRITE_EXTERNAL_READ_PHONE_STATE_CODE || requestCode == PermissionsManager.WRITE_EXTERNAL_STORAGE_REQUEST_CODE) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.fragment_parent, new MainFragment()).commitNowAllowingStateLoss();
        }
    }
}
