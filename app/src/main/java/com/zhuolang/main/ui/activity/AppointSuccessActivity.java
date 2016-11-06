package com.zhuolang.main.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import com.zhuolang.main.R;

/**
 * Created by hzg on 2016/11/6.
 */
public class AppointSuccessActivity extends Activity {
    private ImageView img_appoint_success_back;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appoint_success);
        img_appoint_success_back = (ImageView) findViewById(R.id.img_appoint_success_back);
        img_appoint_success_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
