package com.zhuolang.main.ui.activity;

import android.app.Activity;
import android.content.Intent;
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
import com.zhuolang.main.utils.OkHttpUtils;

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

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    String result = (String)msg.obj;
                    if (result.equals("login_success")){
                        //登录成功
                        Intent intent = new Intent();
                        intent.setClass(LoginActivity.this, MainActivity.class);
                        Toast.makeText(LoginActivity.this,"登陆成功！",Toast.LENGTH_SHORT).show();
                        startActivity(intent);
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
        init();
        initMotion();
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
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("testRun","登陆登陆登陆登陆loginActivity----new Thread(new Runnable() {------");
                        //post方式连接  url
                        OkHttpUtils.post(APPConfig.login, new OkHttpUtils.ResultCallback() {
                            @Override
                            public void onSuccess(Object response) {
                                Message message = new Message();
                                message.what = 0;
                                message.obj = response;
                                if(handler.sendMessage(message))
                                    Toast.makeText(LoginActivity.this,"发送数据成功！",Toast.LENGTH_SHORT).show();
                                else {
                                    Toast.makeText(LoginActivity.this,"发送数据失败，请重试！",Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Exception e) {
                                Log.d("testRun","请求失败loginActivity----new Thread(new Runnable() {------");
                                Toast.makeText(LoginActivity.this,"请求网络连接失败，请重试！",Toast.LENGTH_SHORT).show();
                            }
                        },list);
                        finish();
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
    }


}
