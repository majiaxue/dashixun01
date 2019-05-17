package com.example.administrator.travel02.ui.main.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.example.administrator.travel02.R;
import com.example.administrator.travel02.base.BaseActivity;
import com.example.administrator.travel02.base.Constants;
import com.example.administrator.travel02.bean.CommentBean;
import com.example.administrator.travel02.bean.MainSpreadBean;
import com.example.administrator.travel02.presenter.CommentPresenter;
import com.example.administrator.travel02.ui.main.adapter.RecCommentAdapter;
import com.example.administrator.travel02.ui.main.adapter.RecSubjectAdapter;
import com.example.administrator.travel02.utils.SpUtil;
import com.example.administrator.travel02.view.CommentView;
import com.jaeger.library.StatusBarUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CommentActivity extends BaseActivity<CommentView, CommentPresenter> implements CommentView {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.view1)
    View view1;
    @BindView(R.id.recView)
    RecyclerView recView;
    @BindView(R.id.smart)
    SmartRefreshLayout smart;
    private RecCommentAdapter adapter;
    private ArrayList<MainSpreadBean.ResultEntity.ReviewsEntity> list;
    private int page = 1;

    @Override
    protected void initView() {
        StatusBarUtil.setLightMode(this);
        toolbar.setTitle("");
        list = new ArrayList<>();
        toolbar.setNavigationIcon(R.drawable.back_white);
        setSupportActionBar(toolbar);
        adapter = new RecCommentAdapter(this);
        recView.setLayoutManager(new LinearLayoutManager(this));
        recView.setAdapter(adapter);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void initListener() {
        smart.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                ++page;
                initData();
               // smart.finishLoadMoreWithNoMoreData();//完成加载并标记没有更多数据 1.0.4
                // 这个方法最重要，当在最后一页调用完上一个完成加载并标记没有更多数据的方法时，需要将refreshLayout的状态更改为还有更多数据的状态，此时就需要调用此方法，参数为false代表还有更多数据，true代表没有更多数据
               // smart.setNoMoreData(false);//恢复没有更多数据的原始状态 1.0.5
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                if (page == 1) {
                    list.clear();
                }
                initData();

            }
        });
    }

    @Override
    protected void initData() {
        mPresenter.getCommentData((String) SpUtil.getParam(Constants.TOKEN, ""), getIntent().getIntExtra(Constants.DATA, 0), page);

    }

    @Override
    protected CommentPresenter initPresenter() {
        return new CommentPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activty_comment;
    }

    @Override
    public void toastShort(String msg) {

    }

    @Override
    public void onSuccess(MainSpreadBean.ResultEntity result) {
        list.addAll(result.getReviews());
        adapter.setList(list);
        smart.finishLoadMore();
        smart.finishRefresh();

    }

    @Override
    public void onFail(String msg) {

    }
}
