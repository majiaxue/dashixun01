package com.example.administrator.travel02.base;

import com.example.administrator.travel02.bean.LikeBean;
import com.example.administrator.travel02.bean.TravelBean; /**
 * @author xts
 *         Created by asus on 2019/4/2.
 */

public interface BaseMvpView {
    //显示加载loading的方法
    void showLoading();
    //隐藏加载loading的方法
    void hideLoading();
    void toastShort(String msg);
}
