package com.example.storyapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.storyapp.databinding.ActivityAddNoteBinding;

public class AddNoteActivity extends AppCompatActivity {

    ActivityAddNoteBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddNoteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.numberPickerPriority.setMinValue(1);
        binding.numberPickerPriority.setMaxValue(10);

        Intent intent = getIntent();
        if (intent.getStringExtra("destination").equals("Edit")){
            binding.edTxtTitle.setText(intent.getStringExtra("title"));
            binding.edTxtDescription.setText(intent.getStringExtra("description"));
            binding.numberPickerPriority.setValue(intent.getIntExtra("priority",1));

            setTitle("Edit Note");
            binding.tvAddEditNote.setText("Edit Note");
        }else{
            setTitle("Add Note");
        }


    }

    public void saveNote() {
        String title = binding.edTxtTitle.getText().toString();
        String description = binding.edTxtDescription.getText().toString();
        int priority = binding.numberPickerPriority.getValue();

        if (title.trim().isEmpty() || description.trim().isEmpty()) {
            Toast.makeText(this, "Please enter title and description", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent();
        intent.putExtra("title",title);
        intent.putExtra("description",description);
        intent.putExtra("priority",priority);
        int id = getIntent().getIntExtra("id",-1);
        if (id != -1) {
            intent.putExtra("id",id);
            setResult(200,intent);
            finish();
        }else{
            setResult(100,intent);
            finish();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_note_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_note:
                saveNote();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }


    }
}