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


public class HomepageTabFragment extends Fragment implements View.OnClickListener {

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
        initView(view);
        return view;
    }

    /*
     *初始化数据
     */
    private void initView(View view) {
        imageConsult = (ImageView) view.findViewById(R.id.image_item_consult);
        imageConsult.setOnClickListener(this);
        imageDoctor = (ImageView) view.findViewById(R.id.image_item_doctor);
        imageDoctor.setOnClickListener(this);
        imageAppoint = (ImageView) view.findViewById(R.id.image_item_appointment);
        imageAppoint.setOnClickListener(this);
        imageMyAppoint = (ImageView) view.findViewById(R.id.image_item_myappointment);
        imageMyAppoint.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        clickImage(v);
    }

    /**
     * 点击图片
     *
     * @param v
     */
    private void clickImage(View v) {
        switch (v.getId()) {
            case R.id.image_item_doctor:
                Intent intent = new Intent();
                intent.setClass(getActivity(), DoctorListActivity.class);
                startActivity(intent);
                break;
            case R.id.image_item_appointment:
                Intent intent2 = new Intent();
                intent2.setClass(getActivity(), AppointActivity.class);
                startActivity(intent2);
                break;
            case R.id.image_item_myappointment:
                Intent intent3 = new Intent();
                intent3.setClass(getActivity(), MyAppointActivity.class);
                startActivity(intent3);
                break;
            case R.id.image_item_consult:
                Intent intent4 = new Intent();
                intent4.setClass(getActivity(), MyConsultActivity.class);
                startActivity(intent4);
                break;
        }
    }

}
