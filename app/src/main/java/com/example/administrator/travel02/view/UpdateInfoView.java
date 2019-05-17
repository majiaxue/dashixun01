package com.example.administrator.travel02.view;

import com.example.administrator.travel02.base.BaseMvpView;

/**
 * @author xts
 *         Created by asus on 2019/4/29.
 */

public interface UpdateInfoView extends BaseMvpView {
    void onSuccess(String msg);
    void onFail(String msg);
}
