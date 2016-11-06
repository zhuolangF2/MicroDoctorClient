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
import android.widget.TextView;

import com.google.gson.Gson;
import com.zhuolang.main.R;
import com.zhuolang.main.common.APPConfig;
import com.zhuolang.main.model.DoctorDto;
import com.zhuolang.main.ui.activity.LoginActivity;
import com.zhuolang.main.ui.activity.MainActivity;
import com.zhuolang.main.ui.activity.SettingActivity;
import com.zhuolang.main.ui.activity.ShowDotInfoActivity;
import com.zhuolang.main.ui.activity.UpdateDotInfoActivity;
import com.zhuolang.main.utils.SharedPrefsUtil;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by wnf on 2016/10/29.
 * “我”界面的fragment
 */


public class MeTabFragment extends Fragment implements View.OnClickListener{

    private String userDataStr="";
    private DoctorDto userInfo=null;
    private ImageView imageView=null;
    private LinearLayout ll_finish;
    private LinearLayout ll_setting;
    private LinearLayout ll_test;
    private LinearLayout ll_logout;
    private TextView tv_me;
    private View view = null;
    Gson gson=new Gson();
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
        userDataStr=SharedPrefsUtil.getValue(getActivity(), APPConfig.USERDATA, "");
        userInfo=gson.fromJson(userDataStr, DoctorDto.class);
        imageView=(ImageView)view.findViewById(R.id.image_me_mineinfo);
        ll_finish= (LinearLayout) view.findViewById(R.id.me_ll_finish);
        ll_setting= (LinearLayout) view.findViewById(R.id.ll_me_setting);
        ll_test= (LinearLayout) view.findViewById(R.id.me_test);
        ll_logout= (LinearLayout) view.findViewById(R.id.me_ll_logout);
        tv_me=(TextView)view.findViewById(R.id.me_tv_me);
        tv_me.setText(userInfo.getNickname());
        ll_logout.setOnClickListener(this);
        ll_test.setOnClickListener(this);
        ll_finish.setOnClickListener(this);
        ll_setting.setOnClickListener(this);
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
                intent.setClass(getActivity(), ShowDotInfoActivity.class);
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
            case R.id.ll_me_setting:
                Intent intentSet = new Intent();
                intentSet.setClass(getActivity(), SettingActivity.class);
                startActivity(intentSet);
                break;
            case R.id.me_ll_logout:
                AlertDialog.Builder dialog1 = new AlertDialog.Builder(getActivity());
                dialog1.setTitle("温馨提示");
                dialog1.setMessage("是否注销切换账号？");
                dialog1.setCancelable(false);
                dialog1.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPrefsUtil.putValue(getActivity(),APPConfig.IS_LOGIN,false);
                        Intent intent2 = new Intent();
                        intent2.setClass(getActivity(), LoginActivity.class);
                        startActivity(intent2);
                        getActivity().finish();
                    }
                });
                dialog1.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                dialog1.show();
                break;

            default:
                break;

        }
    }
}
