package com.example.instegramproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.instegramproject.databinding.PostItemBinding;

import java.util.ArrayList;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostHolder> {

    ArrayList<Post> posts;
    PostItemBinding binding;
    Context context;

    public PostAdapter(Context context ,ArrayList<Post> posts) {
        this.posts = posts;
        this.context = context;
    }

    @NonNull
    @Override
    public PostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = PostItemBinding.inflate(LayoutInflater.from(context),parent,false);
        return new PostHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PostHolder holder, int position) {
        holder.binding.postTitle.setText(posts.get(position).getTitle());
        holder.binding.postDetaile.setText(posts.get(position).getDetaile());
        holder.binding.nameUser.setText(posts.get(position).getName());
        holder.binding.imgPost.setImageBitmap(posts.get(position).getPostImg());
        holder.binding.imageUser.setImageBitmap(posts.get(position).getUserImg());
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    class PostHolder extends RecyclerView.ViewHolder{

        PostItemBinding binding;

        public PostHolder(@NonNull PostItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }
    }


}
