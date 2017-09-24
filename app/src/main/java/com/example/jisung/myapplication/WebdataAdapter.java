package com.example.jisung.myapplication;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by jisung on 2017-09-19.
 */

public class WebdataAdapter extends BaseAdapter {
    ArrayList<Webdata> list;
    Context context;

    public WebdataAdapter(ArrayList<Webdata> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null)
            convertView = View.inflate(context,R.layout.url_item,null);

        TextView t1 = (TextView)convertView.findViewById(R.id.t1);
        TextView t2 = (TextView)convertView.findViewById(R.id.t2);

        t1.setText(list.get(position).getName());
        t2.setText(list.get(position).getUrldata());

        return convertView;
    }
}
