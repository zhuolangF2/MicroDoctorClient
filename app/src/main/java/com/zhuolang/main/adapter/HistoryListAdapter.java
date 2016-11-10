package com.zhuolang.main.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.zhuolang.main.R;
import com.zhuolang.main.model.AppointmentDto;
import com.zhuolang.main.utils.TimeUtil;

import java.util.Date;
import java.util.List;


/**
 * Created by hzg on 2016/11/9.
 */
public class HistoryListAdapter extends BaseAdapter {
    private Context context;
    private List<AppointmentDto> appointmentDtos;

    private LayoutInflater inflater;
    private ViewHolder holder;

    public HistoryListAdapter(Context context, List<AppointmentDto> appointmentDtos) {
        this.context = context;
        this.appointmentDtos = appointmentDtos;

        inflater = LayoutInflater.from(context);
        holder = new ViewHolder();
    }


    @Override
    public int getCount() {
        if (appointmentDtos == null) {
            return 0;
        } else {
            return appointmentDtos.size();
        }
    }

    @Override
    public Object getItem(int position) {
        return appointmentDtos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    //先用myappoint的子布局来用用，如果不同到时候再新建一个
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_my_appoint, null);
            holder.doctor_name = (TextView) convertView.findViewById(R.id.tv_item_my_appoint_doctor_name);
            holder.disease = (TextView) convertView.findViewById(R.id.tv_item_my_appoint_disease);
            holder.time = (TextView) convertView.findViewById(R.id.tv_item_my_apponint_time);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.doctor_name.setText("" + appointmentDtos.get(position).getDoctor_name());
        holder.disease.setText(appointmentDtos.get(position).getDisease());
        Date date = TimeUtil.longToDateNoTime(appointmentDtos.get(position).getSeeTime());
        holder.time.setText(TimeUtil.dateToStrNoTime(date));
        return convertView;
    }

    public class ViewHolder {
        TextView doctor_name;
        TextView disease;
        TextView time;

    }

}
