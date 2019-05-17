package com.example.administrator.travel02.presenter;

import android.util.Log;

import com.example.administrator.travel02.base.BasePresenter;
import com.example.administrator.travel02.bean.MainBean;
import com.example.administrator.travel02.bean.VerifyCodeBean;
import com.example.administrator.travel02.model.LoginModel;
import com.example.administrator.travel02.model.MainModel;
import com.example.administrator.travel02.net.ResultCallBack;
import com.example.administrator.travel02.view.main.MainView;

public class MainPresenter extends BasePresenter<MainView> {
    private MainModel model;

    @Override
    protected void initModel() {
        model = new MainModel();
        mModels.add(model);
    }

    public void getPre(String token,int page){
        model.model(token,page,new ResultCallBack<MainBean>() {
            @Override
            public void onSuccess(MainBean bean) {
                if (bean!=null){
                    if (mMvpView!=null){
                        mMvpView.success(bean);
                    }
                }
            }

            @Override
            public void onFail(String msg) {

            }
        });
    }
}
