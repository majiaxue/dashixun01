package com.example.administrator.travel02.presenter;
import com.example.administrator.travel02.R;
import com.example.administrator.travel02.base.BaseApp;
import com.example.administrator.travel02.base.BasePresenter;
import com.example.administrator.travel02.bean.VerifyCodeBean;
import com.example.administrator.travel02.model.LoginModel;
import com.example.administrator.travel02.net.ApiService;
import com.example.administrator.travel02.net.ResultCallBack;
import com.example.administrator.travel02.view.main.VerifyView;

/**
 * @author xts
 *         Created by asus on 2019/5/4.
 */

public class VerifyPresenter extends BasePresenter<VerifyView> {
    private LoginModel loginModel;
    @Override
    protected void initModel() {
        loginModel=new LoginModel();
        mModels.add(loginModel);
    }

    public void getVerifyCode() {
        loginModel.getVerifyCode(new ResultCallBack<VerifyCodeBean>() {
            @Override
            public void onSuccess(VerifyCodeBean bean) {
                if (bean!=null&&bean.getCode()== ApiService.SUCCESS_CODE){
                    if (mMvpView!=null){
                        mMvpView.setData(bean.getData());
                    }
                }else {
                    if (mMvpView!=null){
                        mMvpView.toastShort(BaseApp.getRes().getString(R.string.viety_fail));
                    }
                }
            }

            @Override
            public void onFail(String msg) {

            }
        });
    }
}
