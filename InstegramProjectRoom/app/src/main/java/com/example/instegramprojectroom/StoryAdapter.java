package com.example.instegramprojectroom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.instegramprojectroom.databinding.StoryItemBinding;

import java.util.ArrayList;

public class StoryAdapter extends RecyclerView.Adapter<StoryAdapter.StoryHolder> {

    ArrayList<Story> stories;
    StoryItemBinding binding;
    Context context;

    public StoryAdapter(ArrayList<Story> stories, Context context) {
        this.stories = stories;
        this.context = context;
    }

    @NonNull
    @Override
    public StoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = StoryItemBinding.inflate(LayoutInflater.from(context),parent,false);
        return new StoryHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull StoryHolder holder, int position) {
        holder.binding.imageStory.setImageBitmap(stories.get(position).getStoryImg());
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

}
