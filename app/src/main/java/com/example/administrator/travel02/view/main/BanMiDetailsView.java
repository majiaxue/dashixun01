package com.example.administrator.travel02.view.main;

import com.example.administrator.travel02.base.BaseMvpView;
import com.example.administrator.travel02.bean.BanmiInfo;

public interface BanMiDetailsView extends BaseMvpView {
    void onSuccess(BanmiInfo bean);

    void onFail(String msg);

}
