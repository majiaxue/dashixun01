package com.example.administrator.travel02.presenter;

import com.example.administrator.travel02.base.BasePresenter;
import com.example.administrator.travel02.bean.MainSpreadBean;
import com.example.administrator.travel02.bean.TravelBean;
import com.example.administrator.travel02.model.MainSpreadModel;
import com.example.administrator.travel02.model.TravelModel;
import com.example.administrator.travel02.net.ResultCallBack;
import com.example.administrator.travel02.view.main.MainSpresdView;

public class MainSpreadPresenter extends BasePresenter<MainSpresdView> {
    private MainSpreadModel model;
    @Override
    protected void initModel() {
        model=new MainSpreadModel();
        mModels.add(model);
    }
    public void getSpreadData(String token,int routeId) {
        model.getSpreadData(token, routeId, new ResultCallBack<MainSpreadBean>() {
            @Override
            public void onSuccess(MainSpreadBean bean) {
                mMvpView.upDataUi(bean);
            }

            @Override
            public void onFail(String msg) {
                mMvpView.onUiFile(msg);
            }
        });
    }
    public void addLike(String token, int id) {
        model.addLike(token, id, new ResultCallBack<String>() {
            @Override
            public void onSuccess(String bean) {
                mMvpView.onSuccess(bean);
            }

            @Override
            public void onFail(String msg) {
                mMvpView.onFail(msg);
            }
        });
    }

    public void disLike(String token, int id) {
        model.disLike(token, id, new ResultCallBack<String>() {
            @Override
            public void onSuccess(String bean) {
                mMvpView.onSuccess(bean);
            }

            @Override
            public void onFail(String msg) {
                mMvpView.onFail(msg);
            }
        });
    }
}
