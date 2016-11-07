package com.zhuolang.main.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.*;
import com.google.gson.Gson;
import com.zhuolang.main.R;
import com.zhuolang.main.common.APPConfig;
import com.zhuolang.main.model.Appointment;
import com.zhuolang.main.model.DoctorDto;
import com.zhuolang.main.model.User;
import com.zhuolang.main.utils.OkHttpUtils;
import com.zhuolang.main.utils.SharedPrefsUtil;
import com.zhuolang.main.utils.TimeUtil;
import com.zhuolang.main.view.CustomWaitDialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by hzg on 2016/11/5.
 */
public class AppointInfoActivity extends Activity {
    private ImageView im_back;
    private EditText et_disease;
    private DatePicker datepicker;
    private TextView tv;
    private Button bt_appoint;
    private Button bt_cancel;

    private String disease;
    private String seeTime;
    private Appointment appointment;
    private DoctorDto doctorDto;
    private User user;
    private String patientId;//获取当前用户user的id然后转为String类型
    private String doctorId;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    String result = (String) msg.obj;
                    if (result.equals("addAppointment_success")) {
                        Intent intent = new Intent();
                        intent.setClass(AppointInfoActivity.this, AppointSuccessActivity.class);
                        intent.putExtra("disease", disease);
                        intent.putExtra("seeTime",seeTime);
                        intent.putExtra("userName",user.getName());
                        intent.putExtra("doctorName",doctorDto.getName());
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(AppointInfoActivity.this, "预约失败！", Toast.LENGTH_SHORT).show();
                        CustomWaitDialog.miss();
                    }
                    break;
            }

        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appoint_info);
        init();
        initMotion();
    }

    private void init() {
        im_back = (ImageView) findViewById(R.id.img_appoint_back);
        et_disease = (EditText) findViewById(R.id.et_disease);
        datepicker = (DatePicker) findViewById(R.id.datepicker);
        tv = (TextView) findViewById(R.id.tv);
        bt_appoint = (Button) findViewById(R.id.bt_appoint);
        bt_cancel = (Button) findViewById(R.id.bt_cancel);
    }

    private void initMotion() {
        im_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        datepicker.init(datepicker.getYear(), datepicker.getMonth(), datepicker.getDayOfMonth(), new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // 获取一个日历对象，并初始化为当前选中的时间
                long mindate = System.currentTimeMillis() - 1000L;
                view.setMinDate(mindate);
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, monthOfYear, dayOfMonth);
                String date = TimeUtil.dateToStrNoTime(calendar.getTime());
                tv.setText(date);
                seeTime = date;
            }
        });

        bt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        bt_appoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
                if (disease.equals("")) {
                    Toast.makeText(AppointInfoActivity.this, "请填写病症！", Toast.LENGTH_SHORT).show();
                } else if (tv.getText().equals("未选择")) {
                    Toast.makeText(AppointInfoActivity.this, "请选择预约时间！", Toast.LENGTH_SHORT).show();
                } else {
                    getConnect();
                }
            }
        });
    }

    private void getConnect() {
        final List<OkHttpUtils.Param> list = new ArrayList<OkHttpUtils.Param>();
        OkHttpUtils.Param patientIdParam = new OkHttpUtils.Param("patientId", patientId);
        OkHttpUtils.Param doctorIdParam = new OkHttpUtils.Param("doctorId", doctorId);
        OkHttpUtils.Param seeTimeParam = new OkHttpUtils.Param("seeTime", seeTime);
        OkHttpUtils.Param diseaseParam = new OkHttpUtils.Param("disease", disease);
        list.add(patientIdParam);
        list.add(doctorIdParam);
        list.add(seeTimeParam);
        list.add(diseaseParam);
        CustomWaitDialog.show(AppointInfoActivity.this, "预约中...");
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpUtils.post(APPConfig.addAppointment, new OkHttpUtils.ResultCallback() {
                    @Override
                    public void onSuccess(Object response) {
                        Message message = new Message();
                        message.what = 0;
                        message.obj = response;
                        if (handler.sendMessage(message)) {
//                            Toast.makeText(AppointInfoActivity.this, "发送数据成功！", Toast.LENGTH_SHORT).show();
                        }else {
//                            Toast.makeText(AppointInfoActivity.this, "发送数据失败，请重试！", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Exception e) {
                        Toast.makeText(AppointInfoActivity.this, "网络连接失败，请重试！", Toast.LENGTH_SHORT).show();
                        CustomWaitDialog.miss();
                        //网络连接失败会跳转到连接失败提示页面
//                        Intent intent = new Intent();
//                        intent.setClass(AppointInfoActivity.this, ConnectFailure.class);
//                        startActivity(intent);
                    }
                }, list);
            }
        }).start();
    }

    public void getData() {
        String userData = SharedPrefsUtil.getValue(AppointInfoActivity.this, APPConfig.USERDATA, "读取失败");
        Gson gson = new Gson();
        user = gson.fromJson(userData, User.class);
        patientId = "" + user.getId();
        doctorDto = gson.fromJson(getIntent().getStringExtra("doctorDtoStr"), DoctorDto.class);
        doctorId = "" + doctorDto.getId();
        disease = et_disease.getText().toString().trim();
    }
}