package com.example.administrator.travel02.presenter;

import com.example.administrator.travel02.base.BaseMvpView;
import com.example.administrator.travel02.base.BasePresenter;
import com.example.administrator.travel02.bean.LikeBean;
import com.example.administrator.travel02.model.CollectModel;
import com.example.administrator.travel02.net.ResultCallBack;
import com.example.administrator.travel02.view.main.CollectView;

public class CollectPresenter extends BasePresenter<CollectView> {
    private CollectModel model;

    @Override
    protected void initModel() {
        model = new CollectModel();
    }

    public void getLikeData(String token,int page){
        model.getLikeData(page, new ResultCallBack<LikeBean>() {
            @Override
            public void onSuccess(LikeBean bean) {
                if (mMvpView != null){
                    mMvpView.onSuccess(bean.getResult());
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
