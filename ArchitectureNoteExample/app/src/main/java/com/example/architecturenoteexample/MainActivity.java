package com.example.architecturenoteexample;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.architecturenoteexample.database.Note;
import com.example.architecturenoteexample.database.NoteViewModel;
import com.example.architecturenoteexample.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private NoteViewModel viewModel;
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setHasFixedSize(true);



        ActivityResultLauncher<Intent> lancher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {



                if (result.getResultCode() == 100) {
                    Intent backIntent = result.getData();
                    String title = backIntent.getStringExtra("title");
                    String description = backIntent.getStringExtra("description");
                    int priority = backIntent.getIntExtra("priority",1);

                    Note note = new Note(title,description,priority);
                    viewModel.insertNote(note);
                    Toast.makeText(MainActivity.this, "Note Saved", Toast.LENGTH_SHORT).show();
                }else if (result.getResultCode() == 200) {
                    Intent backIntent = result.getData();
                    int id = backIntent.getIntExtra("id",-1);
                    String title = backIntent.getStringExtra("title");
                    String description = backIntent.getStringExtra("description");
                    int priority = backIntent.getIntExtra("priority",1);
                    if (id == -1) {
                        Toast.makeText(MainActivity.this, "Note can`t updated", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    Note note = new Note(title,description,priority);
                    note.setId(id);
                    viewModel.updateNote(note);
                    Toast.makeText(MainActivity.this, "Note Updated", Toast.LENGTH_SHORT).show();
                } else{
                    Toast.makeText(MainActivity.this, "Note not saved", Toast.LENGTH_SHORT).show();
                }
            }
        });



        binding.btnAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,AddNoteActivity.class);
                intent.putExtra("destination","Add");
                lancher.launch(intent);
            }
        });
        NoteAdapter adapter = new NoteAdapter(MainActivity.this);
        viewModel = new ViewModelProvider(this).get(NoteViewModel.class);
        viewModel.getAllNotes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {

                binding.recyclerView.setAdapter(adapter);
                adapter.submitList(notes);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0 ,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                viewModel.deleteNote(adapter.getNoteAt(viewHolder.getAdapterPosition()));
            }
        }).attachToRecyclerView(binding.recyclerView);

        adapter.setOnClickItem(new NoteAdapter.ClickItem() {
            @Override
            public void onItemClicked(Note note) {
                Intent intent = new Intent(MainActivity.this , AddNoteActivity.class);
                intent.putExtra("destination","Edit");
                intent.putExtra("id",note.getId());
                intent.putExtra("title",note.getTitle());
                intent.putExtra("description",note.getDescription());
                intent.putExtra("priority",note.getPriority());
                lancher.launch(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.delete_all_notes_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete_all_notes:
                viewModel.deleteAllNotes();
                Toast.makeText(this, "All Notes Deleted", Toast.LENGTH_SHORT).show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }
}