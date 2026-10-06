package com.example.emaillisthomework;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.emaillisthomework.databinding.EmailDesignBinding;


import java.util.List;

public class CoustemDialogAdapter extends BaseAdapter {



    private Context context;

    private List<Email> emailList;

    private EmailDesignBinding binding;

    public CoustemDialogAdapter(Context context, List<Email> emailList) {
        this.context = context;
        this.emailList = emailList;
    }

    @Override
    public int getCount() {
        return emailList.size();
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
        binding = EmailDesignBinding.inflate(LayoutInflater.from(context),parent,false);

        binding.tvName.setText(emailList.get(position).getName());
        binding.firstLetter.setText(emailList.get(position).getName().substring(0,1));
        binding.tvText.setText(emailList.get(position).getText());
        binding.tvTime.setText(emailList.get(position).getTime());
        binding.tvTitle.setText(emailList.get(position).getTitle());
        return binding.getRoot();
    }
}
