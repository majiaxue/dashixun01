package com.example.administrator.travel02.ui.main.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.travel02.R;
import com.example.administrator.travel02.base.BaseApp;
import com.example.administrator.travel02.base.BaseFragment;
import com.example.administrator.travel02.base.Constants;
import com.example.administrator.travel02.bean.LikeBean;
import com.example.administrator.travel02.presenter.VerifyPresenter;
import com.example.administrator.travel02.ui.main.activity.LoginActivity;
import com.example.administrator.travel02.ui.main.activity.MainActivity;
import com.example.administrator.travel02.utils.Logger;
import com.example.administrator.travel02.view.main.VerifyView;
import com.example.administrator.travel02.widget.IdentifyingCodeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class VerifyFragment extends BaseFragment<VerifyView, VerifyPresenter> implements VerifyView {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_send_again)
    TextView tvSendAgain;
    @BindView(R.id.icv)
    IdentifyingCodeView icv;
    @BindView(R.id.tv_wait)
    TextView tvWait;
   private int mTime;


    public static VerifyFragment getInstance(String code){
       VerifyFragment verifyFragment = new VerifyFragment();
       Bundle bundle=new Bundle();
       bundle.putString(Constants.VERIFY_CODE,code);
       verifyFragment.setArguments(bundle);
       return verifyFragment;
   }

    @Override
    protected VerifyPresenter initPresenter() {
        return new VerifyPresenter();
    }
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_verify;
    }
    @OnClick({R.id.iv_back,R.id.tv_send_again})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.iv_back:
                pop();
                break;
            case R.id.tv_send_again:
               if (mTime==0){
                   mPresenter.getVerifyCode();
                   LoginOrBindFragment fragment= (LoginOrBindFragment) getActivity().getSupportFragmentManager().findFragmentByTag(LoginActivity.Tag);
                   fragment.countDown();
               }
                break;
        }
    }

    private void pop() {
        FragmentManager manager = getActivity().getSupportFragmentManager();
        //Logger.println("回退栈Fragment的数量："+);
        manager.popBackStack();
    }

    @Override
    protected void initData() {
        mPresenter.getVerifyCode();
    }

    @Override
    public void setData(String data) {
        if (!TextUtils.isEmpty(data)&&tvWait!=null){
            tvWait.setText(BaseApp.getRes().getString(R.string.verify_code)+data);
        }
    }

    @Override
    protected void initListener() {
        icv.setOnEditorActionListener(new IdentifyingCodeView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                return false;
            }

            @Override
            public void onTextChanged(String s) {
                autoLogin();
            }
        });
    }

    private void autoLogin() {
        Logger.println(icv.getTextContent());
        if (icv.getTextContent().length()>=4){
            toastShort("自动登录");
            icv.setBackgroundEnter(false);
            tvWait.setText(BaseApp.getRes().getString(R.string.wait_plase));
            showLoading();
            Intent intent=new Intent(getActivity(), MainActivity.class);
            getActivity().startActivity(intent);
        }
    }

    public void setCountDownTime(int time) {
        mTime=time;
        if (tvSendAgain!=null){
            if (time!=0){
                String format = String.format(getResources().getString(R.string.send_again) + "(%ss)", time);
                tvSendAgain.setText(format);
                tvSendAgain.setTextColor(getResources().getColor(R.color.c_999999));
            }else {
                tvSendAgain.setText(getResources().getString(R.string.send_again));
                tvSendAgain.setTextColor(getResources().getColor(R.color.c_fa6a13));
            }
        }
    }

    @Override
    protected void initView() {
        String code = getArguments().getString(Constants.VERIFY_CODE);
        setData(code);
    }
}
