package com.example.dell.signup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Information extends AppCompatActivity {
    TextView name,email,gender,dob;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
       name=findViewById(R.id.name);
       email=findViewById(R.id.email);
       gender=findViewById(R.id.gender);
       dob=findViewById(R.id.dob);
        Intent in=getIntent();
        String s,s2,s3,s4;
        s= in.getStringExtra("name");
        s2= in .getStringExtra("email");
        s3= in .getStringExtra("dob");
        s4= in .getStringExtra("gender");

        name.setText("Name is: "+s);
        email.setText("Email is: "+s2);
        gender.setText("Gender is: "+s4);
        dob.setText("Date of Birth is: "+s3);
    }
}
