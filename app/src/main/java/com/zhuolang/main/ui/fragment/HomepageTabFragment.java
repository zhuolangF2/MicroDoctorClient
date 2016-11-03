package com.zhuolang.main.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.zhuolang.main.R;
import com.zhuolang.main.ui.activity.MainActivity;
import com.zhuolang.main.ui.activity.StartActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wnf on 2016/10/29.
 * 首页界面fragment
 */


public class HomepageTabFragment extends Fragment implements View.OnClickListener{


    private View view = null;
    private ImageView imageView;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,Bundle savedInstanceState){

        view=new View(getActivity());
        view = inflater.inflate(R.layout.homepage, container, false);
        Log.d("activityID", "这个是HomepageTabFragment----------:" + this.toString());
        //初始化数据
        initView(view);
        //mageView.setOnClickListener(this);
        return view;
    }
    /*
     *初始化数据
     */
    private void initView(View view) {

    }
    @Override
    public void onClick(View v) {

    }
}
