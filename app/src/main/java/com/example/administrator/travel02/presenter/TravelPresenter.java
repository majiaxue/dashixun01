package com.example.administrator.travel02.presenter;

import com.example.administrator.travel02.base.BasePresenter;
import com.example.administrator.travel02.bean.TravelBean;
import com.example.administrator.travel02.model.FellowModel;
import com.example.administrator.travel02.model.TravelModel;
import com.example.administrator.travel02.net.ResultCallBack;
import com.example.administrator.travel02.view.main.TravelView;

public class TravelPresenter extends BasePresenter<TravelView> {
    private TravelModel model;
    @Override
    protected void initModel() {
        model=new TravelModel();
        mModels.add(model);
    }
    public void getData(String token,int page){
        model.getLogin(token, page, new ResultCallBack<TravelBean>() {
            @Override
            public void onSuccess(TravelBean bean) {
                mMvpView.onSuccess(bean.getResult());
            }

            @Override
            public void onFail(String msg) {
                mMvpView.onFile(msg);
            }
        });
    }
    public void addLike(String token,int id){
        model.addLike(token, id, new ResultCallBack<String>() {
            @Override
            public void onSuccess(String bean) {
                mMvpView.toastShort(bean);
            }

            @Override
            public void onFail(String msg) {
                mMvpView.toastShort(msg);
            }
        });
    }

    public void disLike(String token,int id){
        model.disLike(token, id, new ResultCallBack<String>() {
            @Override
            public void onSuccess(String bean) {
                mMvpView.toastShort(bean);
            }

            @Override
            public void onFail(String msg) {
                mMvpView.toastShort(msg);
            }
        });
    }
}
