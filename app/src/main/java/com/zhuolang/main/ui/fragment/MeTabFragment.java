package com.zhuolang.main.ui.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.preference.DialogPreference;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.zhuolang.main.R;
import com.zhuolang.main.ui.activity.ShowmeInfoActivity;

/**
 * Created by wnf on 2016/10/29.
 * “我”界面的fragment
 */


public class MeTabFragment extends Fragment implements View.OnClickListener{


    private ImageView imageView=null;
    private LinearLayout ll_finish;


    private View view = null;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState){

        view=new View(getActivity());

        view = inflater.inflate(R.layout.me, container, false);
        Log.d("activityID", "这个是meTabFragment----------:" + this.toString());

        initView(view);

        return view;

    }

    private void initView(View view) {
        imageView=(ImageView)view.findViewById(R.id.image_me_mineinfo);
        ll_finish= (LinearLayout) view.findViewById(R.id.me_ll_finish);
        ll_finish.setOnClickListener(this);
        imageView.setOnClickListener(this);
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
    private void clickImage(View v){
        switch (v.getId()){
            case R.id.image_me_mineinfo:
                Intent intent = new Intent();
                intent.setClass(getActivity(), ShowmeInfoActivity.class);
                startActivity(intent);
                break;
            case R.id.me_ll_finish:
                AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
                dialog.setTitle("温馨提示");
                dialog.setMessage("是否结束体验，退出程序？");
                dialog.setCancelable(false);
                dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getActivity().finish();
                    }
                });
                dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                dialog.show();
                break;
            default:
                break;

        }
    }
}
