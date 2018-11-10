package com.example.dell.signup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Button signup=findViewById(R.id.button);
        Button task2 = findViewById(R.id.button2);
        Button basicinfo=findViewById(R.id.button6);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(SignUp.this,signup1.class);
                startActivity(in);
            }
        });

        task2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in =new Intent(SignUp.this,lab2Task2.class);
                startActivity(in);
            }
        });

        basicinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(SignUp.this,BasicInfo.class);
                startActivity(in);

            }
        });

    }
    public void task3(View v){
        Intent in = new Intent(SignUp.this,Activity1_task3.class);
        startActivity(in);
    }

}
