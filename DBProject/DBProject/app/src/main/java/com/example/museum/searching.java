package com.example.museum;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class searching extends AppCompatActivity {

    ArrayList<RelicData> relicDataList;

    ImageButton search;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searching);

        this.InitializeRelicData();

        final ListView relic_listview = (ListView)findViewById(R.id.relic_found);
        final RelicAdapter relicAdapter = new RelicAdapter(this, relicDataList);
        relic_listview.setAdapter(relicAdapter);

        search = (ImageButton)findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                relic_listview.setVisibility(View.VISIBLE);
            }
        });

    }


    public void InitializeRelicData()
    {
        relicDataList = new ArrayList<RelicData>();

        //relicDataList.add(new RelicData(,,,,));


    }
}
