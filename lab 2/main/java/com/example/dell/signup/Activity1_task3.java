package com.example.dell.signup;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Activity1_task3 extends AppCompatActivity {
TextView def_message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity1_task3);
        def_message = findViewById(R.id.def_message);

    }
    public void btn_message(View v){
        Intent in = new Intent(Activity1_task3.this,Activity2_task3.class);
        startActivityForResult(in,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1){
            if(resultCode==RESULT_OK){
                String result=data.getStringExtra("message");
                def_message.setText(result);
            }
        }
    }
}
