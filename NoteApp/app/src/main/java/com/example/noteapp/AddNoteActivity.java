package com.example.noteapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.noteapp.databinding.ActivityAddNoteBinding;

import java.time.LocalDate;
import java.util.Calendar;

public class AddNoteActivity extends AppCompatActivity {

    ActivityAddNoteBinding binding;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddNoteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        MyNoteDataBase dataBase = new MyNoteDataBase(this);
        LocalDate dateTime = LocalDate.now();
        Calendar currentTime = Calendar.getInstance();
        int hour = currentTime.get(Calendar.HOUR_OF_DAY) ;
        int minute = currentTime.get(Calendar.MINUTE);
        if (hour==0){
            hour = 12;
        }else if (hour > 12){
            hour -= 12;
        }
        String theTime =  hour+":"+minute;
        String theDate = dateTime+"";
        Intent intent = getIntent();

        if (intent.getIntExtra("fromWhere",-1) == 1){
            binding.btnAddNote.setText("Update Note");
            binding.edAddNoteTitle.setText(dataBase.getNoteByID(intent.getIntExtra("noteID",-1)).getTitle());
            binding.edAddNoteText.setText(dataBase.getNoteByID(intent.getIntExtra("noteID",-1)).getText());
            binding.btnAddNote.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (binding.edAddNoteTitle.getText().toString().isEmpty()){
                        AlertDialog.Builder builder = new AlertDialog.Builder(AddNoteActivity.this);
                        builder.setTitle("Warning");
                        builder.setMessage("You should insert the title and the text");
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });
                        AlertDialog dialog=builder.create();
                        dialog.setCancelable(false);
                        dialog.show();


                    }else if ( binding.edAddNoteText.getText().toString().isEmpty()){
                        AlertDialog.Builder builder = new AlertDialog.Builder(AddNoteActivity.this);
                        builder.setTitle("Warning");
                        builder.setMessage("You should insert the title and the text");
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });
                        AlertDialog dialog=builder.create();
                        dialog.setCancelable(false);
                        dialog.show();



                    }else {
                        String title = binding.edAddNoteTitle.getText().toString();
                        String text = binding.edAddNoteText.getText().toString();
                        MyNoteDataBase dataBase = new MyNoteDataBase(AddNoteActivity.this);
                        Note note = new Note(title,text,theDate,theTime);
                        note.setId(intent.getIntExtra("noteID",-1));
                        if (dataBase.updateNote(note)){
                            Toast.makeText(AddNoteActivity.this, "update done", Toast.LENGTH_SHORT).show();
                            finish();
                        }else {
                            Toast.makeText(AddNoteActivity.this, "Problem in update", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });
        }else {
            binding.btnAddNote.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (binding.edAddNoteTitle.getText().toString().isEmpty()){
                        AlertDialog.Builder builder = new AlertDialog.Builder(AddNoteActivity.this);
                        builder.setTitle("Warning");
                        builder.setMessage("You should insert the title and the text");
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });
                        AlertDialog dialog=builder.create();
                        dialog.setCancelable(false);
                        dialog.show();


                    }else if ( binding.edAddNoteText.getText().toString().isEmpty()){
                        AlertDialog.Builder builder = new AlertDialog.Builder(AddNoteActivity.this);
                        builder.setTitle("Warning");
                        builder.setMessage("You should insert the title and the text");
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });
                        AlertDialog dialog=builder.create();
                        dialog.setCancelable(false);
                        dialog.show();



                    } else {
                        String title = binding.edAddNoteTitle.getText().toString();
                        String text = binding.edAddNoteText.getText().toString();
                        MyNoteDataBase dataBase = new MyNoteDataBase(AddNoteActivity.this);
                        Note note = new Note(title,text,theDate,theTime);
                        if (dataBase.insertNote(note)){
                            Toast.makeText(AddNoteActivity.this, "done", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(AddNoteActivity.this, "Problem", Toast.LENGTH_SHORT).show();
                        }
                        finish();
                    }

                }

            });

        }






//        binding.btnGetDate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                binding.tvTime.setText(theTime);
//                binding.tvDate.setText(theDate);
//            }
//        });


                }
            }

