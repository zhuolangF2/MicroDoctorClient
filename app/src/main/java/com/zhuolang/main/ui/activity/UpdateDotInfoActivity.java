package com.zhuolang.main.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.zhuolang.main.R;
import com.zhuolang.main.common.APPConfig;
import com.zhuolang.main.model.DoctorDto;
import com.zhuolang.main.utils.OkHttpUtils;
import com.zhuolang.main.utils.SharedPrefsUtil;
import com.zhuolang.main.view.CustomWaitDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wnf on 2016/11/6.
 */

public class UpdateDotInfoActivity extends Activity{

    private String userDataStr;
    private DoctorDto userInfo;
    private ImageView imageViewBack;
    private LinearLayout ll__hospital;
    private LinearLayout ll__office;

    private TextView tv_phone;

    private EditText et_nickname;
    private EditText et_name;
    private EditText et_age;
    private EditText et_gender;
    private EditText et_address;
    private EditText et_signature;
    private EditText et_introduction;
    private EditText et_hospital;
    private EditText et_office;

    private String nickname;
    private String name;
    private String phone;
    private String age;
    private String gender;
    private String address;
    private String signature;
    private String introduction;
    private String hospital;
    private String office;
    private String type;

    private Button bt_update;
    Gson gson=new Gson();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //获取当前用户信息
        setContentView(R.layout.activity_upda_dot_info);
        initDoctorView();
        initDoctorDatas();
    }


    private void initDoctorView() {
        userDataStr=SharedPrefsUtil.getValue(this, APPConfig.USERDATA, "");
        userInfo=gson.fromJson(userDataStr, DoctorDto.class);

        tv_phone=(TextView)findViewById(R.id.tv_update_dotinfo_phone);
        et_nickname=(EditText)findViewById(R.id.et_update_dotinfo_nickname);
        et_name=(EditText)findViewById(R.id.et_update_dotinfo_name);
        et_age=(EditText)findViewById(R.id.et_update_dotinfo_age);
        et_gender=(EditText)findViewById(R.id.et_update_dotinfo_gender);
        et_address=(EditText)findViewById(R.id.et_update_dotinfo_address);
        et_signature=(EditText)findViewById(R.id.et_update_dotinfo_signature);
        et_introduction=(EditText)findViewById(R.id.et_update_dotinfo_introduction);
        et_hospital=(EditText)findViewById(R.id.et_update_dotinfo_hospital);
        et_office=(EditText)findViewById(R.id.et_update_dotinfo_office);
        imageViewBack=(ImageView)findViewById(R.id.image_updadotinfo_back);
        ll__hospital= (LinearLayout) findViewById(R.id.ll_updatedotinfo_hospt);
        ll__office= (LinearLayout) findViewById(R.id.ll_updatedotinfo_office);
        bt_update= (Button) findViewById(R.id.bt_update_doctorinfo);
//        imageViewBack.setOnClickListener(this);
    }

    private void initDoctorDatas() {

        tv_phone.setText("账号："+userInfo.getPhone());
        type=""+userInfo.getType();
        et_nickname.setText(userInfo.getNickname());
        et_name.setText(userInfo.getName());
        et_age.setText(""+userInfo.getAge());
        if (userInfo.getGender() == 1) {
            et_gender.setText("女");
        }else {
            et_gender.setText("男");
        }
        et_address.setText(userInfo.getAddress());
        et_signature.setText(userInfo.getSignature());
        et_introduction.setText(userInfo.getIntroduction());

        if (type.equals("0")){
            ll__hospital.setVisibility(View.GONE);
            ll__office.setVisibility(View.GONE);
        }else {
            et_hospital.setText(userInfo.getHospital());
            et_office.setText(userInfo.getOffice());
        }
        imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(UpdateDotInfoActivity.this, ShowDotInfoActivity.class);
                startActivity(intent);
                finish();
            }
        });
        bt_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nickname = et_nickname.getText().toString().trim();
                name = et_name.getText().toString().trim();
                age = et_age.getText().toString().trim();
                gender = et_gender.getText().toString().trim();
                if (gender.equals("女")) {
                    gender = "1";
                } else {
                    gender = "0";
                }
                address = et_address.getText().toString().trim();
                signature = et_signature.getText().toString().trim();
                introduction = et_introduction.getText().toString().trim();
                if (type.equals("1")) {
                    hospital = et_hospital.getText().toString().trim();
                    office = et_office.getText().toString().trim();
                } else {
                    hospital = "";
                    office = "";
                }



                final List<OkHttpUtils.Param> list = new ArrayList<OkHttpUtils.Param>();
                OkHttpUtils.Param userIdParam = new OkHttpUtils.Param("id", "" + userInfo.getId());
                OkHttpUtils.Param nicknameParam = new OkHttpUtils.Param("nickname", nickname);
                OkHttpUtils.Param nameParam = new OkHttpUtils.Param("name", name);
                OkHttpUtils.Param ageParam = new OkHttpUtils.Param("age", age);
                OkHttpUtils.Param genderParam = new OkHttpUtils.Param("gender", gender);
                OkHttpUtils.Param phoneParam = new OkHttpUtils.Param("phone", userInfo.getPhone());
                OkHttpUtils.Param addressParam = new OkHttpUtils.Param("address", address);
                OkHttpUtils.Param signatureParam = new OkHttpUtils.Param("signature", signature);
                OkHttpUtils.Param introductionParam = new OkHttpUtils.Param("introduction", introduction);

                OkHttpUtils.Param hospitalParam = new OkHttpUtils.Param("hospital", hospital);
                OkHttpUtils.Param officeParam = new OkHttpUtils.Param("office", office);

                OkHttpUtils.Param typeParam = new OkHttpUtils.Param("type", type);
                list.add(userIdParam);
                list.add(nicknameParam);
                list.add(nameParam);
                list.add(ageParam);
                list.add(genderParam);
                list.add(phoneParam);
                list.add(addressParam);
                list.add(signatureParam);
                list.add(introductionParam);
                list.add(hospitalParam);
                list.add(officeParam);
                list.add(typeParam);
                CustomWaitDialog.show(UpdateDotInfoActivity.this,"修改信息中...");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("testRun", "登陆登陆登陆登陆loginActivity----new Thread(new Runnable() {------");
                        //post方式连接  url

                        OkHttpUtils.post(APPConfig.updateUser, new OkHttpUtils.ResultCallback() {
                            @Override
                            public void onSuccess(Object response) {
                                if (response.equals("update_success")) {
                                    Toast.makeText(UpdateDotInfoActivity.this, "信息修改成功！", Toast.LENGTH_SHORT).show();
                                    CustomWaitDialog.miss();
                                    userInfo.setNickname(nickname);
                                    userInfo.setName(name);
                                    userInfo.setGender(Integer.parseInt(gender));
                                    userInfo.setAddress(address);
                                    userInfo.setSignature(signature);
                                    userInfo.setIntroduction(introduction);
                                    userInfo.setAge(Integer.parseInt(age));
                                    userInfo.setHospital(hospital);
                                    userInfo.setOffice(office);

                                    SharedPrefsUtil.putValue(UpdateDotInfoActivity.this, APPConfig.USERDATA, gson.toJson(userInfo));
                                    Log.d("testRun", "UpdateDotInfoActivity  userJson==" + gson.toJson(userInfo));

                                    Intent intent1 = new Intent();
                                    intent1.setClass(UpdateDotInfoActivity.this, ShowDotInfoActivity.class);
                                    startActivity(intent1);
                                    finish();
                                } else {
                                    Toast.makeText(UpdateDotInfoActivity.this, "信息修改失败，请重试！", Toast.LENGTH_SHORT).show();
                                    CustomWaitDialog.miss();
                                }
                            }
                            @Override
                            public void onFailure(Exception e) {
                                Log.d("testRun", "请求失败loginActivity----new Thread(new Runnable() {------");
                                Toast.makeText(UpdateDotInfoActivity.this, "请求网络连接失败，请重试！", Toast.LENGTH_SHORT).show();
                                CustomWaitDialog.miss();
                            }
                        }, list);
                    }
                }).start();

            }
        });
    }

}
