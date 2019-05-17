package com.example.administrator.travel02.presenter;

import com.example.administrator.travel02.base.BasePresenter;
import com.example.administrator.travel02.bean.BanmiInfo;
import com.example.administrator.travel02.model.BanMiDetailsModel;
import com.example.administrator.travel02.net.ResultCallBack;
import com.example.administrator.travel02.view.main.BanMiDetailsView;

public class BanmiDetailsPresenter extends BasePresenter<BanMiDetailsView> {
    private BanMiDetailsModel model;

    @Override
    protected void initModel() {
        model = new BanMiDetailsModel();
    }

    public void getBanmiInfo(String token,int id,int page){
        model.getBanmiInfo(token, id, page, new ResultCallBack<BanmiInfo>() {
            @Override
            public void onSuccess(BanmiInfo bean) {
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
