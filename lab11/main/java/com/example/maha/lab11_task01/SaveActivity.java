package com.example.maha.lab11_task01;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SaveActivity extends AppCompatActivity {
    EditText editTextName;
    EditText editTextRoll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save);
        editTextName = findViewById(R.id.nameEditText);
        editTextRoll = findViewById(R.id.rollEditText);
    }

    public void onBtnClick(View view) {

        final String name = editTextName.getText().toString().trim();
        final String rollno = editTextRoll.getText().toString().trim();
        if (name.isEmpty()) {
            editTextName.setError("Name Required");
            editTextName.requestFocus();
            return;
        }

        if (rollno.isEmpty()) {
            editTextRoll.setError("Roll Required");
            editTextRoll.requestFocus();
            return;
        }

        class SaveStudent extends AsyncTask<Void, Void, Void> {
            @Override
            protected Void doInBackground(Void... voids) {
                Student student = new Student();
                student.setName(name);
                student.setRollno(rollno);
                // Add to database
                StdDatabase.getStdDb(getApplicationContext())
                        .stdDao()
                        .insert(student);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                finish();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
            }
        }
        SaveStudent ss = new SaveStudent();
        ss.execute();
    }
}
