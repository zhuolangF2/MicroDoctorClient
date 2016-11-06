package com.zhuolang.main.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.zhuolang.main.R;
import com.zhuolang.main.common.APPConfig;
import com.zhuolang.main.model.DoctorDto;
import com.zhuolang.main.utils.SharedPrefsUtil;

/**
 * Created by wnf on 2016/11/1.
 */

public class UpdateDotInfoActivity extends Activity{

    private String userDataStr;
    private DoctorDto userInfo;
    private ImageView imageViewBack;

    private EditText et_acount;
    private EditText et_nickname;
    private EditText et_name;
    private EditText et_phone;
    private EditText et_age;
    private EditText et_gender;
    private EditText et_address;
    private EditText et_signature;
    private EditText et_introduction;
    private EditText et_hospital;
    private EditText et_amount;
    private EditText et_office;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //获取当前用户信息
        setContentView(R.layout.activity_upda_dot_info);
        initDoctorView();
        initDoctorDatas();
    }


    private void initDoctorView() {
//        userDataStr=SharedPrefsUtil.getValue(this, APPConfig.USERDATA, "");
//        Gson gson=new Gson();
//        userInfo=gson.fromJson(userDataStr,DoctorDto.class);
//        et_nickname=(EditText)findViewById(R.id.et_update_dotinfo_nickname);
//        et_name=(EditText)findViewById(R.id.et_update_dotinfo_name);
//        et_phone=(EditText)findViewById(R.id.et_update_dotinfo_phone);
//        et_age=(EditText)findViewById(R.id.et_update_dotinfo_age);
//        et_gender=(EditText)findViewById(R.id.et_update_dotinfo_gender);
//        et_address=(EditText)findViewById(R.id.et_update_dotinfo_address);
//        et_signature=(EditText)findViewById(R.id.et_update_dotinfo_signature);
//        et_introduction=(EditText)findViewById(R.id.et_update_dotinfo_introduction);
//        et_hospital=(EditText)findViewById(R.id.et_update_dotinfo_hospital);
//        et_office=(EditText)findViewById(R.id.et_update_dotinfo_office);
//        imageViewBack=(ImageView)findViewById(R.id.image_updadotinfo_back);
//        imageViewBack.setOnClickListener(this);
    }

    private void initDoctorDatas() {
//        et_nickname.setText(userInfo.getNickname());
//        et_name.setText(userInfo.getName());
//        et_phone.setText(userInfo.getPhone());
//        et_age.setText(""+userInfo.getAge());
//        if (userInfo.getGender() == 0) {
//            et_gender.setText("男");
//        } else if (userInfo.getGender() == 1) {
//            et_gender.setText("女");
//        } else {
//            et_gender.setText("未填写");
//        }
//        et_address.setText(userInfo.getAddress());
//        et_signature.setText(userInfo.getSignature());
//        et_introduction.setText(userInfo.getIntroduction());
//        et_hospital.setText(userInfo.getHospital());
//        et_office.setText(userInfo.getOffice());
//        et_amount.setText(""+userInfo.getAmount());
    }

}
