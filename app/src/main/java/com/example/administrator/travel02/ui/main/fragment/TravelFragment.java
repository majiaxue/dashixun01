package com.example.administrator.travel02.ui.main.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.travel02.R;
import com.example.administrator.travel02.base.BaseFragment;
import com.example.administrator.travel02.base.Constants;
import com.example.administrator.travel02.bean.LikeBean;
import com.example.administrator.travel02.bean.TravelBean;
import com.example.administrator.travel02.net.BanMiServer;
import com.example.administrator.travel02.presenter.FellowPresenter;
import com.example.administrator.travel02.presenter.TravelPresenter;
import com.example.administrator.travel02.ui.main.activity.BanMiDetailsActivity;
import com.example.administrator.travel02.ui.main.adapter.RecBanMiAdapter;
import com.example.administrator.travel02.utils.SpUtil;
import com.example.administrator.travel02.utils.ToastUtil;
import com.example.administrator.travel02.view.main.FellowView;
import com.example.administrator.travel02.view.main.TravelView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class TravelFragment extends BaseFragment<TravelView, TravelPresenter> implements TravelView {


    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.smart)
    SmartRefreshLayout smart;
    private int page = 1;
    private RecBanMiAdapter adapter;
    private ArrayList<TravelBean.ResultBean.BanmiBean> list;

    public TravelFragment() {
        // Required empty public constructor
    }

    @Override
    protected void initView() {
        showLoading();
        list = new ArrayList<>();
        adapter = new RecBanMiAdapter(list, getContext());
        recycler.setAdapter(adapter);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        recycler.setLayoutManager(manager);
        smart.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                ++page;
                initData();
                smart.finishLoadMore();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                list.clear();
                initData();
                smart.finishRefresh();
            }
        });
    }

    @Override
    protected TravelPresenter initPresenter() {
        return new TravelPresenter();
    }

    @Override
    protected void initListener() {
        adapter.setFellowListener(new RecBanMiAdapter.FellowListener() {
            @Override
            public void onFellow(int id, int position) {
                mPresenter.addLike((String) SpUtil.getParam(Constants.TOKEN, ""), id);
            }

            @Override
            public void cancelFellow(int id, int position) {
                mPresenter.disLike((String) SpUtil.getParam(Constants.TOKEN, ""), id);
            }
        });
        adapter.setOnItemClickListener(new RecBanMiAdapter.OnItemClickListener() {
            @Override
            public void onClick(TravelBean.ResultBean.BanmiBean bean) {
                Intent intent=new Intent(getActivity(), BanMiDetailsActivity.class);
                intent.putExtra(Constants.DATA,bean.getId());
                getActivity().startActivity(intent);
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_travel;
    }

    @Override
    public void onSuccess(TravelBean.ResultBean result) {
        hideLoading();
        if (page == 1) {
            list.clear();
        }
        list.addAll(result.getBanmi());
        adapter.setBanmiBeans(list);
        smart.finishLoadMore();
        smart.finishRefresh();
    }

    @Override
    protected void initData() {
        mPresenter.getData((String) SpUtil.getParam(Constants.TOKEN, ""), page);
    }

    @Override
    public void onFile(String msg) {
        ToastUtil.showShort(msg);
    }
}
