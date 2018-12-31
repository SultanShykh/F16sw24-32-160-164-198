package com.example.dell.gestures;

import android.content.Intent;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.graphics.Matrix;
import static android.view.MotionEvent.actionToString;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener,GestureDetector.OnDoubleTapListener{
TextView gesture;
private ImageView imgView;
private float scale =1f;
String TAG = "DBG";
private Matrix matrix = new Matrix();

private static final int SWIPE_THRESHOLD = 100;
private static final int SWIPE_VELOCITY_THRESHOLD = 100;

int img[]= new int[]{
        R.drawable.pic1,
        R.drawable.pic2,
        R.drawable.pic3,
        R.drawable.robot
};
int count=0;
Button b;
GestureDetectorCompat gestureDetectorCompat;
ScaleGestureDetector s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gesture=findViewById(R.id.textView);
        b = findViewById(R.id.button);
        imgView = findViewById(R.id.imgView);
        gestureDetectorCompat=new GestureDetectorCompat(this,this);
        gestureDetectorCompat.setIsLongpressEnabled(true);
        s=new ScaleGestureDetector(this,new ScaleListener());

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(MainActivity.this,Main2Activity.class);
                startActivity(in);
            }
        });
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        gesture.setText("Single tap confirmed");
        return true;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        gesture.setText("Double tap up");

        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        gesture.setText("Double tap Event");

        return true;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        gesture.setText("Down");
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        gesture.setText("show press");
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        gesture.setText("Single tap up");

        return true;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        gesture.setText("Scroll up");
        return true;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        gesture.setText("Long press");

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
       boolean result = false;
        float diffY = e2.getY() - e1.getY();
        float diffX = e2.getX() - e1.getX();
        if (Math.abs(diffX) > Math.abs(diffY)) {
            if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                if (diffX > 0) {
                    onSwipeRight();
                } else {
                    onSwipeLeft();
                }
            }
            result = true;
        }
        else if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
            if (diffY > 0) {
            } else {
            }
        }
        result = true;
        return result;
    }
    public void onSwipeRight(){
        if (count<3){
            count++;
            imgView.setImageResource(img[count]);
        }
    }
    public void onSwipeLeft(){
        if(count>0){
            count--;
            imgView.setImageResource(img[count]);
        }
    }

    public boolean onTouchEvent(MotionEvent event) {
        if(event.getPointerCount() > 1){
        gesture.setText("Multi Touch\nEvent");
        int action = event.getAction();
        int index = event.getActionIndex();
        gesture.append("\n"+ actionToString(action)+"\n Pointer Index: "+index);
    }
        gestureDetectorCompat.onTouchEvent(event);
        s.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    public class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener{
        float onScaleBegin=0;
        float onScaleEnd=0;

        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            scale *= detector.getScaleFactor();
            scale=Math.max(0.1f,Math.min(scale,5f));
            matrix.setScale(scale,scale);
            imgView.setImageMatrix(matrix);
            gesture.setText("Scale factor"+scale);
            return true;
        }
        public boolean onScaleBegin(ScaleGestureDetector detector) {
            Log.d(TAG, "onScaleBegin");
            onScaleBegin = scale;
            return true;
        }
        public void onScaleEnd(ScaleGestureDetector detector) {
            Log.d(TAG, "onScaleEnd");
            onScaleEnd = scale;
             if (onScaleEnd>onScaleBegin){
                 Toast.makeText(getApplicationContext(), "Scaled Up by a factor of " + String.valueOf(onScaleEnd / onScaleBegin), Toast.LENGTH_SHORT).show();
             }            if (onScaleEnd<onScaleBegin){
                Toast.makeText(getApplicationContext(), "Scaled down by a factor of " + String.valueOf(onScaleBegin / onScaleEnd), Toast.LENGTH_SHORT).show();
            }
            super.onScaleEnd(detector);

        }
    }
}
