package com.example.rodri.irabus2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.rodri.irabus2.R;
import com.example.rodri.irabus2.helpers.Utils;
import com.example.rodri.irabus2.model.CityPeriodSchedule;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rrosatti on 9/9/17.
 */

public class CityPeriodScheduleAdapter extends BaseAdapter {

    private Context context;
    private List<CityPeriodSchedule> list = new ArrayList<>();
    private LayoutInflater inflater = null;
    private Utils utils = new Utils();

    public CityPeriodScheduleAdapter(Context context, List<CityPeriodSchedule> list) {
        this.context = context;
        this.list = list;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public CityPeriodSchedule getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return getItem(i).getId();
    }

    public class ViewHolder {
        TextView txtTime;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = new ViewHolder();
        View v = view;
        if (view == null) {
            v = inflater.inflate(R.layout.city_period_schedule_item, null);

            holder.txtTime = (TextView) v.findViewById(R.id.txtTime);

            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }

        holder.txtTime.setText(utils.convertFromMillisecondsToStringTime(getItem(i).getTime()));

        return  v;
    }
}
