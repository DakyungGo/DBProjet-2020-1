package com.example.museum;

public class EventData {

    private String event_name;
    private String event_duration;
    private String event_url;
    private String poster;
    private String location;

    public EventData(String en, String ed, String eu, String poster, String loc){
        this.event_name = en;
        this.event_duration = ed;
        this.event_url = eu;
        this.poster = poster;
        this.location = loc;
    }

    public String getEvent_name()
    {
        return this.event_name;
    }

    public String getEvent_duration()
    {
        return this.event_duration;
    }

    public String getEvent_url()
    {
        return this.event_url;
    }

    public String getPoster()
    {
        return this.poster;
    }

}
