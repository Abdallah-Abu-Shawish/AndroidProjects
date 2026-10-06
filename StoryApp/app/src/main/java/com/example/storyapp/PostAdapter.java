package com.example.storyapp;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.storyapp.databinding.PostItemBinding;

import java.util.ArrayList;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.myViewHolder>{


    private PostItemBinding binding;
    private Context context;
    private ArrayList<Post> posts;

    public PostAdapter(Context c, ArrayList<Post> posts) {
        this.posts = posts;
        this.context = c;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = PostItemBinding.inflate(LayoutInflater.from(context),parent,false);
        return new myViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        holder.binding.txTitle.setText(posts.get(position).getTitle());
        holder.binding.nameItem.setText(posts.get(position).getName());
        holder.binding.imageUser.setImageURI(Uri.parse(posts.get(position).getUserImg()));
        holder.binding.imgPost.setImageURI(Uri.parse(posts.get(position).getPostImg()));
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    class myViewHolder extends RecyclerView.ViewHolder{


        PostItemBinding binding;


        public myViewHolder(@NonNull PostItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
