package com.example.administrator.travel02.net;

import android.content.Context;
import android.content.Intent;
import android.webkit.JavascriptInterface;

import com.example.administrator.travel02.MainSpresdActivity;
import com.example.administrator.travel02.base.Constants;
import com.example.administrator.travel02.ui.main.activity.SubjectActivity;
import com.just.agentweb.AgentWeb;

public class AndroidInterface {
    private AgentWeb agentWeb;
    Context context;

    public AndroidInterface(AgentWeb agentWeb, Context context) {
        this.agentWeb = agentWeb;
        this.context = context;
    }
    @JavascriptInterface
    public void callAndroid(String type,int id){
        Intent intent=new Intent(context, MainSpresdActivity.class);
        intent.putExtra(Constants.DATA,id);
        context.startActivity(intent);
    }
    @JavascriptInterface
    public void callAndroid(String type){
        context.startActivity(new Intent(context, SubjectActivity.class));
    }
}
