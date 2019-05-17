package com.example.administrator.travel02.net;

import com.example.administrator.travel02.bean.LoadBean;
import com.example.administrator.travel02.bean.VerifyCodeBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;

/**
 * @author xts
 *         Created by asus on 2019/4/2.
 */

public interface ApiService {
    int SUCCESS_CODE=200;
    String sBaseUrl = "http://yun918.cn/study/public/index.php/";

    /**
     * 获取验证码
     * @return
     */
    @GET("verify")
    Observable<VerifyCodeBean> getVerifyCode();

    String LoadUrl="https://api.banmi.com/api/app/common/";
    @GET("getVersionInfo?operating_system=android")
    Observable<LoadBean>getLoadData(@Header("banmi-app-token")String token);
}
