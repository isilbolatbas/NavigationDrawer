package com.example.vallason;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


/**
 * A simple {@link Fragment} subclass.
 */
public class MapFragment extends Fragment implements OnMapReadyCallback {

    GoogleMap map;
    FloatingActionButton fab;
    boolean isClicked = false;
    boolean isDone = false;
    Context context;


    public MapFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_map, container, false);
        fab = v.findViewById(R.id.button);

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = getContext();
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map1);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        map = googleMap;

        fab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                isClicked = true;
                if (isDone) {
                    final Dialog dialog = CustomDialog.createDialog(context);
                    dialog.show();
                }
            }

        });


        map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng destination) {
                if (isClicked) {
                    map.clear();
                    MarkerOptions options = new MarkerOptions();
                    options.position(destination);
                    options.title("Lat=" + destination.latitude + ", Long=" + destination.longitude);
                    map.addMarker(options);
                    map.animateCamera(CameraUpdateFactory.newLatLng(destination));
                    fab.setImageResource(R.drawable.ic_done_black_24dp);
                    isDone = true;
                }



            }
        });


    }


}
