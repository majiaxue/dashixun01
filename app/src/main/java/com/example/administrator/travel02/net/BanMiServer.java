package com.example.administrator.travel02.net;

import com.example.administrator.travel02.bean.FollowedBean;
import com.example.administrator.travel02.bean.TravelBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface BanMiServer {
    public static final String getUrl="http://api.banmi.com/";
    int SuccessCose=0;
    @GET("api/3.0/banmi")
    Observable<TravelBean> getData(@Header("banmi-app-token")String token,@Query("page") int page);

    @GET("api/3.0/account/followedBanmi")
    Observable<TravelBean>getFellowedData(@Header("banmi-app-token") String token,@Query("page")int page);

    @POST("api/3.0/banmi/{banmiId}/follow")
    Observable<String> addLike(@Header("banmi-app-token") String token, @Path("banmiId") int id);

    @POST("api/3.0/banmi/{banmiId}/unfollow")
    Observable<String> disLike(@Header("banmi-app-token") String token, @Path("banmiId") int id);

    

}
