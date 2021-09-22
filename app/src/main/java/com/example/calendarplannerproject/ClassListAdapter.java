package com.example.calendarplannerproject;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import static android.view.View.inflate;

class ClassListAdapter extends BaseAdapter {

    private final Context mContext;
    private final ArrayList<Class> classList;

    public ClassListAdapter(Context mContext, ArrayList<Class> classList) {
        this.mContext = mContext;
        this.classList = classList;
    }

    @Override
    public int getCount() {
        return classList.size();
    }

    @Override
    public Class getItem(int position) {
        return classList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View v = inflate(mContext, R.layout.items_listed, null);
        TextView name = v.findViewById(R.id.nameOnList);
        TextView dateTime = v.findViewById(R.id.date_time);

        name.setText(classList.get(position).getName());
        String date = classList.get(position).getDate() + " at " + classList.get(position).getTime();
        dateTime.setText(date);
        return v;
    }
}
