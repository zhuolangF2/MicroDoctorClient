package com.zhuolang.main.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.zhuolang.main.R;
import com.zhuolang.main.ui.activity.*;

/**
 * Created by wnf on 2016/10/29.
 * 首页界面fragment
 */


public class HomepageTabFragment extends Fragment{

    private View view = null;

    private ImageView imageDoctor = null;//医师展示
    private ImageView imageAppoint = null;//预约挂号
    private ImageView imageMyAppoint = null;//我的预约
    private ImageView imageConsult = null;//我的咨询

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {

        view = new View(getActivity());
        view = inflater.inflate(R.layout.homepage, container, false);
        Log.d("activityID", "这个是HomepageTabFragment----------:" + this.toString());
        //初始化数据
        initView(view);
        imageConsult = (ImageView) view.findViewById(R.id.image_item_consult);
        imageDoctor = (ImageView) view.findViewById(R.id.image_item_doctor);
        imageAppoint = (ImageView) view.findViewById(R.id.image_item_appointment);
        imageMyAppoint = (ImageView) view.findViewById(R.id.image_item_myappointment);
        //这里的intent需要关闭吗?怎么关闭???
        imageDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), DoctorActivity.class);
                startActivity(intent);
            }
        });

        imageAppoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), AppointActivity.class);
                startActivity(intent);
            }
        });

        imageMyAppoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), MyAppointActivity.class);
                startActivity(intent);
            }
        });
        imageConsult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), MyConsultActivity.class);
                startActivity(intent);
            }
        });


        return view;

    }

    /*
     *初始化数据
     */
    private void initView(View view) {
    }
}
