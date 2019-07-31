package com.example.vallason;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>{

    private List<Event> events;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView event;
        public TextView type;

        public MyViewHolder(View v) {
            super(v);
            event = v.findViewById(R.id.event);
            type = v.findViewById(R.id.type);
        }

    }

    public RecyclerViewAdapter(List<Event> events) {

        this.events=events;
    }


    @Override
    public RecyclerViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view, parent, false);

       return new MyViewHolder(v);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Event ev = events.get(position);
        holder.event.setText(ev.getEvent());
        holder.type.setText(Event.EventType.getType(ev.getType()));


//
//        MyViewHolder post = re(position);
//        holder.bind(post);

    }

    @Override
    public int getItemCount() {
        return events.size();
    }
}
