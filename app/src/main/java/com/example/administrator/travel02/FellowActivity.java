package com.example.administrator.travel02;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.administrator.travel02.base.BaseActivity;
import com.example.administrator.travel02.base.Constants;
import com.example.administrator.travel02.bean.LikeBean;
import com.example.administrator.travel02.bean.TravelBean;
import com.example.administrator.travel02.presenter.FellowPresenter;
import com.example.administrator.travel02.ui.main.adapter.RecBanMiAdapter;
import com.example.administrator.travel02.utils.SpUtil;
import com.example.administrator.travel02.view.main.FellowView;
import com.jaeger.library.StatusBarUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FellowActivity extends BaseActivity<FellowView, FellowPresenter> implements FellowView {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.sml)
    SmartRefreshLayout sml;
    private int page=1;
    private RecBanMiAdapter adapter;
    private ArrayList<TravelBean.ResultBean.BanmiBean> list;

    @Override
    protected void initView() {
        StatusBarUtil.setLightMode(this);
        list=new ArrayList<>();
        recycler.setLayoutManager(new LinearLayoutManager(this));
        adapter=new RecBanMiAdapter(list,this);
        recycler.setAdapter(adapter);
        toolbar.setTitle("");
        toolbar.setNavigationIcon(R.drawable.back_white);
    }

    @Override
    protected void initListener() {
        sml.setOnRefreshListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                initData();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page=1;
                initData();
            }
        });
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        adapter.setFellowListener(new RecBanMiAdapter.FellowListener() {
            @Override
            public void onFellow(int id,int position) {
                mPresenter.addLike((String) SpUtil.getParam(Constants.TOKEN,""),id);
            }

            @Override
            public void cancelFellow(int id,int position) {
                mPresenter.disLike((String) SpUtil.getParam(Constants.TOKEN,""),id);
                }
        });
    }

    @Override
    protected void initData() {
        mPresenter.getLinkData((String) SpUtil.getParam(Constants.TOKEN,""),page);
    }

    @Override
    protected FellowPresenter initPresenter() {
        return new FellowPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_fellow;
    }

    @Override
    public void onSuccess(TravelBean.ResultBean result) {
        if (page==1){
            list.clear();
        }
        list.addAll(result.getBanmi());
        adapter.setBanmiBeans(list);
        sml.finishLoadMore();
        sml.finishRefresh();
    }

    @Override
    public void onFile(String msg) {

    }

    @Override
    public void toastShort(String msg) {

    }
}
