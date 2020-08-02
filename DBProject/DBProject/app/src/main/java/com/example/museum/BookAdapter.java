package com.example.museum;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class BookAdapter extends BaseAdapter {

    Context mContext = null;
    LayoutInflater mLayoutInflater = null;
    ArrayList<BookData> bookData;

    public BookAdapter(Context context, ArrayList<BookData> data){

        mContext = context;
        bookData = data;
        mLayoutInflater = LayoutInflater.from(mContext);

    }


    @Override
    public int getCount() {
        return bookData.size();
    }

    @Override
    public BookData getItem(int i) {
        return bookData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View v= mLayoutInflater.inflate(R.layout.book_view, null);

        TextView book_name = (TextView)v.findViewById(R.id.book_name);
        TextView book_year = (TextView)v.findViewById(R.id.book_year);
        TextView book_url = (TextView)v.findViewById(R.id.book_url);
        ImageView book_image = (ImageView)v.findViewById(R.id.book_poster);

        book_name.setText(bookData.get(i).getBook_name());
        book_year.setText(bookData.get(i).getPublished_year());
        book_url.setText(bookData.get(i).getBook_url());
        String poster_url = bookData.get(i).getBook_image();
        Glide.with(v).load(poster_url).into(book_image);

        //Glide: URL로 이미지 가져오기
        // ImageView ivImage;
        // ivImage = (ImageView)findViewById(R.id.imageview);
        // String imageurl = ""
        // Glide.with(this).load(imageurl).into(ivImage);

        return v;

    }
}
