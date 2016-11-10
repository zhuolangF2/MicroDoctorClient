package com.zhuolang.main.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.zhuolang.main.R;
import com.zhuolang.main.common.APPConfig;
import com.zhuolang.main.model.DoctorDto;
import com.zhuolang.main.ui.activity.*;
import com.zhuolang.main.utils.SharedPrefsUtil;

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
    private TextView tv_appointMine;
    private TextView tv_history;

    private String type;

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
        type = SharedPrefsUtil.getValue(getContext(), APPConfig.TYPE, "");
        tv_appointMine= (TextView) view.findViewById(R.id.tv_homep_appointmine);
        tv_history= (TextView) view.findViewById(R.id.tv_homep_history);
        imageConsult = (ImageView) view.findViewById(R.id.image_item_consult);
        imageDoctor = (ImageView) view.findViewById(R.id.image_item_doctor);
        imageAppoint = (ImageView) view.findViewById(R.id.image_item_appointment);
        imageMyAppoint = (ImageView) view.findViewById(R.id.image_item_myappointment);
        if (type.equals("1")){
            tv_appointMine.setText("预约我的");
            tv_history.setText("历史记录");
        }else {
            tv_appointMine.setText("我的预约");
            tv_history.setText("我的咨询");
        }
        imageAppoint.setOnClickListener(this);
        imageMyAppoint.setOnClickListener(this);
        imageDoctor.setOnClickListener(this);
        imageConsult.setOnClickListener(this);
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
                intent3.setClass(getActivity(), MyAppointListActivity.class);
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
