package com.zhuolang.main.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhuolang.main.R;

/**
 * Created by wnf on 2016/10/29.
 * “分享圈”界面的fragment
 */

//Fragment显示的控件，显示外部传入title的textview
public class ShareTabFragment extends Fragment{

    private String mTitle = "Default";//显示在textview上

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

        view = inflater.inflate(R.layout.share, container, false);
        Log.d("activityID", "这个是shareTabFragment----------:" + this.toString());
        return view;

    }

}
