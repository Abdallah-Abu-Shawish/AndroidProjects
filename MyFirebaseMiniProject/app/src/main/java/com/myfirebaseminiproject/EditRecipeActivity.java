package com.myfirebaseminiproject;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
//import com.bumptech.glide.Glide;
import com.myfirebaseminiproject.models.Recipe;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.firestore.DocumentReference;
//import com.google.firebase.firestore.FirebaseFirestore;
//import com.google.firebase.storage.FirebaseStorage;
//import com.google.firebase.storage.StorageReference;
import java.util.HashMap;
import java.util.Map;

public class EditRecipeActivity extends AppCompatActivity {
    private ImageView recipeImageView;
    private Button selectImageButton, updateRecipeButton;
    private EditText titleEditText, ingredientsEditText, stepsEditText, categoryEditText, videoUrlEditText;
    private Uri imageUri;
    private String imageUrl;
    private String recipeId;
//    private FirebaseAuth mAuth;
//    private FirebaseFirestore db;
//    private FirebaseStorage storage;
    private Recipe recipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_recipe);

        recipeImageView = findViewById(R.id.recipeImageView);
        selectImageButton = findViewById(R.id.selectImageButton);
        updateRecipeButton = findViewById(R.id.updateRecipeButton);
        titleEditText = findViewById(R.id.titleEditText);
        ingredientsEditText = findViewById(R.id.ingredientsEditText);
        stepsEditText = findViewById(R.id.stepsEditText);
        categoryEditText = findViewById(R.id.categoryEditText);
        videoUrlEditText = findViewById(R.id.videoUrlEditText);
//
//        mAuth = FirebaseAuth.getInstance();
//        db = FirebaseFirestore.getInstance();
//        storage = FirebaseStorage.getInstance();

        recipeId = getIntent().getStringExtra("recipeId");
        if (TextUtils.isEmpty(recipeId)) {
            Toast.makeText(this, "Recipe not found", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }
//        loadRecipe();

        selectImageButton.setOnClickListener(v -> selectImage());
        updateRecipeButton.setOnClickListener(v -> updateRecipe());
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
//            // Pre-fill fields
//            titleEditText.setText(recipe.getTitle());
//            ingredientsEditText.setText(recipe.getIngredients());
//            stepsEditText.setText(recipe.getSteps());
//            categoryEditText.setText(recipe.getCategory());
//            videoUrlEditText.setText(recipe.getVideoUrl());
//            imageUrl = recipe.getImageUrl();
//            Glide.with(this).load(imageUrl).into(recipeImageView);
//        });
//    }

    private void selectImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();
            recipeImageView.setImageURI(imageUri);
        }
    }

    private boolean isValidUrl(String url) {
        return TextUtils.isEmpty(url) || android.util.Patterns.WEB_URL.matcher(url).matches();
    }

    private void updateRecipe() {
        String title = titleEditText.getText().toString().trim();
        String ingredients = ingredientsEditText.getText().toString().trim();
        String steps = stepsEditText.getText().toString().trim();
        String category = categoryEditText.getText().toString().trim();
        String videoUrl = videoUrlEditText.getText().toString().trim();
//        String userId = mAuth.getCurrentUser() != null ? mAuth.getCurrentUser().getUid() : "";

        if (TextUtils.isEmpty(title)) {
            Toast.makeText(this, "Please enter a title", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(ingredients)) {
            Toast.makeText(this, "Please enter ingredients", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(steps)) {
            Toast.makeText(this, "Please enter steps", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(category)) {
            Toast.makeText(this, "Please enter a category", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!isValidUrl(videoUrl)) {
            Toast.makeText(this, "Please enter a valid YouTube URL or leave it blank", Toast.LENGTH_SHORT).show();
            return;
        }

        if (imageUri != null) {
//            StorageReference ref = storage.getReference().child("recipe_images/" + System.currentTimeMillis() + ".jpg");
//            ref.putFile(imageUri).addOnSuccessListener(taskSnapshot -> {
//                ref.getDownloadUrl().addOnSuccessListener(uri -> {
//                    updateRecipeInFirestore(title, ingredients, steps, category, videoUrl, userId, uri.toString());
//                }).addOnFailureListener(e -> {
//                    Toast.makeText(this, "Failed to get image URL. Please try again.", Toast.LENGTH_SHORT).show();
//                });
//            }).addOnFailureListener(e -> {
//                Toast.makeText(this, "Image upload failed. Please try again.", Toast.LENGTH_SHORT).show();
//            });
        } else {
            updateRecipeInFirestore(title, ingredients, steps, category, videoUrl, userId, imageUrl);
        }
    }

    private void updateRecipeInFirestore(String title, String ingredients, String steps, String category, String videoUrl, String userId, String imageUrl) {
        Map<String, Object> updatedRecipe = new HashMap<>();
        updatedRecipe.put("title", title);
        updatedRecipe.put("ingredients", ingredients);
        updatedRecipe.put("steps", steps);
        updatedRecipe.put("category", category);
        updatedRecipe.put("imageUrl", imageUrl);
        updatedRecipe.put("videoUrl", videoUrl);
        updatedRecipe.put("userId", userId);
        updatedRecipe.put("timestamp", System.currentTimeMillis());
//        db.collection("recipes").document(recipeId).update(updatedRecipe)
//            .addOnSuccessListener(aVoid -> {
//                Toast.makeText(this, "Recipe updated successfully", Toast.LENGTH_SHORT).show();
//                finish();
//            })
//            .addOnFailureListener(e -> {
//                Toast.makeText(this, "Failed to update recipe. Please try again.", Toast.LENGTH_SHORT).show();
//            });
    }
} 