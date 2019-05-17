package com.example.administrator.travel02.model;

import com.example.administrator.travel02.base.BaseModel;
import com.example.administrator.travel02.bean.MainBean;
import com.example.administrator.travel02.net.BaseObserver;
import com.example.administrator.travel02.net.HttpUtils;
import com.example.administrator.travel02.net.MainSpreadServer;
import com.example.administrator.travel02.net.ResultCallBack;
import com.example.administrator.travel02.net.RxUtils;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public class LineModel extends BaseModel {
    public void getLineData(String token, int id, int page, final ResultCallBack<MainBean> callBack) {
        MainSpreadServer apiserver = HttpUtils.getInstance().getApiserver(MainSpreadServer.CollectURL, MainSpreadServer.class);
        Observable<MainBean> lineInfo = apiserver.getLineInfo(token, id, page);
        lineInfo.compose(RxUtils.<MainBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<MainBean>() {
                    @Override
                    public void error(String msg) {
                        callBack.onFail(msg);
                    }

                    @Override
                    protected void subscribe(Disposable d) {
                        addDisposable(d);
                    }

                    @Override
                    public void onNext(MainBean mainBean) {
                        if (mainBean != null) {
                            if (mainBean.getCode() == MainSpreadServer.SuccessCode) {
                                callBack.onSuccess(mainBean);
                            } else {
                                callBack.onFail(mainBean.getDesc());
                            }
                        }
                    }
                });
    }
}
