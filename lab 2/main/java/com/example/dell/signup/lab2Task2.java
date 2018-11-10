package com.example.dell.signup;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class lab2Task2 extends AppCompatActivity {
    EditText tv1;
    String s;
    Intent in;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab2_task2);
        tv1= findViewById(R.id.editText4);
    }
    public  void callbtn(View v){
        s = tv1.getText().toString();
        in = new Intent(Intent.ACTION_CALL);
        in.setData(Uri.parse("tel:"+s));
        startActivity(in);
    }
    public  void camerabtn(View v){
        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivity(i);
    }
    public  void contactbtn(View v){
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse("content://contacts/people/")); startActivity(i);
    }
    public  void browserbtn(View v){
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse("https://www."+s+".com"));
        startActivity(i);
    }
    public  void call_logbtn(View v){
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse("content://call_log/calls/1"));
        startActivity(i);
    }
    public  void gallerybtn(View v){
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse("content://media/external/images/media/"));
        startActivity(i);
    }
    public  void dialpadbtn(View v){
        Intent i = new Intent(Intent.ACTION_DIAL);
        i.setData(Uri.parse("tel:"+s));
        startActivity(i);
    }


}
