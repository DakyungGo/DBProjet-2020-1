package com.example.museum;

public class RelicData {

    private String relic_index;
    private String relic_name;
    private String relic_era;
    private String location_found;
    private String relic_explanation;

    public RelicData(String relic_index, String relic_name, String relic_era, String location_found, String relic_explanation)
    {
        this.relic_index = relic_index;
        this.relic_name = relic_name;
        this.relic_era = relic_era;
        this.location_found = location_found;
        this.relic_explanation = relic_explanation;

    }

    public String getRelic_index() {
        return this.relic_index;
    }

    public String getRelic_name() {
        return this.relic_name;
    }

    public String getRelic_era() {
        return this.relic_era;
    }

    public String getLocation_found() {
        return this.location_found;
    }

    public String getRelic_explanation(){
        return this.relic_explanation;
    }
}
