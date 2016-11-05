package com.zhuolang.main.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.zhuolang.main.common.APPConfig;
import com.zhuolang.main.utils.OkHttpUtils;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/5.
 */
public class FinduserService extends Service{

    @Override
    public void onCreate() {
        Log.i("info", "Service--onCreate()");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("info", "Service--onStartCommand()");
        String phone=intent.getStringExtra("account");
        final List<OkHttpUtils.Param> list = new ArrayList<OkHttpUtils.Param>();
        OkHttpUtils.Param phoneParam = new OkHttpUtils.Param("phone",phone);
        list.add(phoneParam);
        new Thread(new Runnable() {
            @Override
            public void run() {

                //post方式连接  url
                OkHttpUtils.post(APPConfig.login, new OkHttpUtils.ResultCallback() {
                    @Override
                    public void onSuccess(Object response) {
                        Log.d("testRun", "网络请求成功------");


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
        Log.i("info", "Service--onDestroy()");
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i("info", "Service--onBind()");
        return null;
    }
}
