package com.zhuolang.main.ui.activity;

import android.app.Activity;
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
public class DoctorActivity extends Activity implements AdapterView.OnItemClickListener {

    private ListView listView;
    private SimpleAdapter simpleAdapter;
    private List<Map<String, Object>> dataList;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            String result = (String) msg.obj;
            Gson gson = new Gson();
            List<DoctorDto> doctorDtos = gson.fromJson(result, new TypeToken<List<DoctorDto>>() {
            }.getType());
            dataList = new ArrayList<Map<String, Object>>();
//            listView = (ListView) findViewById(R.id.listView);
            for (DoctorDto d : doctorDtos) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("imageView", R.drawable.myicon);
                map.put("textView_name", d.getName());
                map.put("textView_amount", d.getAmount());
                map.put("textView_introduction", d.getIntroduction());
                map.put("textView_office", d.getOffice());
                dataList.add(map);
            }
            simpleAdapter = new SimpleAdapter(DoctorActivity.this, dataList, R.layout.doctor_item, new String[]{"imageView", "textView_name", "textView_amount", "textView_introduction", "textView_office"}, new int[]{R.id.imageView, R.id.textView_name, R.id.textView_amount, R.id.textView_introduction, R.id.textView_office});
            listView.setAdapter(simpleAdapter);
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctorshow);
        listView = (ListView) findViewById(R.id.listView);
        initMotion();
        //设置listview的元素被选中时的事件处理监听器
        listView.setOnItemClickListener(this);

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
                        if (handler.sendMessage(message))
                            Toast.makeText(DoctorActivity.this, "发送数据成功！", Toast.LENGTH_SHORT).show();
                        else {
                            Toast.makeText(DoctorActivity.this, "发送数据失败，请重试！", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Exception e) {
                        Toast.makeText(DoctorActivity.this, "请求网络连接失败，请重试！", Toast.LENGTH_SHORT).show();
                    }

                }, list);
            }
        }).start();
    }

    //    事件处理监听器方法
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // 获取点击ListView item中的内容信息
        String text = listView.getItemAtPosition(position) + "";
        // 弹出Toast信息显示点击位置和内容
        Toast.makeText(this, "position=" + position + "content=" + text, Toast.LENGTH_SHORT).show();
    }
}
