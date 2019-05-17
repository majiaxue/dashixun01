package com.example.administrator.travel02.view.main;

import com.example.administrator.travel02.base.BaseMvpView;
import com.example.administrator.travel02.bean.TravelBean;

public interface FellowView extends BaseMvpView {
    void onSuccess(TravelBean.ResultBean result);

    void onFile(String msg);
}
