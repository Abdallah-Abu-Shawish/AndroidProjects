package com.example.simplebooksapi_project_withvolley;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.simplebooksapi_project_withvolley.databinding.ActivityShowBookBinding;

import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

public class ShowBookActivity extends AppCompatActivity {

    ActivityShowBookBinding binding;
    RequestQueue queue;
    String url;
    String ordersUrl;
    int bookId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityShowBookBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent intent = getIntent();
        bookId = intent.getIntExtra("bookId", -1);
        queue = Volley.newRequestQueue(this);
        url = "https://simple-books-api.glitch.me/books/" + bookId;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    try {
                        int id = response.getInt("id");
                        String name = response.getString("name");
                        String author = response.getString("author");
                        String type = response.getString("type");
                        double price = response.getDouble("price");
                        int currentStock = response.getInt("current-stock");
                        boolean available = response.getBoolean("available");
                        Book book = new Book(id, name, author, type, price, currentStock, available);

                        binding.nameTv.append(book.getName());
                        binding.authorTv.append(book.getAuthor());
                        binding.typeTv.append(book.getType());
                        binding.priceTv.append(String.valueOf(book.getPrice()));
                        if (book.isAvailable()) {
                            binding.isAvailableTv.setText("The Book is available for ordering");
                            binding.currentStockTv.setVisibility(View.VISIBLE);
                            binding.currentStockTv.append(String.valueOf(book.getCurrentStock()));
                        }
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                },
                error -> Log.d("Error when getting book by id", "onErrorResponse: "+error.getMessage()));

        queue.add(request);

        ordersUrl = "https://simple-books-api.glitch.me/orders";
        binding.orderBtn.setOnClickListener(view -> {
            JsonObjectRequest orderRequest = new JsonObjectRequest(Request.Method.POST, ordersUrl, null,
                    response -> {
                        try {
                            Log.d("onResponse => ", "onResponse: "+response.toString());
                            String orderId = response.getString("orderId");
                            boolean isCreated = response.getBoolean("created");

                            if (isCreated) {
                                Toast.makeText(ShowBookActivity.this, "Book has been ordered", Toast.LENGTH_SHORT).show();
                                Intent intent1 = new Intent(ShowBookActivity.this, CheckOrderActivity.class);
                                intent1.putExtra("orderId", orderId);
                                startActivity(intent1);
                            }
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    },
                    error -> Log.d("Sending Order Error", "Error message : "+error.getMessage()))
            {
                @Nullable
                @Override
                protected Map getParams() throws AuthFailureError {
                    super.getParams();
                    Map<String, Object> params = new HashMap<>();
                    params.put("bookId", bookId);
                    params.put("customerName", "Abdallah");
                    return params;
                }
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    super.getHeaders();
                    Map<String, String> headers = new HashMap<>();
                    headers.put("Authorization", "Bearer 34d94348666652a9b0cce593ecf53b81d5a0116cd72c3bf047a8283deba47ebe");
                    headers.put("Content-Type", "application/json");
                    return headers;
                }
            };
            queue.add(orderRequest);
        });

    }

}