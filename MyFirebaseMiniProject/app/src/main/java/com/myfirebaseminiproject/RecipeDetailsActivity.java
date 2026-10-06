package com.myfirebaseminiproject;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
//import com.bumptech.glide.Glide;
import com.myfirebaseminiproject.models.Recipe;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.firestore.DocumentReference;
//import com.google.firebase.firestore.FirebaseFirestore;

public class RecipeDetailsActivity extends AppCompatActivity {
    private ImageView recipeImageView, editIcon, deleteIcon;
    private TextView titleTextView, categoryTextView, ingredientsTextView, stepsTextView, videoUrlTextView;
//    private FirebaseFirestore db;
//    private FirebaseAuth mAuth;
    private String recipeId;
    private Recipe recipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);

        recipeImageView = findViewById(R.id.recipeImageView);
        editIcon = findViewById(R.id.editIcon);
        deleteIcon = findViewById(R.id.deleteIcon);
        titleTextView = findViewById(R.id.titleTextView);
        categoryTextView = findViewById(R.id.categoryTextView);
        ingredientsTextView = findViewById(R.id.ingredientsTextView);
        stepsTextView = findViewById(R.id.stepsTextView);
        videoUrlTextView = findViewById(R.id.videoUrlTextView);

//        db = FirebaseFirestore.getInstance();
//        mAuth = FirebaseAuth.getInstance();

        recipeId = getIntent().getStringExtra("recipeId");
        if (TextUtils.isEmpty(recipeId)) {
            Toast.makeText(this, "Recipe not found", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }
//        loadRecipe();
    }

//    private void loadRecipe() {
//        DocumentReference docRef = db.collection("recipes").document(recipeId);
//        docRef.get().addOnSuccessListener(documentSnapshot -> {
//            recipe = documentSnapshot.toObject(Recipe.class);
//            if (recipe == null) {
//                Toast.makeText(this, "Recipe not found", Toast.LENGTH_SHORT).show();
//                finish();
//                return;
//            }
//            showRecipeDetails();
//        });
//    }

//    private void showRecipeDetails() {
////        Glide.with(this).load(recipe.getImageUrl()).into(recipeImageView);
//        titleTextView.setText(recipe.getTitle());
//        categoryTextView.setText(recipe.getCategory());
//        ingredientsTextView.setText(recipe.getIngredients().replace(",", "\n"));
//        stepsTextView.setText(recipe.getSteps().replace(",", "\n"));
//        videoUrlTextView.setText(recipe.getVideoUrl());
//        videoUrlTextView.setOnClickListener(v -> {
//            String url = recipe.getVideoUrl();
//            if (!TextUtils.isEmpty(url)) {
//                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
//                startActivity(intent);
//            }
//        });
//        // Show edit/delete if user is creator
//        String currentUserId = mAuth.getCurrentUser() != null ? mAuth.getCurrentUser().getUid() : "";
//        if (currentUserId.equals(recipe.getUserId())) {
//            editIcon.setVisibility(View.VISIBLE);
//            deleteIcon.setVisibility(View.VISIBLE);
//            editIcon.setOnClickListener(v -> {
//                Intent intent = new Intent(RecipeDetailsActivity.this, EditRecipeActivity.class);
//                intent.putExtra("recipeId", recipeId);
//                startActivity(intent);
//                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
//            });
//            deleteIcon.setOnClickListener(v -> {
//                deleteRecipe();
//            });
//        } else {
//            editIcon.setVisibility(View.GONE);
//            deleteIcon.setVisibility(View.GONE);
//        }
//    }

//    private void deleteRecipe() {
//        db.collection("recipes").document(recipeId).delete()
//            .addOnSuccessListener(aVoid -> {
//                Toast.makeText(this, "Recipe deleted successfully", Toast.LENGTH_SHORT).show();
//                finish();
//            })
//            .addOnFailureListener(e -> {
//                Toast.makeText(this, "Failed to delete recipe: " + e.getMessage(), Toast.LENGTH_SHORT).show();
//            });
//    }
} 