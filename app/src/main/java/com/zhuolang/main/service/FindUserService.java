package com.zhuolang.main.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zhuolang.main.R;
import com.zhuolang.main.common.APPConfig;
import com.zhuolang.main.model.DoctorDto;
import com.zhuolang.main.model.User;
import com.zhuolang.main.utils.OkHttpUtils;
import com.zhuolang.main.utils.SharedPrefsUtil;

import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wnf on 2016/11/5.
 */
public class FindUserService extends Service{


    @Override
    public void onCreate() {
        Log.i("testRun", "Service--onCreate()");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String phone=intent.getStringExtra("account");
        Log.i("testRun", "Service--onStartCommand() phone:=="+phone);
        final List<OkHttpUtils.Param> list = new ArrayList<OkHttpUtils.Param>();
        OkHttpUtils.Param phoneParam = new OkHttpUtils.Param("phone",phone);
        list.add(phoneParam);
        new Thread(new Runnable() {
            @Override
            public void run() {

                //post方式连接  url
                OkHttpUtils.post(APPConfig.findUserByPhone, new OkHttpUtils.ResultCallback() {
                    @Override
                    public void onSuccess(Object response) {

                        Log.d("testRun", "FindUserService 网络请求成功------");
                        Log.d("testRun", "response.toString()" + response.toString());
                        String userData= response.toString();
//                        Gson gson = new Gson();
//                        DoctorDto doctorDto=gson.fromJson(userData,DoctorDto.class);
//                        Log.d("testRun","user.toString()"+doctorDto.toString());
                        SharedPrefsUtil.putValue(FindUserService.this,APPConfig.USERDATA, userData);
                        Log.d("testRun","user信息缓存成功");
                    }

                    @Override
                    public void onFailure(Exception e) {
                        Log.d("testRun", "网络请求失败------");
//                        Toast.makeText(FinduserService.this, "请求网络连接失败，请重试！", Toast.LENGTH_SHORT).show();
                    }
                }, list);
            }
        }).start();

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.i("testRun", "Service--onDestroy()");
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i("testRun", "Service--onBind()");
        return null;
    }
}
