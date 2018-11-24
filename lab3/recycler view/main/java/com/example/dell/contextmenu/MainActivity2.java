package com.example.dell.contextmenu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class MainActivity2 extends AppCompatActivity {
RecyclerView Recycler_view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Recycler_view = findViewById(R.id.Recycler_view);
        Recycler_view.setLayoutManager(new LinearLayoutManager(this));
        String Students[]={"Ahmed","sultan","Aqib","Adeel","Zaka","Haseeb","Yasir","Maryam","Asra","Alishba","Haris","Waqar","Fiza","Musaib","Raven","Sami","Sarmad","Ali","Sarang","Jawad"};
        Recycler_view.setAdapter(new Recycler_vew_Adapter(Students));

    }
}
