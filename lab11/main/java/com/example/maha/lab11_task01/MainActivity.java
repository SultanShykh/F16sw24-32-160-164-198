package com.example.maha.lab11_task01;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = findViewById(R.id.listView);
    }

    private void getStudents() {
        class GetStudents extends AsyncTask<Void, Void, List<Student>> {
            @Override
            protected List<Student> doInBackground(Void... voids) {
                return StdDatabase
                        .getStdDb(getApplicationContext())
                        .stdDao()
                        .getAll();
            }

            @Override
            protected void onPostExecute(List<Student> students) {
                super.onPostExecute(students);
                ArrayList<String> rollnos = new ArrayList<String>();
                for (Student s : students) {
                    rollnos.add(s.getRollno());
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,
                        android.R.layout.simple_list_item_1, android.R.id.text1, rollnos);
                lv.setAdapter(adapter);

            }
        }
        GetStudents gs = new GetStudents();
        gs.execute();
    }

    public void addStudents(View view) {
        Intent i = new Intent(getApplicationContext(), SaveActivity.class);
        startActivity(i);
    }
}
