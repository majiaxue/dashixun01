package com.example.administrator.travel02.ui.main.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.administrator.travel02.R;
import com.example.administrator.travel02.base.BaseActivity;
import com.example.administrator.travel02.base.Constants;
import com.example.administrator.travel02.bean.LikeBean;
import com.example.administrator.travel02.model.UpdateInfoModel;
import com.example.administrator.travel02.net.ResultCallBack;
import com.example.administrator.travel02.presenter.EmptyPresenter;
import com.example.administrator.travel02.utils.GlideUtil;
import com.example.administrator.travel02.utils.SpUtil;
import com.example.administrator.travel02.view.main.EmptyView;
import com.jaeger.library.StatusBarUtil;

import butterknife.BindView;

public class InformationActivity extends BaseActivity<EmptyView, EmptyPresenter> implements EmptyView, View.OnClickListener {

    @BindView(R.id.toolBar)
    Toolbar toolBar;
    @BindView(R.id.rl_header)
    RelativeLayout rlHeader;
    @BindView(R.id.rl_nick)
    RelativeLayout rlNick;
    @BindView(R.id.rl_gender)
    RelativeLayout rlGender;
    @BindView(R.id.rl_signature)
    RelativeLayout rlSignature;
    @BindView(R.id.rl_updatePsw)
    RelativeLayout rlUpdatePsw;
    @BindView(R.id.rl_bindPhone)
    RelativeLayout rlBindPhone;
    @BindView(R.id.iv_header)
    ImageView ivHeader;
    @BindView(R.id.tv_nick)
    TextView tvNick;
    @BindView(R.id.tv_gender)
    TextView tvGender;
    @BindView(R.id.tv_signature)
    TextView tvSignature;
    @BindView(R.id.ll_parent)
    LinearLayout mLl;
    private int type;
    /**
     * 男
     */
    private Button mBtM;
    /**
     * 女
     */
    private Button mBtF;
    /**
     * 保密
     */
    private Button mBtU;
    /**
     * 取消
     */
    private Button mBtCancel;
    private PopupWindow popupWindow;
    private Button mBtXiangji;
    private Button mBtXiangce;
    private Button mBtCancel_header;
    private PopupWindow window;

    @Override
    protected EmptyPresenter initPresenter() {
        return new EmptyPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_information;
    }

    @Override
    protected void initView() {
        StatusBarUtil.setLightMode(this);
        toolBar.setNavigationIcon(R.drawable.back_white);
        toolBar.setTitle("");
        setSupportActionBar(toolBar);
        GlideUtil.loadUrlCircleImage(R.mipmap.mm, R.mipmap.mm, (String) SpUtil.getParam(Constants.PHOTO, ""), ivHeader, this);
        tvNick.setText((String) SpUtil.getParam(Constants.USERNAME, "user"));
        setGender();
        tvSignature.setText((String) SpUtil.getParam(Constants.DESC, ""));

        rlHeader.setOnClickListener(this);
        rlNick.setOnClickListener(this);
        rlGender.setOnClickListener(this);
        rlSignature.setOnClickListener(this);

    }

    private void setGender() {
        String gender = (String) SpUtil.getParam(Constants.GENDER, "保密");
        if (gender.equals("M")) {
            tvGender.setText("男");
        } else if (gender.equals("F")) {
            tvGender.setText("女");
        } else {
            tvGender.setText("保密");
        }
    }

    @Override
    protected void initListener() {
        toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_header:
                Intent intent=new Intent(InformationActivity.this,TakePhoteActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_nick:
                type = UpdateInfoActivity.NICK_TYPE;
                UpdateInfoActivity.startAct(this, type, "");
                break;
            case R.id.rl_gender:
                popup();
                break;
            case R.id.rl_signature:
                type = UpdateInfoActivity.SIGNATURE_TYPE;
                UpdateInfoActivity.startAct(this, type, "");
                break;
        }
    }

