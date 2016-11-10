package com.zhuolang.main.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zhuolang.main.R;
import com.zhuolang.main.adapter.HistoryListAdapter;
import com.zhuolang.main.common.APPConfig;
import com.zhuolang.main.model.Appointment;
import com.zhuolang.main.model.AppointmentDto;
import com.zhuolang.main.model.User;
import com.zhuolang.main.utils.OkHttpUtils;
import com.zhuolang.main.utils.SharedPrefsUtil;
import com.zhuolang.main.view.CustomWaitDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hzg on 2016/11/9.
 */
public class HistoryActivity extends Activity implements AdapterView.OnItemClickListener {
    private ListView listView;
    private List<AppointmentDto> appointmentDtos;
    private List<AppointmentDto> appointHistory = new ArrayList<AppointmentDto>();
    private ImageView img_back;
    private HistoryListAdapter adapter;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            String result = msg.obj.toString();
            CustomWaitDialog.miss();
            Gson gson = new Gson();
            appointmentDtos = gson.fromJson(result, new TypeToken<List<AppointmentDto>>() {
            }.getType());
            //循环通过判断诊断的信息是否为空来判断是否是历史记录，如果有填过就诊信息就是历史记录
            for (AppointmentDto a : appointmentDtos) {
                if (a.getDiagnose() != null) {
                    appointHistory.add(a);
                }
            }
            adapter = new HistoryListAdapter(HistoryActivity.this, appointHistory);
            listView.setAdapter(adapter);
        }
    };


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        initMotion();

        listView = (ListView) findViewById(R.id.lv_appoint_history_list);
        listView.setOnItemClickListener(this);
        img_back = (ImageView) findViewById(R.id.img_back);
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initMotion() {
        final List<OkHttpUtils.Param> list = new ArrayList<OkHttpUtils.Param>();
        Gson gson = new Gson();
        String userData = SharedPrefsUtil.getValue(HistoryActivity.this, APPConfig.USERDATA, "读取失败");
        User user = gson.fromJson(userData, User.class);
        OkHttpUtils.Param typeParam = new OkHttpUtils.Param("patientId", user.getId() + "");
        list.add(typeParam);
        CustomWaitDialog.show(HistoryActivity.this, "连接服务中...");
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpUtils.post(APPConfig.showMyAppointList, new OkHttpUtils.ResultCallback() {
                    @Override
                    public void onSuccess(Object response) {
                        Message message = new Message();
                        message.what = 0;
                        message.obj = response;
                        if (response.toString().equals("find_failure")) {
                            CustomWaitDialog.miss();
                            Toast.makeText(HistoryActivity.this, "没有找到您的预约记录！", Toast.LENGTH_LONG).show();
                        } else {
                            handler.sendMessage(message);
                        }
                    }

                    @Override
                    public void onFailure(Exception e) {
                        Toast.makeText(HistoryActivity.this, "网络连接失败，请重试！", Toast.LENGTH_SHORT).show();
                        CustomWaitDialog.miss();
                    }
                }, list);
            }
        }).start();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent();
        intent.setClass(HistoryActivity.this, HistoryDetailActivity.class);
        AppointmentDto appointmentDto = appointHistory.get(position);
        Gson gson = new Gson();
        String appointHistoryStr = gson.toJson(appointmentDto);
        intent.putExtra("appointHistoryStr", appointHistoryStr);
        startActivity(intent);
    }
}
