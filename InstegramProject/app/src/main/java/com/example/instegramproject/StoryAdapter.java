package com.example.instegramproject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.instegramproject.databinding.StoryItemBinding;

import java.util.ArrayList;

public class StoryAdapter extends RecyclerView.Adapter<StoryAdapter.StoryHolder> {

    ArrayList<Story> stories;
    StoryItemBinding binding;
    Context context;
    onStoryClickable listener;

    public StoryAdapter(ArrayList<Story> stories, Context context ,onStoryClickable listener) {
        this.stories = stories;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public StoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = StoryItemBinding.inflate(LayoutInflater.from(context),parent,false);
        return new StoryHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull StoryHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.binding.imageStory.setImageBitmap(stories.get(position).getStoryImg());
        holder.binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.getClickedStory(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return stories.size();
    }

    class StoryHolder extends RecyclerView.ViewHolder{

        StoryItemBinding binding;
        public StoryHolder(@NonNull StoryItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    interface onStoryClickable {
        void getClickedStory(int position);
    }

}
