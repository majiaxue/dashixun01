package com.example.administrator.travel02.ui.main.activity;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;

import com.example.administrator.travel02.R;
import com.example.administrator.travel02.base.BaseActivity;
import com.example.administrator.travel02.base.Constants;
import com.example.administrator.travel02.bean.LikeBean;
import com.example.administrator.travel02.presenter.LoginPresenter;
import com.example.administrator.travel02.ui.main.fragment.LoginOrBindFragment;
import com.example.administrator.travel02.view.main.LoginView;
import com.umeng.socialize.UMShareAPI;

public class LoginActivity extends BaseActivity<LoginView,LoginPresenter> implements LoginView {
    public static final int TYPE_LOGIN=0;
    private static final int TYPE_BIND=1;
    public static String Tag="loginFragment";
    private int mType;

    public static void startAct(Context context,int type){
        Intent intent = new Intent(context, LoginActivity.class);
        intent.putExtra(Constants.TYPE,type);
        context.startActivity(intent);
    }

    @Override
    protected LoginPresenter initPresenter() {
        return new LoginPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }



    @Override
    protected void initView() {
        getIntentData();
        FragmentManager manager=getSupportFragmentManager();
        LoginOrBindFragment fragment = LoginOrBindFragment.newInstance(mType);
        manager.beginTransaction().add(R.id.fl_container,fragment,Tag).commit();
    }

    @Override
    protected void initListener() {

    }

    private void getIntentData() {
        mType=getIntent().getIntExtra(Constants.TYPE,TYPE_LOGIN);
    }

    @Override
    public void toastShort(String msg) {

    }

    @Override
    protected void initData() {
        mPresenter.getVerifyCode();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        UMShareAPI.get(this).release();
    }
}
