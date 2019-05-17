package com.example.administrator.travel02.view;

import com.example.administrator.travel02.base.BaseMvpView;
import com.example.administrator.travel02.bean.CommentBean;
import com.example.administrator.travel02.bean.MainSpreadBean;

public interface CommentView extends BaseMvpView{
    void onSuccess(MainSpreadBean.ResultEntity result);

    void onFail(String msg);
}
