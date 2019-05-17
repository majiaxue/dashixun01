package com.example.administrator.travel02.ui.main.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.example.administrator.travel02.R;
import com.example.administrator.travel02.base.BaseActivity;
import com.example.administrator.travel02.base.Constants;
import com.example.administrator.travel02.bean.LoadBean;
import com.example.administrator.travel02.presenter.VersionPresenter;
import com.example.administrator.travel02.utils.Logger;
import com.example.administrator.travel02.utils.SpUtil;
import com.example.administrator.travel02.utils.Tools;
import com.example.administrator.travel02.view.VersionView;

public class SplashActivity extends AppCompatActivity{

    private ImageView img;
    private SharedPreferences.Editor edit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initView();
    }
    private void initView() {
        img = (ImageView) findViewById(R.id.img);
        AlphaAnimation animation = new AlphaAnimation(1f, 1f);
        animation.setDuration(2000);
        img.startAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            /** 动画结束执行的方法*/
            @Override
            public void onAnimationEnd(Animation animation) {
                redirectTo();
            }
        });
    }

    private void redirectTo() {
        if (!TextUtils.isEmpty((String) SpUtil.getParam(Constants.TOKEN, ""))) {
            startActivity(new Intent(this, MainActivity.class));
        } else if ((boolean) SpUtil.getParam("isPagerOpened", false)) {
            startActivity(new Intent(this, LoginActivity.class));
        } else {
            startActivity(new Intent(this, LeadActivity.class));
        }
        finish();
    }
}
