package com.example.administrator.travel02.view.main;

import android.app.Activity;

import com.example.administrator.travel02.base.BaseMvpView;

/**
 * @author xts
 *         Created by asus on 2019/4/29.
 */

public interface LoginOrBindView extends BaseMvpView {
    String getPhone();

    void go2MainActivity();

    Activity getAct();

    void setData(String code);
}
