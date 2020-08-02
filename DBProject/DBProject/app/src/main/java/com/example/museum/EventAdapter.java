package com.example.museum;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class EventAdapter extends BaseAdapter {

    Context mContext;
    LayoutInflater mLayoutInflater = null;
    ArrayList<EventData> eventData;

    public EventAdapter(Context context, ArrayList<EventData> data){

        mContext = context;
        eventData = data;
        mLayoutInflater = LayoutInflater.from(mContext);

    }


    @Override
    public int getCount() {
        return eventData.size();
    }

    @Override
    public EventData getItem(int i) {
        return eventData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View v= mLayoutInflater.inflate(R.layout.event_view, null);

        TextView event_name = (TextView)v.findViewById(R.id.event_name);
        TextView event_duration = (TextView)v.findViewById(R.id.event_duration);
        TextView event_url = (TextView)v.findViewById(R.id.event_url);
        ImageView event_image = (ImageView)v.findViewById(R.id.event_poster);

        ImageButton to_details = (ImageButton)v.findViewById(R.id.to_details);
        to_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        event_name.setText(eventData.get(i).getEvent_name());
        event_duration.setText(eventData.get(i).getEvent_duration());
        event_url.setText(eventData.get(i).getEvent_url());
        String poster_url = eventData.get(i).getPoster();
        Glide.with(v).load(poster_url).into(event_image);

        //Glide: URL로 이미지 가져오기
        // ImageView ivImage;
        // ivImage = (ImageView)findViewById(R.id.imageview);
        // String imageurl = ""
        // Glide.with(this).load(imageurl).into(ivImage);

        return v;
    }
}
