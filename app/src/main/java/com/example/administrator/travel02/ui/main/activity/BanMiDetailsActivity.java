package com.example.administrator.travel02.ui.main.activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bm.library.PhotoView;
import com.example.administrator.travel02.MainSpresdActivity;
import com.example.administrator.travel02.R;
import com.example.administrator.travel02.base.BaseActivity;
import com.example.administrator.travel02.base.Constants;
import com.example.administrator.travel02.bean.BanmiInfo;
import com.example.administrator.travel02.model.BanMiDetailsModel;
import com.example.administrator.travel02.model.TravelModel;
import com.example.administrator.travel02.net.ResultCallBack;
import com.example.administrator.travel02.presenter.BanmiDetailsPresenter;
import com.example.administrator.travel02.ui.main.adapter.RecDyncAdapter;
import com.example.administrator.travel02.ui.main.adapter.RecMainSpreadAdapter;
import com.example.administrator.travel02.ui.main.fragment.DynamicFragment;
import com.example.administrator.travel02.ui.main.fragment.LineFragment;
import com.example.administrator.travel02.utils.GlideUtil;
import com.example.administrator.travel02.utils.SpUtil;
import com.example.administrator.travel02.view.main.BanMiDetailsView;
import com.jaeger.library.StatusBarUtil;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.bumptech.glide.Glide.with;

public class BanMiDetailsActivity extends BaseActivity<BanMiDetailsView, BanmiDetailsPresenter> implements BanMiDetailsView {
    @BindView(R.id.tv_job)
    TextView job;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.iv_share)
    ImageView ivShare;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.ll)
    LinearLayout ll;
    @BindView(R.id.view1)
    View view1;
    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_notice)
    TextView tvNotice;
    @BindView(R.id.img_notice)
    ImageView imgNotice;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.iv_location)
    ImageView ivLocation;
    @BindView(R.id.tv_location)
    TextView tvLocation;
    @BindView(R.id.iv_induct)
    ImageView ivInduct;
    @BindView(R.id.cardview)
    CardView cardview;
    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.fragment_container)
    FrameLayout fragmentContainer;
    @BindView(R.id.rl_parent)
    FrameLayout rlparent;
    private BanmiInfo.ResultEntity.BanmiEntity banmi;
    private RecDyncAdapter adapter;
    private BanmiInfo.ResultEntity.BanmiEntity banmiEntity;
    private BanmiInfo.ResultEntity.ShareEntity resultEntity;
    private ArrayList<Fragment> fragments;
    private FragmentManager manager;
    private PopupWindow popupWindow;

    @Override
    protected void initView() {
        StatusBarUtil.setLightMode(this);
        fragments = new ArrayList<>();
        toolbar.setTitle("");
        tab.addTab(tab.newTab().setText("动态"));
        tab.addTab(tab.newTab().setText("线路"));
        manager = getSupportFragmentManager();
    }

    @Override
    protected void initListener() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void popop(String imgUrl) {
        View view = View.inflate(this, R.layout.layout_zoomimg, null);
        PhotoView photo = view.findViewById(R.id.photo);
        with(this).load(imgUrl).into(photo);
        photo.enable();
        popupWindow = new PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        popupWindow.setOutsideTouchable(true);
        popupWindow.showAtLocation(rlparent, Gravity.CENTER,0,0);
    }

    @Override
    protected void initData() {
        mPresenter.getBanmiInfo((String) SpUtil.getParam(Constants.TOKEN,""),
                getIntent().getIntExtra(Constants.DATA,0),1);
    }

    @Override
    protected BanmiDetailsPresenter initPresenter() {
        return new BanmiDetailsPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_banmi_details;
    }

    @Override
    public void toastShort(String msg) {

    }

    @Override
    public void onSuccess(BanmiInfo bean) {
        banmiEntity=bean.getResult().getBanmi();
        GlideUtil.loadUrlRoundImg(10,R.drawable.zhanweitu_touxiang,R.drawable.zhanweitu_touxiang, banmiEntity.getPhoto(),img,this);
        if (banmiEntity.isIsFollowed()){
            GlideUtil.loadResImage(R.drawable.zhanweitu_touxiang,R.drawable.zhanweitu_touxiang,R.drawable.follow,imgNotice,this);
        }
        tvContent.setText("简介："+banmiEntity.getIntroduction());
        tvLocation.setText(banmiEntity.getLocation());
        tvName.setText(banmiEntity.getName());
        tvNotice.setText(banmiEntity.getFollowing()+"人关注");
        job.setText(banmiEntity.getOccupation());
        initFragment(bean);
        manager.beginTransaction().add(R.id.fragment_container,fragments.get(0)).commit();
        setClick();
    }

    private void setClick() {
        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case  0:
                        switchFragment(0);
                        break;
                    case  1:
                        switchFragment(1);
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        imgNotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (banmi != null) {
                    TravelModel model = new TravelModel();
                    if (banmi.isIsFollowed()) {
                        model.disLike((String) SpUtil.getParam(Constants.TOKEN, ""), banmi.getId(), new ResultCallBack<String>() {
                            @Override
                            public void onSuccess(String bean) {
                            }

                            @Override
                            public void onFail(String msg) {
                            }
                        });
                        Toast.makeText(BanMiDetailsActivity.this, "已取消关注", Toast.LENGTH_SHORT).show();
                        imgNotice.setImageResource(R.drawable.follow_unselected);
                    } else {
                        model.addLike((String) SpUtil.getParam(Constants.TOKEN, ""), banmi.getId(), new ResultCallBack<String>() {
                            @Override
                            public void onSuccess(String bean) {
                            }

                            @Override
                            public void onFail(String msg) {
                            }
                        });
                        Toast.makeText(BanMiDetailsActivity.this, "已关注", Toast.LENGTH_SHORT).show();
                        imgNotice.setImageResource(R.drawable.follow);
                    }
                }
            }
        });


    }
    private int lastPosition = 0;
    private void switchFragment(int type) {
        Fragment fragment = fragments.get(type);
        FragmentTransaction tran = manager.beginTransaction();
        if (!fragment.isAdded()){
            tran.add(R.id.fragment_container,fragment);
        }
        tran.hide(fragments.get(lastPosition));
        tran.show(fragment);
        tran.commit();
        lastPosition = type;
    }

    private void initFragment(BanmiInfo bean) {
        DynamicFragment fragment = new DynamicFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.DATA,bean.getResult());
        fragment.setArguments(bundle);
        fragments.add(fragment);

        LineFragment lineFragment = new LineFragment();
        Bundle bundle1 = new Bundle();
        bundle1.putInt("id",bean.getResult().getBanmi().getId());
        lineFragment.setArguments(bundle1);
        fragments.add(lineFragment);
    }

    @Override
    public void onFail(String msg) {

    }
    @OnClick({R.id.iv_share, R.id.img_notice})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_share:
                break;
            case R.id.img_notice:
                break;
        }
    }

}
