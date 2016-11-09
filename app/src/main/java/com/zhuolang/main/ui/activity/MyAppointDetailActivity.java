package com.zhuolang.main.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.zhuolang.main.R;
import com.zhuolang.main.model.Appointment;
import com.zhuolang.main.model.AppointmentDto;
import com.zhuolang.main.model.DoctorDto;
import com.zhuolang.main.utils.TimeUtil;

import java.util.Date;

/**
 * Created by hzg on 2016/11/3.
 */
public class MyAppointDetailActivity extends Activity {

    private String doctorDtoStr;
    private TextView tv_appoint_doctorName;
    private TextView tv_appoint_seeTime;
    private TextView tv_appoint_disease;
    private TextView tv_appoint_dNumber;
    private TextView tv_appoint_diagnose;
    private TextView tv_appoint_discuss;
    private ImageView imageViewback;

    private AppointmentDto appointmentDto;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_appointdetails);
        tv_appoint_doctorName = (TextView) findViewById(R.id.tv_appoint_doctorName);
        tv_appoint_seeTime = (TextView) findViewById(R.id.tv_appoint_seeTime);
        tv_appoint_disease = (TextView) findViewById(R.id.tv_appoint_disease);
        tv_appoint_dNumber = (TextView) findViewById(R.id.tv_appoint_dNumber);
        tv_appoint_diagnose = (TextView) findViewById(R.id.tv_appoint_diagnose);
        tv_appoint_discuss = (TextView) findViewById(R.id.tv_appoint_discuss);
        imageViewback = (ImageView) findViewById(R.id.img_doctorList_back);

        Gson gson = new Gson();
        doctorDtoStr = getIntent().getStringExtra("doctorDtoStr");
        appointmentDto = gson.fromJson(doctorDtoStr, AppointmentDto.class);

        tv_appoint_doctorName.setText("" + appointmentDto.getDoctor_name());
        Date date = TimeUtil.longToDateNoTime(appointmentDto.getSeeTime());
        tv_appoint_seeTime.setText(TimeUtil.dateToStrNoTime(date));
        tv_appoint_disease.setText(appointmentDto.getDisease());
        tv_appoint_dNumber.setText("" + appointmentDto.getdNumber());
        tv_appoint_diagnose.setText(appointmentDto.getDiagnose());
        tv_appoint_discuss.setText(appointmentDto.getDstar() + "");

        imageViewback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
