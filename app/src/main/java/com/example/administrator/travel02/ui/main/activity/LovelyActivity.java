package com.example.administrator.travel02.ui.main.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.travel02.R;
import com.example.administrator.travel02.base.BaseActivity;
import com.example.administrator.travel02.bean.LikeBean;
import com.example.administrator.travel02.presenter.UpDataPresenter;
import com.example.administrator.travel02.view.UpDataView;

import org.greenrobot.eventbus.EventBus;

public class LovelyActivity extends BaseActivity<UpDataView,UpDataPresenter> implements UpDataView, View.OnClickListener {
    private ImageView img;
    private TextView tv_title;
    private TextView tv_down;
    private Toolbar toolbar;
    private EditText ed_write_personality;
    private int type=1;
    private static int mType;
    private static String mImgUrl = "";
    public static int NICK_TYPE = 0;
    public static int SIGNATURE_TYPE = 1;
    public static int HEADER_TYPE = 2;
    public static int GENDER_TYPE = 3;
    @Override
    protected void initView() {
        img = (ImageView) findViewById(R.id.img);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_down = (TextView) findViewById(R.id.tv_down);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        ed_write_personality = (EditText) findViewById(R.id.ed_write_personality);
        toolbar.setTitle("");
        tv_title.setText("修改昵称");
        tv_down.setOnClickListener(this);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected UpDataPresenter initPresenter() {
        return new UpDataPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_lovely;
    }

    public static void startAct(Context context, int type, String imgUrl) {
        mType=type;
        mImgUrl=imgUrl;
        context.startActivity(new Intent(context,MeActivity.class));
    }

    @Override
    public void onSuccess(String bean) {

    }

    @Override
    public void onFail(String msg) {

    }

    @Override
    public void toastShort(String msg) {

    }

    @Override
    public void onClick(View v) {
        String person = ed_write_personality.getText().toString();
        /*if (!TextUtils.isEmpty(person)){
            if (mType==NICK_TYPE||mType==)
        }
        switch (v.getId()){

        }*/
    }
}
