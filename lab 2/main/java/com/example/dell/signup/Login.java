package com.example.dell.signup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {
EditText ed1,ed2;
    TextView tv1;
String s1,s2,s3,name,email,password,dob,gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Intent in = getIntent();
        name = in.getStringExtra("username");
        email = in.getStringExtra("email");
        password = in.getStringExtra("password");
        dob=in.getStringExtra("dob");
        gender=in.getStringExtra("gender");

        ed1 =  findViewById(R.id.editText2);
        ed2 =  findViewById(R.id.editText6);

    }

    public void btnlogin(View v){

        s1 = ed1.getText().toString();
        s2= ed2.getText().toString();

        if(s1.equals(email) && s2.equals(password)) {
            Intent in = new Intent(Login.this, Information.class);
            in.putExtra("name", name);
            in.putExtra("email", email);
            in.putExtra("dob",dob);
            in.putExtra("gender",gender);
            startActivity(in);
        }
        else{
            Toast.makeText(getApplicationContext(),"problem is here in data transfering",Toast.LENGTH_LONG).show();
        }
    }
}
