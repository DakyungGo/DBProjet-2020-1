package com.example.museum;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class RelicAdapter extends BaseAdapter {

    Context mContext = null;
    LayoutInflater mLayoutInflater = null;
    ArrayList<RelicData> relicData;

    public RelicAdapter(Context context, ArrayList<RelicData> data)
    {
        mContext = context;
        relicData = data;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return relicData.size();
    }

    @Override
    public RelicData getItem(int i) {
        return relicData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v= mLayoutInflater.inflate(R.layout.relic_view, null);

        TextView relic_index = (TextView)v.findViewById(R.id.relic_index);
        TextView relic_name = (TextView)v.findViewById(R.id.relic_name);
        TextView relic_era = (TextView)v.findViewById(R.id.relic_era);
        TextView relic_location_found = (TextView)v.findViewById(R.id.relic_location_found);
        TextView relic_explanation = (TextView)v.findViewById(R.id.relic_explanation);

        relic_index.setText(relicData.get(i).getRelic_index());
        relic_name.setText(relicData.get(i).getRelic_name());
        relic_era.setText(relicData.get(i).getRelic_era());
        relic_location_found.setText(relicData.get(i).getLocation_found());
        relic_explanation.setText(relicData.get(i).getRelic_explanation());

        return v;
    }


}
