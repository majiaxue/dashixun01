package com.example.administrator.travel02.ui.main.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.example.administrator.travel02.R;
import com.example.administrator.travel02.base.BaseActivity;
import com.example.administrator.travel02.base.Constants;
import com.example.administrator.travel02.bean.LikeBean;
import com.example.administrator.travel02.presenter.UpDataPresenter;
import com.example.administrator.travel02.presenter.UpdateInfoPresenter;
import com.example.administrator.travel02.utils.SpUtil;
import com.example.administrator.travel02.view.UpDataView;
import com.example.administrator.travel02.view.UpdateInfoView;
import com.jaeger.library.StatusBarUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class UpdateInfoActivity extends BaseActivity<UpdateInfoView, UpdateInfoPresenter> implements UpdateInfoView {

    @BindView(R.id.tb_title)
    TextView tbTitle;
    @BindView(R.id.iv_big_header)
    ImageView ivBigHeader;
    @BindView(R.id.tb_finished)
    TextView tbFinished;
    @BindView(R.id.toolBar)
    Toolbar toolBar;
    @BindView(R.id.et_info)
    EditText etInfo;
    @BindView(R.id.text_count)
    TextView textCount;
    @BindView(R.id.cv_info)
    CardView cvInfo;
    @BindView(R.id.ll_parent)
    LinearLayout mLl;
    public static int NICK_TYPE = 0;
    public static int SIGNATURE_TYPE = 1;
    public static int HEADER_TYPE = 2;
    public static int GENDER_TYPE = 3;
    private static int mType;
    private static String mImgUrl = "";
    private PopupWindow popupWindow;
    private Button mPopbtCamera;
    private Button mPopbtPhoto;
    private Button mPopbtCancel;

    public static void startAct(Context context, int type,String imgUrl){
        mType = type;
        mImgUrl = imgUrl;
        context.startActivity(new Intent(context,UpdateInfoActivity.class));
    }

    @Override
    protected UpdateInfoPresenter initPresenter() {
        return new UpdateInfoPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_update_info;
    }

    @OnClick({R.id.tb_finished})
    public void onViewClicked() {
        String info = etInfo.getText().toString().trim();
        if (!TextUtils.isEmpty(info)){
            if (mType == NICK_TYPE || mType == SIGNATURE_TYPE){
                ivBigHeader.setVisibility(View.GONE);
                cvInfo.setVisibility(View.VISIBLE);
                mPresenter.updateInfo((String) SpUtil.getParam(Constants.TOKEN,""),info,mType);
            }
        }
        if (mType == HEADER_TYPE){
            cvInfo.setVisibility(View.GONE);
            ivBigHeader.setVisibility(View.VISIBLE);
            mPresenter.updateInfo((String) SpUtil.getParam(Constants.TOKEN,""),mImgUrl,mType);
        }
    }

    @Override
    protected void initView() {
        toolBar.setTitle("");
        toolBar.setNavigationIcon(R.drawable.back_white);
        setSupportActionBar(toolBar);
        StatusBarUtil.setLightMode(this);
        if (mType == NICK_TYPE){
            tbTitle.setText("修改昵称");
            etInfo.setText((String) SpUtil.getParam(Constants.USERNAME,""));
            textCount.setText(etInfo.getText().toString().trim().length()+"/27");
        }else if (mType == SIGNATURE_TYPE){
            tbTitle.setText("个性签名");
            etInfo.setText((String) SpUtil.getParam(Constants.DESC,""));
            textCount.setText(etInfo.getText().toString().trim().length()+"/27");
        }else if (mType == HEADER_TYPE){
            tbTitle.setText("个人头像");

        }
    }

    private static final String TAG = "UpdateInfoActivity";
    @Override
    public void onSuccess(String msg) {
        if (mType == NICK_TYPE){
            SpUtil.setParam(Constants.USERNAME,etInfo.getText().toString().trim());
            finish();
        }else if (mType == SIGNATURE_TYPE){
            SpUtil.setParam(Constants.DESC,etInfo.getText().toString().trim());
            finish();
        }

    }



    @Override
    public void onFail(String msg) {

    }

    @Override
    protected void initListener() {
        etInfo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                textCount.setText(s.toString().length()+"/27");
            }
        });

        toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void initData() {

    }
    @Override
    public void toastShort(String msg) {

    }
}