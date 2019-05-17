package com.example.administrator.travel02.presenter;

import com.example.administrator.travel02.base.BasePresenter;
import com.example.administrator.travel02.bean.BanmiInfo;
import com.example.administrator.travel02.bean.LoadBean;
import com.example.administrator.travel02.model.BanMiDetailsModel;
import com.example.administrator.travel02.model.VersionModel;
import com.example.administrator.travel02.net.ResultCallBack;
import com.example.administrator.travel02.view.VersionView;

public class VersionPresenter extends BasePresenter<VersionView> {
    private VersionModel model;

    @Override
    protected void initModel() {
        model = new VersionModel();
    }

    public void getVersionData(String token){
        model.getVersionData(token, new ResultCallBack<LoadBean>() {
            @Override
            public void onSuccess(LoadBean bean) {
                if (mMvpView != null){
                    mMvpView.onSuccess(bean);
                }
            }

            @Override
            public void onFail(String msg) {
                if (mMvpView != null){
                    mMvpView.onFail(msg);
                }
            }
        });
    }
}
