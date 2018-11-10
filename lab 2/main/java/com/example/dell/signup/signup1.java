package com.example.dell.signup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class signup1 extends AppCompatActivity {
    String email1,pass,nam,dob,gender1;
    EditText name;
    EditText email;
    EditText password;
    EditText dobs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup1);

         name=findViewById(R.id.editText);
        email =  findViewById(R.id.editText5);
        password=  findViewById(R.id.editText3);
        dobs=findViewById(R.id.dobs);

        Button sign = findViewById(R.id.button4);
        RadioGroup gender = (RadioGroup) findViewById(R.id.radiogroup);



        gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton male = (RadioButton) findViewById(R.id.radioButton2);
                RadioButton female = (RadioButton) findViewById(R.id.radioButton3);

                if(male.isChecked()){
                    gender1 ="Male";

                } else if (female.isChecked()){
                    gender1 ="Female";
                }
            }
        });
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                nam=name.getText().toString();
                email1 =email.getText().toString();
                pass=password.getText().toString();
                dob=dobs.getText().toString();
                Intent in = new Intent(signup1.this,Login.class);

                in.putExtra("username",nam);
                in.putExtra("email",email1);
                in.putExtra("password",pass);
                in.putExtra("gender",gender1);
                in.putExtra("dob",dob);
                startActivity(in);
            }
        });

        }
}
