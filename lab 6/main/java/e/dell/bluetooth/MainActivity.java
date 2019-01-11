package e.dell.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    Button b1,b2,b3,b4;
    ListView lv;
    private BluetoothAdapter bluetoothAdapter;
    ArrayAdapter<String> arrayAdapter;
    ArrayList<String> list;
    Set<BluetoothDevice> paired_devices;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1 = findViewById(R.id.button);
        b2 = findViewById(R.id.button2);
        b3 = findViewById(R.id.button3);
        b4 = findViewById(R.id.button4);
        lv = findViewById(R.id.listview);

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!bluetoothAdapter.isEnabled()){
                    Intent in = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivityForResult(in,123);
                    Toast.makeText(getApplicationContext(), "Bluetooth turned on", Toast.LENGTH_SHORT).show();
                }

            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bluetoothAdapter.disable();
                Toast.makeText(MainActivity.this, "Bluetooth turned off", Toast.LENGTH_SHORT).show();
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!bluetoothAdapter.isDiscovering()){
                    Intent in = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
                    startActivityForResult(in,123);
                Toast.makeText(MainActivity.this, "Device is discoverable", Toast.LENGTH_SHORT).show();
            }}
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paired_devices = bluetoothAdapter.getBondedDevices();
                list = new ArrayList<String>();
                for(BluetoothDevice bd: paired_devices){
                    String name = bd.getName();
                    String mac = bd.getAddress();
                    list.add("Name is: "+name+" Mac address is "+mac);
                }
                arrayAdapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,list);
                lv.setAdapter(arrayAdapter);
            }
        });


    }
}
