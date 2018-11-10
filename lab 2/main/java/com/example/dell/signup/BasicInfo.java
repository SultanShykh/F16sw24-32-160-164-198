package com.example.dell.signup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class BasicInfo extends AppCompatActivity {
    int count=0;
    String[] name=new String[]{
            "Sultan",
            "Maryam",
            "Alishba",
            "Asra",
            "Yasir"
    };
    String[] rollno=new String[]{
            "F16sw24",
            "F16sw32",
            "F16sw160",
            "F16sw164",
            "F16sw198"
    };
    String[] email=new String[]{
            "m.shykh@outlook.com",
            "MaryamAnwar@gmail.com",
            "Alishbaikhlaq@gmail.com",
            "AsraMumtaz@gmail.com",
            "YasirQurshi@gmail.com"
    };
    String[] DOB=new String[]{
            "15-07-1998",
            "28-11-1998",
            "18-08-1998",
            "02-09-1998",
            "20-05-1996"
    };
    int []image = new int[]{
            R.drawable.sultan1,
            R.drawable.maryam,
            R.drawable.asra,
            R.drawable.alice,
            R.drawable.yasir
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_info);
        TextView tvname = (TextView) findViewById(R.id.textView10);
        TextView tvrollno= (TextView)findViewById(R.id.textView11);
        TextView tvemail= (TextView)findViewById(R.id.textView12);
        TextView tvdob= (TextView)findViewById(R.id.textView13);

        final TextView name1= (TextView)findViewById(R.id.textView14);
        final TextView rollno1= (TextView)findViewById(R.id.textView15);
        final  TextView email1= (TextView)findViewById(R.id.textView16);
        final TextView dob1= (TextView)findViewById(R.id.textView17);

        final ImageView img1= (ImageView) findViewById(R.id.imageView);

        Button prev= (Button) findViewById(R.id.button7);
        Button nex= (Button) findViewById(R.id.button8);

        name1.setText(name[count]);
        rollno1.setText(rollno[count]);
        email1.setText(email[count]);
        dob1.setText(DOB[count]);
        img1.setImageResource(image[count]);

        nex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count < 4) {
                    count++;
                    name1.setText(name[count]);
                    rollno1.setText(rollno[count]);
                    email1.setText(email[count]);
                    dob1.setText(DOB[count]);
                    img1.setImageResource(image[count]);
                }
            }
        });
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count > 0) {
                    count--;
                    name1.setText(name[count]);
                    rollno1.setText(rollno[count]);
                    email1.setText(email[count]);
                    dob1.setText(DOB[count]);
                    img1.setImageResource(image[count]);
                }
            }
        });

    }
}
