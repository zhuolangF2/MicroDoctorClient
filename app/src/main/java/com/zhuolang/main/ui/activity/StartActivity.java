package com.zhuolang.main.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.zhuolang.main.R;
import com.zhuolang.main.utils.SharedPrefsUtil;

/**
 * Created by jat on 2016/11/1.
 */

public class StartActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                String Uid = SharedPrefsUtil.getValue(StartActivity.this, "account", "");
                Intent intent = new Intent();
                if (!Uid.equals(""))
                    intent.setClass(StartActivity.this, MainActivity.class);
                else
                    intent.setClass(StartActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }, 2000); //停留2秒钟
    }
}
