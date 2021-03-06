package com.zhuolang.main.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zhuolang.main.R;
import com.zhuolang.main.adapter.MyAppointListAdapter;
import com.zhuolang.main.common.APPConfig;
import com.zhuolang.main.model.Appointment;
import com.zhuolang.main.model.AppointmentDto;
import com.zhuolang.main.model.User;
import com.zhuolang.main.utils.OkHttpUtils;
import com.zhuolang.main.utils.SharedPrefsUtil;
import com.zhuolang.main.view.CustomWaitDialog;

import java.util.*;

/**
 * Created by hzg on 2016/11/3.
 */
public class MyAppointListActivity extends Activity implements AdapterView.OnItemClickListener {
    private ListView listView;
    private List<AppointmentDto> appointmentDtos = new ArrayList<>();
    private ImageView img_back;
    private MyAppointListAdapter adapter;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            String result = msg.obj.toString();
            Log.d("testRun", "result======" + result);
            CustomWaitDialog.miss();
            Gson gson = new Gson();
            appointmentDtos = gson.fromJson(result, new TypeToken<List<AppointmentDto>>() {}.getType());
            adapter = new MyAppointListAdapter(MyAppointListActivity.this, appointmentDtos);
            listView.setAdapter(adapter);
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_appointlist);
        initMotion();

        listView = (ListView) findViewById(R.id.lv_my_appoint_list);
        listView.setOnItemClickListener(this);
        img_back = (ImageView) findViewById(R.id.img_back);
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void initMotion() {
        final List<OkHttpUtils.Param> list = new ArrayList<OkHttpUtils.Param>();
        Gson gson = new Gson();
        String userData = SharedPrefsUtil.getValue(MyAppointListActivity.this, APPConfig.USERDATA, "读取失败");
        User user = gson.fromJson(userData, User.class);
        OkHttpUtils.Param typeParam = new OkHttpUtils.Param("patientId", user.getId() + "");//接口要求传类型，类型一为医师
        list.add(typeParam);
        CustomWaitDialog.show(MyAppointListActivity.this, "连接服务中...");
        new Thread(new Runnable() {
            @Override
            public void run() {
                //post方式连接  url
                OkHttpUtils.post(APPConfig.showMyAppointList, new OkHttpUtils.ResultCallback() {
                    @Override
                    public void onSuccess(Object response) {
                        Message message = new Message();
                        message.what = 0;
                        message.obj = response;
                        if (response.toString().equals("find_failure")) {
                            CustomWaitDialog.miss();
                            Toast.makeText(MyAppointListActivity.this, "没有找到您的预约数据！", Toast.LENGTH_LONG).show();
                        } else {
                            handler.sendMessage(message);
                        }
                    }

                    @Override
                    public void onFailure(Exception e) {
                        Toast.makeText(MyAppointListActivity.this, "网络连接失败，请重试！", Toast.LENGTH_SHORT).show();
                        CustomWaitDialog.miss();
                    }
                }, list);
            }
        }).start();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent();
        intent.setClass(MyAppointListActivity.this, MyAppointDetailActivity.class);
        AppointmentDto appointmentDto = appointmentDtos.get(position);
        Gson gson = new Gson();
        String doctorDtoStr = gson.toJson(appointmentDto);
        Log.d("testRun", "doctorDtoStr==========" + doctorDtoStr);
        intent.putExtra("doctorDtoStr", doctorDtoStr);
        startActivity(intent);
    }
}
