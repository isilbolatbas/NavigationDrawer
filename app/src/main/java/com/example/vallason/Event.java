package com.example.vallason;


import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "location")
public class Event implements Serializable {
    public Event(String event, String details, int type) {
        this.event = event;
        this.details = details;
        this.type = type;
    }

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name= "event")
    private String event;

    @ColumnInfo(name= "details")
    private String details;

    @ColumnInfo(name= "type")
    private int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Embedded(prefix = "location")
    Location location;

    public int getId() {
        return id;
    }



    public void setId(int id) {
        this.id = id;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }



    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public enum EventType {
        CONCERT("Concert"), SPORT("Sport"), MEETING("Meeting"), OTHER("Other");

        private String type;

        EventType(String type) {
            this.type = type;
        }

        public static String getType(int type) {
            for(EventType t : EventType.values()) {
                if (t.ordinal() == type)
                    return t.type;
            }
            return "";
        }
//        public static int getType(String type) {
////            for(EventType t : EventType.values()) {
////                if (t.type.equals(type))
////                    return t.ordinal();
////            }
////            return -1;
////        }


    }
}
