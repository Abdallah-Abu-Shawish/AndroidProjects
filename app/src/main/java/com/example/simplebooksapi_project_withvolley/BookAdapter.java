package com.example.simplebooksapi_project_withvolley;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.simplebooksapi_project_withvolley.databinding.BookItemBinding;

import java.util.ArrayList;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookItemHolder> {

    Context context;
    ArrayList<Book> books;
    ClickItem clickItem;

    public BookAdapter(Context context, ArrayList<Book> books, ClickItem clickItem) {
        this.context = context;
        this.books = books;
        this.clickItem = clickItem;
    }

    @NonNull
    @Override
    public BookItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        BookItemBinding binding = BookItemBinding.inflate(LayoutInflater.from(context), parent, false);
        return new BookItemHolder(binding);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull BookItemHolder holder, int position) {

        holder.binding.numberTv.setText(String.valueOf(position+1));
        holder.binding.typeTv.setText("type : "+books.get(position).getType());
        holder.binding.nameTv.setText(books.get(position).getName());
        if (books.get(position).isAvailable())
            holder.binding.isAvailableTv.setText("available");
        else
            holder.binding.isAvailableTv.setText("not available");
        holder.binding.getRoot().setOnClickListener(v -> {
            clickItem.onClickItem(books.get(position).getId());
        });
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    class BookItemHolder extends RecyclerView.ViewHolder {
        BookItemBinding binding;
        public BookItemHolder(BookItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    interface ClickItem {
        void onClickItem(int bookId);
    }
}