    private void popup() {
        View inflate = LayoutInflater.from(this).inflate(R.layout.popup_sex, null, false);
        TextView take = inflate.findViewById(R.id.bt_takePhoto);
        TextView photo = inflate.findViewById(R.id.bt_photo);
        TextView bt_cancel1 = inflate.findViewById(R.id.bt_cancel1);
        TextView cancel = inflate.findViewById(R.id.bt_cancel);

        take.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new UpdateInfoModel().updateInfo((String) SpUtil.getParam(Constants.TOKEN, ""), "M", UpdateInfoActivity.GENDER_TYPE, new ResultCallBack<String>() {
                    @Override
                    public void onSuccess(String bean) {
                        SpUtil.setParam(Constants.GENDER, "M");
                        tvGender.setText("男");
                    }
                    @Override
                    public void onFail(String msg) {
                        Log.e(TAG, "onFail: e=" + msg);
                    }
                });
                window.dismiss();
            }
        });
        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new UpdateInfoModel().updateInfo((String) SpUtil.getParam(Constants.TOKEN, ""), "F", UpdateInfoActivity.GENDER_TYPE, new ResultCallBack<String>() {
                    @Override
                    public void onSuccess(String bean) {
                        SpUtil.setParam(Constants.GENDER, "F");
                        tvGender.setText("女");
                    }

                    @Override
                    public void onFail(String msg) {
                        Log.e(TAG, "onFail: e=" + msg);
                    }
                });
                window.dismiss();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new UpdateInfoModel().updateInfo((String) SpUtil.getParam(Constants.TOKEN, ""), "U", UpdateInfoActivity.GENDER_TYPE, new ResultCallBack<String>() {
                    @Override
                    public void onSuccess(String bean) {
                        SpUtil.setParam(Constants.GENDER, "U");
                        tvGender.setText("保密");
                    }

                    @Override
                    public void onFail(String msg) {
                        Log.e(TAG, "onFail: e=" + msg);
                    }
                });
                window.dismiss();
            }
        });
        bt_cancel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                window.dismiss();
            }
        });
        window = new PopupWindow(inflate, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        window.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                // popupWindow隐藏时恢复屏幕正常透明度
                setBackgroundAlpha(1.0f);
            }
        });

        setBackgroundAlpha(0.5f);//设置屏幕透明度

        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        setBackgroundAlpha(0.5f);//设置屏幕透明度

        window.setOutsideTouchable(true);
        window.setTouchable(true);
        window.setAnimationStyle(R.style.pwanim);
        window.showAtLocation(inflate, Gravity.BOTTOM, 0, 0);
        }
    private void setBackgroundAlpha(float bg) {
        WindowManager.LayoutParams lp = this.getWindow()
                .getAttributes();
        lp.alpha = bg;
        this.getWindow().setAttributes(lp);

    }


    private static final String TAG = "InformationActivity";

    private void clickListener() {
        mBtM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new UpdateInfoModel().updateInfo((String) SpUtil.getParam(Constants.TOKEN, ""), "M", UpdateInfoActivity.GENDER_TYPE, new ResultCallBack<String>() {
                    @Override
                    public void onSuccess(String bean) {
                        SpUtil.setParam(Constants.GENDER, "M");
                        tvGender.setText("男");
                    }
                    @Override
                    public void onFail(String msg) {
                        Log.e(TAG, "onFail: e=" + msg);
                    }
                });
                popupWindow.dismiss();
            }
        });
        mBtF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new UpdateInfoModel().updateInfo((String) SpUtil.getParam(Constants.TOKEN, ""), "F", UpdateInfoActivity.GENDER_TYPE, new ResultCallBack<String>() {
                    @Override
                    public void onSuccess(String bean) {
                        SpUtil.setParam(Constants.GENDER, "F");
                        tvGender.setText("女");
                    }

                    @Override
                    public void onFail(String msg) {
                        Log.e(TAG, "onFail: e=" + msg);
                    }
                });
                popupWindow.dismiss();
            }
        });
        mBtU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new UpdateInfoModel().updateInfo((String) SpUtil.getParam(Constants.TOKEN, ""), "U", UpdateInfoActivity.GENDER_TYPE, new ResultCallBack<String>() {
                    @Override
                    public void onSuccess(String bean) {
                        SpUtil.setParam(Constants.GENDER, "U");
                        tvGender.setText("保密");
                    }

                    @Override
                    public void onFail(String msg) {
                        Log.e(TAG, "onFail: e=" + msg);
                    }
                });
                popupWindow.dismiss();
            }
        });
        mBtCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

        mBtXiangji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (type == UpdateInfoActivity.NICK_TYPE) {
            String name = (String) SpUtil.getParam(Constants.USERNAME, "no");
            tvNick.setText(name);
        } else if (type == UpdateInfoActivity.SIGNATURE_TYPE) {
            String signature = (String) SpUtil.getParam(Constants.DESC, "未设置");
            tvSignature.setText(signature);
        }
        String gender = (String) SpUtil.getParam(Constants.GENDER, "保密");
        if (gender.equals("F")) {
            tvGender.setText("女");
        } else if (gender.equals("M")) {
            tvGender.setText("男");
        } else {
            tvGender.setText("保密");
        }
    }


    @Override
    public void toastShort(String msg) {

    }
}
