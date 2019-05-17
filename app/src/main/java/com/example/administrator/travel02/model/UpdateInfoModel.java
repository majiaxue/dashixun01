package com.example.administrator.travel02.model;

import android.util.Log;
import com.example.administrator.travel02.base.BaseModel;
import com.example.administrator.travel02.net.BaseObserver;
import com.example.administrator.travel02.net.HttpUtils;
import com.example.administrator.travel02.net.MyService;
import com.example.administrator.travel02.net.ResultCallBack;
import com.example.administrator.travel02.net.RxUtils;
import com.example.administrator.travel02.ui.main.activity.UpdateInfoActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;

/**
 * Created by 灵风 on 2019/5/7.
 */
public class UpdateInfoModel extends BaseModel {
    private static final String TAG = "UpdateInfoModel";

    public void updateInfo(String token, String info, int type, final ResultCallBack<String> callBack) {
        MyService apiserver = HttpUtils.getInstance().getApiserver(MyService.BaseUrl, MyService.class);
        HashMap<String, String> map = new HashMap<>();
        if (type == UpdateInfoActivity.NICK_TYPE) {
            map.put("username", info);
        } else if (type == UpdateInfoActivity.SIGNATURE_TYPE) {
            map.put("description", info);
        } else if (type == UpdateInfoActivity.HEADER_TYPE) {

        } else {
            map.put("gender", info);
        }
        apiserver.updateInfo(token, map)
                .compose(RxUtils.<ResponseBody>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<ResponseBody>() {
                    @Override
                    public void error(String msg) {
                        Log.e(TAG, "error: e="+msg);
                    }

                    @Override
                    protected void subscribe(Disposable d) {
                        addDisposable(d);
                    }

                    @Override
                    public void onNext(ResponseBody s) {
                        try {
                            String string = s.string();
                            if (new JSONObject(string).getInt("code") == MyService.SUCCESS_CODE){
                                callBack.onSuccess(string);
                            }else {
                                callBack.onFail(new JSONObject(string).getString("desc"));
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Log.e(TAG, "onNext: e=" + s);
                    }
                });
    }
}
