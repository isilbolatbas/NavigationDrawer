package com.example.vallason;

import android.os.Bundle;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.util.Log;
import android.view.MenuItem;
import com.google.android.material.navigation.NavigationView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;
import android.view.Menu;
import android.view.View;
import android.widget.ExpandableListView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    private List <Event> events = new ArrayList<>();
//    private RecyclerView recyclerView;
//    private RecyclerViewAdapter mAdapter;

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    String listDataChild;
    EventDatabase eventDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        events.add(new Event("hjgjh","kjhjk", "mjhjhjk"));
//        events.add(new Event("hjgdvjh","kjhvxcjk", "mjvxchjhjk"));
//        events.add(new Event("hjgjxcvxh","kvxcvjhjk", "mvxcjhjhjk"));
//        events.add(new Event("hjgxvjh","kjhvxjk", "mjxcvhjhjk"));
//        eventDatabase = EventDatabase.getDatabase(this);
//        eventDatabase.daoEvent().insert();
//
//        eventDatabase = EventDatabase.getInstance(getApplicationContext());

//        recyclerView = (RecyclerView) findViewById(R.id.list);
//        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
//        recyclerView.setLayoutManager(mLayoutManager);



        eventDatabase = EventDatabase.getDatabase(getApplicationContext());
        expListView = (ExpandableListView) findViewById(R.id.lvExp);



        EventDatabase.getDatabase(this).daoEvent().getAll().observe(this, new Observer<List<Event>>() {
                    @Override
                    public void onChanged(List<Event> all) {
                        if(all != null){

                            events.clear();
                            events.addAll(all);
//                            mAdapter = new RecyclerViewAdapter(events);
//                            recyclerView.setAdapter(mAdapter);
//                            mAdapter.notifyDataSetChanged();

                            listAdapter = new ExpandableListAdapter(MainActivity.this, events, listDataChild);
                            final int[] prevExpandPosition = {-1};
                            expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
                                @Override
                                public void onGroupExpand(int groupPosition) {
                                    if (prevExpandPosition[0] >= 0 && prevExpandPosition[0] != groupPosition) {
                                        expListView.collapseGroup(prevExpandPosition[0]);
                                    }
                                    prevExpandPosition[0] = groupPosition;
                                }
                            });



                            expListView.setAdapter(listAdapter);
                            listAdapter.notifyDataSetChanged();


                        }

                    }
                });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        MapFragment mapFragment = new MapFragment();
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.mainLayout, mapFragment).commit();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);

        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;

    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }




}
