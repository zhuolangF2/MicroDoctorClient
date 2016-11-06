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
import com.google.gson.reflect.TypeToken;
import com.zhuolang.main.R;
import com.zhuolang.main.common.APPConfig;
import com.zhuolang.main.model.Appointment;
import com.zhuolang.main.model.DoctorDto;
import com.zhuolang.main.model.User;
import com.zhuolang.main.utils.OkHttpUtils;
import com.zhuolang.main.utils.SharedPrefsUtil;

import java.util.*;

/**
 * Created by hzg on 2016/11/3.
 */
public class MyAppointListActivity extends Activity implements AdapterView.OnItemClickListener {
    private ListView listView;
    private SimpleAdapter simpleAdapter;
    private List<Map<String, Object>> dataList;
    private List<Appointment> appointments;
    private ImageView img_back;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
            String result = (String) msg.obj;
            Gson gson = new Gson();
            appointments = gson.fromJson(result, new TypeToken<List<Appointment>>() {}.getType());
            dataList = new ArrayList<Map<String, Object>>();
            for (Appointment a : appointments) {
                Map<String, Object> map = new HashMap<String, Object>();
//                map.put("imageView", R.drawable.myicon);
//                map.put("name", a.getName());
//                map.put("amount", d.getAmount());
//                map.put("introduction", d.getIntroduction());
//                map.put("office", d.getOffice());
//                map.put("doctorId", d.getId());
                dataList.add(map);
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_appointList);
        initMotion();
        listView = (ListView) findViewById(R.id.listView);
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
        OkHttpUtils.Param typeParam = new OkHttpUtils.Param("patientId", user.getId()+"");//接口要求传类型，类型一为医师
        list.add(typeParam);
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
                        if (handler.sendMessage(message))
                            Toast.makeText(MyAppointListActivity.this, "发送数据成功！", Toast.LENGTH_SHORT).show();
                        else {
                            Toast.makeText(MyAppointListActivity.this, "发送数据失败，请重试！", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Exception e) {
                        Toast.makeText(MyAppointListActivity.this, "网络连接失败，请重试！", Toast.LENGTH_SHORT).show();

                    }
                }, list);
            }
        }).start();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent();
        intent.setClass(MyAppointListActivity.this, MyAppointDetailActivity.class);
        Appointment appointment = appointments.get(position);
        Gson gson = new Gson();
        String doctorDtoStr = gson.toJson(appointment);
        intent.putExtra("doctorDtoStr", doctorDtoStr);
        startActivity(intent);
    }
}
