package com.zhuolang.main.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.google.gson.Gson;
import com.zhuolang.main.R;
import com.zhuolang.main.model.DoctorDto;

/**
 * Created by hzg on 2016/11/4.
 */
public class DoctorDetail extends Activity {

    private TextView tv_Dname;
    private TextView tv_Dnickname;
    private TextView tv_Dage;
    private TextView tv_Dgender;
    private TextView tv_Dphone;
    private TextView tv_Daddress;
    private TextView tv_Dsignature;
    private TextView tv_Dintroduction;
    private TextView tv_Dhospital;
    private TextView tv_Doffice;
    private TextView tv_Damount;

    private Button bt_doctor_back;

    private DoctorDto doctorDto;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_detail);
        init();
        initMotion();
    }

    //初始化控件
    private void init(){
        tv_Dname = (TextView) findViewById(R.id.tv_Dname);
        tv_Dnickname = (TextView) findViewById(R.id.tv_Dnickname);
        tv_Dage = (TextView) findViewById(R.id.tv_Dage);
        tv_Dgender = (TextView) findViewById(R.id.tv_Dgender);
        tv_Dphone = (TextView) findViewById(R.id.tv_Dphone);
        tv_Daddress = (TextView) findViewById(R.id.tv_Daddress);
        tv_Dsignature = (TextView) findViewById(R.id.tv_Dsignature);
        tv_Dintroduction = (TextView) findViewById(R.id.tv_Dintroduction);
        tv_Dhospital = (TextView) findViewById(R.id.tv_Dhospital);
        tv_Doffice = (TextView) findViewById(R.id.tv_Doffice);
        tv_Damount = (TextView) findViewById(R.id.tv_Damount);

        bt_doctor_back = (Button) findViewById(R.id.bt_doctor_back);
    }

    //监听
    private void initMotion(){
        bt_doctor_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        String doctorDtoStr = getIntent().getStringExtra("doctorDtoStr");
        Gson gson = new Gson();
        doctorDto = gson.fromJson(doctorDtoStr, DoctorDto.class);
        tv_Dname.setText(doctorDto.getName());
        tv_Dnickname.setText(doctorDto.getNickname());
        tv_Dage.setText(""+doctorDto.getAge());
        if (doctorDto.getGender() == 0) {
            tv_Dgender.setText("男");
        } else if (doctorDto.getGender() == 1) {
            tv_Dgender.setText("女");
        } else {
            tv_Dgender.setText("未知");
        }
        tv_Dphone.setText(doctorDto.getPhone());
        tv_Daddress.setText(doctorDto.getAddress());
        tv_Dsignature.setText(doctorDto.getSignature());
        tv_Dintroduction.setText(doctorDto.getIntroduction());
        tv_Dhospital.setText(doctorDto.getHospital());
        tv_Doffice.setText(doctorDto.getOffice());
        tv_Damount.setText(""+doctorDto.getAmount());
    }
}
