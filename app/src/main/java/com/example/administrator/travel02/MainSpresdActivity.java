package com.example.administrator.travel02;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.bumptech.glide.Glide;

import com.bm.library.PhotoView;
import com.example.administrator.travel02.base.BaseActivity;
import com.example.administrator.travel02.base.Constants;
import com.example.administrator.travel02.bean.LikeBean;
import com.example.administrator.travel02.bean.MainSpreadBean;
import com.example.administrator.travel02.presenter.MainSpreadPresenter;
import com.example.administrator.travel02.ui.main.activity.CommentActivity;
import com.example.administrator.travel02.ui.main.adapter.RecMainSpreadAdapter;
import com.example.administrator.travel02.utils.SpUtil;
import com.example.administrator.travel02.view.main.MainSpresdView;
import com.jaeger.library.StatusBarUtil;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.bumptech.glide.Glide.with;

//appKey:5cda26a04ca3577afd000bec
public class MainSpresdActivity extends BaseActivity<MainSpresdView, MainSpreadPresenter> implements MainSpresdView {


    @BindView(R.id.recView)
    RecyclerView recView;
    @BindView(R.id.bt_startLine)
    Button btStartLine;
    @BindView(R.id.btn_show_line)
    Button btnShowLine;
    @BindView(R.id.btn_price)
    Button btnPrice;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.iv_share)
    ImageView ivShare;
    @BindView(R.id.rl_parent)
    RelativeLayout rlParent;
    private RecMainSpreadAdapter adapter;
    private PopupWindow popupWindow;
    private MainSpreadBean.ResultEntity.RouteEntity entity;
    @Override
    protected void initView() {
        recView.setLayoutManager(new LinearLayoutManager(this));
        StatusBarUtil.setLightMode(this);
    }

    @Override
    @OnClick({R.id.iv_back,R.id.iv_share})
    protected void initListener() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        rlParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (popupWindow != null){
                    popupWindow.dismiss();
                }
            }
        });
    }

    @Override
    protected void initData() {
        mPresenter.getSpreadData((String) SpUtil.getParam( Constants.TOKEN,""),getIntent().getIntExtra(Constants.DATA, 0));
    }

    @Override
    protected MainSpreadPresenter initPresenter() {
        return new MainSpreadPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main_spresd;
    }

    @Override
    public void toastShort(String msg) {


    }

    private void setClick(final MainSpreadBean.ResultEntity.RouteEntity route) {
      adapter.setOnItemLikeListener(new RecMainSpreadAdapter.OnItemLikeListener() {
          @Override
          public void addLike(int id) {
              mPresenter.addLike((String) SpUtil.getParam(Constants.TOKEN,""),id);
          }

          @Override
          public void disLike(int id) {
              mPresenter.disLike((String) SpUtil.getParam(Constants.TOKEN,""),id);
          }
      });
      adapter.setOnImageClickListener(new RecMainSpreadAdapter.OnImageClickListener() {
          @Override
          public void onClick(String imgUrl) {
              popop(imgUrl);
          }
      });
      ivShare.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {

          }
      });
      adapter.setOnCommentListener(new RecMainSpreadAdapter.onCommentListener() {
          @Override
          public void onCommentClick(int id) {
              Intent intent=new Intent(MainSpresdActivity.this, CommentActivity.class);
              intent.putExtra(Constants.DATA,id);
              startActivity(intent);
          }
      });
        ivShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UMImage image = new UMImage(MainSpresdActivity.this, route.getCardURL());
                UMWeb web = new UMWeb(route.getShareURL());
                web.setTitle(route.getTitle());//标题
                web.setThumb(image);  //缩略图
                web.setDescription(route.getIntro());//描述
                new ShareAction(MainSpresdActivity.this).withText(route.getIntro()).withMedia(web).setDisplayList(SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.SINA)
                        .setCallback(umShareListener).open();
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
        popupWindow.showAtLocation(rlParent, Gravity.CENTER,0,0);
    }

    @Override
    public void onUiFile(String msg) {

    }

    @Override
    public void upDataUi(MainSpreadBean bean) {
        if (bean.getResult().getRoute().isIsPurchased()){
            btnShowLine.setVisibility(View.GONE);
            btnPrice.setVisibility(View.GONE);
            btStartLine.setVisibility(View.VISIBLE);
        }else {
            btnShowLine.setVisibility(View.VISIBLE);
            btnPrice.setVisibility(View.VISIBLE);
            btStartLine.setVisibility(View.GONE);
        }
        adapter=new RecMainSpreadAdapter(this,bean.getResult());
        recView.setAdapter(adapter);
        setClick(bean.getResult().getRoute());
    }

    @Override
    public void onSuccess(String bean) {

    }

    @Override
    public void onFail(String msg) {

    }
    private UMShareListener umShareListener = new UMShareListener() {
        /**
         * @descrption 分享开始的回调
         * @param platform 平台类型
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {
            showLoading();
        }

        /**
         * @descrption 分享成功的回调
         * @param platform 平台类型
         */
        @Override
        public void onResult(SHARE_MEDIA platform) {
            Toast.makeText(MainSpresdActivity.this, "成功了", Toast.LENGTH_LONG).show();
            hideLoading();
        }

        /**
         * @descrption 分享失败的回调
         * @param platform 平台类型
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(MainSpresdActivity.this, "失败" + t.getMessage(), Toast.LENGTH_LONG).show();
            hideLoading();
        }

        /**
         * @descrption 分享取消的回调
         * @param platform 平台类型
         */
        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(MainSpresdActivity.this, "取消了", Toast.LENGTH_LONG).show();
            hideLoading();
        }
    };

}
