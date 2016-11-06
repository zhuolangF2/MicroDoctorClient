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
public class ConnectFailure extends Activity {
    private ImageView img_connect_failure_back;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect_failure);
        img_connect_failure_back = (ImageView) findViewById(R.id.img_connect_failure_back);
        img_connect_failure_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
