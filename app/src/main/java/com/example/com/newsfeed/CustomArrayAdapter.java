package com.example.com.newsfeed;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Vishal Bhati on 3/10/2017.
 */

public class CustomArrayAdapter extends ArrayAdapter<Item> {
    public CustomArrayAdapter(Context context, List objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listView=convertView;
        if(listView==null){
            listView=LayoutInflater.from(getContext()).inflate(R.layout.each_view,parent,false);
        }
        Item currentItem=getItem(position);

        TextView title= (TextView) listView.findViewById(R.id.title_View);
        title.setText(currentItem.getTitle());

        TextView section= (TextView) listView.findViewById(R.id.section_view);
        section.setText(currentItem.getSection());

        TextView date= (TextView) listView.findViewById(R.id.date_view);
        date.setText(currentItem.getDate());
        return listView;
    }
}
