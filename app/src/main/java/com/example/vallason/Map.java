package com.example.vallason;


import android.content.Context;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;



public class Map {

    private GoogleMap map;

    private EventDatabase eventDatabase;
    Context context;
    private  Marker marker;



    private static Map maps = null;

    private Map() {
    }

    public static synchronized Map getInstance() {
        if (maps == null) {
            maps = new Map();
        }
        return maps;
    }

    public void setMap(GoogleMap map) {
        this.map = map;
    }

    public void clearMap() {
        map.clear();
    }

    public void addMarker(LatLng destination){

        MarkerOptions options = new MarkerOptions();
        options.position(destination);
        this.marker = map.addMarker(options);
        options.title("Lat=" + destination.latitude + ", Long=" + destination.longitude);
        map.animateCamera(CameraUpdateFactory.newLatLng(destination));

    }

    public void  setMarker(Marker marker){
        this.marker = marker;

    }

    public Marker  getMarker(){
        return marker;
    }


}