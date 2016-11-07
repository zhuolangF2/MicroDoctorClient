package com.zhuolang.main.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

public class RegisterActivity extends Activity {

    private RadioGroup radiogroup1;        //定义注册用户类型的复选框对象
    private RadioGroup radiogroup2;
    private EditText et_phone;
    private EditText et_psd;
    private EditText et_name;
    private EditText et_nickname;
    private EditText et_age;
//    private EditText et_gender;
    private EditText et_address;
    private Button bt_register;

    private String phone;
    private String psd;
    private String name;
    private String nickname;
    private String age="";
    private String gender="";
    private String address;
    private String type="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register);
        initView();
        initMotion();
    }
    private void initView() {
        //通过findViewById得到对应的控件对象
        et_phone=(EditText)findViewById(R.id.et_register_phone);
        et_psd=(EditText)findViewById(R.id.et_register_psd);
        et_name=(EditText)findViewById(R.id.et_register_name);
        et_nickname=(EditText)findViewById(R.id.et_register_nickname);
//        et_gender=(EditText)findViewById(R.id.et_register_gender);
        et_address=(EditText)findViewById(R.id.et_register_address);
        et_age=(EditText)findViewById(R.id.et_register_age);
        bt_register=(Button)findViewById(R.id.bt_register_reg);

        radiogroup1 = (RadioGroup)findViewById(R.id.register_radiogro);
        radiogroup2 = (RadioGroup)findViewById(R.id.rg_register_gender);
    }

    /**
     * 初始化监听等
     */
    private void initMotion(){
        //注册类型选择
        radiogroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup Group, int Checkid) {
                String str;
                RadioButton radioButton = (RadioButton) findViewById(radiogroup1.getCheckedRadioButtonId());
                str = radioButton.getText().toString();
                if (str.equals("普通用户注册")) {
                    type = "0";
                } else {
                    type = "1";
                }
                Log.d("testradio", "选择对象是：值:" + str + " type:" + type);
            }
        });

        radiogroup2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup Group, int Checkid) {
                String str;
                str = ((RadioButton) findViewById(radiogroup2.getCheckedRadioButtonId())).getText().toString();
                if (str.equals("男")) {
                    gender = "0";
                } else {
                    gender = "1";
                }
                Log.d("testradio", "选择对象是：值:" + str + " gender:" + gender);
            }
        });

        bt_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取数据
                phone = et_phone.getText().toString().trim();
                psd = et_psd.getText().toString().trim();
                name = et_name.getText().toString().trim();
                nickname = et_nickname.getText().toString().trim();
                age = et_age.getText().toString().trim();
                address = et_address.getText().toString().trim();
                if (phone.equals("")||psd.equals("")){
                    Toast.makeText(RegisterActivity.this,"账号密码不能为空！",Toast.LENGTH_SHORT).show();
                }
                else {
                    name=(name.equals("")? "未填写" : name);
                    nickname=(nickname.equals("")? "未填写" : nickname);
                    age=(age.equals("")? "0" : age);
                    address=(address.equals("")? "未填写" : address);
                    gender=(gender.equals("")? "0" : gender);
                    type=(type.equals("")? "0" : type);
                    //运用okhttp框架 子线程获取后台数据
                    final List<OkHttpUtils.Param> list = new ArrayList<OkHttpUtils.Param>();

                    OkHttpUtils.Param phoneParam = new OkHttpUtils.Param("phone",phone);
                    OkHttpUtils.Param psdParam = new OkHttpUtils.Param("password",psd);
                    OkHttpUtils.Param nameParam = new OkHttpUtils.Param("name",name);
                    OkHttpUtils.Param nicknameParam = new OkHttpUtils.Param("nickname",nickname);
                    OkHttpUtils.Param ageParam = new OkHttpUtils.Param("age",age);
                    OkHttpUtils.Param genderParam = new OkHttpUtils.Param("gender",gender);
                    OkHttpUtils.Param addressParam = new OkHttpUtils.Param("address",address);
                    OkHttpUtils.Param typeParam = new OkHttpUtils.Param("type",type);
//                OkHttpUtils.Param signatureParam = new OkHttpUtils.Param("signature","无");
//                OkHttpUtils.Param introductionParam = new OkHttpUtils.Param("introduction","无");


                    list.add(phoneParam);
                    list.add(nicknameParam);
                    list.add(psdParam);
                    list.add(nameParam);
                    list.add(ageParam);
                    list.add(genderParam);
                    list.add(addressParam);
                    list.add(typeParam);
//                list.add(signatureParam);
//                list.add(introductionParam);
                    CustomWaitDialog.show(RegisterActivity.this, "注册中...");
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            Log.d("testRun", "登陆登陆登陆登陆loginActivity----new Thread(new Runnable() {------");
                            //post方式连接  url
                            OkHttpUtils.post(APPConfig.register, new OkHttpUtils.ResultCallback() {
                                @Override
                                public void onSuccess(Object response) {
                                    if (response.equals("register_failure")){
                                        Toast.makeText(RegisterActivity.this, "已存在该手机号用户，请换个号码注册！", Toast.LENGTH_LONG).show();
                                        CustomWaitDialog.miss();
                                    }
                                    else {
                                        Message message = new Message();
                                        message.what = 0;
                                        message.obj = response;
                                        if (handler.sendMessage(message)) {
//                                            Toast.makeText(RegisterActivity.this, "发送数据成功！", Toast.LENGTH_SHORT).show();
                                        }else {
//                                            Toast.makeText(RegisterActivity.this, "发送数据失败，请重试！", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }

                                @Override
                                public void onFailure(Exception e) {
                                    Log.d("testRun", "请求失败loginActivity----new Thread(new Runnable() {------");
                                    Toast.makeText(RegisterActivity.this, "请求网络连接失败，请重试！", Toast.LENGTH_SHORT).show();
                                    CustomWaitDialog.miss();
                                }
                            }, list);
                        }

                    }).start();

                    }
                }
        });

    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    String result = (String)msg.obj;
                    if (result.equals("register_success")){
                        //注册成功
                        Toast.makeText(RegisterActivity.this,"注册成功！",Toast.LENGTH_SHORT).show();
                        Intent intentService = new Intent();
                        intentService.setClass(RegisterActivity.this, FindUserService.class);
                        intentService.putExtra("account", phone);
                        RegisterActivity.this.startService(intentService);
                        //保存登录状态
                        SharedPrefsUtil.putValue(RegisterActivity.this, APPConfig.IS_LOGIN, true);
                        Intent intent = new Intent();
                        intent.setClass(RegisterActivity.this, MainActivity.class);
//                        Toast.makeText(RegisterActivity.this,"登陆成功！",Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                        finish();
                    }
                    else{
                        Toast.makeText(RegisterActivity.this,"注册失败，请重试！",Toast.LENGTH_SHORT).show();
                        CustomWaitDialog.miss();
                    }
                    break;
            }
        }

    };


}
