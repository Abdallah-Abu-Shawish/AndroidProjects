package com.myfirebaseminiproject;

import static android.os.Build.VERSION_CODES.R;

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
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.firestore.FirebaseFirestore;
//import com.google.firebase.storage.FirebaseStorage;
//import com.google.firebase.storage.StorageReference;
import java.util.HashMap;
import java.util.Map;

public class AddRecipeActivity extends AppCompatActivity {
    private ImageView recipeImageView;
    private Button selectImageButton, addRecipeButton;
    private EditText titleEditText, ingredientsEditText, stepsEditText, categoryEditText, videoUrlEditText;
    private Uri imageUri;
//    private FirebaseAuth mAuth;
//    private FirebaseFirestore db;
//    private FirebaseStorage storage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);

        recipeImageView = findViewById(R.id.recipeImageView);
        selectImageButton = findViewById(R.id.selectImageButton);
        addRecipeButton = findViewById(R.id.addRecipeButton);
        titleEditText = findViewById(R.id.titleEditText);
        ingredientsEditText = findViewById(R.id.ingredientsEditText);
        stepsEditText = findViewById(R.id.stepsEditText);
        categoryEditText = findViewById(R.id.categoryEditText);
        videoUrlEditText = findViewById(R.id.videoUrlEditText);

//        mAuth = FirebaseAuth.getInstance();
//        db = FirebaseFirestore.getInstance();
//        storage = FirebaseStorage.getInstance();

        selectImageButton.setOnClickListener(v -> selectImage());
        addRecipeButton.setOnClickListener(v -> addRecipe());
    }

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

    private void addRecipe() {
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
        if (imageUri == null) {
            Toast.makeText(this, "Please select an image", Toast.LENGTH_SHORT).show();
            return;
        }

//        StorageReference ref = storage.getReference().child("recipe_images/" + System.currentTimeMillis() + ".jpg");
//        ref.putFile(imageUri).addOnSuccessListener(taskSnapshot -> {
//            ref.getDownloadUrl().addOnSuccessListener(uri -> {
//                Map<String, Object> recipe = new HashMap<>();
//                recipe.put("title", title);
//                recipe.put("ingredients", ingredients);
//                recipe.put("steps", steps);
//                recipe.put("category", category);
//                recipe.put("imageUrl", uri.toString());
//                recipe.put("videoUrl", videoUrl);
//                recipe.put("userId", userId);
//                recipe.put("timestamp", System.currentTimeMillis());
//                db.collection("recipes").add(recipe)
//                    .addOnSuccessListener(documentReference -> {
//                        Toast.makeText(this, "Recipe added successfully", Toast.LENGTH_SHORT).show();
//                        finish();
//                    })
//                    .addOnFailureListener(e -> {
//                        Toast.makeText(this, "Failed to add recipe. Please try again.", Toast.LENGTH_SHORT).show();
//                    });
//            }).addOnFailureListener(e -> {
//                Toast.makeText(this, "Failed to get image URL. Please try again.", Toast.LENGTH_SHORT).show();
//            });
//        }).addOnFailureListener(e -> {
//            Toast.makeText(this, "Image upload failed. Please try again.", Toast.LENGTH_SHORT).show();
//        });
    }
} 