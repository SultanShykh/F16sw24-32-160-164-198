package com.example.dell.toolbarapp;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MainActivity extends AppCompatActivity {
Toolbar toolbar;
ListView lv;
ArrayAdapter<String> adapter;
ArrayList<String> listitem;
ActionMode amodeAction;
int MyAdapterPosition;
private List<String> userSelection = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Raven's App");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);


        lv = findViewById(R.id.listview);

        listitem = new ArrayList<String>();
        for (int i=0; i<=50; i++){
        listitem.add("F16sw"+i);
        }

        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listitem);
        lv.setAdapter(adapter);



        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                MyAdapterPosition=position;
                amodeAction = MainActivity.this.startActionMode(aModecallback);
                lv.setSelected(true);
                lv.setItemChecked(position,true);

                return true;
            }
        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String value= lv.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(),value,Toast.LENGTH_LONG).show();
            }
        });

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_mian,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.delete:
                Toast.makeText(getApplicationContext(),item.getTitle(),Toast.LENGTH_LONG).show();
                return true;
            case R.id.search:
                Toast.makeText(getApplicationContext(),item.getTitle(),Toast.LENGTH_LONG).show();
                return true;
            case R.id.settings:
                Toast.makeText(getApplicationContext(),item.getTitle(),Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);


    }

    ActionMode.Callback aModecallback = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            getMenuInflater().inflate(R.menu.context_menu,menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {


               AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
               alert.setTitle("ALERT");
               alert.setMessage("Are you sure you want to delete the item");
               alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
               @Override
            public void onClick(DialogInterface dialog, int which) {
                 listitem.remove(MyAdapterPosition);
                 adapter.notifyDataSetChanged();

                 }
                 });

               alert.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
               @Override

            public void onClick(DialogInterface dialog, int which) {
                       Toast.makeText(getApplicationContext(),"Pressed cancel",Toast.LENGTH_SHORT).show();

                       }
                        });

            alert.show();
            amodeAction.finish();
            return true;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {

        }
    };

}
