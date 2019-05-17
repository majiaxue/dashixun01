package com.example.administrator.travel02.ui.main.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.travel02.MainSpresdActivity;
import com.example.administrator.travel02.R;
import com.example.administrator.travel02.base.BaseFragment;
import com.example.administrator.travel02.base.Constants;
import com.example.administrator.travel02.bean.MainBean;
import com.example.administrator.travel02.presenter.EmptyPresenter;
import com.example.administrator.travel02.presenter.LinePresenter;
import com.example.administrator.travel02.ui.main.adapter.RecMainSpreadAdapter;
import com.example.administrator.travel02.ui.main.adapter.RecShouAdapter;
import com.example.administrator.travel02.utils.SpUtil;
import com.example.administrator.travel02.view.main.EmptyView;
import com.example.administrator.travel02.view.main.MainView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class LineFragment extends BaseFragment<MainView, LinePresenter> implements MainView{
    @BindView(R.id.recView)
    RecyclerView recView;
    private RecShouAdapter adapter;
    @Override
    protected LinePresenter initPresenter() {
        return new LinePresenter();
    }
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_line;
    }

    @Override
    public void success(MainBean bean) {

    }

    @Override
    public void onFail(String string) {

    }

    @Override
    public void onSuccess(final MainBean.ResultBean result) {
        adapter.setRoutes(result.getRoutes());
        adapter.setOnItemClickListener(new RecShouAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent(getContext(), MainSpresdActivity.class);
                intent.putExtra(Constants.DATA,result.getRoutes().get(position).getId());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initView() {
        recView.setLayoutManager(new LinearLayoutManager(getContext()){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        adapter=new RecShouAdapter(getContext());
        recView.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        mPresenter.getLineData(getArguments().getInt("id"),1, (String) SpUtil.getParam(Constants.TOKEN,""));
    }
}
