package com.example.administrator.travel02.net;

import com.example.administrator.travel02.bean.MainBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface MainServer {
    public static final String getUrl="http://api.banmi.com/";
    @GET("api/3.0/content/routesbundles")
    Observable<MainBean> getLogin(@Header("banmi-app-token") String token, @Query("page")int page);
}
