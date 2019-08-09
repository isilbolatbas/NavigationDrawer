package com.example.vallason;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.view.GravityCompat;

import java.util.List;

import static com.example.vallason.MainActivity.closeDrawer;

public class ExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<Event> events;
    boolean isClicked = false;
    boolean isDone = false;
    EventDatabase eventDatabase;

    public ExpandableListAdapter(Context context, List<Event> events,  String details ) {
        this.context = context;
        this.events = events;

    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return "";
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_item, null);
        }

        final TextView txtListChild = (TextView) convertView.findViewById(R.id.lblListItem);


        txtListChild.setText(events.get(groupPosition).getDetails());


        Button detail = (Button) convertView.findViewById(R.id.detail);
        Button location = (Button) convertView.findViewById(R.id.locaiton);

        detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                closeDrawer();

                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.detail_custom_dialog);

                final Button update = (Button) dialog.findViewById(R.id.update);
                final Button cancel = (Button) dialog.findViewById(R.id.cancel);
                final EditText eventEdit = (EditText) dialog.findViewById(R.id.eventEdit);
                final EditText detailEdit = (EditText) dialog.findViewById(R.id.detailEdit);
                final Spinner spinner = (Spinner) dialog.findViewById(R.id.action_bar_spinner);

//                holder.type.setText(Event.EventType.getType(ev.getType()));

                detailEdit.setText(events.get(groupPosition).getDetails());
                eventEdit.setText(events.get(groupPosition).getEvent());
                spinner.setSelection(events.get(groupPosition).getType());

//
//         if( Event.EventType.getType(ev.getType())=="secim yapiniz"){
//
//             spinner.setSelection(0);
//
//         }
//                if( Event.EventType.getType(ev.getType())=="concert"){
//
//                    spinner.setSelection(1);
//
//                }
//                if( Event.EventType.getType(ev.getType())=="sport"){
//
//                    spinner.setSelection(2);
//
//                }  if( Event.EventType.getType(ev.getType())=="meeting"){
//
//                    spinner.setSelection(3);
//
//                }  if( Event.EventType.getType(ev.getType())=="other"){
//
//                    spinner.setSelection(4);
//
//                }



//                for(int i = 0; i  < 5; i++) {
//
//
//
//                        Event.EventType.getType(ev.getType());
//                        spinner.setSelection(i);
//                }




                eventDatabase = EventDatabase.getDatabase(context);
                dialog.show();

                update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        if(eventEdit.getText().toString().isEmpty()){
                            Toast.makeText(context, "event bos olamaz", Toast.LENGTH_SHORT).show();
                            return;

                        }
                        else if(detailEdit.getText().toString().isEmpty()){
                            Toast.makeText(context, "event bos olamaz", Toast.LENGTH_SHORT).show();
                            return;

                        }
                        else if(spinner.getSelectedItemPosition()== 0){
                            Toast.makeText(context, "type secmeye ihtiyacın var", Toast.LENGTH_SHORT).show();

                        } else {
//                            Event event = new Event(eventEdit.getText().toString(), detailEdit.getText().toString(), spinner.getSelectedItemPosition());
                            events.get(groupPosition).setEvent(eventEdit.getText().toString());
                            events.get(groupPosition).setDetails(detailEdit.getText().toString());
                            events.get(groupPosition).setType( spinner.getSelectedItemPosition());
                            EventDatabase.getDatabase(context).daoEvent().updateDialog(events.get(groupPosition));
                            dialog.dismiss();
                        }

                    }



                });

                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

            }
        });


        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {





            }
        });

        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Event getGroup(int groupPosition) {
        return this.events.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this.events.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_group, null);
        }

        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.lblListHeader);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(events.get(groupPosition).getEvent());

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}