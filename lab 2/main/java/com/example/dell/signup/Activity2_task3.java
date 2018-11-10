package com.example.dell.signup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Activity2_task3 extends AppCompatActivity {
EditText message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity2_task3);
        message= findViewById(R.id.editText7);
    }
    public  void submit_btn(View v){
        String s = message.getText().toString();
        Intent in = new Intent(Activity2_task3.this,Activity1_task3.class);
        in.putExtra("message",s);
        setResult(RESULT_OK,in);
        finish();
    }
}
