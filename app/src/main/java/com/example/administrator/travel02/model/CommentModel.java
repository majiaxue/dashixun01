package com.example.administrator.travel02.model;

import com.example.administrator.travel02.base.BaseModel;
import com.example.administrator.travel02.base.Constants;
import com.example.administrator.travel02.bean.CommentBean;
import com.example.administrator.travel02.bean.MainSpreadBean;
import com.example.administrator.travel02.net.BaseObserver;
import com.example.administrator.travel02.net.HttpUtils;
import com.example.administrator.travel02.net.MainSpreadServer;
import com.example.administrator.travel02.net.ResultCallBack;
import com.example.administrator.travel02.net.RxUtils;
import com.example.administrator.travel02.utils.SpUtil;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public class CommentModel extends BaseModel {
    public void getCommentData(String token, final int id, int page, final ResultCallBack<MainSpreadBean> callBack){
        MainSpreadServer apiserver = HttpUtils.getInstance().getApiserver(MainSpreadServer.CollectURL, MainSpreadServer.class);
        Observable<MainSpreadBean> data = apiserver.getCommentData(token, id, page);
        data.compose(RxUtils.<MainSpreadBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<MainSpreadBean>() {
                    @Override
                    public void onNext(MainSpreadBean commentBean) {
                        if (commentBean==null){
                            callBack.onFail(commentBean.getDesc());
                        }else {
                            if (commentBean!=null&&commentBean.getCode()==MainSpreadServer.SuccessCode){
                                callBack.onSuccess(commentBean);
                            }
                        }
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
