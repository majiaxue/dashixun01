package com.example.administrator.travel02.view;

import com.example.administrator.travel02.base.BaseMvpView;

public interface UpDataView extends BaseMvpView{
    void onSuccess(String bean);

    void onFail(String msg);
}
