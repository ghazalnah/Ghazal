package com.example.user.ghazal;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.ghazal.R;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<Item> {

    private int resourceLayout;
    private Context mContext;

    public CustomAdapter(@NonNull Context context, int resource, @NonNull List<Item> objects) {
        super(context, resource, objects);
        this.resourceLayout = resource;
        this.mContext = context;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        View v = convertView;
        if (v == null)
            v = LayoutInflater.from(mContext).inflate(resourceLayout,parent,false);

        Item p = getItem(position);

        if(p != null) {
            TextView tvName = (TextView) v.findViewById(R.id.tvName);
            tvName.setText(p.getName());

            ImageView imageView=v.findViewById(R.id.imageView);
            imageView.setImageResource(p.getImage());
        }
        return v;
    }
}
