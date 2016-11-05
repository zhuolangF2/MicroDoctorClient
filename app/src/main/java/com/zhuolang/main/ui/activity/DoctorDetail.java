package com.zhuolang.main.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.gson.Gson;
import com.zhuolang.main.R;
import com.zhuolang.main.model.DoctorDto;

/**
 * Created by hzg on 2016/11/4.
 */
public class DoctorDetail extends Activity {

    private TextView tv_DoctorName;
    private TextView tv_DoctorNickname;
    private TextView tv_DoctorAge;
    private TextView tv_DoctorGender;
    private TextView tv_DoctorPhone;
    private TextView tv_DoctorAddress;
    private TextView tv_DoctorSignature;
    private TextView tv_DoctorIntroduction;
    private TextView tv_DoctorHospital;
    private TextView tv_DoctorOffice;
    private TextView tv_DoctorAmount;

    private Button bt_doctor_back;
    private ImageView img_doctorList_back;

    private DoctorDto doctorDto;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_detail);
        init();
        initMotion();
    }

    //初始化控件
    private void init() {
        tv_DoctorName = (TextView) findViewById(R.id.tv_Dname);
        tv_DoctorNickname = (TextView) findViewById(R.id.tv_Dnickname);
        tv_DoctorAge = (TextView) findViewById(R.id.tv_Dage);
        tv_DoctorGender = (TextView) findViewById(R.id.tv_Dgender);
        tv_DoctorPhone = (TextView) findViewById(R.id.tv_Dphone);
        tv_DoctorAddress = (TextView) findViewById(R.id.tv_Daddress);
        tv_DoctorSignature = (TextView) findViewById(R.id.tv_Dsignature);
        tv_DoctorIntroduction = (TextView) findViewById(R.id.tv_Dintroduction);
        tv_DoctorHospital = (TextView) findViewById(R.id.tv_Dhospital);
        tv_DoctorOffice = (TextView) findViewById(R.id.tv_Doffice);
        tv_DoctorAmount = (TextView) findViewById(R.id.tv_Damount);

        bt_doctor_back = (Button) findViewById(R.id.bt_doctor_back);
        img_doctorList_back = (ImageView) findViewById(R.id.img_doctorList_back);
    }

    //监听
    private void initMotion() {
        bt_doctor_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        img_doctorList_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        String doctorDtoStr = getIntent().getStringExtra("doctorDtoStr");
        Gson gson = new Gson();
        doctorDto = gson.fromJson(doctorDtoStr, DoctorDto.class);
        tv_DoctorName.setText(doctorDto.getName());
        tv_DoctorNickname.setText(doctorDto.getNickname());
        tv_DoctorAge.setText("" + doctorDto.getAge());
        if (doctorDto.getGender() == 0) {
            tv_DoctorGender.setText("男");
        } else if (doctorDto.getGender() == 1) {
            tv_DoctorGender.setText("女");
        } else {
            tv_DoctorGender.setText("未知");
        }
        tv_DoctorPhone.setText(doctorDto.getPhone());
        tv_DoctorAddress.setText(doctorDto.getAddress());
        tv_DoctorSignature.setText(doctorDto.getSignature());
        tv_DoctorIntroduction.setText(doctorDto.getIntroduction());
        tv_DoctorHospital.setText(doctorDto.getHospital());
        tv_DoctorOffice.setText(doctorDto.getOffice());
        tv_DoctorAmount.setText("" + doctorDto.getAmount());
    }
}
