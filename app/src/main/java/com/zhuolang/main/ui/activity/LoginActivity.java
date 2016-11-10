package com.zhuolang.main.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.zhuolang.main.R;
import com.zhuolang.main.common.APPConfig;
import com.zhuolang.main.service.FindUserService;
import com.zhuolang.main.utils.OkHttpUtils;
import com.zhuolang.main.utils.SharedPrefsUtil;
import com.zhuolang.main.view.CustomWaitDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jat on 2016/11/1.
 */

public class LoginActivity extends Activity {

    private EditText et_login_account;
    private EditText et_login_psd;
    private Button bt_login_login;
    private TextView tv_login_find;
    private TextView tv_login_register;

    private String account;
    private String psd;
    private int type;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    CustomWaitDialog.miss();
                    String result = (String)msg.obj;
                    if (result.equals("login_success0")||result.equals("login_success1")){
                        //保存登录状态
                        SharedPrefsUtil.putValue(LoginActivity.this,APPConfig.IS_LOGIN,true);
                        Intent intentService = new Intent();
                        intentService.setClass(LoginActivity.this, FindUserService.class);
                        intentService.putExtra("account",account);
                        LoginActivity.this.startService(intentService);
                        //登录成功
                        Toast.makeText(LoginActivity.this,"登陆成功！",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent();
                        intent.setClass(LoginActivity.this, MainActivity.class);
//                        intent.putExtra("type", type + "");
                        startActivity(intent);
                        finish();
                    }
                    else{
                        Toast.makeText(LoginActivity.this,"登陆失败！",Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        boolean is_login = SharedPrefsUtil.getValue(this,APPConfig.IS_LOGIN,false);
//        boolean is_login=false;
        if (is_login){
            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
            this.startActivity(intent);
            finish();
        }{
            init();
            initMotion();
        }

    }

    /**
     * 初始化控件
     */
    private void init(){
        et_login_account = (EditText)findViewById(R.id.et_login_account);//账号，对应phone
        et_login_psd = (EditText)findViewById(R.id.et_login_psd);
        bt_login_login = (Button)findViewById(R.id.bt_login_login);
        tv_login_find = (TextView)findViewById(R.id.tv_login_find);
        tv_login_register = (TextView)findViewById(R.id.tv_login_register);
    }

    /**
     * 初始化监听等
     */
    private void initMotion(){
        bt_login_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取账号 密码
                account = et_login_account.getText().toString().trim();
                psd = et_login_psd.getText().toString().trim();
                Log.d("testRun","登陆登陆登陆登陆loginActivity----new Thread(new Runnable() {-----account-"+account+"psd:"+psd);
                //运用okhttp框架 子线程获取后台数据
                final List<OkHttpUtils.Param> list = new ArrayList<OkHttpUtils.Param>();
                OkHttpUtils.Param accountParam = new OkHttpUtils.Param("phone",account);
                OkHttpUtils.Param psdParam = new OkHttpUtils.Param("password",psd);
                list.add(accountParam);
                list.add(psdParam);
                CustomWaitDialog.show(LoginActivity.this,"登录中...");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("testRun", "登陆登陆登陆登陆loginActivity----new Thread(new Runnable() {------");
                        //post方式连接  url
                        OkHttpUtils.post(APPConfig.login, new OkHttpUtils.ResultCallback() {
                            @Override
                            public void onSuccess(Object response) {
                                Message message = new Message();
                                message.what = 0;
                                message.obj = response;
                                if (response.toString().equals("login_success0")) {
                                    type = 0 ;
                                    SharedPrefsUtil.putValue(LoginActivity.this,APPConfig.TYPE,"0");
//                            Toast.makeText(AppointActivity.this, "发送数据成功！", Toast.LENGTH_SHORT).show();
                                } else {
                                    type = 1 ;
                                    SharedPrefsUtil.putValue(LoginActivity.this,APPConfig.TYPE,"1");
//                            Toast.makeText(AppointActivity.this, "发送数据失败，请重试！", Toast.LENGTH_SHORT).show();
                                }
                                handler.sendMessage(message);
                            }

                            @Override
                            public void onFailure(Exception e) {
                                Log.d("testRun", "请求失败loginActivity----new Thread(new Runnable() {------");
                                Toast.makeText(LoginActivity.this, "请求网络连接失败，请重试！", Toast.LENGTH_SHORT).show();
                                CustomWaitDialog.miss();
                            }
                        }, list);
//                        finish();
                    }
                }).start();
            }
        });

        tv_login_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });

        tv_login_find.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
