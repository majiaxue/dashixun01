package com.example.administrator.travel02.presenter;

import com.example.administrator.travel02.base.BasePresenter;
import com.example.administrator.travel02.bean.MainBean;
import com.example.administrator.travel02.model.LineModel;
import com.example.administrator.travel02.net.ResultCallBack;
import com.example.administrator.travel02.view.main.MainView;

public class LinePresenter extends BasePresenter<MainView> {
    private LineModel model;

    @Override
    protected void initModel() {
        model = new LineModel();
        mModels.add(model);
    }

    public void getLineData(int id, int page, String token) {
        model.getLineData(token, id, page, new ResultCallBack<MainBean>() {
            @Override
            public void onSuccess(MainBean bean) {
                mMvpView.onSuccess(bean.getResult());
            }

            @Override
            public void onFail(String msg) {
                mMvpView.onFail(msg);
            }
        });
    }
}
