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
import com.zhuolang.main.model.DoctorDto;
import com.zhuolang.main.utils.OkHttpUtils;

import java.util.*;

/**
 * Created by hzg on 2016/11/3.
 */
public class DoctorListActivity extends Activity implements AdapterView.OnItemClickListener {

    private ListView listView;
    private SimpleAdapter simpleAdapter;
    private List<Map<String, Object>> dataList;
    private List<DoctorDto> doctorDtos;
    private ImageView img_back;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            String result = (String) msg.obj;
            Gson gson = new Gson();
            doctorDtos = gson.fromJson(result, new TypeToken<List<DoctorDto>>() {
            }.getType());
            dataList = new ArrayList<Map<String, Object>>();
            for (DoctorDto d : doctorDtos) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("imageView", R.drawable.myicon);
                map.put("name", d.getName());
                map.put("amount", d.getAmount());
                map.put("introduction", d.getIntroduction());
                map.put("office", d.getOffice());
                map.put("doctorId", d.getId());
                dataList.add(map);
            }
            simpleAdapter = new SimpleAdapter(DoctorListActivity.this, dataList, R.layout.doctor_item, new String[]{"imageView", "name", "amount", "introduction", "office"}, new int[]{R.id.imageView, R.id.textView_name, R.id.textView_amount, R.id.textView_introduction, R.id.textView_office});
            listView.setAdapter(simpleAdapter);
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctorlist);
        listView = (ListView) findViewById(R.id.listView);
        initMotion();
        //设置listview的元素被选中时的事件处理监听器
        listView.setOnItemClickListener(this);
        img_back = (ImageView) findViewById(R.id.img_doctorList_back);
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    public void initMotion() {
        final List<OkHttpUtils.Param> list = new ArrayList<OkHttpUtils.Param>();
        OkHttpUtils.Param typeParam = new OkHttpUtils.Param("type", "1");//接口要求传类型，类型一为医师
        list.add(typeParam);
        new Thread(new Runnable() {
            @Override
            public void run() {
                //post方式连接  url
                OkHttpUtils.post(APPConfig.showDoctor, new OkHttpUtils.ResultCallback() {
                    @Override
                    public void onSuccess(Object response) {
                        Message message = new Message();
                        message.what = 0;
                        message.obj = response;
                        if (handler.sendMessage(message)) {
//                            Toast.makeText(DoctorListActivity.this, "发送数据成功！", Toast.LENGTH_SHORT).show();
                        }else {
//                            Toast.makeText(DoctorListActivity.this, "发送数据失败，请重试！", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Exception e) {
                        //网络连接失败会跳转到连接失败提示页面
//                        Intent intent = new Intent();
//                        intent.setClass(DoctorListActivity.this, ConnectFailure.class);
//                        startActivity(intent);
//                        finish();
                        Toast.makeText(DoctorListActivity.this, "网络连接失败，请重试！", Toast.LENGTH_SHORT).show();

                    }
                }, list);
            }
        }).start();
    }

    //    事件处理监听器方法
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent();
        intent.setClass(DoctorListActivity.this, DoctorDetail.class);
        DoctorDto doctorDto = doctorDtos.get(position);
        Gson gson = new Gson();
        String doctorDtoStr = gson.toJson(doctorDto);
        intent.putExtra("doctorDtoStr", doctorDtoStr);
        startActivity(intent);
    }
}
