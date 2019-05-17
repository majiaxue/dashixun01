package com.example.administrator.travel02.presenter;

import com.example.administrator.travel02.base.BasePresenter;
import com.example.administrator.travel02.bean.SubJectBean;
import com.example.administrator.travel02.model.SubJectModel;
import com.example.administrator.travel02.net.ResultCallBack;
import com.example.administrator.travel02.view.SubjectView;

public class SubjectPresenter extends BasePresenter<SubjectView> {
    private SubJectModel model;

    @Override
    protected void initModel() {
        model = new SubJectModel();
        mModels.add(model);
    }

    public void getBundles(){
        model.getBundles(new ResultCallBack<SubJectBean>() {
            @Override
            public void onSuccess(SubJectBean bean) {
                mMvpView.onSuccess(bean.getResult());
            }

            @Override
            public void onFail(String msg) {
                mMvpView.onFail(msg);
            }
        });
    }
}
