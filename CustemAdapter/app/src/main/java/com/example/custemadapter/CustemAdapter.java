package com.example.custemadapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

public class CustemAdapter extends BaseAdapter {

    private Context context;
    private List<Country> countries ;


    public CustemAdapter(Context context ,List<Country> countries) {
        this.countries = countries;
        this.context = context;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }


}
