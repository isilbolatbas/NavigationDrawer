package com.example.vallason;

import androidx.room.ColumnInfo;

public class Location {

    @ColumnInfo(name= "lng")
    private long lng;
    @ColumnInfo(name= "lat")
    private long lat;


    public long getLng() {
        return lng;
    }

    public void setLng(long lng) {
        this.lng = lng;
    }

    public long getLat() {
        return lat;
    }

    public void setLat(long lat) {
        this.lat = lat;
    }



}
