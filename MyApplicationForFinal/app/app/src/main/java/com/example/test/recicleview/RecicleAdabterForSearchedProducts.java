package com.example.test.recicleview;


import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test.Product;
import com.example.test.Product2;
import com.example.test.databinding.CardViewBinding;

import java.util.ArrayList;

public class RecicleAdabterForSearchedProducts extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {
    @NonNull


    private Context context;
    private ArrayList<Product> products;
    private CardViewBinding binding;
    private ClickItem clickItem;



    public RecicleAdabterForSearchedProducts(@NonNull Context context, ArrayList<Product> products, ClickItem clickItem) {
        this.context = context;
        this.products = products;
        this.clickItem = clickItem;

    }
    public RecicleAdabterForSearchedProducts(@NonNull Context context, ArrayList<Product> products) {
        this.context = context;
        this.products = products;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       binding = CardViewBinding.inflate(LayoutInflater.from(context),parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
       ViewHolder viewHolder = (ViewHolder) holder;
       viewHolder.binding.tvName.setText(products.get(position).getType());
       viewHolder.binding.tvPrice.setText(products.get(position).getPrice()+"$");
       viewHolder.binding.tvSpecialName.setText(products.get(position).getName());
       viewHolder.binding.samsungWatch.setImageBitmap(products.get(position).getProductImage());
       viewHolder.binding.getRoot().setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               clickItem.clickOnItem(position);
           }
       });
       viewHolder.binding.getRoot().setOnLongClickListener(new View.OnLongClickListener() {
           @Override
           public boolean onLongClick(View v) {
               clickItem.LongClick(position);
               return false;
           }
       });

    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        CardViewBinding binding;

        public ViewHolder(@NonNull CardViewBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

    }



    public interface ClickItem{

        void clickOnItem(int position);
        void LongClick(int postion);

    }

//    public Uri getImageUri(Context inContext, Bitmap inImage) {
//        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
//        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
//        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
//        return Uri.parse(path);
//    }

}
