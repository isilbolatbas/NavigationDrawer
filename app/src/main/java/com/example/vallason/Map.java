package com.example.vallason;


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;



public class Map {




    private GoogleMap map;

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
        map.addMarker(options);
        options.title("Lat=" + destination.latitude + ", Long=" + destination.longitude);
        map.animateCamera(CameraUpdateFactory.newLatLng(destination));

    }

}