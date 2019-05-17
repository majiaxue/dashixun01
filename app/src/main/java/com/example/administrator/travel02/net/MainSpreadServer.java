package com.example.administrator.travel02.net;

import com.example.administrator.travel02.bean.BanmiInfo;
import com.example.administrator.travel02.bean.CommentBean;
import com.example.administrator.travel02.bean.LikeBean;
import com.example.administrator.travel02.bean.MainBean;
import com.example.administrator.travel02.bean.MainSpreadBean;
import com.example.administrator.travel02.bean.SubJectBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MainSpreadServer {
    public String URL="https://api.banmi.com/api/3.0/content/routes/";
    int SuccessCode=0;

    @GET("{routeId}")
    Observable<MainSpreadBean>getSpreadData(@Header("banmi-app-token")String token, @Path("routeId")int routeId);

    @POST("api/3.0/content/routes/{routeId}/like")
    Observable<String> addLike(@Header("banmi-app-token") String token, @Path("routeId") int id);

    @POST("api/3.0/content/routes/{routeId}/dislike")
    Observable<String> disLike(@Header("banmi-app-token") String token, @Path("routeId") int id);

    public String CollectURL="https://api.banmi.com/";
    @GET("api/3.0/account/collectedRoutes")
    Observable<LikeBean> getCollectData(@Header("banmi-app-token") String token, @Query("page") int page);
    @GET("api/3.0/banmi/{banmiId}")
    Observable<BanmiInfo> getBanmiInfo(@Header("banmi-app-token") String token, @Path("banmiId") int id, @Query("page") int page);

    @GET("api/3.0/banmi/{banmiId}/routes")
    Observable<MainBean> getLineInfo(@Header("banmi-app-token") String token, @Path("banmiId") int id, @Query("page") int page);
    //api/3.0/content/bundles

    @GET("api/3.0/content/bundles")
    Observable<SubJectBean>getSubjectData(@Header("banmi-app-token")String token);

    @GET("api/3.0/content/routes/{routeId}/reviews")
    Observable<MainSpreadBean>getCommentData(@Header("banmi-app-token")String token,@Path("routeId")int routeId,@Query("page")int page);
}
