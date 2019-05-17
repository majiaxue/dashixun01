package com.example.administrator.travel02.view;

import com.example.administrator.travel02.base.BaseMvpView;
import com.example.administrator.travel02.bean.LoadBean;

public interface VersionView extends BaseMvpView{
    void onSuccess(LoadBean bean);

    void onFail(String msg);
}
