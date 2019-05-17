package com.example.administrator.travel02.presenter;

import com.example.administrator.travel02.base.BasePresenter;
import com.example.administrator.travel02.model.UpDataModel;
import com.example.administrator.travel02.net.ResultCallBack;
import com.example.administrator.travel02.view.UpDataView;

public class UpDataPresenter extends BasePresenter<UpDataView> {
    private UpDataModel upDataModel;
    @Override
    protected void initModel() {
        upDataModel=new UpDataModel();
        mModels.add(upDataModel);
    }
    public void upDataInfo(String token,String info,int type){
        upDataModel.upDataInfo(token, info, type, new ResultCallBack<String>() {
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
