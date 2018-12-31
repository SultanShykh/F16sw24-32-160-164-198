package com.example.dell.gestures;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class Main3Activity extends AppCompatActivity implements SensorEventListener {
    SensorManager sman;
    Sensor gyroscope;
    ImageView img;
    int count=0;
    int imageArray []=new int[]{
            R.drawable.raven,
            R.drawable.robot,
            R.drawable.pic1,
            R.drawable.pic2,
            R.drawable.pic3
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        img = findViewById(R.id.imageView2);
        sman = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        gyroscope = sman.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sman.registerListener(this,gyroscope,sman.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sman.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.values[0] > 0.5f) {
            if (count < 4) {
                count++;
                img.setImageResource(imageArray[count]);
            }
        } else if (event.values[1] > 0.5f) {
            if (count > 0) {
                count--;
                img.setImageResource(imageArray[count]);
            }
        } else if (event.values[2] > 0.5f) {
            if (count < 4) {
                count++;
                img.setImageResource(imageArray[count]);
            }

        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
