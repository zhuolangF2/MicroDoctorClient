package com.zhuolang.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by wnf on 2016/10/29.
 * “我”界面的fragment
 */


public class MeTabFragment extends Fragment{

    private String mTitle = "Default";//显示在textview上
    private ImageView imageView=null;
    //    private Layout layout;
    public static final String TITLE = "title";
    private View view = null;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState){
        if (getArguments() != null){
            mTitle = getArguments().getString(TITLE);//设置title显示在textView上
        }
        view=new View(getActivity());

        view = inflater.inflate(R.layout.me, container, false);
        Log.d("activityID", "这个是meTabFragment----------:" + this.toString());
        return view;

    }

}
