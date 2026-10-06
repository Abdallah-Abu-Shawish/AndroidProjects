package com.example.simplebooksapi_project_withvolley;

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
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.simplebooksapi_project_withvolley.databinding.ActivityGetAllOrdersBinding;

import org.json.JSONArray;

import java.util.ArrayList;

public class GetAllOrdersActivity extends AppCompatActivity {


    ActivityGetAllOrdersBinding binding;

    RequestQueue queue;

    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityGetAllOrdersBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        queue = Volley.newRequestQueue(this);

        url = "https://simple-books-api.glitch.me/orders";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        ArrayList<Order> ordersList = getOrdersFromAPI(response);
                        OrderAdapter adapter = new OrderAdapter(GetAllOrdersActivity.this, ordersList, orderId -> {

                        });
                        binding.ordersRv.setAdapter(adapter);
                        binding.ordersRv.setLayoutManager(new LinearLayoutManager(GetAllOrdersActivity.this));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error when getting books", "onErrorResponse: "+error.getMessage());
                    }
                });

        queue.add(jsonArrayRequest);


    }

    private ArrayList<Order> getOrdersFromAPI(JSONArray ordersJsonArray) {
        ArrayList<Order> orders = new ArrayList<>();
//        for (int i = 0; i < ordersJsonArray.length(); i++) {
//            try {
//                JSONObject bookObject = ordersJsonArray.getJSONObject(i);
//                int id = bookObject.getInt("id");
//                int bookId = bookObject.getInt("bookId");
//                String customerName = bookObject.getString("customerName");
//                String createdBy = bookObject.getString("createdBy");
//                int quantity = bookObject.getInt("quantity");
//                double timestamp = bookObject.getDouble("timestamp");
//
//            } catch (JSONException e) {
//                throw new RuntimeException(e);
//            }
//        }
        return orders;
    }

}