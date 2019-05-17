package com.example.administrator.travel02.model;

import com.example.administrator.travel02.base.BaseModel;
import com.example.administrator.travel02.bean.LoginInfo;
import com.example.administrator.travel02.bean.MainBean;
import com.example.administrator.travel02.net.BaseObserver;
import com.example.administrator.travel02.net.EveryWhereService;
import com.example.administrator.travel02.net.HttpUtils;
import com.example.administrator.travel02.net.MainServer;
import com.example.administrator.travel02.net.ResultCallBack;
import com.example.administrator.travel02.net.RxUtils;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public class MainModel extends BaseModel {
    public void model(String token,int page,final ResultCallBack<MainBean> callBack) {
        MainServer myApi = HttpUtils.getInstance().getApiserver(MainServer.getUrl, MainServer.class);
        Observable<MainBean> apiMain = myApi.getLogin(token,page);
        apiMain.compose(RxUtils.<MainBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<MainBean>() {
                    @Override
                    public void onNext(MainBean mainBean) {
                        callBack.onSuccess(mainBean);
                    }

                    @Override
                    public void error(String msg) {
                        callBack.onFail(msg);
                    }

                    @Override
                    protected void subscribe(Disposable d) {
                        addDisposable(d);
                    }
                });
    }
}
