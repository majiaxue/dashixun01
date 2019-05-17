package com.example.administrator.travel02.ui.main.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.travel02.R;

import org.greenrobot.eventbus.EventBus;

public class PersonalityActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView img;
    private TextView tv_title;
    private TextView tv_down;
    private Toolbar toolbar;
    private EditText ed_write_personality;
    private int type=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personality);
        initView();
    }

    private void initView() {
        img = (ImageView) findViewById(R.id.img);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_down = (TextView) findViewById(R.id.tv_down);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        ed_write_personality = (EditText) findViewById(R.id.ed_write_personality);
        toolbar.setTitle("");
        tv_title.setText(getResources().getString(R.string.person_nmae));
        setSupportActionBar(toolbar);
        tv_down.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        EventBus.getDefault().postSticky(ed_write_personality.getText().toString());
        Intent intent=new Intent(this,MeActivity.class);
        startActivity(intent);
    }
}
