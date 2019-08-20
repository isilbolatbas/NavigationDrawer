package com.example.vallason;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Ignore;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import androidx.room.Transaction;


import java.util.List;


@Dao
public interface DaoEvent {

    @Query("SELECT * FROM location")
   LiveData< List <Event>> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Event event);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllOrders(List <Event> order);

    @Update
    void updateDialog(Event event);



}
