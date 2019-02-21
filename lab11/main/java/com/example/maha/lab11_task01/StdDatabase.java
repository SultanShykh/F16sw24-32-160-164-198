package com.example.maha.lab11_task01;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {Student.class}, version = 1)
public abstract class StdDatabase extends RoomDatabase {
    public abstract StudentDao stdDao(); // Dao object for student table
    public static StdDatabase INSTANCE; // static instance of database
    public static StdDatabase getStdDb(Context context) { // method that will return database instance
        if (INSTANCE == null) { // create instance
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    StdDatabase.class,
                    "student")
                    .build();
        }
        return INSTANCE;
    }
}