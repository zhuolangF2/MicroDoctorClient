package com.zhuolang.main.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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
 * Created by wnf on 2016/11/1.
 */

public class SettingActivity extends Activity implements View.OnClickListener{

    private String userDataStr;
    private DoctorDto userInfo;
    private ImageView imageViewBack;
    private LinearLayout llUpdatePsw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //获取当前用户信息

        setContentView(R.layout.activity_setting);
        initView();
        initDatas();

    }
    private void initView() {
        userDataStr=SharedPrefsUtil.getValue(this, APPConfig.USERDATA, "");
        Gson gson=new Gson();
        userInfo=gson.fromJson(userDataStr,DoctorDto.class);
        llUpdatePsw=(LinearLayout)findViewById(R.id.ll_setting_updatepsw);
        imageViewBack=(ImageView)findViewById(R.id.setting_iv_back);
        llUpdatePsw.setOnClickListener(this);
        imageViewBack.setOnClickListener(this);
    }

    private void initDatas() {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.setting_iv_back:
                finish();
                break;
            case R.id.ll_setting_updatepsw:
                Intent intent=new Intent();
                intent.setClass(SettingActivity.this, UpdatePswActivity.class);
                startActivity(intent);
            default:
                break;
        }
    }
}
