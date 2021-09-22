package com.example.calendarplannerproject;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;

import static android.view.View.*;

class EventListAdapter extends BaseAdapter {

    private final Context mContext;
    private final ArrayList<Event> eventList;

    public EventListAdapter(Context mContext, ArrayList<Event> eventList) {
        this.mContext = mContext;
        this.eventList = eventList;
    }

    @Override
    public int getCount() {
         return eventList.size();
    }

    @Override
    public Event getItem(int position) {
        return eventList.get(position);
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

        name.setText(eventList.get(position).getName());
        String date = eventList.get(position).getDate() + " at " + eventList.get(position).getTime();
        dateTime.setText(date);

        return v;
    }
}
