package com.example.administrator.travel02.presenter;

import com.example.administrator.travel02.base.BasePresenter;
import com.example.administrator.travel02.bean.CommentBean;
import com.example.administrator.travel02.bean.MainSpreadBean;
import com.example.administrator.travel02.model.CommentModel;
import com.example.administrator.travel02.net.ResultCallBack;
import com.example.administrator.travel02.view.CommentView;

public class CommentPresenter extends BasePresenter<CommentView> {
    private CommentModel model;
    @Override
    protected void initModel() {
        model=new CommentModel();
    }
    public void getCommentData(String token,int id,int page){
        model.getCommentData(token,id,page, new ResultCallBack<MainSpreadBean>() {
            @Override
            public void onSuccess(MainSpreadBean bean) {
                if (mMvpView!=null){
                    mMvpView.onSuccess(bean.getResult());
                }
            }

            @Override
            public void onFail(String msg) {
                if (mMvpView!=null){
                    mMvpView.onFail(msg);
                }
            }
        });
    }
}
