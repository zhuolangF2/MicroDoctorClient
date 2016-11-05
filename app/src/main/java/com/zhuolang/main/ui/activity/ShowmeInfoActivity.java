package com.zhuolang.main.ui.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.zhuolang.main.R;
import com.zhuolang.main.common.APPConfig;
import com.zhuolang.main.model.DoctorDto;
import com.zhuolang.main.utils.SharedPrefsUtil;

/**
 * Created by jat on 2016/11/1.
 */

public class ShowmeInfoActivity extends Activity implements View.OnClickListener{

    private String userDataStr;
    private DoctorDto userInfo;
    private ImageView imageViewBack;

    private TextView tv_acount;
    private TextView tv_nickname;
    private TextView tv_name;
    private TextView tv_phone;
    private TextView tv_age;
    private TextView tv_gender;
    private TextView tv_address;
    private TextView tv_signature;
    private TextView tv_introduction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showmeinfo);

        initView();
        initDatas();
    }
    private void initView() {
        userDataStr=SharedPrefsUtil.getValue(this, APPConfig.USERDATA, "");
        Gson gson=new Gson();
        userInfo=gson.fromJson(userDataStr,DoctorDto.class);
        Log.d("testRun","user:"+userInfo.toString());
        Log.d("testRun","user.getAddress:"+userInfo.getAddress());
        Log.d("testRun","user.getNickname:"+userInfo.getNickname());
        tv_acount=(TextView)findViewById(R.id.tv_sacount);
        tv_nickname=(TextView)findViewById(R.id.tv_snickname);
        tv_name=(TextView)findViewById(R.id.tv_sname);
        tv_phone=(TextView)findViewById(R.id.tv_sphone);
        tv_age=(TextView)findViewById(R.id.tv_sage);
        tv_gender=(TextView)findViewById(R.id.tv_sgender);
        tv_address=(TextView)findViewById(R.id.tv_saddress);
        tv_signature=(TextView)findViewById(R.id.tv_ssignature);
        tv_introduction=(TextView)findViewById(R.id.tv_sintroduction);
        imageViewBack=(ImageView)findViewById(R.id.shmeinfo_image_back);
        imageViewBack.setOnClickListener(this);
    }

    private void initDatas() {
        tv_acount.setText(userInfo.getPhone());
        tv_nickname.setText(userInfo.getNickname());
        tv_name.setText(userInfo.getName());
        tv_phone.setText(userInfo.getPhone());
        tv_age.setText(""+userInfo.getAge());
        if (userInfo.getGender() == 0) {
            tv_gender.setText("男");
        } else if (userInfo.getGender() == 1) {
            tv_gender.setText("女");
        } else {
            tv_gender.setText("未知");
        }
        tv_address.setText(userInfo.getAddress());
        tv_signature.setText(userInfo.getSignature());
        tv_introduction.setText(userInfo.getIntroduction());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.shmeinfo_image_back:
                finish();
                break;
            default:
                break;
        }
    }
}
