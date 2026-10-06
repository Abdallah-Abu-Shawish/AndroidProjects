package com.example.androidfinalexamapplecation.noteapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;


import com.example.androidfinalexamapplecation.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    NoteRVAdapter adapter;
    NoteRVAdapter adapter2;
    ArrayList<Note> notes;
    ArrayList<Note> searchedNotes;
    int noteDeletedPosition;
    MyNoteDataBase dataBase;
    int a = 0;
    String title = "";
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        dataBase = new MyNoteDataBase(this);
        notes = dataBase.getAllNotes();



        binding.iconSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a++;
                if (a%2==1) {
                    binding.edForSearch.setVisibility(View.VISIBLE);
                    binding.btnSearch.setVisibility(View.VISIBLE);
                    binding.rvSearchedNotesList.setVisibility(View.VISIBLE);
                    binding.edForSearch.setText("");
                    searchedNotes = new ArrayList<>();
                } else if (a%2==0){
                    binding.edForSearch.setVisibility(View.GONE);
                    binding.btnSearch.setVisibility(View.GONE);
                    binding.rvSearchedNotesList.setVisibility(View.GONE);
                    binding.edForSearch.setText("");
                    searchedNotes = new ArrayList<>();

                }
            }
        });

        binding.btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.edForSearch.getText().toString().isEmpty()){
                    AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Warning");
                    builder.setMessage("You should fill the search feild");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                            });
                    AlertDialog dialog=builder.create();
                    dialog.setCancelable(false);
                    dialog.show();
                }else {
                    title = binding.edForSearch.getText().toString();
                    searchedNotes = dataBase.searchForNote(title);
                    adapter2 = new NoteRVAdapter(MainActivity.this, searchedNotes, new NoteRVAdapter.ClickNote() {
                        @Override
                        public void clickOnNote(int position) {
                        }

                        @Override
                        public void LongclickOnNote(int position) {
                        }
                    });
                    binding.rvSearchedNotesList.setAdapter(adapter2);
                    LinearLayoutManager manager = new LinearLayoutManager(MainActivity.this);
                    binding.rvSearchedNotesList.setLayoutManager(manager);

                }
            }
        });



        binding.fabtnAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(MainActivity.this,AddNoteActivity.class);
                intent.putExtra("fromWhere",0);
                startActivity(intent);
            }
        });

        notes = dataBase.getAllNotes();
        adapter = new NoteRVAdapter(this, notes, new NoteRVAdapter.ClickNote() {
            @Override
            public void clickOnNote(int position) {
                MyNoteDataBase dataBase = new MyNoteDataBase(MainActivity.this);
                Intent intent  = new Intent(MainActivity.this,AddNoteActivity.class);
                intent.putExtra("fromWhere",1);
                intent.putExtra("noteID",dataBase.getAllNotes().get(position).getId());
                startActivity(intent);
            }

            @Override
            public void LongclickOnNote(int position) {
                noteDeletedPosition = position;
                ShowAlertDialgoToDeleteNote();
            }
        });
        binding.rvNotesList.setAdapter(adapter);
        LinearLayoutManager manager = new LinearLayoutManager(MainActivity.this);
        binding.rvNotesList.setLayoutManager(manager);



//       LocalDate dateTime = LocalDate.now();
//        Calendar currentTime = Calendar.getInstance();
//        int hour = currentTime.get(Calendar.HOUR_OF_DAY) ;
//        if (hour==0){
//            hour = 12;
//        }else if (hour > 12){
//            hour -= 12;
//        }
//        int minute = currentTime.get(Calendar.MINUTE);
//        String theTime =  hour+":"+minute;;
//        String theDate = dateTime+"";
//        binding.btnGetDate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                binding.tvTime.setText(theTime);
//                binding.tvDate.setText(theDate);
//            }
//        });



    }


    private void ShowAlertDialgoToDeleteNote(){
//التصميم الطبيعي

        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Warning");
        builder.setMessage("Are you sure that you want to delete this note?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dataBase.deleteNote(dataBase.getAllNotes().get(noteDeletedPosition).getId());
                notes = dataBase.getAllNotes();
                adapter = new NoteRVAdapter(MainActivity.this, notes, new NoteRVAdapter.ClickNote() {
                    @Override
                    public void clickOnNote(int position) {
                        MyNoteDataBase dataBase = new MyNoteDataBase(MainActivity.this);
                        Intent intent = new Intent(MainActivity.this,AddNoteActivity.class);
                        intent.putExtra("fromWhere",1);
                        intent.putExtra("noteID",dataBase.getAllNotes().get(position).getId());
                        startActivity(intent);
                    }

                    @Override
                    public void LongclickOnNote(int position) {
                        noteDeletedPosition = position;
                        ShowAlertDialgoToDeleteNote();
                    }
                });
                binding.rvNotesList.setAdapter(adapter);
                LinearLayoutManager manager = new LinearLayoutManager(MainActivity.this);
                binding.rvNotesList.setLayoutManager(manager);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        AlertDialog dialog=builder.create();
        dialog.setCancelable(false);
        dialog.show();

    }

    private void ShowAlertDialgoToDeleteNote2(){
//التصميم الطبيعي

        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Warning");
        builder.setMessage("Are you sure that you want to delete this note?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dataBase.deleteNote(dataBase.searchForNote(title).get(noteDeletedPosition).getId());
                searchedNotes = dataBase.searchForNote(title);
                adapter2 = new NoteRVAdapter(MainActivity.this, searchedNotes, new NoteRVAdapter.ClickNote() {
                    @Override
                    public void clickOnNote(int position) {
                        MyNoteDataBase dataBase = new MyNoteDataBase(MainActivity.this);
                        Intent intent = new Intent(MainActivity.this,AddNoteActivity.class);
                        intent.putExtra("fromWhere",1);
                        intent.putExtra("noteID",dataBase.getAllNotes().get(position).getId());
                        startActivity(intent);
                    }

                    @Override
                    public void LongclickOnNote(int position) {
                        noteDeletedPosition = position;
                        ShowAlertDialgoToDeleteNote2();
                    }
                });
                binding.rvSearchedNotesList.setAdapter(adapter2);
                LinearLayoutManager manager = new LinearLayoutManager(MainActivity.this);
                binding.rvSearchedNotesList.setLayoutManager(manager);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        AlertDialog dialog=builder.create();
        dialog.setCancelable(false);
        dialog.show();

    }

    @Override
    protected void onResume() {
        super.onResume();
        notes = dataBase.getAllNotes();
        adapter = new NoteRVAdapter(this, notes, new NoteRVAdapter.ClickNote() {
            @Override
            public void clickOnNote(int position) {
                MyNoteDataBase dataBase = new MyNoteDataBase(MainActivity.this);
                Intent intent  = new Intent(MainActivity.this,AddNoteActivity.class);
                intent.putExtra("fromWhere",1);
                intent.putExtra("noteID",dataBase.getAllNotes().get(position).getId());
                startActivity(intent);
            }

            @Override
            public void LongclickOnNote(int position) {
                noteDeletedPosition = position;
                ShowAlertDialgoToDeleteNote();
            }
        });
        binding.rvNotesList.setAdapter(adapter);
        LinearLayoutManager manager = new LinearLayoutManager(MainActivity.this);
        binding.rvNotesList.setLayoutManager(manager);

    }
}