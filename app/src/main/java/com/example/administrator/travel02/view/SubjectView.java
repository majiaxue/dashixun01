package com.example.administrator.travel02.view;

import com.example.administrator.travel02.base.BaseMvpView;
import com.example.administrator.travel02.bean.SubJectBean;

public interface SubjectView extends BaseMvpView {
    void onSuccess(SubJectBean.ResultBean bean);

    void onFail(String msg);
}
