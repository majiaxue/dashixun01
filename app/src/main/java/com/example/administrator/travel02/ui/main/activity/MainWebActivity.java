package com.example.administrator.travel02.ui.main.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.travel02.R;
import com.example.administrator.travel02.base.Constants;
import com.example.administrator.travel02.net.AndroidInterface;
import com.jaeger.library.StatusBarUtil;
import com.just.agentweb.AgentWeb;

public class MainWebActivity extends AppCompatActivity {

    private TextView tv_title;
    private Toolbar toolbar;
    private LinearLayout ll_parent;
    private AgentWeb mAgentWeb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_web);
        initView();
    }

    private void initView() {
        tv_title = (TextView) findViewById(R.id.tv_title);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        ll_parent = (LinearLayout) findViewById(R.id.ll_parent);
        StatusBarUtil.setLightMode(this);
        toolbar.setTitle("");
        tv_title.setText(getIntent().getStringExtra(Constants.TITLE));
        toolbar.setNavigationIcon(R.drawable.back_white);
        setSupportActionBar(toolbar);
        mAgentWeb=AgentWeb.with(this)
                .setAgentWebParent(ll_parent,new LinearLayout.LayoutParams(-1,-1))
                .closeIndicator()
                .createAgentWeb()
                .ready()
                .go(getIntent().getStringExtra(Constants.DATA));
        mAgentWeb.getJsInterfaceHolder().addJavaObject("android",new AndroidInterface(mAgentWeb,this));
        initListener();
    }
    private void initListener() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
