package com.zhuolang.main.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

public class ShowDotInfoActivity extends Activity implements View.OnClickListener{

    private String userDataStr;
    private DoctorDto userInfo;
    private ImageView imageViewBack;
    private Button bt_updateInfo;

    private TextView tv_acount;
    private TextView tv_nickname;
    private TextView tv_name;
    private TextView tv_phone;
    private TextView tv_age;
    private TextView tv_gender;
    private TextView tv_address;
    private TextView tv_signature;
    private TextView tv_introduction;
    private TextView tv_hospital;
    private TextView tv_amount;
    private TextView tv_office;
    private LinearLayout ll_hospital;
    private LinearLayout ll_office;
    private LinearLayout ll_amount;
    Gson gson=new Gson();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //获取当前用户信息
        setContentView(R.layout.activity_sh_me_dot_info);
        initDoctorView();
        initDoctorDatas();
    }


    private void initDoctorView() {
        userDataStr=SharedPrefsUtil.getValue(this, APPConfig.USERDATA, "");
        userInfo=gson.fromJson(userDataStr, DoctorDto.class);
        tv_acount=(TextView)findViewById(R.id.tv_shdot_acount);
        tv_nickname=(TextView)findViewById(R.id.tv_shdot_nickname);
        tv_name=(TextView)findViewById(R.id.tv_shdot_name);
        tv_phone=(TextView)findViewById(R.id.tv_shdot_phone);
        tv_age=(TextView)findViewById(R.id.tv_shdot_age);
        tv_gender=(TextView)findViewById(R.id.tv_shdot_gender);
        tv_address=(TextView)findViewById(R.id.tv_shdot_address);
        tv_signature=(TextView)findViewById(R.id.tv_shdot_signature);
        tv_introduction=(TextView)findViewById(R.id.tv_shdot_introduction);
        tv_hospital=(TextView)findViewById(R.id.tv_shdot_hospital);
        tv_office=(TextView)findViewById(R.id.tv_shdot_office);
        tv_amount=(TextView)findViewById(R.id.tv_shdot_amount);
        ll_amount= (LinearLayout) findViewById(R.id.ll_showinfo_amount);
        ll_hospital= (LinearLayout) findViewById(R.id.ll_showinfo_hospt);
        ll_office= (LinearLayout) findViewById(R.id.ll_showinfo_office);
        bt_updateInfo= (Button) findViewById(R.id.bt_show_doctorinfo);
        imageViewBack=(ImageView)findViewById(R.id.image_shdotinfo_back);
        bt_updateInfo.setOnClickListener(this);
        imageViewBack.setOnClickListener(this);
    }

    private void initDoctorDatas() {
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
            tv_gender.setText("未填写");
        }
        tv_address.setText(userInfo.getAddress());
        tv_signature.setText(userInfo.getSignature());
        tv_introduction.setText(userInfo.getIntroduction());
        if (userInfo.getType()==1){
            tv_hospital.setText(userInfo.getHospital());
            tv_office.setText(userInfo.getOffice());
            tv_amount.setText(""+userInfo.getAmount());
        }else {
            ll_hospital.setVisibility(View.GONE);
            ll_office.setVisibility(View.GONE);
            ll_amount.setVisibility(View.GONE);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.image_shdotinfo_back:
                finish();
                break;
            case R.id.bt_show_doctorinfo:
                Intent intent = new Intent();
                intent.setClass(ShowDotInfoActivity.this, UpdateDotInfoActivity.class);
                startActivity(intent);
                finish();
                break;
            default:
                break;
        }
    }
}
