package com.example.lab10;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
ProgressBar progressBar;
Button b1;
TextView tv1,tv2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progressBar);
        b1 = findViewById(R.id.b1);
        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Downloader().execute();
            }
        });

    }
    class Downloader extends AsyncTask<Void,Integer,Integer>{


        @Override
        protected Integer doInBackground(Void... voids) {
            Thread thread = new Thread();
            for (int i = 0; i<100; i++){
                publishProgress(i);
                try{
                    thread.sleep(100);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            return null;
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            tv2.setText("Completed");
        }

        @Override
        protected void onPreExecute() {
            progressBar.setMax(100);
            tv1.setText("started");
            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            tv1.setText("Updating");
            progressBar.setProgress(values[0]);
        }
    }
}
