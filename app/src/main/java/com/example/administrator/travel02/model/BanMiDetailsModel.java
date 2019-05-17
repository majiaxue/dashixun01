package com.example.administrator.travel02.model;

import android.util.Log;

import com.example.administrator.travel02.base.BaseModel;
import com.example.administrator.travel02.bean.BanmiInfo;
import com.example.administrator.travel02.bean.MainBean;
import com.example.administrator.travel02.net.BaseObserver;
import com.example.administrator.travel02.net.HttpUtils;
import com.example.administrator.travel02.net.MainServer;
import com.example.administrator.travel02.net.MainSpreadServer;
import com.example.administrator.travel02.net.ResultCallBack;
import com.example.administrator.travel02.net.RxUtils;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public class BanMiDetailsModel extends BaseModel {
    private static final String TAG = "BanmiInfoModel";

    public void getBanmiInfo(String token, int id, int page, final ResultCallBack<BanmiInfo> callBack){
        MainSpreadServer apiserver = HttpUtils.getInstance().getApiserver(MainSpreadServer.CollectURL, MainSpreadServer.class);
        apiserver.getBanmiInfo(token,id,page)
                .compose(RxUtils.<BanmiInfo>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<BanmiInfo>() {
                    @Override
                    public void error(String msg) {
                        Log.e(TAG, "error: e="+msg );
                    }

                    @Override
                    protected void subscribe(Disposable d) {
                        addDisposable(d);
                    }

                    @Override
                    public void onNext(BanmiInfo banmiInfo) {
                        if (banmiInfo != null){
                            if (banmiInfo.getCode() == MainSpreadServer.SuccessCode){
                                callBack.onSuccess(banmiInfo);
                            }else {
                                callBack.onFail(banmiInfo.getDesc());
                            }
                        }
                    }
                });
    }
}
