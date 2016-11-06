package com.zhuolang.main.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.zhuolang.main.R;
import com.zhuolang.main.common.APPConfig;
import com.zhuolang.main.model.DoctorDto;
import com.zhuolang.main.service.FindUserService;
import com.zhuolang.main.utils.OkHttpUtils;
import com.zhuolang.main.utils.SharedPrefsUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wnf on 2016/11/1.
 */

public class UpdatePswActivity extends Activity {

    private Button btUpdatePsw;
    private EditText etOldPsw;
    private EditText etNewPsw;
    private ImageView ivBack;
    private String oldPsw;
    private String newPsw;
    private String userId;
    private String userDataStr;
    private DoctorDto userInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_updatepsw);
        initView();
        initDatas();

    }
    private void initView() {
        userDataStr=SharedPrefsUtil.getValue(this, APPConfig.USERDATA, "");
        Gson gson=new Gson();
        userInfo=gson.fromJson(userDataStr,DoctorDto.class);
        etNewPsw= (EditText) findViewById(R.id.et_updatepsw_newpsd);
        etOldPsw= (EditText) findViewById(R.id.et_updatepsw_oldpsw);
        btUpdatePsw= (Button) findViewById(R.id.bt_updatepsw_pss);
        ivBack=(ImageView)findViewById(R.id.image_updatepsw_back);
    }

    private void initDatas() {
        btUpdatePsw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取账号 密码
                userId= ""+userInfo.getId();
                oldPsw= etOldPsw.getText().toString().trim();
                newPsw= etNewPsw.getText().toString().trim();
                if (oldPsw.equals("")||newPsw.equals("")){
                    Toast.makeText(UpdatePswActivity.this, "密码不能为空！", Toast.LENGTH_SHORT).show();
                }else {
                    Log.d("testRun", "登陆登陆登陆登陆loginActivity----new Thread(new Runnable() {-----account-");
                    //运用okhttp框架 子线程获取后台数据
                    final List<OkHttpUtils.Param> list = new ArrayList<OkHttpUtils.Param>();
                    OkHttpUtils.Param userIdParam = new OkHttpUtils.Param("id", userId);
                    OkHttpUtils.Param oldPswParam = new OkHttpUtils.Param("oldPassword", oldPsw);
                    OkHttpUtils.Param newPsdParam = new OkHttpUtils.Param("newPassword", newPsw);
                    list.add(userIdParam);
                    list.add(oldPswParam);
                    list.add(newPsdParam);
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            Log.d("testRun", "登陆登陆登陆登陆loginActivity----new Thread(new Runnable() {------");
                            //post方式连接  url
                            OkHttpUtils.post(APPConfig.updatePassword, new OkHttpUtils.ResultCallback() {
                                @Override
                                public void onSuccess(Object response) {
                                    Message message = new Message();
                                    message.what = 0;
                                    message.obj = response;
                                    if (handler.sendMessage(message))
                                        Toast.makeText(UpdatePswActivity.this, "发送数据成功！", Toast.LENGTH_SHORT).show();
                                    else {
                                        Toast.makeText(UpdatePswActivity.this, "发送数据失败，请重试！", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onFailure(Exception e) {
                                    Log.d("testRun", "请求失败loginActivity----new Thread(new Runnable() {------");
                                    Toast.makeText(UpdatePswActivity.this, "请求网络连接失败，请重试！", Toast.LENGTH_SHORT).show();
                                }
                            }, list);
                        }
                    }).start();
                }
            }
        });

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    String result = (String)msg.obj;
                    if (result.equals("updatePassword_success")){
                        Toast.makeText(UpdatePswActivity.this,"密码修改成功，请记住密码！",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(UpdatePswActivity.this,"密码修改失败，请重试！",Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }

    };
}
