package com.example.edwin.clinicacetu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by desarrollo on 05-12-18.
 */

public class MyAdapter  extends BaseAdapter {
    private Context context;
    private  int layout;
    private List<String> names;

    public MyAdapter(Context context, int layout, List<String> names){
        this.context=context;
        this.layout = layout;
        this.names = names;
    }

    @Override
    public int getCount() {
        return this.names.size();
    }

    @Override
    public Object getItem(int position) {
        return this.names.get(position);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        View v = convertView;
        LayoutInflater layoutInflater = LayoutInflater.from(this.context);
        v= layoutInflater.inflate(R.layout.list_item, null);

        String currentName = names.get(position);
        //currentName = (String) getItem(position);
        TextView tvDetalle = (TextView)v.findViewById(R.id.tvDetalle);
        tvDetalle.setText(currentName);
        return v;
    }
}
