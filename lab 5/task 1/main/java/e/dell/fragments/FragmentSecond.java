package e.dell.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class FragmentSecond extends Fragment {
    ListView lv;
    ArrayList<String> listset;
    ArrayAdapter<String> listadapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_fragment_second, container, false);
        lv = view.findViewById(R.id.listview);
        listset = new ArrayList<String>();
        for (int i=0; i<16; i++){
            listset.add("Mobile Phone: "+i);
        }
        listadapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,listset);
        lv.setAdapter(listadapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String value = lv.getItemAtPosition(position).toString();
                Toast.makeText(getActivity(), value, Toast.LENGTH_SHORT).show();
                Intent in = new Intent(getActivity(),MainActivity.class);
                startActivity(in);
            }
        });
        return view;
    }

}
