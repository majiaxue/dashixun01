package com.example.administrator.travel02.view.main;

import com.example.administrator.travel02.base.BaseMvpView;
import com.example.administrator.travel02.bean.MainBean;

public interface MainView extends BaseMvpView{
    void success(MainBean bean);
    void onFail(String string);

    void onSuccess(MainBean.ResultBean result);
}
