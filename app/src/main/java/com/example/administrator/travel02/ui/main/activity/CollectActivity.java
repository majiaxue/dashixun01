package com.example.administrator.travel02.ui.main.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.administrator.travel02.MainSpresdActivity;
import com.example.administrator.travel02.R;
import com.example.administrator.travel02.base.BaseActivity;
import com.example.administrator.travel02.base.Constants;
import com.example.administrator.travel02.bean.LikeBean;
import com.example.administrator.travel02.presenter.CollectPresenter;
import com.example.administrator.travel02.ui.main.adapter.RecCollectAdapter;
import com.example.administrator.travel02.utils.SpUtil;
import com.example.administrator.travel02.utils.ToastUtil;
import com.example.administrator.travel02.view.main.CollectView;
import com.jaeger.library.StatusBarUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CollectActivity extends BaseActivity<CollectView, CollectPresenter> implements CollectView{
    @BindView(R.id.toolBar)
    Toolbar toolBar;
    @BindView(R.id.recView)
    RecyclerView recView;
    @BindView(R.id.srl)
    SmartRefreshLayout srl;
    private int page = 1;
    private RecCollectAdapter adapter;
    private ArrayList<LikeBean.ResultBean.CollectedRoutesBean> list;

    @Override
    protected void initView() {
        StatusBarUtil.setLightMode(this);
        list = new ArrayList<>();
        recView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecCollectAdapter(this);
        recView.setAdapter(adapter);
        toolBar.setNavigationIcon(R.drawable.back_white);
    }

    @Override
    protected void initListener() {
        adapter.setOnItemClickListener(new RecCollectAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent(CollectActivity.this, MainSpresdActivity.class);
                intent.putExtra(Constants.DATA,list.get(position).getId());
                startActivity(intent);
            }
        });
        srl.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                initData();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                initData();
            }
        });
        toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void initData() {
        mPresenter.getLikeData((String) SpUtil.getParam(Constants.TOKEN,""),page);
    }

    @Override
    protected CollectPresenter initPresenter() {
        return new CollectPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_collect;
    }

    @Override
    public void toastShort(String msg) {

    }

    @Override
    public void onSuccess(LikeBean.ResultBean result) {
        if (page == 1){
            list.clear();
        }
        list.addAll(result.getCollectedRoutes());
        adapter.setList(list);
        srl.finishRefresh();
        srl.finishLoadMore();
    }

    @Override
    public void onFail(String msg) {
        ToastUtil.showShort(msg);
    }
}
