package com.example.administrator.travel02.view.main;

import com.example.administrator.travel02.base.BaseMvpView;
import com.example.administrator.travel02.bean.LikeBean;

public interface CollectView extends BaseMvpView{
    void onSuccess(LikeBean.ResultBean result);

    void onFail(String msg);
}
