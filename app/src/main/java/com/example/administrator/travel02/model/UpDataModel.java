package com.example.administrator.travel02.model;

import android.util.Log;
import android.widget.Toast;

import com.example.administrator.travel02.base.BaseModel;
import com.example.administrator.travel02.net.BaseObserver;
import com.example.administrator.travel02.net.HttpUtils;
import com.example.administrator.travel02.net.MyService;
import com.example.administrator.travel02.net.ResultCallBack;
import com.example.administrator.travel02.net.RxUtils;
import com.example.administrator.travel02.ui.main.activity.MeActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;

public class UpDataModel extends BaseModel {
    public void upDataInfo(String token, String info,int type,final ResultCallBack<String> callBack){
        MyService apiserver = HttpUtils.getInstance().getApiserver(MyService.BaseUrl, MyService.class);
        HashMap<String, String> hashMap = new HashMap<>();
        if (type== MeActivity.NICK_TYPE){
            hashMap.put("username",info);
        }else if (type==MeActivity.SINGNATURE_TYPE){
            hashMap.put("description",info);
        }else if (type==MeActivity.HEADER_TYPE){

        }else {
            hashMap.put("gender",info);
        }
        apiserver.updateInfo(token,hashMap)
                .compose(RxUtils.<ResponseBody>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<ResponseBody>() {
                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            String string = responseBody.string();
                            if (new JSONObject(string).getInt("code")==MyService.SUCCESS_CODE){
                            callBack.onSuccess(string);
                            }else {
                                callBack.onFail(new JSONObject(string).getString("desc"));

                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Log.d("updata","信息"+responseBody);
                    }

                    @Override
                    public void error(String msg) {

                    }

                    @Override
                    protected void subscribe(Disposable d) {
                        addDisposable(d);
                    }
                });
    }
}
