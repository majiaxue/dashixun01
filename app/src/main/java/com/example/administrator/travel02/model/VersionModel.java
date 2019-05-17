package com.example.administrator.travel02.model;

import android.util.Log;

import com.example.administrator.travel02.base.BaseModel;
import com.example.administrator.travel02.base.Constants;
import com.example.administrator.travel02.bean.LikeBean;
import com.example.administrator.travel02.bean.LoadBean;
import com.example.administrator.travel02.net.ApiService;
import com.example.administrator.travel02.net.BaseObserver;
import com.example.administrator.travel02.net.HttpUtils;
import com.example.administrator.travel02.net.MainSpreadServer;
import com.example.administrator.travel02.net.ResultCallBack;
import com.example.administrator.travel02.net.RxUtils;
import com.example.administrator.travel02.utils.SpUtil;

import io.reactivex.disposables.Disposable;

public class VersionModel extends BaseModel {
    private static final String TAG = "VersionModel";
    public void getVersionData(String token, final ResultCallBack<LoadBean> callBack){
        ApiService apiserver = HttpUtils.getInstance().getApiserver(ApiService.LoadUrl, ApiService.class);
        apiserver.getLoadData(token)
                .compose(RxUtils.<LoadBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<LoadBean>() {
                    @Override
                    public void error(String msg) {
                        Log.e(TAG, "error: e=" + msg);
                    }

                    @Override
                    protected void subscribe(Disposable d) {
                        addDisposable(d);
                    }

                    @Override
                    public void onNext(LoadBean likeBean) {
                        if (likeBean != null) {
                            if (likeBean.getCode() == 0) {
                                callBack.onSuccess(likeBean);
                            } else {
                                callBack.onFail(likeBean.getDesc());
                            }
                        }
                    }
                });
    }
}
