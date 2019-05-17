package com.example.administrator.travel02.ui.main.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.administrator.travel02.R;
import com.example.administrator.travel02.base.BaseActivity;
import com.example.administrator.travel02.base.Constants;
import com.example.administrator.travel02.bean.LikeBean;
import com.example.administrator.travel02.presenter.EmptyPresenter;
import com.example.administrator.travel02.presenter.UpDataPresenter;
import com.example.administrator.travel02.utils.GlideUtil;
import com.example.administrator.travel02.utils.SpUtil;
import com.example.administrator.travel02.view.UpDataView;
import com.example.administrator.travel02.view.main.EmptyView;
import com.jaeger.library.StatusBarUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MeActivity extends BaseActivity<EmptyView,EmptyPresenter>implements EmptyView, View.OnClickListener {

    public static int NICK_TYPE=0;
    public static int SINGNATURE_TYPE=1;
    public static int HEADER_TYPE=2;
    public static int CANDER_TYPE=2;
    private static String mType;


    @BindView(R.id.me_img)
    ImageView meImg;
    @BindView(R.id.me_ll)
    ImageView meLl;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.name)
    View name;
    @BindView(R.id.line_title)
    View lineTitle;
    @BindView(R.id.line_ti)
    View lineTi;
    @BindView(R.id.line_show)
    View lineShow;
    @BindView(R.id.tv_personiatiy)
    TextView tvPerson;
    @BindView(R.id.tv_lovely)
    TextView tvLoveName;
    @BindView(R.id.tv_sex)
    TextView tv_sex;
    @BindView(R.id.tv_ge)
    TextView tvge;
    private int type;
    private String name1;
    private PopupWindow window;

    @Override
    protected EmptyPresenter initPresenter() {
        return new EmptyPresenter();
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_me;
    }
    @Override
    protected void initView() {
        StatusBarUtil.setLightMode(this);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        GlideUtil.loadUrlCircleImage(R.mipmap.mm,R.mipmap.mm,(String) SpUtil.getParam(Constants.PHOTO, ""), meImg, this);
        tvLoveName.setText((String) SpUtil.getParam(Constants.USERNAME, "user"));
        setGender();
        tvPerson.setText((String) SpUtil.getParam(Constants.DESC, ""));

        tvLoveName.setOnClickListener(this);
        tvPerson.setOnClickListener(this);
        tv_sex.setOnClickListener(this);
        meImg.setOnClickListener(this);
    }

    private void setGender() {
        String gender= (String) SpUtil.getParam(Constants.CANDER,"保密");
        if (gender.equals("M")){
            tv_sex.setText("男");
        }else if (gender.equals("F")){
            tv_sex.setText("女");
        }else {
            tv_sex.setText("保密");
        }
    }


    @Override
    protected void initListener() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void initPop(){
        View inflate = LayoutInflater.from(this).inflate(R.layout.popup_sex, null, false);
        TextView take = inflate.findViewById(R.id.bt_takePhoto);
        TextView photo = inflate.findViewById(R.id.bt_photo);
        TextView cancel = inflate.findViewById(R.id.bt_cancel);
        take.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                window.dismiss();
            }
        });
        window = new PopupWindow(inflate, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        window.setOutsideTouchable(true);
        window.setTouchable(true);
        window.setAnimationStyle(R.style.pwanim);
        window.showAtLocation(inflate, Gravity.BOTTOM, 0, 0);
    }
    @Override
    protected void initData() {

    }

    @Override
    public void toastShort(String msg) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_lovely:
                type = LovelyActivity.NICK_TYPE;
                LovelyActivity.startAct(this, type, "");
                break;
            case R.id.tv_sex:
                initPop();
                break;
            case R.id.tv_ge:
                Intent intent=new Intent(MeActivity.this,PersonalityActivity.class);
                startActivity(intent);
                break;
            case R.id.me_img:
                Intent intent1=new Intent(MeActivity.this,TakePhoteActivity.class);
                startActivity(intent1);
                break;
        }
    }
}
