package com.zhuolang.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Layout;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.graphics.Color;

/**
 * Created by wnf on 2016/10/29.
 * 首页界面fragment
 */


public class HomepageTabFragment extends Fragment{


    private ImageView imageView_item1=null;
    private ImageView imageView_doctor=null;
    private ImageView imageView_appointment=null;
    private ImageView imageView_myappointment=null;
    private ImageView imageView_login=null;

    private View view = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState){

        view=new View(getActivity());
        view = inflater.inflate(R.layout.homepage, container, false);
        Log.d("activityID", "这个是HomepageTabFragment----------:" + this.toString());
        //初始化数据
        initView(view);

        return view;

    }

    /*
     *初始化数据
     */
    private void initView(View view) {
        imageView_item1= (ImageView) view.findViewById(R.id.image_item1);
        imageView_doctor= (ImageView) view.findViewById(R.id.image_item_doctor);
        imageView_appointment= (ImageView) view.findViewById(R.id.image_item_appointment);
        imageView_myappointment= (ImageView) view.findViewById(R.id.image_item_myappointment);
        imageView_login= (ImageView) view.findViewById(R.id.image_item_login);

        imageView_item1.setImageResource(R.drawable.homeitem01);
        imageView_doctor.setImageResource(R.drawable.rdoctor);
        imageView_appointment.setImageResource(R.drawable.rappointment);
        imageView_myappointment.setImageResource(R.drawable.rmyappointment);
        imageView_login.setImageResource(R.drawable.rlogin);
    }

}
