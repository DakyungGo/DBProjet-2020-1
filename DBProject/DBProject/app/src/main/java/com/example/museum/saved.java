package com.example.museum;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class saved extends AppCompatActivity {

    ArrayList<EventData> eventDataList;
    ArrayList<BookData> bookDataList;

    LinearLayout list_layout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved);

        list_layout = (LinearLayout)findViewById(R.id.list_layout);

        this.InitializeEventData();
        this.InitializeBookData();

        final ListView event_listview = (ListView)findViewById(R.id.saved_event_list);
        final EventAdapter eventAdapter = new EventAdapter(this, eventDataList);
        event_listview.setAdapter(eventAdapter);

        final ListView book_listview = (ListView)findViewById(R.id.saved_book_list);
        final BookAdapter bookAdapter = new BookAdapter(this, bookDataList);
        book_listview.setAdapter(bookAdapter);

        list_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                event_listview.setVisibility(View.VISIBLE);
                book_listview.setVisibility(View.VISIBLE);
            }
        });

        list_layout.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View view) {

                String url = "https://jeonju.museum.go.kr/event.es?mid=a10403000000&seq=1741&act=view";

                Log.d("url_details",url);
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse(url));
                startActivity(intent);
                return false;
            }
        });

        event_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String url = "https://jeonju.museum.go.kr/event.es?mid=a10403000000&seq=1741&act=view";

                Log.d("url_details",url);
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });



    }

    public void InitializeBookData()
    {
        bookDataList = new ArrayList<BookData>();

        //bookDataList.add(new RelicData(,,,,));

    }

    public void InitializeEventData()
    {
        eventDataList = new ArrayList<EventData>();

        //eventDataList.add(new EventData(,,,,));


    }


}
