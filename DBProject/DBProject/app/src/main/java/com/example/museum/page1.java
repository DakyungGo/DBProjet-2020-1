package com.example.museum;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class page1 extends AppCompatActivity {

    ImageButton btn_search, btn_saved, btn_sight;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page1);


        btn_search = (ImageButton)findViewById(R.id.btn_search);
        btn_sight = (ImageButton)findViewById(R.id.btn_sight);
        btn_saved = (ImageButton)findViewById(R.id.btn_saved);

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_search = new Intent(page1.this, searching.class);
                startActivity(intent_search);
            }
        });

        btn_sight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_sight = new Intent(page1.this, sighting.class);
                startActivity(intent_sight);
            }
        });

        btn_saved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_saved = new Intent(page1.this, saved.class);
                startActivity(intent_saved);
            }
        });

    }
}
