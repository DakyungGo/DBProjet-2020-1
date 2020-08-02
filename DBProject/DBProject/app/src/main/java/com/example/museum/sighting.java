package com.example.museum;

import android.app.usage.UsageEvents;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Debug;
import android.util.EventLog;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class sighting extends AppCompatActivity {

    Spinner museumspin;
    Spinner itemspin;
    String selected_museum, selected_item;

    ArrayList<RelicData> relicDataList;
    ArrayList<EventData> eventDataList;
    ArrayList<BookData> bookDataList;


    ImageButton startsight, gotowebsite;
    TextView m_loc, m_con, m_nor;
    String m_url;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sighting);

        startsight = (ImageButton)findViewById(R.id.start_sight);
        gotowebsite = (ImageButton)findViewById(R.id.gotoWebsite);

        m_loc = (TextView)findViewById(R.id.tv_museum_location);
        m_con = (TextView)findViewById(R.id.tv_museum_contact);
        m_nor = (TextView)findViewById(R.id.tv_museum_nor);
        /////////////////////////

        /////List View////////
        this.InitializeRelicData();
        this.InitializeEventData();
        this.InitializeBookData();

        final ListView relic_listview = (ListView)findViewById(R.id.relic_listview);
        final RelicAdapter relicAdapter = new RelicAdapter(this, relicDataList);
        relic_listview.setAdapter(relicAdapter);

        final ListView event_listview = (ListView)findViewById(R.id.event_listview);
        final EventAdapter eventAdapter = new EventAdapter(this, eventDataList);
        event_listview.setAdapter(eventAdapter);

        final ListView book_listview = (ListView)findViewById(R.id.book_listview);
        final BookAdapter bookAdapter = new BookAdapter(this, bookDataList);
        book_listview.setAdapter(bookAdapter);

        museumspin = (Spinner)findViewById(R.id.select_museum);
        museumspin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selected_museum = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                selected_museum = adapterView.getItemAtPosition(0).toString();
            }
        });

        itemspin = (Spinner)findViewById(R.id.select_itemtype);
        itemspin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selected_item = adapterView.getItemAtPosition(i).toString();
                Log.d("item", selected_item);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        startsight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new JSONTask_museum().execute("http://172.30.1.54:3000/museums?name="+selected_museum);

                if(selected_item.equals("유물")){
                    Log.d("item","유물");
                    relic_listview.setVisibility(View.VISIBLE);
                    event_listview.setVisibility(View.GONE);
                    book_listview.setVisibility(View.GONE);

                   //new JSONTask_relic().execute("http://192.168.219.113:3000/relics?museum_name="+selected_museum);
                }
                else if(selected_item.equals("이벤트")){

                    Log.d("item","이벤트");

                    relic_listview.setVisibility(View.GONE);
                    event_listview.setVisibility(View.VISIBLE);
                    book_listview.setVisibility(View.GONE);
                    //new JSONTask().execute("http://192.168.219.113:3000/events?museum_name="+selected_museum);
                }
                else if(selected_item.equals("출판물")){
                    Log.d("item","출판물");

                    relic_listview.setVisibility(View.GONE);
                    event_listview.setVisibility(View.GONE);
                    book_listview.setVisibility(View.VISIBLE);
                    //new JSONTask().execute("http://192.168.219.113:3000/books?museum_name="+selected_museum);
                }

            }
        });

        gotowebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse(m_url));
                startActivity(intent);
            }
        });

        m_nor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("dddddddd","동작");
                relic_listview.setVisibility(View.VISIBLE);
                event_listview.setVisibility(View.GONE);
                book_listview.setVisibility(View.GONE);
                //new JSONTask_relic().execute("http://192.168.219.113:3000/relics?museum_name="+selected_museum);
            }
        });

    }


    public void InitializeRelicData()
    {
        relicDataList = new ArrayList<RelicData>();

        //relicDataList.add(new RelicData("번호","이름","시대","출토지","설명"));



    }

    public void InitializeBookData()
    {
        bookDataList = new ArrayList<BookData>();

        //bookDataList.add(new RelicData(,,,,));
        //https://gongju.museum.go.kr/_prog/download/?func_gbn_cd=book&atch=book_img&mng_no=141608&site_dvs_cd=gongju
    }

    public void InitializeEventData()
    {
        eventDataList = new ArrayList<EventData>();

        //eventDataList.add(new EventData(,,,,));

    }


    ///JSON
    public class JSONTask_museum extends AsyncTask<String, String, String>{

        @Override
        protected String doInBackground(String... params) {

            try{
                JSONObject jsonObject = new JSONObject();
                //jsonObject.accumulate("status","true");
                jsonObject.accumulate("data","museum");

                HttpURLConnection Conn = null;
                BufferedReader reader = null;

                try{
                    String url = params[0];
                    //String Body = params[1];

                    URL URLObject = new URL(url);   //url 가져오기
                    Conn = (HttpURLConnection)URLObject.openConnection();
                    Conn.connect(); //연결 수행

                    InputStream stream = Conn.getInputStream();
                    reader = new BufferedReader(new InputStreamReader(stream));
                    StringBuffer buffer = new StringBuffer();   //실제 데이터를 받는 곳
                    String line = "";

                    while((line = reader.readLine()) != null){
                        buffer.append(line);
                    }
                    Log.d("buffer",buffer.toString());

                    return buffer.toString();
                } catch (MalformedURLException e){
                    e.printStackTrace();
                } catch (IOException e){
                    e.printStackTrace();
                } finally {
                    if(Conn != null){
                        Conn.disconnect();
                    }
                    try{
                        if(reader != null){
                            reader.close();
                        }
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }

            } catch(Exception e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            //Log.d("result",result);
            jsonParsing_museum(result);
        }
    }

    public class JSONTask_relic extends AsyncTask<String, String, String>{

        @Override
        protected String doInBackground(String... params) {

            try{
                JSONObject jsonObject = new JSONObject();
                //jsonObject.accumulate("status","true");
                jsonObject.accumulate("data","relic");

                HttpURLConnection Conn = null;
                BufferedReader reader = null;

                try{
                    String url = params[0];
                    //String Body = params[1];

                    URL URLObject = new URL(url);   //url 가져오기
                    Conn = (HttpURLConnection)URLObject.openConnection();
                    Conn.connect(); //연결 수행

                    InputStream stream = Conn.getInputStream();
                    reader = new BufferedReader(new InputStreamReader(stream));
                    StringBuffer buffer = new StringBuffer();   //실제 데이터를 받는 곳
                    String line = "";

                    while((line = reader.readLine()) != null){
                        buffer.append(line);
                    }
                    Log.d("buffer",buffer.toString());

                    return buffer.toString();
                } catch (MalformedURLException e){
                    e.printStackTrace();
                } catch (IOException e){
                    e.printStackTrace();
                } finally {
                    if(Conn != null){
                        Conn.disconnect();
                    }
                    try{
                        if(reader != null){
                            reader.close();
                        }
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }

            } catch(Exception e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            jsonParsing_relic(result);
        }
    }

    private void jsonParsing_museum(String json){

        try{
            JSONObject jsonObject = new JSONObject(json);
            JSONArray museumArray = jsonObject.getJSONArray("data");

            for(int i=0; i<museumArray.length(); i++){
                JSONObject museumObject = museumArray.getJSONObject(i);

                String s1 = museumObject.getString("name");
                String s2 = museumObject.getString("location");
                String s3 = museumObject.getString("num_of_relics");
                String s4 = museumObject.getString("contact");
                String s5 = museumObject.getString("website");
                m_loc.setText("위치: " + s2);
                m_con.setText("연락처: "+s4);
                m_nor.setText("보유 유물 수: "+ s3);
                m_url = s5;

            }

        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    private void jsonParsing_relic(String json){

        try{
            JSONObject jsonObject = new JSONObject(json);
            JSONArray relicArray = jsonObject.getJSONArray("data");

            for(int i=0; i<relicArray.length(); i++){
                JSONObject relicObject = relicArray.getJSONObject(i);

                String s1 = relicObject.getString("relic_id");
                String s2 = relicObject.getString("museum_name");
                String s3 = relicObject.getString("relic_name");
                String s4 = relicObject.getString("era");
                String s5 = relicObject.getString("location_found");
                String s6 = relicObject.getString("detail");

                Log.d("relics",  s1+"\t"+s3+"\t" +s4+"\t"+ s5 + "\t" + s6);


                relicDataList.add(new RelicData(s1,s3,s4,s5,s6));

            }
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

}
