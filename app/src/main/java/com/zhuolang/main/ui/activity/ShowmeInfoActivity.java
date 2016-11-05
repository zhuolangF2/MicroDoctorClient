package com.zhuolang.main.ui.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.zhuolang.main.R;
import com.zhuolang.main.common.APPConfig;
import com.zhuolang.main.model.DoctorDto;
import com.zhuolang.main.utils.SharedPrefsUtil;

/**
 * Created by jat on 2016/11/1.
 */

public class ShowmeInfoActivity extends Activity implements View.OnClickListener{

    private ImageView imageViewBack;
    private String userData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showmeinfo);

        initView();

    }

    private void initView() {
        userData=SharedPrefsUtil.getValue(this, APPConfig.USERDATA, "");
        Gson gson=new Gson();
        DoctorDto user=gson.fromJson(userData,DoctorDto.class);
        Log.d("testRun","user:"+user.toString());
        imageViewBack=(ImageView)findViewById(R.id.shmeinfo_image_back);
        imageViewBack.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.shmeinfo_image_back:
                finish();
                break;
            default:
                break;
        }
    }
}
