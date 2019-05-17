package com.example.administrator.travel02.ui.main.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.travel02.R;
import com.example.administrator.travel02.base.BaseFragment;
import com.example.administrator.travel02.base.Constants;
import com.example.administrator.travel02.bean.LikeBean;
import com.example.administrator.travel02.presenter.LoginOrBindPresenter;
import com.example.administrator.travel02.ui.main.activity.LoginActivity;
import com.example.administrator.travel02.ui.main.activity.MainActivity;
import com.example.administrator.travel02.ui.main.activity.WebActivity;
import com.example.administrator.travel02.utils.Tools;
import com.example.administrator.travel02.view.main.LoginOrBindView;
import com.umeng.socialize.bean.SHARE_MEDIA;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginOrBindFragment extends BaseFragment<LoginOrBindView, LoginOrBindPresenter> implements LoginOrBindView {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_hello)
    TextView tvHello;
    @BindView(R.id.tv_login)
    TextView tvLogin;
    @BindView(R.id.tv_coutry_code)
    TextView tvCoutryCode;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.btn_send_verify)
    Button btnSendVerify;
    @BindView(R.id.ll_container)
    LinearLayout llContainer;
    @BindView(R.id.view)
    View view;
    @BindView(R.id.ll_or)
    LinearLayout llOr;
    @BindView(R.id.iv_wechat)
    ImageView ivWechat;
    @BindView(R.id.iv_qq)
    ImageView ivQq;
    @BindView(R.id.iv_sina)
    ImageView ivSina;
    @BindView(R.id.tv_protocol)
    TextView tvProtocol;
    @BindView(R.id.ll_oauth_login)
    LinearLayout llOauthLogin;
    private int mType;
    private VerifyFragment mVerifyFragment;
    private static int COUNT_DOWN_TIME=10;
    private int mTime=COUNT_DOWN_TIME;
    private Handler mHandler;
    private String mVerifyCode="";

    public static LoginOrBindFragment newInstance(int type){
        LoginOrBindFragment fragment = new LoginOrBindFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(Constants.TYPE,type);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected LoginOrBindPresenter initPresenter() {
        return new LoginOrBindPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_login_bind;
    }

    @Override
    protected void initListener() {
        etPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                swicchBtnState(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void swicchBtnState(CharSequence s) {
        if (TextUtils.isEmpty(s)){
            btnSendVerify.setBackgroundResource(R.drawable.bg_btn_ea_r15);
        }else {
            btnSendVerify.setBackgroundResource(R.drawable.bg_btn_fa6a13_r15);
        }
    }

    @OnClick({R.id.iv_back,R.id.btn_send_verify,R.id.iv_wechat,R.id.iv_qq,R.id.iv_sina})
    public void onViewClicked(View view){
        switch (view.getId()){
            case R.id.iv_back:
                getActivity().finish();
                break;
            case R.id.btn_send_verify:
                addVerietyFragment();
                time();
                break;
            case R.id.iv_wechat:
                mPresenter.oauthLogin(SHARE_MEDIA.WEIXIN);
                break;
            case R.id.iv_qq:
                mPresenter.oauthLogin(SHARE_MEDIA.QQ);
                break;
            case R.id.iv_sina:
                mPresenter.oauthLogin(SHARE_MEDIA.SINA);
                break;
        }
    }

    private void time() {
        if (mTime>0&&mTime<COUNT_DOWN_TIME){
            return;
        }
        countDown();
    }

    public void countDown() {
        if (mHandler==null){
            mHandler=new Handler();
        }
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mTime<=0){
                    mTime=COUNT_DOWN_TIME;
                    return;
                }
                mTime--;
                if (mVerifyFragment!=null){
                    mVerifyFragment.setCountDownTime(mTime);
                }
                countDown();
            }
        },1000);
    }

    private void getVerifyCode() {
        if (mTime>0&&mTime<COUNT_DOWN_TIME-1){
            return;
        }
        mVerifyCode="";
        mPresenter.getVerifyCode();
    }

    private void addVerietyFragment() {
        if (TextUtils.isEmpty(getPhone())){
            return;
        }
        FragmentManager manager=getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.addToBackStack(null);
        mVerifyFragment=VerifyFragment.getInstance(mVerifyCode);
        transaction.add(R.id.fl_container,mVerifyFragment).commit();
        Tools.closeKeyBoard(getActivity());
    }

    @Override
    public String getPhone() {
        return etPhone.getText().toString().trim();
    }

    @Override
    public void go2MainActivity() {
        MainActivity.startAct(getContext());
    }

    @Override
    public Activity getAct() {
        return getActivity();
    }

    @Override
    public void setData(String code) {
        this.mVerifyCode=code;
        if (mVerifyFragment!=null){
            mVerifyFragment.setData(code);
        }
    }

    @Override
    protected void initView() {
        getArgumentsData();
        setPorTocal();
        showOrHideView();
    }

    private void showOrHideView() {
        if (mType== LoginActivity.TYPE_LOGIN){
            ivBack.setVisibility(View.INVISIBLE);
            llOauthLogin.setVisibility(View.VISIBLE);
            llOr.setVisibility(View.VISIBLE);
        }else {
            ivBack.setVisibility(View.VISIBLE);
            llOauthLogin.setVisibility(View.GONE);
            llOr.setVisibility(View.GONE);
        }
    }

    private void setPorTocal() {
        SpannableStringBuilder builder=new SpannableStringBuilder(getResources().getString(R.string.agree_protocol));
        ClickableSpan clickableSpan=new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                WebActivity.startAct(getActivity());
            }
        };
        builder.setSpan(clickableSpan,11,15, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        UnderlineSpan underlineSpan = new UnderlineSpan();
        builder.setSpan(underlineSpan,11,15, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        ForegroundColorSpan what = new ForegroundColorSpan(getResources().getColor(R.color.c_fa6a13));
        builder.setSpan(what,11,15,Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        tvProtocol.setMovementMethod(LinkMovementMethod.getInstance());
        tvProtocol.setText(builder);
    }
    private void getArgumentsData() {
        Bundle arguments = getArguments();
        mType=arguments.getInt(Constants.TYPE);
    }
}
