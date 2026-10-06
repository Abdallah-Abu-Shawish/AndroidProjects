package com.myfirebaseminiproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
//import com.bumptech.glide.Glide;
import com.myfirebaseminiproject.adapters.RecipeAdapter;
import com.myfirebaseminiproject.models.Recipe;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.firestore.FirebaseFirestore;
//import com.google.firebase.firestore.QueryDocumentSnapshot;
import java.util.ArrayList;
import java.util.List;

public class ProfileActivity extends AppCompatActivity {
    private ImageView userImageView;
    private TextView userNameTextView, userEmailTextView;
    private RecyclerView userRecipesRecyclerView;
    private Button logoutButton;
    private RecipeAdapter adapter;
    private List<Recipe> userRecipes = new ArrayList<>();
//    private FirebaseAuth mAuth;
//    private FirebaseFirestore db;
    private String userId;
    private TextView emptyStateTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        userImageView = findViewById(R.id.userImageView);
        userNameTextView = findViewById(R.id.userNameTextView);
        userEmailTextView = findViewById(R.id.userEmailTextView);
        userRecipesRecyclerView = findViewById(R.id.userRecipesRecyclerView);
        logoutButton = findViewById(R.id.logoutButton);
        emptyStateTextView = findViewById(R.id.emptyStateTextView);
//
//        mAuth = FirebaseAuth.getInstance();
//        db = FirebaseFirestore.getInstance();
//        userId = mAuth.getCurrentUser() != null ? mAuth.getCurrentUser().getUid() : "";

        adapter = new RecipeAdapter(this, userRecipes, recipe -> {
            Intent intent = new Intent(ProfileActivity.this, RecipeDetailsActivity.class);
            intent.putExtra("recipeId", recipe.getId());
            startActivity(intent);
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        });
        userRecipesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        userRecipesRecyclerView.setAdapter(adapter);

        logoutButton.setOnClickListener(v -> {
//            mAuth.signOut();
            Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            finish();
        });

//        loadUserInfo();
//        loadUserRecipes();
    }

//    private void loadUserInfo() {
//        db.collection("users").document(userId).get().addOnSuccessListener(documentSnapshot -> {
//            String name = documentSnapshot.getString("name");
//            String email = documentSnapshot.getString("email");
//            String imageUrl = documentSnapshot.getString("imageUrl");
//            userNameTextView.setText(name);
//            userEmailTextView.setText(email);
//            Glide.with(this).load(imageUrl).into(userImageView);
//        });
//    }

//    private void loadUserRecipes() {
//        db.collection("recipes").whereEqualTo("userId", userId).get().addOnSuccessListener(queryDocumentSnapshots -> {
//            userRecipes.clear();
//            for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {
//                Recipe recipe = doc.toObject(Recipe.class);
//                recipe.setId(doc.getId());
//                userRecipes.add(recipe);
//            }
//            adapter.notifyDataSetChanged();
//            if (userRecipes.isEmpty()) {
//                emptyStateTextView.setVisibility(View.VISIBLE);
//                userRecipesRecyclerView.setVisibility(View.GONE);
//            } else {
//                emptyStateTextView.setVisibility(View.GONE);
//                userRecipesRecyclerView.setVisibility(View.VISIBLE);
//            }
//        });
//    }
} 