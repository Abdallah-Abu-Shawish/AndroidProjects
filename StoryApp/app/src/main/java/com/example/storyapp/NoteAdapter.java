package com.example.storyapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;


import com.example.storyapp.database.Note;
import com.example.storyapp.databinding.NoteCardBinding;

public class NoteAdapter extends ListAdapter<Note,RecyclerView.ViewHolder> {

    private Context context;

    private NoteCardBinding binding;
    private ClickItem listener;

    protected NoteAdapter(Context context) {
        super(DIFF_CALLBACK);
        this.context = context;
    }

    private static final DiffUtil.ItemCallback<Note> DIFF_CALLBACK = new DiffUtil.ItemCallback<Note>() {
        @Override
        public boolean areItemsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return oldItem.getTitle().equals(newItem.getTitle()) &&
                    oldItem.getDescription().equals(newItem.getDescription()) &&
                    oldItem.getPriority() == newItem.getPriority();
        }
    };
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = NoteCardBinding.inflate(LayoutInflater.from(context),parent,false);
        return new NoteHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        NoteHolder myHolder = (NoteHolder) holder;
        myHolder.binding.tvTitle.setText(getItem(position).getTitle());
        myHolder.binding.tvDescription.setText(getItem(position).getDescription());
        myHolder.binding.tvPriority.setText(getItem(position).getPriority()+"");
        myHolder.binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null && position != RecyclerView.NO_POSITION) {
                    listener.onItemClicked(getItem(position));
                }
            }
        });
        myHolder.binding.getRoot().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return false;
            }
        });
    }


    public Note getNoteAt(int position) {
        return getItem(position);
    }

    class NoteHolder extends RecyclerView.ViewHolder{

        NoteCardBinding binding;
        public NoteHolder(@NonNull NoteCardBinding view) {
            super(view.getRoot());
            binding = view;


        }
    }

    public interface ClickItem {
        void onItemClicked(Note note);
    }

    public void setOnClickItem(ClickItem listener) {
        this.listener = listener;
    }

}
