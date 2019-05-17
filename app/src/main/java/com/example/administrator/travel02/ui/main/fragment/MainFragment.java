package com.example.administrator.travel02.ui.main.fragment;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.example.administrator.travel02.MainSpresdActivity;
import com.example.administrator.travel02.R;
import com.example.administrator.travel02.base.BaseFragment;
import com.example.administrator.travel02.base.Constants;
import com.example.administrator.travel02.bean.LikeBean;
import com.example.administrator.travel02.bean.MainBean;
import com.example.administrator.travel02.presenter.MainPresenter;
import com.example.administrator.travel02.ui.main.activity.MainWebActivity;
import com.example.administrator.travel02.ui.main.adapter.RecShouAdapter;
import com.example.administrator.travel02.utils.SpUtil;
import com.example.administrator.travel02.utils.ToastUtil;
import com.example.administrator.travel02.view.main.MainView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends BaseFragment<MainView, MainPresenter> implements MainView {
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.smart)
    SmartRefreshLayout smart;
    private ArrayList<MainBean.ResultBean.BannersBean> bannersBeans;
    private ArrayList<MainBean.ResultBean.RoutesBean> routesBeans;
    private RecShouAdapter adapter;
    private int page=1;

    @Override
    protected MainPresenter initPresenter() {
        return new MainPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    protected void initView() {
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        bannersBeans = new ArrayList<>();
        routesBeans = new ArrayList<>();
        adapter = new RecShouAdapter(getContext());
        recycler.setAdapter(adapter);
        showLoading();
    }

    @Override
    protected void initListener() {
        smart.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
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
        adapter.setOnItemClickListener(new RecShouAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                MainBean.ResultBean.RoutesBean entity = routesBeans.get(position);
                Intent intent = new Intent(getContext(), MainSpresdActivity.class);
                intent.putExtra(Constants.DATA,entity.getId());
                startActivity(intent);
            }
        });
        adapter.setOnBundleClickListener(new RecShouAdapter.OnBundleClickListener() {
            @Override
            public void onClick(String url, String title) {
                Intent intent=new Intent(getContext(), MainWebActivity.class);
                intent.putExtra(Constants.DATA,url+"?os=android");
                intent.putExtra(Constants.TITLE,title);
                startActivity(intent);
            }
        });

    }

    @Override
    public void success(MainBean bean) {
        hideLoading();
        if (page==1){
            bannersBeans.clear();
            routesBeans.clear();
        }
        bannersBeans.addAll(bean.getResult().getBanners());
        routesBeans.addAll(bean.getResult().getRoutes());
        adapter.setBanners(bannersBeans);
        adapter.setRoutes(routesBeans);
        smart.finishLoadMore();
        smart.finishRefresh();
    }

    @Override
    public void onFail(String string) {
        ToastUtil.showShort(string);
    }

    @Override
    public void onSuccess(MainBean.ResultBean result) {

    }

    @Override
    protected void initData() {
        mPresenter.getPre((String) SpUtil.getParam(Constants.TOKEN,""),page);
    }
}
