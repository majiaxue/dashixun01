package com.example.administrator.travel02.ui.main.fragment;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bm.library.PhotoView;
import com.bumptech.glide.Glide;
import com.example.administrator.travel02.R;
import com.example.administrator.travel02.base.BaseFragment;
import com.example.administrator.travel02.base.Constants;
import com.example.administrator.travel02.bean.BanmiInfo;
import com.example.administrator.travel02.presenter.EmptyPresenter;
import com.example.administrator.travel02.ui.main.adapter.RecDyncAdapter;
import com.example.administrator.travel02.view.main.EmptyView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class DynamicFragment extends BaseFragment<EmptyView, EmptyPresenter> implements EmptyView {
    @BindView(R.id.tv_status)
    TextView tvStatus;
    @BindView(R.id.rl_parent)
    RelativeLayout rlparent;
    @BindView(R.id.recView)
    RecyclerView recView;
    private RecDyncAdapter adapter;
    private PopupWindow popupWindow;

    @Override
    protected EmptyPresenter initPresenter() {
        return new EmptyPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_dyna;
    }

    @Override
    protected void initView() {
        recView.setLayoutManager(new LinearLayoutManager(getContext()){
            @Override
            public boolean canScrollVertically() {
              return false;
            }
        });
        BanmiInfo.ResultEntity resultEntity=(BanmiInfo.ResultEntity) getArguments().getSerializable(Constants.DATA);
        adapter=new RecDyncAdapter((resultEntity.getActivities()),getContext());
        recView.setAdapter(adapter);
    }

    @Override
    protected void initListener() {
        adapter.setOnImageClickListener(new RecDyncAdapter.OnImageClickListener() {
            @Override
            public void onClick(String imgUrl) {
                popup(imgUrl);
            }
        });
    }

   private void popup(String imgUrl) {
        View view = View.inflate(getContext(), R.layout.layout_zoomimg, null);
        PhotoView photo = view.findViewById(R.id.photo);
        Glide.with(this).load(imgUrl).into(photo);
        photo.enable();
        popupWindow = new PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        popupWindow.setOutsideTouchable(true);
        popupWindow.showAtLocation(rlparent, Gravity.CENTER,0,0);
        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
    }
}
