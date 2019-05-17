package com.example.administrator.travel02.model;

import android.util.Log;

import com.example.administrator.travel02.base.BaseModel;
import com.example.administrator.travel02.base.Constants;
import com.example.administrator.travel02.bean.LikeBean;
import com.example.administrator.travel02.net.BaseObserver;
import com.example.administrator.travel02.net.HttpUtils;
import com.example.administrator.travel02.net.MainSpreadServer;
import com.example.administrator.travel02.net.ResultCallBack;
import com.example.administrator.travel02.net.RxUtils;
import com.example.administrator.travel02.utils.SpUtil;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public class CollectModel extends BaseModel {
    private static final String TAG = "LikeModel";

    public void getLikeData(int page, final ResultCallBack<LikeBean> callBack) {
        MainSpreadServer apiserver = HttpUtils.getInstance().getApiserver(MainSpreadServer.CollectURL, MainSpreadServer.class);
        apiserver.getCollectData((String) SpUtil.getParam(Constants.TOKEN, ""), page)
                .compose(RxUtils.<LikeBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<LikeBean>() {
                    @Override
                    public void error(String msg) {
                        Log.e(TAG, "error: e=" + msg);
                    }

                    @Override
                    protected void subscribe(Disposable d) {
                        addDisposable(d);
                    }

                    @Override
                    public void onNext(LikeBean likeBean) {
                        if (likeBean != null) {
                            if (likeBean.getCode() == MainSpreadServer.SuccessCode) {
                                callBack.onSuccess(likeBean);
                            } else {
                                callBack.onFail(likeBean.getDesc());
                            }
                        }
                    }
                });
    }
}
