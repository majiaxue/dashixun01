package com.example.administrator.travel02.ui.main.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.administrator.travel02.FellowActivity;
import com.example.administrator.travel02.R;
import com.example.administrator.travel02.base.BaseActivity;
import com.example.administrator.travel02.base.Constants;
import com.example.administrator.travel02.bean.LoadBean;
import com.example.administrator.travel02.presenter.VersionPresenter;
import com.example.administrator.travel02.ui.main.adapter.MyPagerAdapter;
import com.example.administrator.travel02.ui.main.fragment.CenterPragment;
import com.example.administrator.travel02.ui.main.fragment.MainFragment;
import com.example.administrator.travel02.ui.main.fragment.MapFragment;
import com.example.administrator.travel02.ui.main.fragment.TravelFragment;
import com.example.administrator.travel02.utils.GlideUtil;
import com.example.administrator.travel02.utils.InstallUtil;
import com.example.administrator.travel02.utils.Logger;
import com.example.administrator.travel02.utils.SpUtil;
import com.example.administrator.travel02.utils.Tools;
import com.example.administrator.travel02.view.VersionView;
import com.jaeger.library.StatusBarUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends BaseActivity<VersionView, VersionPresenter> implements VersionView {

    private ImageView main_girl;
    private ImageView main_box;
    private Toolbar main_toolbar;
    private TabLayout tab;
    private ViewPager vp;
    private NavigationView nv;
    private ArrayList<Fragment> fragments;
    private File sd;

    public static void startAct(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initView() {
        main_girl = (ImageView) findViewById(R.id.main_girl);
        main_box = (ImageView) findViewById(R.id.main_box);
        main_toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        tab = (TabLayout) findViewById(R.id.tab);
        vp = (ViewPager) findViewById(R.id.vp);
        nv = findViewById(R.id.nv);
        StatusBarUtil.setLightMode(this);
        initToolbar();
        initNav();
        initData();
        initPer();
    }

    private void initPer() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED) {
            createFile();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission
                    .WRITE_EXTERNAL_STORAGE}, 100);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            createFile();
        }
    }

    private void createFile() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            sd = Environment.getExternalStorageDirectory();
        }
    }


    private void initNav() {

    }

    private void initToolbar() {
        setSupportActionBar(main_toolbar);
    }

    @Override
    protected VersionPresenter initPresenter() {
        return new VersionPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initListener() {
        fragments = new ArrayList<>();
        fragments.add(new MainFragment());
        fragments.add(new MapFragment());
        fragments.add(new TravelFragment());
        fragments.add(new CenterPragment());

        tab.addTab(tab.newTab().setText(R.string.home_text).setIcon(R.drawable.selector_center));
        tab.addTab(tab.newTab().setText(R.string.find).setIcon(R.drawable.selector_find));
        tab.addTab(tab.newTab().setText(R.string.banmi_text).setIcon(R.drawable.main));
        tab.addTab(tab.newTab().setText(R.string.center).setIcon(R.drawable.banmi));
        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                vp.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        vp.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tab));
        MyPagerAdapter mainAdapter = new MyPagerAdapter(getSupportFragmentManager(), fragments);
        vp.setAdapter(mainAdapter);
    }

    @Override
    protected void initData() {
        mPresenter.getVersionData("Tt5zZCbOqYHXLjqaOr8sPrE0XCWWT0Kc4H3Vulg5z4KPZVZtmpI7iRpwxDQqYZ2AB9acOH8oxM9yuUYUHcef0Fu2yX88qqKNpgONHy86zPm7iiqbouMvvqeqOxAOKFp5yQ");
    }

    @Override
    public void onSuccess(LoadBean bean) {
        String versionName = Tools.getVersionName();
        if (versionName.equals(bean.getResult().getInfo().getVersion())) {
            Logger.logD("tag", "已是最新版本");
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            //设置标题
            builder.setTitle("新版本:" + bean.getResult().getInfo().getVersion());
            //设置图标
            builder.setIcon(R.mipmap.ic_launcher);
            //设置描述信息
            builder.setMessage("修复bug");
            //设置升级按钮
            builder.setPositiveButton("升级", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    okDownload(sd + "/" + "abc.apk");
                }
            });
            //设置取消按钮
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            //显示对话框
//		builder.create().show();效果一致
            builder.show();
        }
    }

    private void okDownload(final String path) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .build();

        Request request = new Request.Builder()
                .url("http://cdn.banmi.com/banmiapp/apk/banmi_333.apk")
                .get()
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, "下载失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                InputStream inputStream = response.body().byteStream();
                long max = response.body().contentLength();
                savaFile(inputStream, path, max);
            }
        });
    }

    private void savaFile(InputStream inputStream, final String path, long max) {
        try {
            File file = new File(path);
            FileOutputStream outputStream = new FileOutputStream(file);

            int length = -1;
            byte[] bys = new byte[1024 * 10];

            long count = 0;

            while ((length = inputStream.read(bys)) != -1) {
                outputStream.write(bys, 0, length);

                count += length;
            }

            inputStream.close();
            outputStream.close();

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(MainActivity.this, "下载成功", Toast.LENGTH_SHORT).show();
                    InstallUtil.installApk(MainActivity.this, path);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onFail(String msg) {
        Logger.logD("tag", msg);
    }

    @Override
    public void toastShort(String msg) {

    }
}
