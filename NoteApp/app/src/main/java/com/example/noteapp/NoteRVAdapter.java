package com.example.noteapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.noteapp.databinding.NoteDesigenBinding;

import java.util.ArrayList;

public class NoteRVAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private ArrayList<Note> notes;
    private NoteDesigenBinding binding;
    private ClickNote clickNote;

    public NoteRVAdapter(@NonNull Context context,ArrayList<Note> notes,ClickNote clickNote) {
        this.context = context;
        this.notes = notes;
        this.clickNote = clickNote;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = NoteDesigenBinding.inflate(LayoutInflater.from(context),parent,false);
        return new myViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        myViewHolder viewHolder = (myViewHolder) holder;

        viewHolder.binding.tvShowNoteTitle.setText(notes.get(position).getTitle());
        viewHolder.binding.tvShowNoteText.setText(notes.get(position).getText());
        viewHolder.binding.tvShowNoteDate.setText(notes.get(position).getDate());
        viewHolder.binding.tvShowNoteTime.setText(notes.get(position).getTime());
        viewHolder.binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickNote.clickOnNote(position);
            }
        });
        viewHolder.binding.getRoot().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                clickNote.LongclickOnNote(position);
                return false;
            }
        });


    }

    @Override
    public int getItemCount() {
        return notes.size();
    }


    public class myViewHolder extends RecyclerView.ViewHolder{


        NoteDesigenBinding binding;


        public myViewHolder(@NonNull NoteDesigenBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }


    public interface ClickNote{
        void clickOnNote(int position);
        void LongclickOnNote(int position);
    }


}
