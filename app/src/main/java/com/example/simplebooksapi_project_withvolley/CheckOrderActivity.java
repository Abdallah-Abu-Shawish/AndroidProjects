package com.example.simplebooksapi_project_withvolley;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.simplebooksapi_project_withvolley.databinding.ActivityCheckOrderBinding;

import org.json.JSONException;

public class CheckOrderActivity extends AppCompatActivity {

    ActivityCheckOrderBinding binding;

    RequestQueue queue;

    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityCheckOrderBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);

        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent intent = getIntent();
        String orderId = intent.getStringExtra("orderId");
        queue = Volley.newRequestQueue(this);
        url = "https://simple-books-api.glitch.me/orders/" + orderId;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    try {
                        binding.messageTv.append(response.getString("orderId"));
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                },
                error -> Log.d("Error when getting order by id", "onErrorResponse: "+error.getMessage()));
        queue.add(request);

    }
}