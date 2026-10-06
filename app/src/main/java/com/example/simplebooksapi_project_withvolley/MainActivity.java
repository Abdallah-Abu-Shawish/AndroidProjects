package com.example.simplebooksapi_project_withvolley;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.simplebooksapi_project_withvolley.databinding.ActivityMainBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    RequestQueue queue;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);

        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        queue = Volley.newRequestQueue(this);
        url = "https://simple-books-api.glitch.me/books";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                response -> {
                    ArrayList<Book> bookList = getBookFromAPI(response);
                    BookAdapter adapter = new BookAdapter(MainActivity.this, bookList, bookId -> {
                        Intent intent = new Intent(MainActivity.this, ShowBookActivity.class);
                        intent.putExtra("bookId", bookId);
                        startActivity(intent);
                    });
                    binding.quakesRv.setAdapter(adapter);
                    binding.quakesRv.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                },
                error -> Log.d("Error when getting books", "onErrorResponse: "+error.getMessage()));

        queue.add(jsonArrayRequest);
    }
    private ArrayList<Book> getBookFromAPI(JSONArray booksJsonArray) {
        ArrayList<Book> books = new ArrayList<>();
        for (int i = 0; i < booksJsonArray.length(); i++) {
            try {
                JSONObject bookObject = booksJsonArray.getJSONObject(i);
                int id = bookObject.getInt("id");
                String name = bookObject.getString("name");
                String type = bookObject.getString("type");
                boolean available = bookObject.getBoolean("available");
                books.add(new Book(id, name, type, available));
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
        return books;
    }
}