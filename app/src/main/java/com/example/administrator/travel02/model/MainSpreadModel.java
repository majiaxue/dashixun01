package com.example.administrator.travel02.model;

import android.util.Log;

import com.example.administrator.travel02.base.BaseModel;
import com.example.administrator.travel02.bean.MainSpreadBean;
import com.example.administrator.travel02.net.BanMiServer;
import com.example.administrator.travel02.net.BaseObserver;
import com.example.administrator.travel02.net.HttpUtils;
import com.example.administrator.travel02.net.MainSpreadServer;
import com.example.administrator.travel02.net.ResultCallBack;
import com.example.administrator.travel02.net.RxUtils;

import org.json.JSONException;
import org.json.JSONObject;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public class MainSpreadModel extends BaseModel {
    private static final String TAG = "MainSpreadModel";
    public void getSpreadData(String token, int routeId, final ResultCallBack<MainSpreadBean> callBack){
        MainSpreadServer apiserver = HttpUtils.getInstance().getApiserver(MainSpreadServer.URL, MainSpreadServer.class);
        Observable<MainSpreadBean> data = apiserver.getSpreadData(token, routeId);
        data.compose(RxUtils.<MainSpreadBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<MainSpreadBean>() {
                    @Override
                    public void onNext(MainSpreadBean mainSpreadBean) {
                        if (mainSpreadBean!=null){
                            if (mainSpreadBean.getCode()== MainSpreadServer.SuccessCode){
                                callBack.onSuccess(mainSpreadBean);
                            }else {
                                callBack.onFail(mainSpreadBean.getDesc());
                            }
                        }
                    }

                    @Override
                    public void error(String msg) {

                    }

                    @Override
                    protected void subscribe(Disposable d) {
                        addDisposable(d);
                    }
                });
    }
    public void addLike(String token, int id, final ResultCallBack<String> callBack) {
        MainSpreadServer service = HttpUtils.getInstance().getApiserver(MainSpreadServer.CollectURL, MainSpreadServer.class);
        service.addLike(token, id)
                .compose(RxUtils.<String>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<String>() {
                    @Override
                    public void error(String msg) {
                        Log.e(TAG, "error: e=" + msg);
                    }

                    @Override
                    protected void subscribe(Disposable d) {
                        addDisposable(d);
                    }

                    @Override
                    public void onNext(String s) {
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            if (jsonObject.getInt("code") == MainSpreadServer.SuccessCode) {
                                callBack.onSuccess(jsonObject.getString("desc"));
                            } else {
                                callBack.onFail(jsonObject.getString("desc"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    public void disLike(String token, int id, final ResultCallBack<String> callBack) {
        MainSpreadServer service = HttpUtils.getInstance().getApiserver(MainSpreadServer.CollectURL, MainSpreadServer.class);
        service.disLike(token, id)
                .compose(RxUtils.<String>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<String>() {
                    @Override
                    public void error(String msg) {
                        Log.e(TAG, "error: e=" + msg);
                    }

                    @Override
                    protected void subscribe(Disposable d) {
                        addDisposable(d);
                    }

                    @Override
                    public void onNext(String s) {
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            if (jsonObject.getInt("code") == MainSpreadServer.SuccessCode) {
                                callBack.onSuccess(jsonObject.getString("desc"));
                            } else {
                                callBack.onFail(jsonObject.getString("desc"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }
}
