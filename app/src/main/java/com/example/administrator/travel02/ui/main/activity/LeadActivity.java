package com.example.administrator.travel02.ui.main.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.example.administrator.travel02.R;
import com.example.administrator.travel02.ui.main.adapter.ViewPagerAdapter;
import com.example.administrator.travel02.widget.PreviewIndicator;
import com.example.administrator.travel02.utils.SpUtil;
import com.jaeger.library.StatusBarUtil;

import java.util.ArrayList;

public class LeadActivity extends AppCompatActivity {

    private ViewPager vp;
    private ArrayList<View> mViewList;
    private PreviewIndicator prev;
    private SharedPreferences.Editor edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lead);
        initView();
    }

    private void initView() {
        vp = (ViewPager) findViewById(R.id.vp);
        prev = (PreviewIndicator) findViewById(R.id.prev);
        mViewList = new ArrayList<View>();
        LayoutInflater lf = getLayoutInflater().from(LeadActivity.this);
        View view1 = lf.inflate(R.layout.we_indicator1, null);
        View view2 = lf.inflate(R.layout.we_indicator2, null);
        View view3 = lf.inflate(R.layout.we_indicator3, null);
        mViewList.add(view1);
        mViewList.add(view2);
        mViewList.add(view3);
        Button bt = view3.findViewById(R.id.bt);
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
                if (i==0){
                    prev.setSelected(0);
                    prev.setVisibility(View.VISIBLE);
                }else if (i==1){
                    prev.setSelected(1);
                    prev.setVisibility(View.VISIBLE);
                }else {
                    prev.setSelected(2);
                    prev.setVisibility(View.GONE);

                }

            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
            bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SpUtil.setParam("isPagerOpened",true);
                    startActivity(new Intent(LeadActivity.this,LoginActivity.class));
                    finish();
                }
            });
        vp.setAdapter(new ViewPagerAdapter(mViewList));
        prev.initSize(80, 32, 6);
        prev.setNumbers(3);
        StatusBarUtil.setLightMode(this);
    }
}
