package com.example.administrator.travel02.model;

import android.util.Log;

import com.example.administrator.travel02.base.BaseModel;
import com.example.administrator.travel02.bean.FollowedBean;
import com.example.administrator.travel02.bean.TravelBean;
import com.example.administrator.travel02.net.BanMiServer;
import com.example.administrator.travel02.net.BaseObserver;
import com.example.administrator.travel02.net.HttpUtils;
import com.example.administrator.travel02.net.ResultCallBack;
import com.example.administrator.travel02.net.RxUtils;

import org.json.JSONException;
import org.json.JSONObject;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public class FellowModel extends BaseModel {
    public void getLinkData(String token, int page, final ResultCallBack<TravelBean> callBack){
        BanMiServer apiserver = HttpUtils.getInstance().getApiserver(BanMiServer.getUrl, BanMiServer.class);
        Observable<TravelBean> data = apiserver.getFellowedData(token, page);
        data.compose(RxUtils.<TravelBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<TravelBean>() {
                    @Override
                    public void onNext(TravelBean bean) {
                        if (bean!=null){
                            if (bean.getCode()==BanMiServer.SuccessCose){
                                callBack.onSuccess(bean);
                            }else {
                                callBack.onFail(bean.getDesc());
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
    private static final String TAG = "BanmiModel";
    public void addLike(String token, int id, final ResultCallBack<String> callBack) {
        BanMiServer service = HttpUtils.getInstance().getApiserver(BanMiServer.getUrl, BanMiServer.class);
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
                            if (jsonObject.getInt("code") == BanMiServer.SuccessCose) {
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
        BanMiServer service = HttpUtils.getInstance().getApiserver(BanMiServer.getUrl, BanMiServer.class);
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
                            if (jsonObject.getInt("code") == BanMiServer.SuccessCose) {
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
