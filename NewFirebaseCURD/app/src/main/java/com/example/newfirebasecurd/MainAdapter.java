package com.example.newfirebasecurd;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.newfirebasecurd.databinding.MainItemBinding;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class MainAdapter extends FirebaseRecyclerAdapter<Teacher,MainAdapter.myViewHolder> {

    MainItemBinding binding;
    Context context;
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public MainAdapter(@NonNull FirebaseRecyclerOptions options,Context context) {
        super(options);
        this.context = context;
    }

    @NonNull
    @Override
    public MainAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = MainItemBinding.inflate(LayoutInflater.from(context),parent,false);
        return new myViewHolder(binding);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull Teacher model) {
        holder.binding.teacherName.setText(model.getName());
        holder.binding.teacherCourse.setText(model.getCourse());
        holder.binding.teacherEmail.setText(model.getEmail());

        Glide.with(holder.binding.teacherImg.getContext())
                .load(model.getTurl())
                .placeholder(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark)
                .circleCrop()
                .error(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark_normal)
                .into(holder.binding.teacherImg);
    }

    class myViewHolder extends RecyclerView.ViewHolder{
        MainItemBinding binding;

        public myViewHolder(@NonNull MainItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
