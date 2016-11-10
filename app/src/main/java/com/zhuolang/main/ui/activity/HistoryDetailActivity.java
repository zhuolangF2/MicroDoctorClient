package com.zhuolang.main.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.gson.Gson;
import com.zhuolang.main.R;
import com.zhuolang.main.model.AppointmentDto;
import com.zhuolang.main.utils.TimeUtil;

import java.util.Date;

/**
 * Created by hzg on 2016/11/9.
 */
public class HistoryDetailActivity extends Activity {

    private String appointHistoryStr;
    private AppointmentDto appointmentDto;
    private TextView tv_history_doctorName;
    private TextView tv_history_seeTime;
    private TextView tv_history_disease;
    private TextView tv_history_dNumber;
    private TextView tv_history_diagnose;
    private TextView tv_history_discuss;
    private ImageView img_history_back;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appoint_history);
        init();
        img_history_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Gson gson = new Gson();
        appointHistoryStr = getIntent().getStringExtra("appointHistoryStr");
        appointmentDto = gson.fromJson(appointHistoryStr, AppointmentDto.class);

        tv_history_doctorName.setText(appointmentDto.getDoctor_name());
        Date date = TimeUtil.longToDateNoTime(appointmentDto.getSeeTime());
        tv_history_seeTime.setText(TimeUtil.dateToStrNoTime(date));
        tv_history_disease.setText(appointmentDto.getDisease());
        tv_history_dNumber.setText("" + appointmentDto.getdNumber());
        tv_history_diagnose.setText(appointmentDto.getDiagnose());
        tv_history_discuss.setText("" + appointmentDto.getDstar());
    }

    private void init() {
        tv_history_doctorName = (TextView) findViewById(R.id.tv_history_doctorName);
        tv_history_seeTime = (TextView) findViewById(R.id.tv_history_seeTime);
        tv_history_disease = (TextView) findViewById(R.id.tv_history_disease);
        tv_history_dNumber = (TextView) findViewById(R.id.tv_history_dNumber);
        tv_history_diagnose = (TextView) findViewById(R.id.tv_history_diagnose);
        tv_history_discuss = (TextView) findViewById(R.id.tv_history_discuss);
        img_history_back = (ImageView) findViewById(R.id.img_history_back);
    }

}
