package com.example.maha.lab11_task01;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class Student {
    @PrimaryKey
    @NonNull
    private String rollno;
    @ColumnInfo(name = "name")
    @NonNull
    private String name;

    // Getters and Setters
    public String getRollno() {
        return rollno;
    }

    public void setRollno(String rollno) {
        this.rollno = rollno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}