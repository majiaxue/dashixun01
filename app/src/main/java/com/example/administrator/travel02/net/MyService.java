package com.example.administrator.travel02.net;

import java.util.HashMap;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface MyService {
    public static final int SUCCESS_CODE = 0;
    public static final int VERIFY_CODE_ERROR = 200502;
    public static final int WECHAT_HAVE_BINDED = 20171102;
    //token 失效
    public static final int TOKEN_EXPIRE = 20170707;
    //余额不足
    public static final int MONEY_NOT_ENOUGH = 200607;
    String BaseUrl = "http://api.banmi.com/";

    @FormUrlEncoded
    @POST("api/3.0/account/updateInfo")
    Observable<ResponseBody> updateInfo(@Header("banmi-app-token") String token, @FieldMap HashMap<String, String> map);
}
