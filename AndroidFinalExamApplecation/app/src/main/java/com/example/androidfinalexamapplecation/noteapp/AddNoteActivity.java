package com.example.androidfinalexamapplecation.noteapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;


import com.example.androidfinalexamapplecation.SingUpActivity;
import com.example.androidfinalexamapplecation.databinding.ActivityAddNoteBinding;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Calendar;

public class AddNoteActivity extends AppCompatActivity {

    ActivityAddNoteBinding binding;
    Bitmap bitmap;
    Uri uri;
    int a = 0;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddNoteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        a = 0;
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


        a = 0;
        ActivityResultLauncher<Intent> lancher2 = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        Intent intent = result.getData();
                        assert intent != null;
                        uri = intent.getData();
                        try {
                            bitmap = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), uri);
                        } catch (IOException e) {
                            throw  new RuntimeException(e);
                        }

                    }
                });

        binding.selectImgAuther.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a = 1;
                Intent intent2=new Intent();
                intent2.setAction(Intent.ACTION_GET_CONTENT);
                intent2.setType("image/*");
                lancher2.launch(intent2);
            }
        });





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

                        if (a == 1) {


                            String title = binding.edAddNoteTitle.getText().toString();
                            String text = binding.edAddNoteText.getText().toString();
                            MyNoteDataBase dataBase = new MyNoteDataBase(AddNoteActivity.this);
                            Note note = new Note(title, text, theDate, theTime,bitmap);
                            if (dataBase.insertNote(note)) {
                                Toast.makeText(AddNoteActivity.this, "done", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(AddNoteActivity.this, "Problem", Toast.LENGTH_SHORT).show();
                            }
                            finish();
                        } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(AddNoteActivity.this);
                        builder.setTitle("Warning").setMessage("You should select an image for the auther");
                        builder.show();
                    }
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

