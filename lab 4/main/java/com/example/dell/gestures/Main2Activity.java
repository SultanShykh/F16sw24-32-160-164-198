package com.example.dell.gestures;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity implements SensorEventListener {

    ImageView img;
    Button b1;
    SensorManager sman;
    Sensor proximity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        img = findViewById(R.id.imageView);
        sman = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        proximity = sman.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        b1 = findViewById(R.id.button2);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent (Main2Activity.this,Main3Activity.class);
                startActivity(in);
            }
        });

        if(proximity==null){
            Toast.makeText(this,"your phone does not support proximity sensor",Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        sman.registerListener(this,proximity,sman.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sman.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.values[0]<proximity.getMaximumRange()){
            ConstraintLayout.LayoutParams consimg = new ConstraintLayout.LayoutParams(800,1000);
            img.setLayoutParams(consimg);
            getWindow().getDecorView().setBackgroundColor(Color.RED);
        }else {
            ConstraintLayout.LayoutParams consimg = new ConstraintLayout.LayoutParams(313,426);
            img.setLayoutParams(consimg);
            getWindow().getDecorView().setBackgroundColor(Color.GREEN);
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
