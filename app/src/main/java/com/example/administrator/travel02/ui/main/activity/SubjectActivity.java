package com.example.administrator.travel02.ui.main.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.travel02.R;
import com.example.administrator.travel02.base.BaseActivity;
import com.example.administrator.travel02.base.Constants;
import com.example.administrator.travel02.bean.SubJectBean;
import com.example.administrator.travel02.presenter.SubjectPresenter;
import com.example.administrator.travel02.ui.main.adapter.RecSubjectAdapter;
import com.example.administrator.travel02.view.SubjectView;
import com.jaeger.library.StatusBarUtil;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SubjectActivity extends BaseActivity<SubjectView, SubjectPresenter> implements SubjectView {
    @BindView(R.id.recView)
    RecyclerView recView;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private ArrayList<SubJectBean.ResultBean.BundlesBean> list;
    private RecSubjectAdapter adapter;

    @Override
    public void onSuccess(SubJectBean.ResultBean bean) {
        list= (ArrayList<SubJectBean.ResultBean.BundlesBean>) bean.getBundles();
        adapter.setList(list);
    }

    @Override
    protected void initView() {
        StatusBarUtil.setLightMode(this);
        toolbar.setTitle("");
        list = new ArrayList<>();
        toolbar.setNavigationIcon(R.drawable.back_white);
        setSupportActionBar(toolbar);
        adapter = new RecSubjectAdapter(this);
        recView.setLayoutManager(new LinearLayoutManager(this));
        recView.setAdapter(adapter);
    }

    @Override
    protected void initListener() {
        adapter.setOnItemClick(new RecSubjectAdapter.onItemClick() {
            @Override
            public void onClick(String url, String title) {
                Intent intent=new Intent(SubjectActivity.this,MainWebActivity.class);
                intent.putExtra(Constants.DATA,url+"?os=android");
                intent.putExtra(Constants.TITLE,title);
                startActivity(intent);
                finish();
            }
        });
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void initData() {
        mPresenter.getBundles();
    }

    @Override
    protected SubjectPresenter initPresenter() {
        return new SubjectPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_subject;
    }

    @Override
    public void onFail(String msg) {

    }

    @Override
    public void toastShort(String msg) {

    }
}
