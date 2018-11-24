package com.example.dell.contextmenu;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class Recycler_vew_Adapter extends RecyclerView.Adapter<Recycler_vew_Adapter.Recycler_view_Holder> {

    private String [] data;
    public Recycler_vew_Adapter(String [] data){
        this.data=data;

    }
    @NonNull
    @Override
    public Recycler_view_Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.recycler_list,viewGroup,false);
        return new Recycler_view_Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Recycler_view_Holder recycler_view_holder, int i) {
        String Title = data[i];
        recycler_view_holder.txtTitle.setText(Title);
    }

    @Override
    public int getItemCount() {

        return data.length;
    }

    public class Recycler_view_Holder extends RecyclerView.ViewHolder{
        ImageView ImgIcon;
        TextView txtTitle;

        public Recycler_view_Holder(@NonNull View itemView) {
            super(itemView);
            ImgIcon = itemView.findViewById(R.id.imgIcon);
            txtTitle = itemView.findViewById(R.id.txtTitle);
        }
    }
}
