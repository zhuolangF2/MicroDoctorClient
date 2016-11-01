package com.zhuolang.main.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.zhuolang.main.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wnf on 2016/10/29.
 * 首页界面fragment
 */


public class HomepageTabFragment extends Fragment{


    private View view = null;

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

        return view;

    }

    /*
     *初始化数据
     */
    private void initView(View view) {

    }

}
