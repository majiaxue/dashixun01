package com.example.administrator.travel02.model;

import android.util.Log;

import com.example.administrator.travel02.base.BaseModel;
import com.example.administrator.travel02.base.Constants;
import com.example.administrator.travel02.bean.SubJectBean;
import com.example.administrator.travel02.net.BaseObserver;
import com.example.administrator.travel02.net.HttpUtils;
import com.example.administrator.travel02.net.MainSpreadServer;
import com.example.administrator.travel02.net.ResultCallBack;
import com.example.administrator.travel02.net.RxUtils;
import com.example.administrator.travel02.utils.SpUtil;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public class SubJectModel extends BaseModel {
    private static final String TAG = "SubjectModel";

    public void getBundles(final ResultCallBack<SubJectBean> callBack){
        HttpUtils.getInstance().getApiserver(MainSpreadServer.CollectURL,MainSpreadServer.class)
                .getSubjectData((String) SpUtil.getParam(Constants.TOKEN,""))
                .compose(RxUtils.<SubJectBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<SubJectBean>() {
                    @Override
                    public void error(String msg) {
                        Log.e(TAG, "error: e="+msg );
                    }

                    @Override
                    protected void subscribe(Disposable d) {
                        addDisposable(d);
                    }

                    @Override
                    public void onNext(SubJectBean bundlesBean) {
                        if (bundlesBean != null){
                            if (bundlesBean.getCode() == MainSpreadServer.SuccessCode){
                                callBack.onSuccess(bundlesBean);
                            }else {
                                callBack.onFail(bundlesBean.getDesc());
                            }
                        }
                    }
                });
    }
}
