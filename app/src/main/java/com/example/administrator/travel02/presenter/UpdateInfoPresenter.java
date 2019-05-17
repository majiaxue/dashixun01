package com.example.administrator.travel02.presenter;
import com.example.administrator.travel02.base.BasePresenter;
import com.example.administrator.travel02.model.UpdateInfoModel;
import com.example.administrator.travel02.net.ResultCallBack;
import com.example.administrator.travel02.view.UpdateInfoView;

/**
 * @author xts
 *         Created by asus on 2019/5/4.
 */

public class UpdateInfoPresenter extends BasePresenter<UpdateInfoView> {

    private UpdateInfoModel model;

    @Override
    protected void initModel() {
        model = new UpdateInfoModel();
        mModels.add(model);
    }

    public void updateInfo(String token,String info,int type){
        model.updateInfo(token, info, type, new ResultCallBack<String>() {
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
