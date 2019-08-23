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

    @Query("SELECT * FROM register WHERE username=:username and password= :password")
    LoginEvent control(String username,String password);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Event event);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllOrders(List <Event> order);

    @Update
    void updateDialog(Event event);

    @Insert()
    void insertRegister(LoginEvent loginEvents);


 @Query("UPDATE location SET locationlng = :lng , locationlat = :lat  WHERE id LIKE :id")
 int updateLocation( int id , double lng ,double lat);

}
