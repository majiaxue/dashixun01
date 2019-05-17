package com.example.administrator.travel02.ui.main.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.travel02.FellowActivity;
import com.example.administrator.travel02.R;
import com.example.administrator.travel02.base.BaseFragment;
import com.example.administrator.travel02.presenter.EmptyPresenter;
import com.example.administrator.travel02.ui.main.activity.CollectActivity;
import com.example.administrator.travel02.ui.main.activity.InformationActivity;
import com.example.administrator.travel02.view.main.EmptyView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class CenterPragment extends BaseFragment<EmptyView, EmptyPresenter> implements EmptyView {
    @BindView(R.id.head_img)
    ImageView headImg;
    @BindView(R.id.head_name)
    TextView headName;
    @BindView(R.id.head_title)
    TextView headTitle;
    @BindView(R.id.head_show)
    TextView headShow;
    @BindView(R.id.rl_ll)
    RelativeLayout rlLl;
    @BindView(R.id.head_price)
    ImageView headPrice;
    @BindView(R.id.head_price_name)
    TextView headPriceName;
    @BindView(R.id.head_price_show)
    TextView headPriceShow;
    @BindView(R.id.price_rl)
    RelativeLayout priceRl;
    @BindView(R.id.id_name)
    ImageView idName;
    @BindView(R.id.line_name)
    View lineName;
    @BindView(R.id.id_title)
    ImageView idTitle;
    @BindView(R.id.line_title)
    View lineTitle;
    @BindView(R.id.id_ti)
    ImageView idTi;
    @BindView(R.id.header_collect)
    TextView headerCollect;
    @BindView(R.id.line_ti)
    View lineTi;
    @BindView(R.id.id_show)
    ImageView idShow;
    @BindView(R.id.head_about)
    TextView headAbout;
    @BindView(R.id.line_show)
    View lineShow;
    @BindView(R.id.px)
    RelativeLayout px;
    @BindView(R.id.card)
    CardView card;
    @BindView(R.id.id_img)
    ImageView idImg;
    @BindView(R.id.id_img1)
    ImageView idImg1;
    @BindView(R.id.id_img2)
    ImageView idImg2;
    Unbinder unbinder;

    @Override
    protected EmptyPresenter initPresenter() {
        return new EmptyPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_center;
    }
    @OnClick({R.id.header_collect,R.id.head_about,R.id.rl_ll})
    @Override
    protected void initView() {
        headerCollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), CollectActivity.class));
            }
        });

        headAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), FellowActivity.class));
            }
        });
        rlLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), InformationActivity.class));
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
