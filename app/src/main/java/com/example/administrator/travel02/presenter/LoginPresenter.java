package com.example.administrator.travel02.presenter;
import com.example.administrator.travel02.base.BasePresenter;
import com.example.administrator.travel02.bean.VerifyCodeBean;
import com.example.administrator.travel02.model.LoginModel;
import com.example.administrator.travel02.net.ResultCallBack;
import com.example.administrator.travel02.view.main.LoginView;

/**
 * @author xts
 *         Created by asus on 2019/4/29.
 */

public class LoginPresenter extends BasePresenter<LoginView> {
    private LoginModel mLoginModel;

    @Override
    protected void initModel() {
        mLoginModel = new LoginModel();
        mModels.add(mLoginModel);
    }

    public void getVerifyCode() {
        mLoginModel.getVerifyCode(new ResultCallBack<VerifyCodeBean>() {
            @Override
            public void onSuccess(VerifyCodeBean bean) {

            }

            @Override
            public void onFail(String msg) {

            }
        });
    }
}
