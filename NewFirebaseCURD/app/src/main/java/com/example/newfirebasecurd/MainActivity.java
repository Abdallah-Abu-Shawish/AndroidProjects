package com.example.newfirebasecurd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;

import com.example.newfirebasecurd.databinding.ActivityMainBinding;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    MainAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.rvTeachers.setLayoutManager(new LinearLayoutManager(this));
       FirebaseApp.initializeApp(this);

        FirebaseRecyclerOptions<Teacher> options =
                new FirebaseRecyclerOptions.Builder<Teacher>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("teachers"), Teacher.class)
                        .build();


        adapter = new MainAdapter(options,this);
        binding.rvTeachers.setAdapter(adapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu,menu);
        MenuItem item = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                txtSearch(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                txtSearch(query);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    public void txtSearch(String str) {
        FirebaseRecyclerOptions<Teacher> options =
                new FirebaseRecyclerOptions.Builder<Teacher>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("teachers")
                                .orderByChild("name").startAt(str).endAt(str+"~"), Teacher.class)
                                .build();

        adapter = new MainAdapter(options,MainActivity.this);
        adapter.startListening();
        binding.rvTeachers.setAdapter(adapter);
    }

}